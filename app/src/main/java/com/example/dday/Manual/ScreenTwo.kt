package com.example.dday.Manual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dday.R

class ScreenTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_two)

        findViewById<View>(R.id.next).setOnClickListener {
            startActivity(Intent(applicationContext, ScreenThree::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

    }
}