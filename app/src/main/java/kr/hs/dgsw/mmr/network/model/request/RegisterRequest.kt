package kr.hs.dgsw.mmr.network.model.request

data class RegisterRequest(
    val id: String,
    val pw: String,
    val name: String
)
