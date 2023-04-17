package com.example.oblig4Meg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.databinding.LinearViewItemBinding
import com.example.oblig4Meg.network.ArtPhoto

class LinearAdapter: ListAdapter<Basket, LinearAdapter.ArtPhotoLinearViewHolder> (DiffCallback){



    companion object DiffCallback: DiffUtil.ItemCallback<Basket>(){
        override fun areContentsTheSame(oldItem: Basket, newItem: Basket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Basket, newItem: Basket): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }
    }

    class ArtPhotoLinearViewHolder(private var binding: LinearViewItemBinding) : RecyclerView.ViewHolder(binding.root)  {

        fun bind(basket: Basket){
            binding.basket = basket
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtPhotoLinearViewHolder {
        return ArtPhotoLinearViewHolder(LinearViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArtPhotoLinearViewHolder, position: Int) {
        val basket = getItem(position)
        holder.bind(basket)
    }
}