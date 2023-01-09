package com.example.dday.All_Recipes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.dday.Food
import com.example.dday.Open
import com.example.dday.R
import kotlinx.android.synthetic.main.activity_item1.*

class Item1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item1)

        back.setOnClickListener {
            val intent = Intent(this, Open::class.java)
            startActivity(intent)
        }

//        copy.setOnClickListener {
//            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//
//            val clip = ClipData.newPlainText("TextView",Recipe.getText().toString())
//
//            clipboard.setPrimaryClip(clip)
//
//            Toast.makeText(this,"Recipe copied",Toast.LENGTH_SHORT).show()
//        }

        share.setOnClickListener{

            //share image
//            val b=BitmapFactory.decodeResource(resources,R.drawable.adobo_rp)
//            val intent=Intent()
//            intent.action= Intent.ACTION_SEND
//
//            val path=MediaStore.Images.Media.insertImage(contentResolver,b,"Title",null)
//
//            val uri= Uri.parse(path)
//
//            intent.putExtra(Intent.EXTRA_STREAM,uri)
//            intent.type="image/*"
//            startActivity(Intent.createChooser(intent,"Share To : "))

            //Get text from TextView and store in variable "s"
            val s = Recipe.text.toString()
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(Intent.createChooser(shareIntent,"Share via"))

        }
    }
}