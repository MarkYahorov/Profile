package com.example.profile.Activitys

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.profile.R
import com.example.profile.UserPhotoForInstagram

class CurrentPhoto : AppCompatActivity() {

    private lateinit var photo: UserPhotoForInstagram
    private lateinit var likeButton: ImageView
    private lateinit var likeCount: TextView
    private lateinit var marker: ImageView
    private lateinit var minus: View

    private var isChecked = true
    private var isCheckedMarker = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_photo)
        photo = intent.getSerializableExtra(EXTRA_PHOTO) as UserPhotoForInstagram
        findViewById<ImageView>(R.id.current_photo).setImageResource(photo.photo)

        checkAndAddLike()
        addMarker()
    }

    companion object {

        private const val EXTRA_PHOTO = "EXTRA_PHOTO"

        fun getStartIntent(context: Context, photo: UserPhotoForInstagram) =
            Intent(context, CurrentPhoto::class.java).apply {
                putExtra(EXTRA_PHOTO, photo)
            }
    }

    private fun checkAndAddLike() {
        likeCount = findViewById(R.id.count_like)
        likeButton = findViewById(R.id.btn_likes)
        var count = 0
        count = likeCount.text.toString().toInt()
        likeButton.setOnClickListener {
            if (isChecked) {
                likeButton.setImageResource(R.drawable.ic_likes_active)
                count+=1
                likeCount.text = count.toString()
                isChecked = false
            } else {
                likeButton.setImageResource(R.drawable.ic_likes)
                count-=1
                likeCount.text = count.toString()
                isChecked = true
            }
        }
    }

    private fun addMarker() {
        marker = findViewById(R.id.btn_marker)
        marker.setOnClickListener {
            if (isCheckedMarker) {
                marker.setImageResource(R.drawable.ic_marker)
                isCheckedMarker = false
            } else {
                marker.setImageResource(R.drawable.ic_marker_no_active)
            }
        }
    }






}