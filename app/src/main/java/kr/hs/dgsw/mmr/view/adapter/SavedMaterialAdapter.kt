package kr.hs.dgsw.mmr.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.databinding.ItemSavedMaterialBinding
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest

class SavedMaterialAdapter : RecyclerView.Adapter<SavedMaterialAdapter.MaterialViewHolder>() {

    var materialList = ArrayList<MaterialRequest>()

    val deleteEvent = MutableLiveData<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {

        val binding = DataBindingUtil.inflate<ItemSavedMaterialBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_saved_material
            ,
            parent,
            false
        )

        return MaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        holder.bind(materialList[position])
        if (position % 2 == 0) holder.binding.containerSavedMaterial.isSelected = true
        holder.binding.btnRemoveMaterial.setOnClickListener {
            materialList.removeAt(position)
            deleteEvent.value = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return materialList.size
    }

    class MaterialViewHolder(val binding: ItemSavedMaterialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(materialRequest: MaterialRequest) {
            binding.tvSavedMaterialName.text = materialRequest.name
            binding.tvSavedMaterialUrl.text = materialRequest.url
        }
    }
}