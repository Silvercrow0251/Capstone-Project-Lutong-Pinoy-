package com.example.dday.All_Recipes

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextSwitcher
import android.widget.TextView
import com.example.dday.R
import kotlinx.android.synthetic.main.activity_item1.*
import android.view.animation.AnimationUtils
import android.widget.Button

class Item2Activity : AppCompatActivity() {

    private val languages = arrayOf("2 lbs Chicken\n3 pieces of pork","3 lbs Chicken \n4 pieces of pork","Kotlin","Scala","C++")
    private var index = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item2)



        share.setOnClickListener{
            val s = textView.text.toString()
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(Intent.createChooser(shareIntent,"Share via"))

        }






            // accessing the TextSwitcher from XML layout
            val textSwitcher = findViewById<TextSwitcher>(R.id.textSwitcher)
            textSwitcher.setFactory {
                val textView = TextView(this@Item2Activity)
                textView.gravity = Gravity.TOP or Gravity.LEFT
                textView.textSize = 20f
//                textView.setTextColor(Color.BLUE)
                textView
            }
            textSwitcher.setText(languages[index])

//            val textIn = AnimationUtils.loadAnimation(
//                this, android.R.anim.fade_in)
//            textSwitcher.inAnimation = textIn
//
//            val textOut = AnimationUtils.loadAnimation(
//                this, android.R.anim.fade_out)
//            textSwitcher.outAnimation = textOut

            // previous button functionality
            val prev = findViewById<Button>(R.id.prev)
            prev.setOnClickListener {
                index = if (index - 1 >= 0) index - 1 else 0
                textSwitcher.setText(languages[index])
            }
            // next button functionality
            val button = findViewById<Button>(R.id.next)
            button.setOnClickListener {
                index = if (index + 1 < languages.size) index + 1 else 4
                textSwitcher.setText(languages[index])
            }




    }
}