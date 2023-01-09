package com.example.dday.A_FoodAdapter

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
//  Add Search Feature
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dday.*
import com.example.dday.All_Recipes.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_lunch.*

class Lunch : AppCompatActivity(), ItemsAdapter.ClickListener{

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

            R.id.mSearch -> return@OnNavigationItemSelectedListener true

            R.id.mNotes -> {
                startActivity(Intent(applicationContext, Notes::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }


            R.id.mCalendar -> {
                startActivity(Intent(applicationContext, Notes::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
//===========================================================================================================

    val imagesName = arrayOf(
        ItemsModal("Adobo", "1hr 5mins", R.drawable.lnd_01_adobo, "adobo", "Serves 4", "Lunch and Dinner"),
        ItemsModal("Filipino Barbecue", "40 mins", R.drawable.lnd_02_barbecue, "filipino barbecue", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Bulalo", "2hrs 15mins", R.drawable.lnd_03_bulalo,"bulalo", "Serves 4", "Lunch and Dinner"),
        ItemsModal("Dinuguan", "50 mins", R.drawable.lnd_04_dinuguan,"dinuguan", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Kalderata", "2hrs 30mins", R.drawable.lnd_05_kaldereta,"kalderata", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Kare-kare", "45 mins", R.drawable.lnd_06_kare_kare,"kare-kare", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Lechon", "1hr 15mins", R.drawable.lnd_07_lechon,"lechon", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Lumpia", "40 mins", R.drawable.lnd_08_lumpia,"lumpia", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Pancit", "50 mins", R.drawable.lnd_09_pancit,"pancit", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Pinakbet", "35 mins", R.drawable.lnd_10_pinakbet,"pinakbet", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Sinigang", "35 mins", R.drawable.lnd_11_sinigang,"sinigang", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Sisig", "1hr 20mins", R.drawable.lnd_12_sisig,"sisig", "Serves 6", "Lunch and Dinner")
    )

    val itemModalList = ArrayList<ItemsModal>()

    var itemsAdapter : ItemsAdapter? = null;

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)

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
//        tv.text = "Lunch and Dinner" // ActionBar title text
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

//        setTheme(R.style.Material)
        //remove shadow below action bar
        supportActionBar!!.elevation = 0.0f

        for(items in imagesName){
            itemModalList.add(items)
        }

        itemsAdapter = ItemsAdapter(this);
        itemsAdapter!!.setData(itemModalList);
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = itemsAdapter
        // adjust Gridview 1, 2, or more
        recyclerView.layoutManager = GridLayoutManager(this, 1)


//Bottom Navigation
//===========================================================================================================
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//
//        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
//        //bottomNavigation.setSelectedItemId(R.id.home)
//        bottomNavigation.setSelectedItemId(R.id.mSearch)
//===========================================================================================================
    }

    override fun ClickedItem(itemsModal: ItemsModal) {
        Log.e("TAG", itemsModal.name);

        when(itemsModal.name){
            "Adobo" ->
                startActivity(Intent(this@Lunch, LnAdobo::class.java))
            "Filipino Barbecue" ->
                startActivity(Intent(this@Lunch, LnBarbecue::class.java))
            "Bulalo" ->
                startActivity(Intent(this@Lunch, LnBulalo::class.java))
            "Dinuguan" ->
                startActivity(Intent(this@Lunch, LnDinuguan::class.java))
            "Kalderata" ->
                startActivity(Intent(this@Lunch, LnKalderata::class.java))
            "Kare-kare" ->
                startActivity(Intent(this@Lunch, LnKareKare::class.java))
            "Lechon" ->
                startActivity(Intent(this@Lunch, LnLechon::class.java))
            "Lumpia" ->
                startActivity(Intent(this@Lunch, LnLumpia::class.java))
            "Pancit" ->
                startActivity(Intent(this@Lunch, LnPancit::class.java))
            "Pinakbet" ->
                startActivity(Intent(this@Lunch, LnPinakbet::class.java))
            "Sinigang" ->
                startActivity(Intent(this@Lunch, LnSinigang::class.java))
            "Sisig" ->
                startActivity(Intent(this@Lunch, LnSisig::class.java))

            else -> {
                Toast.makeText(this, "No Action", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //  Add Search Feature
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val menuItem = menu!!.findItem(R.id.searchView)

        val searchView = menuItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(filterString: String): Boolean {
                itemsAdapter!!.filter.filter(filterString)
                return true
            }

            override fun onQueryTextChange(filterString: String?): Boolean {

                itemsAdapter!!.filter.filter(filterString)
                return true
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

}