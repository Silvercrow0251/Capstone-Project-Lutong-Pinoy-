package com.example.dday.All_Recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dday.Open
import com.example.dday.R
import kotlinx.android.synthetic.main.activity_item1.*

class LnCrispyPata : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ln_crispy_pata)

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

            //Get text from TextView and store in variable "s"
            val s = textView.text.toString()+"\n"+"\n"+

                    Utensils.text.toString()+"\n"+
                    Utensils_items_1.text.toString()+"\n"+
                    Utensils_items_2.text.toString()+"\n"+
                    Utensils_items_3.text.toString()+"\n"+
                    Utensils_items_4.text.toString()+"\n"+
                    Utensils_items_5.text.toString()+"\n"+"\n"+

                    Ingredients.text.toString()+"\n"+
                    Ingredients_Item_1.text.toString()+"\n"+
                    Ingredients_Item_2.text.toString()+"\n"+
                    Ingredients_Item_3.text.toString()+"\n"+
                    Ingredients_Item_4.text.toString()+"\n"+
                    Ingredients_Item_5.text.toString()+"\n"+
                    Ingredients_Item_6.text.toString()+"\n"+
                    Ingredients_Item_7.text.toString()+"\n"+
                    Ingredients_Item_8.text.toString()+"\n"+
                    Ingredients_Item_9.text.toString()+"\n"+
                    Ingredients_Item_10.text.toString()+"\n"+
                    Ingredients_Item_11.text.toString()+"\n"+"\n"+

                    Instructions.text.toString()+"\n"+"\n"+
                    Step1.text.toString()+"\n"+
                    Step_1.text.toString()+"\n"+"\n"+

                    Step2.text.toString()+"\n"+
                    Step_2.text.toString()+"\n"+"\n"+

                    Step3.text.toString()+"\n"+
                    Step_3.text.toString()+"\n"+"\n"+

                    Step4.text.toString()+"\n"+
                    Step_4.text.toString()+"\n"+"\n"+

                    Step5.text.toString()+"\n"+
                    Step_5.text.toString()+"\n"+"\n"+

                    "Lutong Pinoy"+"\n"






            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(Intent.createChooser(shareIntent,"Share via"))

        }
    }
}