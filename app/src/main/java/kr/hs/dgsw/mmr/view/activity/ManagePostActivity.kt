package kr.hs.dgsw.mmr.view.activity

import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.view.dialog.DeleteDialogFragment
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

        mBinding.manageRefresh.setOnRefreshListener {
            mBinding.manageRefresh.isRefreshing = false
            viewModel.getMyPost(userId.toString())
        }
    }

    override fun observerViewModel() {

        with(mViewModel) {

            mBinding.rvManageList.adapter = adapter
            adapter.context = this@ManagePostActivity
            myPost.observe(this@ManagePostActivity) {
                adapter.postResponse = it as ArrayList<PostResponse>
                adapter.notifyDataSetChanged()
            }
            deletePost.observe(this@ManagePostActivity, {
                val builder = AlertDialog.Builder(ContextThemeWrapper(this@ManagePostActivity, R.style.Theme_AppCompat_Light_Dialog))
                builder.setTitle("경고!")
                builder.setMessage("게시물을 삭제하시겠습니까?")

                builder.setPositiveButton("삭제") {dialog, id ->
                }
                builder.setNegativeButton("취소") {dialog, id ->
                }
                builder.show()
            })


        }
    }
}