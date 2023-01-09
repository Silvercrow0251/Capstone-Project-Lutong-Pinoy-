package com.example.dday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.dday.Manual.Welcome

class SplashScreen : AppCompatActivity() {

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Open::class.java))
        }, 2000)
    }
//    override fun onBackPressed() {
//        if(backPressedTime + 2000 > System.currentTimeMillis()){
//            super.onBackPressed()
//        } else {
//            Toast.makeText(applicationContext, "Press back again to exit app", Toast.LENGTH_SHORT).show()
//        }
//        backPressedTime = System.currentTimeMillis()
//    }
}