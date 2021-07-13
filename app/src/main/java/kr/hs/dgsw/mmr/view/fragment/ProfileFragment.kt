package kr.hs.dgsw.mmr.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseFragment
import kr.hs.dgsw.mmr.databinding.FragmentProfileBinding
import kr.hs.dgsw.mmr.factory.NoParameterViewModelFactory
import kr.hs.dgsw.mmr.viewmodel.fragment.ProfileViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private lateinit var mPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(viewModelStore, NoParameterViewModelFactory()).get(ProfileViewModel::class.java)
        mPreferences = requireActivity().getSharedPreferences("LoginActivity", AppCompatActivity.MODE_PRIVATE)

        val name = mPreferences.getString("name", "")
        viewModel.setName(name!!)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun observerViewModel() {
        with(mViewModel) {
            changeNameEvent.observe(this@ProfileFragment, {

            })
        }
    }

    override fun onErrorEvent(e: Throwable) {
        super.onErrorEvent(e)
        e.printStackTrace()
    }
}