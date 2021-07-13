package kr.hs.dgsw.mmr.viewmodel.activity

import android.view.View
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.utils.SingleLiveEvent
import kr.hs.dgsw.mmr.view.activity.FragmentEnum

class MainViewModel : BaseViewModel() {
    val currentPage = MutableLiveData(FragmentEnum.Home)
    val openSettingEvent = SingleLiveEvent<Unit>()

    fun openSetting(view: View) {
        openSettingEvent.call()
    }
}