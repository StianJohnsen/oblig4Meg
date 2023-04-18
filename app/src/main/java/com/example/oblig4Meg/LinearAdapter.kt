package com.example.oblig4Meg

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.databinding.LinearViewItemBinding
import com.example.oblig4Meg.network.ArtPhoto

class LinearAdapter(private val onItemClicked: (ArtPhoto) -> Unit): ListAdapter<ArtPhoto, LinearAdapter.ArtPhotoLinearViewHolder> (DiffCallback){



    companion object DiffCallback: DiffUtil.ItemCallback<ArtPhoto>(){
        override fun areContentsTheSame(oldItem: ArtPhoto, newItem: ArtPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: ArtPhoto, newItem: ArtPhoto): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }
    }

    class ArtPhotoLinearViewHolder(private var binding: LinearViewItemBinding) : RecyclerView.ViewHolder(binding.root)  {

        fun bind(artPhoto: ArtPhoto){
            binding.photo = artPhoto
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtPhotoLinearViewHolder {
        return ArtPhotoLinearViewHolder(LinearViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArtPhotoLinearViewHolder, position: Int) {
        val artPhoto = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(artPhoto)
        }
        holder.bind(artPhoto)
    }
}