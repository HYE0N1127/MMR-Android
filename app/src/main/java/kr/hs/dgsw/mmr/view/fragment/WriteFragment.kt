package kr.hs.dgsw.mmr.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseFragment
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.databinding.FragmentWriteBinding
import kr.hs.dgsw.mmr.databinding.FragmentWriteBindingImpl
import kr.hs.dgsw.mmr.factory.NoParameterViewModelFactory
import kr.hs.dgsw.mmr.viewmodel.fragment.WriteViewModel

class WriteFragment : BaseFragment<FragmentWriteBinding, WriteViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            viewModelStore,
            NoParameterViewModelFactory()
        ).get(WriteViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun observerViewModel() {
        with(mViewModel) {

        }
    }
}