package kr.hs.dgsw.mmr.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.repository.PostRepository
import kr.hs.dgsw.mmr.view.adapter.MaterialAdapter

class PostDetailViewModel: BaseViewModel() {
    private val repository = PostRepository()

    val detailResponse = MutableLiveData<PostResponse>()

    val postId = MutableLiveData<Int>()

    val adapter = MaterialAdapter()

    fun detailPostGetData(postId: Int){
        addDisposable(repository.getPostByUserId(postId),
        object : DisposableSingleObserver<PostResponse>(){
            override fun onSuccess(t: PostResponse) {
                detailResponse.value = t
                //재료 보여주기
                //사진 처리
                // 버튼 클릭
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }
}