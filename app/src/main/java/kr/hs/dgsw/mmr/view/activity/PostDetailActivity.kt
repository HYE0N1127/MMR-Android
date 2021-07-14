package kr.hs.dgsw.mmr.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseActivity
import kr.hs.dgsw.mmr.databinding.ActivityPostDetailBinding
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.viewmodel.activity.PostDetailViewModel

class PostDetailActivity : BaseActivity<ActivityPostDetailBinding, PostDetailViewModel>() {
    private val STRING = "&||&"
    private val STRING2 = "*()*"

    var postId = 0
    var userId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PostDetailViewModel::class.java)
        postId = intent.getIntExtra("postId", 0)
        userId = getSharedPreferences("LoginActivity", MODE_PRIVATE).getString("id", "") ?: ""
        super.onCreate(savedInstanceState)
        mViewModel.detailPostGetData(postId)
        mViewModel.checkLikePost(postId, userId ?: "")
        mBinding.detailRefresh.setOnRefreshListener {
            mBinding.detailRefresh.isRefreshing = false
            mViewModel.detailPostGetData(postId)
        }
    }

    override fun observerViewModel() {
        with(mViewModel) {


            val sharedPreference = getSharedPreferences("LOGIN_ACTIVITY", MODE_PRIVATE)

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

            mBinding.detailCartBtn.setOnClickListener {
                val result = sharedPreference.getString("material", "")

                if (adapter.materialList.isNotEmpty()) {
                    val add = adapter.materialList.map { "${it.name}$STRING2${it.url}" }
                        .reduce { s1, s2 -> "$s1$STRING$s2" }
                    sharedPreference.edit().putString("material", result + add).apply()
                    Toast.makeText(this@PostDetailActivity, "성공적으로 저장되었습니다", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}