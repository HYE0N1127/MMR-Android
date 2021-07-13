package kr.hs.dgsw.mmr.network.model.response

data class BaseResponse<T>(
    val code: Int,
    val message: String,
    val data: T
)