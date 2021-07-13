package kr.hs.dgsw.mmr.network

import kr.hs.dgsw.mmr.network.api.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Server {

    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://172.28.109.27:8080/v1/")
        .build()
        .create(Api::class.java)

}