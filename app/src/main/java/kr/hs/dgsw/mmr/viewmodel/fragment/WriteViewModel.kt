package kr.hs.dgsw.mmr.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.network.model.request.CreatePostRequest
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.network.model.response.BaseResponse
import kr.hs.dgsw.mmr.repository.PostRepository


class WriteViewModel() : BaseViewModel() {

    val _getWritePostRequest = MutableLiveData<Boolean>()
    val getWritePostRequest: LiveData<Boolean>
        get() = _getWritePostRequest

    private val repository = PostRepository()

    fun postWritePage(
        userId: String,
        title: String,
        summary: String,
        content: String,
        imgUrl: String,
        materials: List<MaterialRequest>
    ) {

        addDisposable(repository.writePost(userId, title, summary, content, imgUrl, materials),
        object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                _getWritePostRequest.value = t
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }

        })

    }
}