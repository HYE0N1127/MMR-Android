package kr.hs.dgsw.mmr.network.model.request

data class DeletePostRequest(
    val postId: Int,
    val userId: String
)