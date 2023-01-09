package com.example.dday.A_FoodAdapter

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
//  Add Search Feature
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dday.*
import com.example.dday.All_Recipes.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_breakfast.*

class Breakfast : AppCompatActivity(), ItemsAdapter.ClickListener{

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
        ItemsModal("Arroz Caldo", "1 hr", R.drawable.a_01_arroz_caldo, "arroz caldo", "Serves 6", "Breakfast"),
        ItemsModal("Chicksilog", "45 mins", R.drawable.a_02_chiksilog, "chicksilog", "Serves 4", "Breakfast"),
        ItemsModal("Cornsilog", "23 mins", R.drawable.a_03_corned_beef_silog, "cornsilog", "Serves 2", "Breakfast"),
        ItemsModal("Daing Na Bangus", "20 mins", R.drawable.a_04_daing_na_bangus, "daing na bangus", "Serves 2", "Breakfast"),
        ItemsModal("Ginisang Kamatis at Itlog", "15 mins", R.drawable.a_05_ginisang_kamatis, "ginisang kamatis at itlog", "Serves 6", "Breakfast"),
        ItemsModal("Hotsilog", "15 mins", R.drawable.a_06_hotsilog, "hotsilog", "Serves 1", "Breakfast"),
        ItemsModal("Longsilog", "30 mins", R.drawable.a_07_longsilog, "longsilog", "Serves 2", "Breakfast"),
        ItemsModal("Tapsilog", "35 mins", R.drawable.a_08_tapsilog, "tapsilog", "Serves 4", "Breakfast"),
        ItemsModal("Tortang Talong", "30 mins", R.drawable.a_09_tortang_talong, "tortang Talong", "Serves 2", "Breakfast"),
        ItemsModal("Tosilog", "45 mins", R.drawable.a_10_tosilog, "tosilog", "Serves 3", "Breakfast"),
        ItemsModal("Tusilog", "30 mins", R.drawable.a_11_tuyo_at_itlog, "tusilog", "Serves 4", "Breakfast")
    )

    val itemModalList = ArrayList<ItemsModal>()

    var itemsAdapter : ItemsAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakfast)

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
//        tv.text = "Breakfast" // ActionBar title text
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
            "Arroz Caldo" ->
                startActivity(Intent(this@Breakfast, BrArrozCaldo::class.java))
            "Chicksilog" ->
                startActivity(Intent(this@Breakfast, BrChiksilog::class.java))
            "Cornsilog" ->
                startActivity(Intent(this@Breakfast, BrCornsilog::class.java))
            "Daing Na Bangus" ->
                startActivity(Intent(this@Breakfast, BrDaingNaBangus::class.java))
            "Ginisang Kamatis at Itlog" ->
                startActivity(Intent(this@Breakfast, BrGinisangKamatis::class.java))
            "Hotsilog" ->
                startActivity(Intent(this@Breakfast, BrHotsilog::class.java))
            "Longsilog" ->
                startActivity(Intent(this@Breakfast, BrLongsilog::class.java))
            "Tapsilog" ->
                startActivity(Intent(this@Breakfast, BrTapsilog::class.java))
            "Tortang Talong" ->
                startActivity(Intent(this@Breakfast, BrTortangTalong::class.java))
            "Tosilog" ->
                startActivity(Intent(this@Breakfast, BrTosilog::class.java))
            "Tusilog" ->
                startActivity(Intent(this@Breakfast, BrTusilog::class.java))

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