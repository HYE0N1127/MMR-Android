package kr.hs.dgsw.mmr.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.view.activity.FragmentEnum

class MainViewModel : BaseViewModel() {
    val currentPage = MutableLiveData(FragmentEnum.Home)
}