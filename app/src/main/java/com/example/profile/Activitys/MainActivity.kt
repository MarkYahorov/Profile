package com.example.profile.Activitys

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.profile.PhotoAdapter
import com.example.profile.R
import com.example.profile.UserPhotoForInstagram

class MainActivity : AppCompatActivity() {

    companion object{
        var thisTheme = 0
        const val STANDART_THEME = 0
        const val MY_THEME = 1

        fun changeTheme(activity: Activity, theme:Int){
            thisTheme = theme
            activity.finish()
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }

        fun setTheme(activity: Activity){
            when(thisTheme){
                STANDART_THEME -> activity.setTheme(R.style.Theme_Profile)
                MY_THEME -> activity.setTheme(R.style.AnotherTheme)
                else -> activity.setTheme(R.style.Theme_Profile)
            }
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var followingButton: Button
    private lateinit var followersCount: TextView
    private lateinit var shareButton: Button
    private lateinit var siteText: TextView
    private lateinit var changeATheme: ImageView

    private var isFollowing = true
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(this)
        setContentView(R.layout.activity_main)

        shareButton = findViewById(R.id.share)
        recyclerView = findViewById(R.id.recycler_photo)
        recyclerView.layoutManager = GridLayoutManager(
            this
            , 3
            , LinearLayoutManager.VERTICAL
            , false
        )
        recyclerView.adapter = PhotoAdapter(
            photos = createList(),
            onClick = {
                startActivity(
                    CurrentPhoto.getStartIntent(
                        this,
                        it
                    )
                )
            })

        following()
        sendOnSite()
        changeTheTheme()
    }


    private fun createList(): ArrayList<UserPhotoForInstagram> {
        val photosForInstagram = ArrayList<UserPhotoForInstagram>()
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.main))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic1))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic2))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic3))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic4))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic5))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic6))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic7))
        photosForInstagram.add(UserPhotoForInstagram(R.drawable.pic8))
        return photosForInstagram
    }

    @SuppressLint("ResourceAsColor")
    private fun following() {
        followersCount = findViewById(R.id.count_followers)
        followingButton = findViewById(R.id.btn_following)
        //followingButton.setBackgroundColor(Color.RED)
        count = followersCount.text.toString().toInt()
        followingButton.setOnClickListener {
            if (isFollowing) {
                followingButton.setBackgroundColor(Color.LTGRAY)
                count+=1
                followersCount.text = count.toString()
                isFollowing = false
            } else {
                followingButton.setBackgroundColor(R.attr.buttonColor)
                count-=1
                followersCount.text = count.toString()
                isFollowing = true
            }
        }
    }

    private fun sendOnSite() {
        siteText = findViewById(R.id.site_text)
        siteText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(siteText.text.toString()))
            startActivity(intent)
        }
    }

    private fun changeTheTheme() {
        changeATheme = findViewById(R.id.more)
        changeATheme.setOnClickListener {
            thisTheme = if(thisTheme == 0) {
                changeTheme(this, MY_THEME)
                1
            } else {
                changeTheme(this, STANDART_THEME)
                0
            }
        }
    }


}