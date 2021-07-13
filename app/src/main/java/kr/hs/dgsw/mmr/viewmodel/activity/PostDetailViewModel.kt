package kr.hs.dgsw.mmr.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.repository.PostRepository
import kr.hs.dgsw.mmr.view.adapter.MaterialAdapter

class PostDetailViewModel : BaseViewModel() {
    private val repository = PostRepository()

    val isLike = MutableLiveData(false)
    val like = MutableLiveData(false)
    val detailResponse = MutableLiveData<PostResponse>()

    val adapter = MaterialAdapter()

    fun detailPostGetData(postId: Int) {
        addDisposable(repository.getPostByUserId(postId),
            object : DisposableSingleObserver<PostResponse>() {
                override fun onSuccess(t: PostResponse) {
                    detailResponse.value = t
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun checkLikePost(postId: Int, userId: String) {
        addDisposable(repository.checkLikePost(postId, userId),
            object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    isLike.value = t
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }

            })
    }

    fun likePost(postId: Int, userId: String) {
        addDisposable(repository.likePost(postId, userId),
            object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    like.value = t
                    checkLikePost(postId, userId)
                    detailPostGetData(postId)
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }

            })
    }

}