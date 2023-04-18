package com.example.oblig4Meg

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.model.ArtApiStatus
import com.example.oblig4Meg.network.ArtPhoto




@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }

}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ArtPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

//@BindingAdapter("listData2")
//fun bindRecyclerView2(recyclerView: RecyclerView, data: List<Basket>?) {
//    val adapter = recyclerView.adapter as LinearAdapter
//    adapter.submitList(data)
//}







@BindingAdapter("artApiStatus")
fun bindStatus(statusImageView: ImageView, status: ArtApiStatus?) {
    when (status) {
        ArtApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ArtApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ArtApiStatus.DONE -> {
            statusImageView.visibility = View.VISIBLE
        }
        else ->
            return
    }
}