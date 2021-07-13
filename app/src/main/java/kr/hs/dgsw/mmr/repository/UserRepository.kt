package kr.hs.dgsw.mmr.repository

import io.reactivex.Single
import kr.hs.dgsw.mmr.network.Server
import kr.hs.dgsw.mmr.network.model.request.LoginRequest
import kr.hs.dgsw.mmr.network.model.request.ModifyNameRequest
import kr.hs.dgsw.mmr.network.model.request.RegisterRequest
import org.json.JSONObject

class UserRepository {

    fun register(id: String, pw: String, name: String): Single<Boolean> {
        val registerRequest = RegisterRequest(id, pw, name)

        return Server.retrofit.register(registerRequest).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            if(it.body()?.code != 200) {
                throw Throwable(it.body()?.message)
            }
            it.body()!!.data
        }
    }

    fun login(id: String, pw: String): Single<String> {
        return Server.retrofit.login(LoginRequest(id, pw)).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            if(it.body()?.code != 200) {
                throw Throwable(it.body()?.message)
            }
            it.body()!!.data
        }
    }

    fun modifyName(id: String, name: String): Single<Boolean> {
        return Server.retrofit.modifyName(ModifyNameRequest(id, name)).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

}