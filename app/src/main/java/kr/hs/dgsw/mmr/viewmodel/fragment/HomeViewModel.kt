package kr.hs.dgsw.mmr.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.repository.PostRepository

class HomeViewModel : BaseViewModel() {
    val _postGetAll = MutableLiveData<List<PostResponse>>()
    val postGetAll: LiveData<List<PostResponse>>
        get() = _postGetAll

    private val repository = PostRepository()

    fun getAllPost(){
        addDisposable(repository.getAllPost(),
        object: DisposableSingleObserver<List<PostResponse>>(){

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }

            override fun onSuccess(t: List<PostResponse>) {
                _postGetAll.value = t
            }

        })
    }


}