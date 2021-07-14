package kr.hs.dgsw.mmr.view.adapter

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.databinding.FragmentHomeBinding
import kr.hs.dgsw.mmr.databinding.ItemPostBinding
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.view.activity.PostDetailActivity
import kr.hs.dgsw.mmr.view.fragment.ProfileFragment

class HomePostListAdapter() : RecyclerView.Adapter<HomePostListAdapter.HomePostViewHolder>() {

    var postResponse = ArrayList<PostResponse>()
    lateinit var context: Context
    lateinit var binding: ItemPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostViewHolder {
        binding = DataBindingUtil.inflate<ItemPostBinding>(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return HomePostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePostViewHolder, position: Int) {
        holder.bind(postResponse[position], context)
    }

    override fun getItemCount(): Int {
        return postResponse.size
    }

    inner class HomePostViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(postResponse: PostResponse, context: Context) {
            binding.tvPostTitle.text = postResponse.title
            binding.tvHeart.text = postResponse.likeNum.toString()
            binding.tvPostSubTitle.text = postResponse.summary
            binding.tvUserName.text = postResponse.userName
            Glide.with(context)
                .load(postResponse.imgUrl)
                .into(binding.itemImage)
            itemView.setOnClickListener {
                val intent = Intent(context, PostDetailActivity::class.java)
                intent.putExtra("postId", postResponse.id)
                context.startActivity(intent)
            }
        }

    }
}