package kr.hs.dgsw.mmr.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityManagePostBinding
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.viewmodel.activity.ManagePostViewModel

class ManagePostActivity : BaseActivity<ActivityManagePostBinding, ManagePostViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ManagePostViewModel::class.java)
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra("userId")
        Log.e("!", userId.toString())
        viewModel.getMyPost(userId.toString())
    }

    override fun observerViewModel() {
        with(mViewModel) {
            mBinding.rvManageList.adapter = adapter
            adapter.context = this@ManagePostActivity
            myPost.observe(this@ManagePostActivity) {
                adapter.postResponse = it as ArrayList<PostResponse>
                adapter.notifyDataSetChanged()
            }

        }
    }
}