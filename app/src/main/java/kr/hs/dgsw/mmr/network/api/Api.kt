package kr.hs.dgsw.mmr.network.api

import io.reactivex.Single
import kr.hs.dgsw.mmr.network.model.request.*
import kr.hs.dgsw.mmr.network.model.response.BaseResponse
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Single<Response<BaseResponse<String>>>

    @POST("user/register")
    fun register(@Body registerRequest: RegisterRequest): Single<Response<BaseResponse<Boolean>>>

    @GET("post")
    fun getAllPost(): Single<Response<BaseResponse<List<PostResponse>>>>

    @GET("post/{id}")
    fun getPostById(@Path("id") postId: Int): Single<Response<BaseResponse<PostResponse>>>

    @POST("post")
    fun createPost(@Body createPostRequest: CreatePostRequest): Single<Response<BaseResponse<Boolean>>>

    @HTTP(method = "DELETE", path = "post", hasBody = true)
    fun deletePost(@Body deletePostRequest: DeletePostRequest): Single<Response<BaseResponse<Boolean>>>

    @POST("post/like/{userId}/{postId}")
    fun likePost(
        @Path("userId") userId: String,
        @Path("postId") postId: Int
    ): Single<Response<BaseResponse<Boolean>>>

    @GET("post/like/{userId}/{postId}")
    fun checkLikePost(
        @Path("userId") userId: String,
        @Path("postId") postId: Int
    ): Single<Response<BaseResponse<Boolean>>>

    @GET("post/my/{userId}")
    fun getMyPost(@Path("userId") userId: String): Single<Response<BaseResponse<List<PostResponse>>>>

    @PUT("user")
    fun modifyName(@Body modifyNameRequest: ModifyNameRequest): Single<Response<BaseResponse<Boolean>>>

}