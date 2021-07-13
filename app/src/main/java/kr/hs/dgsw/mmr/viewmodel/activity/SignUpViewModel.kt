package kr.hs.dgsw.mmr.viewmodel.activity

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.repository.UserRepository

class SignUpViewModel : BaseViewModel() {
    private val repository = UserRepository()

    val registerResult = MutableLiveData<Boolean>()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val checkPw = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    val error = MutableLiveData<Throwable>()

    private fun register(id: String, pw: String, name: String) {
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

    fun onClickRegister(view: View) {
        if (id.value == null || pw.value == null || name.value == null) error.value =
            Throwable("빈 칸 없이 적어 주세요")
        else if (checkPw.value == pw.value) {
            register(id.value.toString(), pw.value.toString(), name.value.toString())
        } else {
            error.value = Throwable("비밀번호가 맞지 않습니다.")
        }
    }

}