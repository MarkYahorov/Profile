package com.example.profile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(private var photos:ArrayList<UserPhotoForInstagram>,
                   private val onClick: (UserPhotoForInstagram) -> Unit):
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        private val onClick: (UserPhotoForInstagram) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private var photo = itemView.findViewById<ImageView>(R.id.photo)

        fun bind(photosItem:UserPhotoForInstagram){
            photo.setImageResource(photosItem.photo)
            photo.setOnClickListener {
                onClick(photosItem)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.photo_item, p0,false)
        return ViewHolder(view, onClick)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(photos[p1])

    }

}