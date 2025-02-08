package io.seoj17.soop.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.seoj17.soop.domain.usecase.GetUserDetailUseCase
import io.seoj17.soop.domain.usecase.GetUserRepoDetailUseCase
import io.seoj17.soop.presentation.base.BaseViewModel
import io.seoj17.soop.presentation.navigation.SoopRoute
import io.seoj17.soop.presentation.ui.detail.model.toUiModel
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailIntent
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailSideEffect
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailUiState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserRepoDetailUseCase: GetUserRepoDetailUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase,
) : BaseViewModel<SearchDetailUiState, SearchDetailSideEffect, SearchDetailIntent>(savedStateHandle) {
    private val userName = savedStateHandle.get<String>(SoopRoute.Search.KEY_USER_NAME)
    private val repoName = savedStateHandle.get<String>(SoopRoute.Search.KEY_USER_NAME)

    init {
        getRepoDetail(userName, repoName)
        getUserDetail(userName)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchDetailUiState {
        return SearchDetailUiState.initialize()
    }

    override fun handleIntent(intent: SearchDetailIntent) {
        when (intent) {
            is SearchDetailIntent.ClickMoreUserInfo -> {
                updateBottomSheetVisible(true)
            }

            is SearchDetailIntent.TouchBottomSheetClose -> {
                updateBottomSheetVisible(false)
            }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        reduce {
            copy(isLoading = isLoading)
        }
    }

    private fun updateBottomSheetVisible(visible: Boolean) {
        reduce {
            copy(isBottomSheetVisible = visible)
        }
    }

    private fun getRepoDetail(userName: String?, repoName: String?) {
        updateLoading(true)
        viewModelScope.launch {
            val repoDetail = getUserRepoDetailUseCase(
                userName = userName.orEmpty(),
                repoName = repoName.orEmpty(),
            )
            reduce {
                copy(repoDetail = repoDetail.toUiModel())
            }
        }
    }

    private fun getUserDetail(userName: String?) {
        viewModelScope.launch {
            getUserDetailUseCase(userName = userName.orEmpty())
                .onStart {
                    updateLoading(true)
                }
                .onCompletion {
                    updateLoading(false)
                }
                .map {
                    it.toUiModel()
                }
                .collect { userDetailUiModel ->
                    reduce {
                        copy(userDetail = userDetailUiModel)
                    }
                }
        }
    }
}
