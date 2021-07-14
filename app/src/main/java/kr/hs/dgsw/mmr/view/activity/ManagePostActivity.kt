package kr.hs.dgsw.mmr.view.activity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.view.dialog.DeleteDialogFragment
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityManagePostBinding
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.viewmodel.activity.ManagePostViewModel

class ManagePostActivity : BaseActivity<ActivityManagePostBinding, ManagePostViewModel>() {

    var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ManagePostViewModel::class.java)
        super.onCreate(savedInstanceState)

        val postId = intent.getIntExtra("postId", 0)
        userId = intent.getStringExtra("userId").toString()

        viewModel.getMyPost(userId.toString())
        viewModel.deleteMyPost(userId.toString(), postId)

        mBinding.manageRefresh.setOnRefreshListener {
            mBinding.manageRefresh.isRefreshing = false
            viewModel.getMyPost(userId.toString())
        }
    }

    override fun onErrorEvent(e: Throwable) {

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
                getMyPost(userId)
            })

            adapter.clickEvent.observe(this@ManagePostActivity, Observer {
                // dialog open
                val alert  = AlertDialog.Builder(ContextThemeWrapper(this@ManagePostActivity, R.style.AlertDialogCustom))
                alert.setTitle("경고!")
                alert.setMessage("게시물을 삭제하시겠습니까?")
                alert.setCancelable(false)

                alert.setPositiveButton("삭제", DialogInterface.OnClickListener { dialog, which ->
                    deleteMyPost(userId, it)
                })
                alert.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })

                val dialog = alert.create()
                dialog.show()


            })

        }
    }
}

