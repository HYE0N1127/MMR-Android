package kr.hs.dgsw.mmr.viewmodel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.network.model.response.BaseResponse
import kr.hs.dgsw.mmr.repository.UserRepository
import retrofit2.Response

class LoginViewModel : BaseViewModel() {

    private val userRepository = UserRepository()

    val loginResult = MutableLiveData<String>()
    val registerResult = MutableLiveData<Boolean>()

    val error = MutableLiveData<Throwable>()

    fun login(id: String, pw: String) {
        addDisposable(userRepository.login(id, pw),
            object : DisposableSingleObserver<String>() {
                override fun onSuccess(t: String) {
                    loginResult.value = t
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }

            })
    }

    fun register(id: String, pw: String, name: String) {
        addDisposable(userRepository.register(id, pw, name),
            object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    registerResult.value = t
                }

                override fun onError(e: Throwable) {
                    error.value = e
                }

            })
    }


}