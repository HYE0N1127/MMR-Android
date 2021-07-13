package kr.hs.dgsw.mmr.viewmodel.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.utils.SingleLiveEvent

class ProfileViewModel : BaseViewModel() {

    val name = MutableLiveData<String>()
    // Shared Preference에 사용되는 name값

    val hasNamed = MutableLiveData<Boolean>(false)
    val canClick = MutableLiveData<Boolean>(false)

    val changeNameEvent = SingleLiveEvent<Unit>()

    val storeMaterialEvent = SingleLiveEvent<Unit>()
    val managePostEvent = SingleLiveEvent<Unit>()

    fun setName(name: String) {
        this.name.value = name
    }

    fun changeName(view: View) {
        changeNameEvent.call()
    }

    fun openStoreMaterial(view: View) {
        storeMaterialEvent.call()
    }

    fun managePost(view: View) {
        managePostEvent.call()
    }

    fun isNameChanged() {

    }
}