package com.example.oblig4Meg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig4Meg.databinding.GridViewItemBinding

import com.example.oblig4Meg.network.ArtPhoto




class PhotoGridAdapter (private val cellClickListener: CellClickListener): ListAdapter<ArtPhoto, PhotoGridAdapter.ArtPhotoViewHolder>(DiffCallback) {



    companion object DiffCallback : DiffUtil.ItemCallback<ArtPhoto>() {
        override fun areContentsTheSame(oldItem: ArtPhoto, newItem: ArtPhoto): Boolean {


            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: ArtPhoto, newItem: ArtPhoto): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }
    }


    class ArtPhotoViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(ArtPhoto:ArtPhoto){
                binding.photo = ArtPhoto

                binding.executePendingBindings()
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtPhotoViewHolder {
        return ArtPhotoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArtPhotoViewHolder, position: Int) {
        val artPhoto = getItem(position)
        holder.bind(artPhoto)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(artPhoto)
        }
    }


}