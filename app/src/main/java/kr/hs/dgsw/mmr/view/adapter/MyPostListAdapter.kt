package kr.hs.dgsw.mmr.view.adapter

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.databinding.FragmentHomeBinding
import kr.hs.dgsw.mmr.databinding.ItemPostBinding
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.utils.SingleLiveEvent
import kr.hs.dgsw.mmr.view.activity.PostDetailActivity
import kr.hs.dgsw.mmr.view.fragment.ProfileFragment
import kr.hs.dgsw.mmr.viewmodel.activity.ManagePostViewModel

class MyPostListAdapter() : RecyclerView.Adapter<MyPostListAdapter.MyPostViewHolder>() {

    var postResponse = ArrayList<PostResponse>()
    lateinit var context: Context
    lateinit var binding: ItemPostBinding

    val clickEvent = SingleLiveEvent<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostListAdapter.MyPostViewHolder {
        binding = DataBindingUtil.inflate<ItemPostBinding>(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return MyPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        holder.bind(postResponse[position], context)
    }

    override fun getItemCount(): Int {
        return postResponse.size
    }

    inner class MyPostViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root){
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

            itemView.setOnLongClickListener {
                clickEvent.value = postResponse.id
                return@setOnLongClickListener true
            }
        }

    }
}