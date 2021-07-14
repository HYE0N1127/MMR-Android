package kr.hs.dgsw.mmr.viewmodel.fragment

import android.view.View
import android.widget.Toast
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
import kr.hs.dgsw.mmr.view.adapter.MaterialAdapter


class WriteViewModel() : BaseViewModel() {

    private val _getWritePostRequest = MutableLiveData<Boolean>()
    val getWritePostRequest: LiveData<Boolean>
        get() = _getWritePostRequest

    private val repository = PostRepository()

    val adapter = MaterialAdapter()

    val userId = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val summary = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val imgUrl = MutableLiveData<String>()
    val materials = MutableLiveData<ArrayList<MaterialRequest>>(arrayListOf())

    private fun postWritePage(
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

    fun onClickCreatePost(view: View) {

        if (title.value == null || summary.value == null || content.value == null || imgUrl.value == null) {
            onErrorEvent.value = Throwable("빈 칸 없이 적어주세요")
        } else postWritePage(
            userId.value!!,
            title.value!!,
            summary.value!!,
            content.value!!,
            imgUrl.value!!,
            materials.value ?: listOf()
        )

    }
}