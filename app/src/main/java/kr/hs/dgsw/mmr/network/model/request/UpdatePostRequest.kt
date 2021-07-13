package kr.hs.dgsw.mmr.network.model.request

data class UpdatePostRequest(
    val postId: Int,
    val userId: String,
    val title: String,
    val summary: String,
    val content: String,
    val imgUrl: String,
    val materials: List<MaterialRequest>
)