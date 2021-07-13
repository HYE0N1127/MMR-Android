package kr.hs.dgsw.mmr.view.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityPostDetailBinding
import kr.hs.dgsw.mmr.viewmodel.activity.PostDetailViewModel

class PostDetailActivity : BaseActivity<ActivityPostDetailBinding, PostDetailViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PostDetailViewModel::class.java)
        super.onCreate(savedInstanceState)

        mViewModel.detailPostGetData(intent.getIntExtra("postId",0))
    }

    override fun observerViewModel() {
        with(mViewModel){
            detailResponse.observe(this@PostDetailActivity, {
                Glide.with(this@PostDetailActivity)
                    .load("https://lh3.googleusercontent.com/proxy/mI-nwybAE2J6j52dkCmQZsSywfuZ7VXy9FMrpIvaf_eQRO1aoiYVWmYv1UQNoynBktfUnU0V8d9R6QaK8BqxZqw")
                    .into(mBinding.detailImage)
                mBinding.detailTitle.text = it.title
                mBinding.detailLike.text = it.likeNum.toString()
                mBinding.detailComment.text = it.summary
                mBinding.detailRecipe.text = it.content
                mBinding.detailMeterial.text = it.materials.toString()
                mBinding.detailUser.text = it.userName
                Log.e("123", it.imgUrl)
                //재료 처리
                //버튼 클릭 처리
            })
        }
    }
}