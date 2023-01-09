package com.example.dday

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.dday.Alarm.RemindersActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class Notes : AppCompatActivity() {
//Bottom Navigation
//===========================================================================================================
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
//            R.id.mHome -> return@OnNavigationItemSelectedListener true
            R.id.mHome -> {
                startActivity(Intent(applicationContext, Open::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }

            R.id.mSearch -> {
                startActivity(Intent(applicationContext, Food::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }

            R.id.mNotes -> return@OnNavigationItemSelectedListener true

            R.id.mCalendar -> {
                startActivity(Intent(applicationContext, RemindersActivity::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
//===========================================================================================================
    lateinit var controller: NavController

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        //Action Bar Custom Font, textsize, and color
//        val actionBar = supportActionBar
//        val tv = TextView(applicationContext)
//        val typeface = ResourcesCompat.getFont(this, R.font.poppins)
//        val lp = RelativeLayout.LayoutParams(
//            RelativeLayout.LayoutParams.MATCH_PARENT,  // Width of TextView
//            RelativeLayout.LayoutParams.WRAP_CONTENT
//        ) // Height of TextView
//
//        tv.layoutParams = lp
//        tv.text = "Notes" // ActionBar title text
//
//        tv.textSize = 20f
//        tv.setTextColor(Color.parseColor("#fc6011"))
//        tv.setTypeface(typeface, Typeface.BOLD)
//        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        actionBar!!.customView = tv

        //Status Bar Color
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.Orange)
        }

        setTheme(R.style.Material)
        //remove shadow below action bar
        supportActionBar!!.elevation = 0.0f












//Bottom Navigation
//===========================================================================================================
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setSelectedItemId(R.id.mNotes)
//===========================================================================================================

        controller = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(controller)
    }

    override fun onBackPressed() {
//        if(backPressedTime + 2000 > System.currentTimeMillis()){
//            super.onBackPressed()
//            finishAffinity()

        finish()
          val intent = Intent(this, Notes::class.java)
        startActivity(intent)

//        if(backPressedTime + 2000 > System.currentTimeMillis()){
//            finishAffinity()
//        } else {
//            Toast.makeText(applicationContext, "Press back again to exit app", Toast.LENGTH_SHORT).show()
//        }
//        backPressedTime = System.currentTimeMillis()

//        }
//        backPressedTime = System.currentTimeMillis()
//        finishAffinity()
//        true
//        if(backPressedTime + 1000 > System.currentTimeMillis()){
//            finishAffinity()
//        } else {
//            Toast.makeText(applicationContext, "Press back again to exit app", Toast.LENGTH_SHORT).show()
//        }
//        backPressedTime = System.currentTimeMillis()
//        val intent = Intent(this, Notes::class.java)
//        startActivity(intent)
    }

    override fun onNavigateUp(): Boolean {
        return controller.navigateUp() || super.onNavigateUp()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_sample,menu)
//        return true
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
//                startActivity(Intent(this, Notes::class.java))
                onBackPressed();
                return true
            }
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
            R.id.share_app   -> {
                val sAIntent = Intent()
        sAIntent.action = Intent.ACTION_SEND
        sAIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Lutong Pinoy is a mobile Filipino app for meal planning and meal preparation. Get it from: https://play.google.com/store/apps/details?id=$packageName"
        )
        sAIntent.type = "text/plain"
        Intent.createChooser(sAIntent, "Share via")
        startActivity(sAIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}