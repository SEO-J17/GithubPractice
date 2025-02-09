# 프로젝트 환경
- Kotlin 1.9
- Gradle JDK : version 17
- kotlin compile version : 2.0.0
- target SDK : 34
- min SDK : 26

**클론 받으시는 분들을 위해 깃허브 api에 필요한 7일 기간의 토큰 값을 하드 코딩 해놨습니다.**
# 사용된 라이브러리
- coroutine
- hilt
- coil-compose
- compose-navigation
- okHttp
- retrofit
- kotlin serialization json
- compose paging
  
# 아키텍처
- **GoogleRecommendArchitecture + MVI** 패턴을 이용해 프로젝트를 구현했습니다.
<img width="772" alt="image" src="https://github.com/user-attachments/assets/d6a19164-bcb1-4341-85f4-d3fa7ac534c7" />

- 모듈 구성은 위 그림과 같습니다.
- 구글 권장 아키텍처에 따라서 data <- domain <- presentation 으로 의존성을 가지도록 구현했습니다.
- 이후 ui단에서 mvi를 이용하여 상태에 따른 ui 변화에 대응하여 구현했습니다.
- 각 레이어에 따라 모델을 구성하여 의존성을 최대한 줄였습니다.
  
# 구현 기능
## 1. 검색 기능 
<img src= "https://github.com/user-attachments/assets/6050b387-c637-4f8a-86b8-801f7b7be712" width=300 />

- 검색 창은 BasicTextField를 커스텀하여 구현했습니다.
- focusManager를 통해 입력이 끝났을시 자동으로 키보드를 내려가게 하여 ux를 향상시켰습니다.
- 요구사항에 맞게 검색 창 밑에 테두리를 그리게 하기 위해 modifier에 drawLine을 이용하여 테두리를 생성했습니다.
- SearchTextField 필드에 키보드 확인 버튼을 누를 때 발생하는 람다, 키보드 유형, hint를 필드로 넣어 재사용성을 고려해 다양한 상황에서 사용할 수 있게 구현했습니다.
- Paging을 적용하여 검색 결과를 나타내고, 첫 호출시 30개의 요소를 받아오며 효율적으로 리스트 구성을 할 수 있도록 구현했습니다.
- **collectAsLazyPagingItems**와 **LazyColumn**을 이용하여 페이징 데이터를 수집하여 나타낼 수 있도록 구현했습니다.
- 페이징 데이터르 flow로 변환하여 데이터 스트림으로 받을 수 있게 했습니다.
- lazyColumn에 단일 item을 넣어 페이징시 로딩 인디케이터가 뜨도록 구현했습니다.

```kotlin
-- 검색 결과 리스트 컴포저블 --
LazyColumn(modifier = modifier) {
        items(repoPagingData.itemCount) { index ->
            val repoInfo = repoPagingData[index] ?: return@items

            RepoInfoItem(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .noRippleClick { onClickSearchResultItem(repoInfo.userName, repoInfo.repoName) }
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = if (index == 0) 20.dp else 13.dp,
                        bottom = if (index == repoPagingData.itemCount - 1) 20.dp else 0.dp,
                    ),
                repoInfo = repoInfo,
            )
            if (index != repoPagingData.itemCount - 1) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth(),
                    color = Color.LightGray,
                    thickness = 1.dp,
                )
            }
        }
        item {
            if (repoPagingData.loadState.append == LoadState.Loading) {
                SoopLoadingCircular(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                )
            }
        }
    }
```

## 2. 상세페이지 화면
<img src= "https://github.com/user-attachments/assets/fa2803f1-451d-409c-a4e9-931128147811" width=300/>

- 이전 검색화면에서 userName, repoName을 인자로 전달받아 구현했습니다.
- usecase에서 runcathing을 이용하고, result객체로 전달받아 ViewModel에서 success와 failure로 나누어 구현했습니다.
- 주요 유스케이스 코드는 다음과 같습니다.
``` kotlin
class GetUserRepoDetailUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
) {
    suspend operator fun invoke(userName: String, repoName: String): Result<RepoDetailDomainModel> {
        return runCatching { repoRepository.getRepoDetail(userName, repoName).toDomainModel() }
    }
}
```

## 3. more버튼 클리시 나오는 유저 정보 바텀시트 화면
<img src = "https://github.com/user-attachments/assets/5b49fd35-be0e-4406-ac0b-eb144292cd49" width="300" />

- ModalBottomSheet를 이용하여 구현 완료했습니다.
- 해당 바텀시트에 필요한 정보는 combine을 이용하여 데이터를 가져왔습니다.
- 유저 정보 api, repo 정보 api를 결합하여 구현했습니다.
- 유저 정보를 가져오는 usecase, repo 정보를 가져오는 usecase를 각각 구현하고, 이를 조합하는 유스케이스를 새로 만들어 구현했습니다. 주요코드는 다음과 같습니다.
  
``` kotlin
class GetUserDetailUseCase @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserRepoListUseCase: GetUserRepoListUseCase,
) {
    operator fun invoke(userName: String): Flow<UserDetailDomainModel> {
        val userInfoFlow = getUserInfoUseCase(userName)
        val userRepoListFlow = getUserRepoListUseCase(userName)
        return combine(userInfoFlow, userRepoListFlow) { userInfo, userRepoList ->
            UserDetailDomainModel.toModel(userInfo, userRepoList)
        }
    }
}
```
- combine을 사용하여 두개의 api를 엮어서 각각 원하는 데이터를 가져온 이후, 데이터 클래스를 하나 만들고 companion object를 통해 toModel이라는 함수를 만들어 원하는 데이터를 가공해낼 수 있었습나다. 코드는 다음과 같습니다.

``` kotlin
data class UserDetailDomainModel(
    val userThumbnailUrl: String,
    val userName: String,
    val followerCount: Int,
    val followingCount: Int,
    val repoCount: Int,
    val usedLanguageList: List<String>,
    val bio: String?,
) {
    companion object {
        fun toModel(
            userInfo: UserInfoDomainModel,
            userRepoList: List<UserRepoDomainModel>,
        ): UserDetailDomainModel {
            return UserDetailDomainModel(
                userThumbnailUrl = userInfo.userThumbnailUrl,
                userName = userInfo.userName,
                followerCount = userInfo.followerCount,
                followingCount = userInfo.followingCount,
                repoCount = userRepoList.size,
                usedLanguageList = userRepoList.mapNotNull { it.usedLanguage },
                bio = userInfo.bio,
            )
        }
    }
}
```
# 고려한 부분

### 재사용성과 확장성
<img width="199" alt="image" src="https://github.com/user-attachments/assets/bc9cfc46-0841-4bc0-b2ff-86db2e373520" />

- 유스케이스를 api당 각각 하나씩 구현하여 향후 다른 요구사항이 생겨도 사용할 수 있도록 구현했습니다.
- 특히 2개 이상의 api를 조합해서 데이터를 가져오는 경우의 요구사항이 있어도, 2개의 api를 하나의 유스케이스에서 구현하는 것이 아닌, 각 api에 해당하는 유스케이스를 만들고 유스케이스 안에서 유스케이스를 조합하여 사용했습니다.
- 위 그림과 같이 유스케이스가 만들어졌으며 향후 유저 정보, 레포 정보만 필요하다면 유스케이스 들을 사용할 수 있게 고려했습니다.

<img width="221" alt="image" src="https://github.com/user-attachments/assets/bd55f436-ade3-46fd-aac7-a25240192d7a" />

- 최대한 재사용 가능한 컴포저블을 만들기 위해 위 그림처럼 분리하여 사용했습니다.
- 특히 버튼, 로딩 인디케이터, 뱃지와 같은 부분은 컴포넌트로 분리하여 ui를 구성했습니다.
  
### 관심사 분리
<img width="140" alt="image" src="https://github.com/user-attachments/assets/08a71ae7-9047-4b8b-adb7-e15db5ca68a8" />

- 다음 그림과 같이 레이어 별로 모듈화를 통해 관심사를 분리했습니다.
- 구글 권장 아키텍처에 따라 data, domain, presentation 레이어로 분리하여 구현했습니다.
- 또한 레이어 전용 model을 따로 두고 파싱하여 의존성을 최대한 줄이도록 구현했습니다.

<img width="743" alt="image" src="https://github.com/user-attachments/assets/49b5e565-92e5-46f1-a23b-2d8b2ae3a7e6" />

- Compose Screen을 Route로 감싸서 Screen은 최대한 ui를 나타내는 로직에 집중 할 수 있도록 했습니다.
- route에서 뷰모델의 intent를 보내는 부분, sideEffect를 구독하는 로직을 분리하여 screen은 ui 관련 로직에 집중 할 수 있도록 했습니다.

### 보일러 플레이트 코드
<img width="757" alt="image" src="https://github.com/user-attachments/assets/f661d7da-1a38-4f0c-bd04-3e4207fb8e79" />

- BaseViewModel을 따로 구현하고 상속받아 사용하면서 중복 코드를 감소시켰습니다.
- MVI 패턴 사용에 따른 state 및 sideEffect 관련 코드를 필수적으로 작성해야 하지만 BaseViewModel을 이용하여 이를 감소시켰습니다.
- 그리고 상속에 따른 필수 함수,로직을 강제 할 수 있어 실수를 줄일 수 있도록 했습니다.

### MVI
<img width="198" alt="image" src="https://github.com/user-attachments/assets/384182a5-04f5-4234-8f83-7d53df903ff3" />

- MVI를 고려하여 코드를 작성했습니다.
- ui에서 어떤 상호 작용이 일어난다면 람다를 통해 Route까지 전달하고, 전달받은 람다에서 뷰모델에 intent를 보내 상태 변경이 ui에 노출되지 않고 상태 변경이 되도록 했습니다.
- 상태를 변경하는 코드는 노출 되지 않아 안전하고 단방향으로 데이터가 흐를 수 있도록 했습니다.

### 리컴포지션

- 컴포저블을 최대한 분리하여 해당하는 컴포저블 안에서만 변경이 일어나도록 구현했습니다.
- 컴포저블에 primitive한 인자와 stable한 인자를 전달하도록 하여 스마트 리컴포지션이 일어나도록 노력했습니다.
