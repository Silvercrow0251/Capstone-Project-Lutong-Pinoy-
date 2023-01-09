package com.example.dday.Manual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dday.Open
import com.example.dday.R

class ScreenThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_three)

        findViewById<View>(R.id.next).setOnClickListener {
            startActivity(Intent(applicationContext, Open::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

    }
}