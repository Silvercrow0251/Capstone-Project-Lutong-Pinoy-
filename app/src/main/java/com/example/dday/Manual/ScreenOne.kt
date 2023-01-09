package com.example.dday.Manual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.dday.R
import kotlin.math.abs

class ScreenOne : AppCompatActivity() {

//    var x1 = 0f
//    var x2:kotlin.Float = 0f
//    var y1:kotlin.Float = 0f
//    var y2:kotlin.Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_one)

        findViewById<View>(R.id.next).setOnClickListener {
            startActivity(Intent(applicationContext, ScreenTwo::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
    }
//    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
//        when (touchEvent.action) {
//            MotionEvent.ACTION_DOWN -> {
//                x1 = touchEvent.x
//                y1 = touchEvent.y
//                if (x1 > x2) {
//                    val i = Intent(this@ScreenOne, Welcome::class.java)
//                    startActivity(i)
//                }
//            }
//            MotionEvent.ACTION_UP -> {
//                x2 = touchEvent.x
//                y2 = touchEvent.y
//                if (x1 < x2) {
//                    val i = Intent(this@ScreenOne, ScreenTwo::class.java)
//                    startActivity(i)
//                }
//            }
//        }
//        return false
//    }

}