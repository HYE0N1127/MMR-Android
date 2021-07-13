package kr.hs.dgsw.mmr.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.repository.UserRepository

class SignUpViewModel : BaseViewModel() {
    private val repository = UserRepository()

    val registerResult = MutableLiveData<Boolean>()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val check_pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    val error = MutableLiveData<Throwable>()

    fun register(id: String, pw: String, name: String) {
        addDisposable(repository.register(id, pw, name),
            object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    registerResult.value = t
                }

                override fun onError(e: Throwable) {
                    error.value = e
                }

            })
    }

    fun onClickRegister(){
        if(check_pw.equals(pw)){
            register(id.value.toString(), pw.value.toString(), name.value.toString())
        } else {
            error.value = Throwable("비밀번호가 맞지 않습니다.")
        }
    }

}