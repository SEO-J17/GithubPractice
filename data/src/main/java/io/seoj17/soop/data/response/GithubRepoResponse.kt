package io.seoj17.soop.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepoResponse(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    @SerialName("items")
    val items: List<RepoInfoResponse>,
    @SerialName("total_count")
    val totalCount: Int,
)
