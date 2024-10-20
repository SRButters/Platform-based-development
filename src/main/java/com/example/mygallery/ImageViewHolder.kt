package com.example.mygallery

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.imageView)
}
