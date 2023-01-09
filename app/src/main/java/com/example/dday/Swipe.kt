package com.example.dday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dday.Tutorial.Intro

class Swipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)

        findViewById<View>(R.id.btn).setOnClickListener {
            startActivity(Intent(applicationContext, Intro::class.java))
//            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

    }
}