package kr.hs.dgsw.mmr.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.databinding.ItemMaterialBinding
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.network.model.response.MaterialResponse

class MaterialAdapter : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    var materialList = ArrayList<MaterialRequest>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {

        val binding = DataBindingUtil.inflate<ItemMaterialBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_material,
            parent,
            false
        )

        return MaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        holder.bind(materialList[position])
        holder.binding.btnMinusMaterial.setOnClickListener {
            materialList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return materialList.size
    }

    class MaterialViewHolder(val binding: ItemMaterialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(materialRequest: MaterialRequest) {
            binding.tvName.text = materialRequest.name
            binding.tvUrl.text = materialRequest.url
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(materialRequest.url))
                it.context.startActivity(intent)
            }
        }
    }
}