package kr.hs.dgsw.mmr.view.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.base.BaseFragment
import kr.hs.dgsw.mmr.databinding.FragmentProfileBinding
import kr.hs.dgsw.mmr.factory.NoParameterViewModelFactory
import kr.hs.dgsw.mmr.view.activity.ManagePostActivity
import kr.hs.dgsw.mmr.view.activity.StoreMaterialActivity
import kr.hs.dgsw.mmr.viewmodel.fragment.ProfileViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private lateinit var mPreferences: SharedPreferences
    var originalName = ""
    var userId = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            viewModelStore,
            NoParameterViewModelFactory()
        ).get(ProfileViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun observerViewModel() {
        with(mViewModel) {
            mPreferences =
                requireActivity().getSharedPreferences(
                    "LoginActivity",
                    AppCompatActivity.MODE_PRIVATE
                )
            originalName = mPreferences.getString("name", "").toString()
            userId = mPreferences.getString("id", "").toString()
            name.value = originalName

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
                canClick.value = it != originalName && !it.isNullOrBlank()
            })
            mBinding.btnChangeName.setOnClickListener {
                modifyName(userId)
            }
            modifyNameResult.observe(this@ProfileFragment, {
                Toast.makeText(context, if (it) "수정 성공" else "수정 실패", Toast.LENGTH_SHORT).show()
                if (it) {
                    mPreferences.edit().putString("name", name.value.toString()).apply()
                }
            })
        }
    }

    override fun onErrorEvent(e: Throwable) {
        super.onErrorEvent(e)
        e.printStackTrace()
    }
}