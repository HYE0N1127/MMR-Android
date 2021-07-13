package kr.hs.dgsw.mmr.view.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseFragment
import kr.hs.dgsw.mmr.databinding.FragmentProfileBinding
import kr.hs.dgsw.mmr.factory.NoParameterViewModelFactory
import kr.hs.dgsw.mmr.view.activity.ManagePostActivity
import kr.hs.dgsw.mmr.view.activity.StoreMaterialActivity
import kr.hs.dgsw.mmr.viewmodel.fragment.ProfileViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private lateinit var mPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            viewModelStore,
            NoParameterViewModelFactory()
        ).get(ProfileViewModel::class.java)
        mPreferences =
            requireActivity().getSharedPreferences("LoginActivity", AppCompatActivity.MODE_PRIVATE)

        val name = mPreferences.getString("name", "")
        viewModel.setName(name!!)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun observerViewModel() {
        with(mViewModel) {
            changeNameEvent.observe(this@ProfileFragment, {
                Log.d("MYTAG", "click : ${name.value}")


            })
            storeMaterialEvent.observe(this@ProfileFragment, {
                val intent = Intent(activity, StoreMaterialActivity::class.java)
                startActivity(intent)
            })
            managePostEvent.observe(this@ProfileFragment, {
                val intent = Intent(activity, ManagePostActivity::class.java)
                intent.putExtra("userId", mPreferences.getString("id", ""))
                startActivity(intent)
            })

            name.observe(this@ProfileFragment, Observer {
                if (!hasNamed.value!!) {
                    Log.d("MYTAG", "false")
                    hasNamed.value = true
                } else {
                    Log.d("MYTAG", "Change Name : ${name.value}")
                    canClick.value = true
                }
            })
        }
    }

    override fun onErrorEvent(e: Throwable) {
        super.onErrorEvent(e)
        e.printStackTrace()
    }
}