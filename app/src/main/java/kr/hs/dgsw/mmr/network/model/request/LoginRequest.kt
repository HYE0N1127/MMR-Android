package kr.hs.dgsw.mmr.network.model.request

data class LoginRequest(
    val id: String,
    val pw: String
)