package kr.hs.dgsw.mmr.repository

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kr.hs.dgsw.mmr.network.Server
import kr.hs.dgsw.mmr.network.model.request.LoginRequest
import kr.hs.dgsw.mmr.network.model.request.RegisterRequest
import kr.hs.dgsw.mmr.network.model.response.BaseResponse
import retrofit2.Response

class UserRepository {

    fun register(id: String, pw: String, name: String): Single<Response<BaseResponse<Boolean>>> {
        val registerRequest = RegisterRequest(id, pw, name)

        return Server.retrofit.register(registerRequest)
    }

    fun login(id: String, pw: String): Single<Response<BaseResponse<String>>> {
        return Server.retrofit.login(LoginRequest(id, pw))
    }

}