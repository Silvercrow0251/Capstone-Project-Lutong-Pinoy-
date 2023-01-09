package com.example.dday.All_Recipes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dday.Open
import com.example.dday.R
import kotlinx.android.synthetic.main.activity_des_biko.Step6
import kotlinx.android.synthetic.main.activity_des_biko.Step_6
import kotlinx.android.synthetic.main.activity_des_buko_pandan.*
import kotlinx.android.synthetic.main.activity_item1.*
import kotlinx.android.synthetic.main.activity_item1.Ingredients
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_1
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_2
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_3
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_4
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_5
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_6
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_7
import kotlinx.android.synthetic.main.activity_item1.Ingredients_Item_8
import kotlinx.android.synthetic.main.activity_item1.Instructions
import kotlinx.android.synthetic.main.activity_item1.Step1
import kotlinx.android.synthetic.main.activity_item1.Step2
import kotlinx.android.synthetic.main.activity_item1.Step3
import kotlinx.android.synthetic.main.activity_item1.Step4
import kotlinx.android.synthetic.main.activity_item1.Step5
import kotlinx.android.synthetic.main.activity_item1.Step_1
import kotlinx.android.synthetic.main.activity_item1.Step_2
import kotlinx.android.synthetic.main.activity_item1.Step_3
import kotlinx.android.synthetic.main.activity_item1.Step_4
import kotlinx.android.synthetic.main.activity_item1.Step_5
import kotlinx.android.synthetic.main.activity_item1.Utensils
import kotlinx.android.synthetic.main.activity_item1.Utensils_items_1
import kotlinx.android.synthetic.main.activity_item1.Utensils_items_2
import kotlinx.android.synthetic.main.activity_item1.Utensils_items_3
import kotlinx.android.synthetic.main.activity_item1.Utensils_items_4
import kotlinx.android.synthetic.main.activity_item1.Utensils_items_5
import kotlinx.android.synthetic.main.activity_item1.back
import kotlinx.android.synthetic.main.activity_item1.share
import kotlinx.android.synthetic.main.activity_item1.textView

class DesBukoPandan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_des_buko_pandan)

        back.setOnClickListener {
            val intent = Intent(this, Open::class.java)
            startActivity(intent)
        }

        copy.setOnClickListener {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

            val clip = ClipData.newPlainText("TextView",

                textView.text.toString()+"\n"+"\n"+

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
                        Ingredients_Item_8.text.toString()+"\n"+"\n"+

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

                        Step6.text.toString()+"\n"+
                        Step_6.text.toString()+"\n"+"\n"+

                        Step7.text.toString()+"\n"+
                        Step_7.text.toString()+"\n"+"\n"+

                        "Lutong Pinoy"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n".toString())

            clipboard.setPrimaryClip(clip)

            Toast.makeText(this,"Recipe copied",Toast.LENGTH_SHORT).show()
        }

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
                    Ingredients_Item_8.text.toString()+"\n"+"\n"+

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

                    Step6.text.toString()+"\n"+
                    Step_6.text.toString()+"\n"+"\n"+

                    Step7.text.toString()+"\n"+
                    Step_7.text.toString()+"\n"+"\n"+

                    "Lutong Pinoy is a mobile Filipino recipe app that can help users plan and prepare meals. Get it from: https://play.google.com/store/apps/details?id=" + packageName +"\n"+"\n"+"\n"+"\n"+"\n"+"\n".toString()+"\n"






            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(Intent.createChooser(shareIntent,"Share via"))

        }
    }
}