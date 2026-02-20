package uz.gita.maxwayclone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.maxwayclone.R
import uz.gita.maxwayclone.data.model.home.AdModel
import uz.gita.maxwayclone.databinding.ItomAdsBinding

class AdsAdapter : ListAdapter<AdModel, AdsAdapter.VH>(DiffCallBack) {


    override fun getItemCount(): Int {
        return if (currentList.isNotEmpty())10000 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItomAdsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
       if (currentList.isNotEmpty()){
           holder.bind(getItem(position % currentList.size))
       }
    }

    inner class VH(val binding: ItomAdsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AdModel) {
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.search)
                .into(binding.imageView)
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<AdModel>() {
        override fun areItemsTheSame(oldItem: AdModel, newItem: AdModel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: AdModel, newItem: AdModel) = oldItem == newItem
    }
}