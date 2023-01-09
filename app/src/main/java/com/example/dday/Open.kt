package com.example.dday

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dday.A_FoodAdapter.Breakfast
import com.example.dday.A_FoodAdapter.Desserts
import com.example.dday.A_FoodAdapter.Lunch
import com.example.dday.Alarm.RemindersActivity
import com.example.dday.All_Recipes.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_open.*

class Open : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
//            R.id.mHome -> return@OnNavigationItemSelectedListener true
            R.id.mHome -> return@OnNavigationItemSelectedListener true

            R.id.mSearch -> {
                startActivity(Intent(applicationContext, Food::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }

            R.id.mNotes -> {
                startActivity(Intent(applicationContext, Notes::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }

            R.id.mCalendar -> {
                startActivity(Intent(applicationContext, RemindersActivity::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)

//        //Action Bar Custom Font, textsize, and color
//        val actionBar = supportActionBar
//        val tv = TextView(applicationContext)
//        val typeface = ResourcesCompat.getFont(this, R.font.poppins)
//        val lp = RelativeLayout.LayoutParams(
//            RelativeLayout.LayoutParams.MATCH_PARENT,  // Width of TextView
//            RelativeLayout.LayoutParams.WRAP_CONTENT
//        ) // Height of TextView
//
//        tv.layoutParams = lp
//        tv.text = "Lutong Pinoy" // ActionBar title text
//
//        tv.textSize = 20f
//        tv.setTextColor(Color.parseColor("#fc6011"))
//        tv.setTypeface(typeface, Typeface.BOLD)
//        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        actionBar!!.customView = tv

//        setTheme(R.style.Theme_Dday)
        //remove shadow below action bar
        supportActionBar!!.elevation = 0.0f

        see_breakfast.setOnClickListener{

            val intent = Intent(this,Breakfast::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        see_lunch.setOnClickListener{

            val intent = Intent(this, Lunch::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        see_dessert.setOnClickListener{

            val intent = Intent(this, Desserts::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        cardView_breakfast_1.setOnClickListener{

            val intent = Intent(this, BrArrozCaldo::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        cardView_breakfast_2.setOnClickListener{

            val intent = Intent(this, BrGinisangKamatis::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        cardView_lunch_1.setOnClickListener{

            val intent = Intent(this,LnAdobo::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        cardView_lunch_2.setOnClickListener{

            val intent = Intent(this,LnBarbecue::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        cardView_dessert_1.setOnClickListener{

            val intent = Intent(this,DesBiko::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

        cardView_dessert_2.setOnClickListener{

            val intent = Intent(this,DesBukoPandan::class.java);
            overridePendingTransition(0, 0)
            startActivity(intent);
        }

//Toolbar
//===========================================================================================================
//        setSupportActionBar(toolbar)
//
//        toolbar.setNavigationOnClickListener {
//            startActivity(Intent(applicationContext, Notes::class.java))
//        }
//===========================================================================================================
//        val btn_press = findViewById<Button>(R.id.btn_press)
//        btn_press.setOnClickListener {
//            val intent = Intent(this, Calendar::class.java)
//            startActivity(intent)
//        }
//Bottom Navigation
//===========================================================================================================
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setSelectedItemId(R.id.mHome)
//===========================================================================================================
    }

//Action Bar
//===========================================================================================================
//  Add Search Feature
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_v2, menu)

    return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.search -> {
//                startActivity(Intent(this, Breakfast::class.java))
//                return true
//            }
            R.id.about -> {
                startActivity(Intent(this, TutorialScreen::class.java))
                overridePendingTransition(0, 0)
                return true
            }

            R.id.tutorial -> {
                startActivity(Intent(this, AboutApp::class.java))
                overridePendingTransition(0, 0)
                return true
            }

            R.id.share -> {
                shareApp()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    protected fun shareApp() {
        val sAIntent = Intent()
        sAIntent.action = Intent.ACTION_SEND
        sAIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Lutong Pinoy is a mobile Filipino app for meal planning and meal preparation. Get it from: https://play.google.com/store/apps/details?id=$packageName"
        )
        sAIntent.type = "text/plain"
        Intent.createChooser(sAIntent, "Share via")
        startActivity(sAIntent)
    }
//===========================================================================================================
    //Disable back press
//    override fun onBackPressed() {
//        return
//    }

    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishAffinity()
        } else {
            Toast.makeText(applicationContext, "Press back again to exit app", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

//Toolbar
//===========================================================================================================
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.mainmenu,menu)
//    return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId){
//            R.id.find -> {
//                startActivity(Intent( this, Breakfast::class.java))
//                true
//                }
//            R.id.about -> {
//                startActivity(Intent(this, Notes::class.java))
//                true
//            }
//                else -> super.onOptionsItemSelected(item)
//        }
//    }



//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.mainmenu,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId){
//            R.id.find -> {
//                startActivity(Intent( this, Food::class.java))
//                true
//                }
//            R.id.about -> {
//                startActivity(Intent(this, Food::class.java))
//                true
//            }
//                else -> super.onOptionsItemSelected(item)
//        }
//    }
//===========================================================================================================
}