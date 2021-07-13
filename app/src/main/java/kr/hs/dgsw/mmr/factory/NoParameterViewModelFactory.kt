package kr.hs.dgsw.mmr.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NoParameterViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor().newInstance()
}