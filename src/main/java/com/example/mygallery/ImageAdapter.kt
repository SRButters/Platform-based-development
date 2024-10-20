package com.example.mygallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val imageList: List<String>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imagePath = imageList[position]
        // Load the image into the ImageView using a library like Glide
        Glide.with(holder.imageView.context)
            .load(imagePath)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}

