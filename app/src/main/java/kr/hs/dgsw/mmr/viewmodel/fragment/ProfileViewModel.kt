package kr.hs.dgsw.mmr.viewmodel.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.repository.UserRepository
import kr.hs.dgsw.mmr.utils.SingleLiveEvent

class ProfileViewModel : BaseViewModel() {

    private val userRepository = UserRepository()

    val modifyNameResult = MutableLiveData<Boolean>()

    val name = MutableLiveData<String>()
    // Shared Preference에 사용되는 name값

    val hasNamed = MutableLiveData<Boolean>(false)
    val canClick = MutableLiveData(false)

    val storeMaterialEvent = SingleLiveEvent<Unit>()
    val managePostEvent = SingleLiveEvent<Unit>()

    fun setName(name: String) {
        this.name.value = name
    }


    fun openStoreMaterial(view: View) {
        storeMaterialEvent.call()
    }

    fun managePost(view: View) {
        managePostEvent.call()
    }

    fun modifyName(userId: String) {
        addDisposable(userRepository.modifyName(userId, name.value?:""),
        object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                modifyNameResult.value = t
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }

        })
    }
}