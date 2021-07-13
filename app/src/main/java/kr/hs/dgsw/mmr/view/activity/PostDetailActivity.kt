package kr.hs.dgsw.mmr.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityPostDetailBinding
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.viewmodel.activity.PostDetailViewModel

class PostDetailActivity : BaseActivity<ActivityPostDetailBinding, PostDetailViewModel>() {

    var postId = 0
    var userId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PostDetailViewModel::class.java)
        super.onCreate(savedInstanceState)
        mViewModel.detailPostGetData(postId)
        mViewModel.checkLikePost(postId, userId ?: "")
    }

    override fun observerViewModel() {
        with(mViewModel) {
            postId = intent.getIntExtra("postId", 0)
            userId =
                getSharedPreferences("LoginActivity", MODE_PRIVATE).getString("id", "").toString()
            detailResponse.observe(this@PostDetailActivity, {
                Glide.with(this@PostDetailActivity)
                    .load(it.imgUrl)
                    .into(mBinding.detailImage)
                mBinding.detailTitle.text = it.title
                mBinding.detailLike.text = it.likeNum.toString()
                mBinding.detailComment.text = it.summary
                mBinding.detailRecipe.text = it.content
                mBinding.detailUser.text = it.userName
                mBinding.rvDetailMaterial.adapter = adapter

                adapter.notifyDataSetChanged()
                adapter.materialList = it.materials.map {
                    MaterialRequest(
                        it.name,
                        it.url
                    )
                } as ArrayList<MaterialRequest>
            })

            isLike.observe(this@PostDetailActivity, {
                mBinding.btnLike.setImageResource(if (it) R.drawable.ic_good_true else R.drawable.ic_good)
            })

            mBinding.btnLike.setOnClickListener {
                likePost(postId, userId ?: "")
            }
        }
    }
}