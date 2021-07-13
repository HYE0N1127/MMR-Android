package kr.hs.dgsw.mmr.network.model.response

import com.google.android.material.resources.MaterialResources

data class PostResponse(
    val id: Int,
    val title: String,
    val content: String,
    val summary: String,
    val imgUrl: String,
    val likeNum: Int,
    val userName: String,
    val materials: List<MaterialResponse>
)