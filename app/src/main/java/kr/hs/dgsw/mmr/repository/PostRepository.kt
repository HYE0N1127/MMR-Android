package kr.hs.dgsw.mmr.repository

import io.reactivex.Single
import kr.hs.dgsw.mmr.network.Server
import kr.hs.dgsw.mmr.network.model.request.CreatePostRequest
import kr.hs.dgsw.mmr.network.model.request.DeletePostRequest
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import org.json.JSONObject

class PostRepository {
    fun writePost(
        userId: String,
        title: String,
        summary: String,
        content: String,
        imgUrl: String,
        materials: List<MaterialRequest>
    ): Single<Boolean> {
        val post = CreatePostRequest(userId, title, summary, content, imgUrl, materials)
        return Server.retrofit.createPost(post).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getAllPost(): Single<List<PostResponse>> {
        return Server.retrofit.getAllPost().map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getPostByUserId(postId: Int): Single<PostResponse> {
        return Server.retrofit.getPostById(postId).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun checkLikePost(postId: Int, userId: String): Single<Boolean> {
        return Server.retrofit.checkLikePost(userId, postId).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun likePost(postId: Int, userId: String): Single<Boolean> {
        return Server.retrofit.likePost(userId, postId).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getMyPost(userId: String): Single<List<PostResponse>> {
        return Server.retrofit.getMyPost(userId).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun deleteMyPost(userId: String, postId: Int) : Single<Boolean> {
        val deletePostRequest = DeletePostRequest(postId , userId)
        return Server.retrofit.deletePost(deletePostRequest).map {
            if(!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }
}