package com.example.dday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class AboutApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)

        supportActionBar?.hide()

        val buttonClick = findViewById<ImageButton>(R.id.back)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Open::class.java)
            startActivity(intent)
        }

    }
}