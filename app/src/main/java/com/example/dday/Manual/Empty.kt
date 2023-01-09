package com.example.dday.Manual

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dday.R
import com.example.dday.SplashScreen

class Empty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this, Welcome::class.java))
//        }, 2000)

//        findViewById<View>(R.id.next).setOnClickListener {
//            startActivity(Intent(applicationContext, Welcome::class.java))
//            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
//        }
//
//        //Check if application is opened for the first time
//        val preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//        val FirstTime = preferences.getString("FirstTimeInstall", "")
//
//        if (FirstTime == "Yes") {
//
//            //If application was opened for the first time
//            val intent = Intent(this@Empty, SplashScreen::class.java)
//            startActivity(intent)
//        } else {
//
//            //Else...
//            val editor = preferences.edit()
//            editor.putString("FirstTimeInstall", "Yes")
//            editor.apply()
//        }

    }
}