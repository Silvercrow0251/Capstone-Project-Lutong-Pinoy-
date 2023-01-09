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

class Desserts : AppCompatActivity(), ItemsAdapter.ClickListener{

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
        ItemsModal("Biko", "45 mins", R.drawable.des_01_biko, "biko", "Serves 6", "Desserts"),
        ItemsModal("Buko Pandan", "35 mins", R.drawable.des_02_buko_pandan, "buko pandan", "Serves 6", "Desserts"),
        ItemsModal("Carioca", "30 mins", R.drawable.des_03_carioca, "carioca", "Serves 6", "Desserts"),
        ItemsModal("Filipino Fruit Salad", "30 mins", R.drawable.des_04_fruit_salad, "filipino fruit salad", "Serves 6", "Desserts"),
        ItemsModal("Ginataang Bilo Bilo", "1 hr", R.drawable.des_05_bilo_bilo, "ginataang bilo bilo", "Serves 6", "Desserts"),
        ItemsModal("Halo Halo", "5 mins", R.drawable.des_06_halo_halo, "halo halo", "Serves 1", "Desserts"),
        ItemsModal("Leche Flan", "1hr 10mins", R.drawable.des_07_leche_flan, "leche flan", "Serves 3", "Desserts"),
        ItemsModal("Mais Con Yelo", "10 mins", R.drawable.des_08_mais_con_yelo, "mais con yelo", "Serves 1", "Desserts"),
        ItemsModal("Maja Blanca", "35 mins", R.drawable.des_09_maja_blanca, "maja blanca", "Serves 6", "Desserts"),
        ItemsModal("Minatamis Na Saging", "25 mins", R.drawable.des_10_minatamis_na_saging, "minatamis na saging", "Serves 3", "Desserts"),
        ItemsModal("Turon", "25 mins", R.drawable.des_11_turon, "turon", "Serves 6", "Desserts"),
        ItemsModal("Ube Halaya", "1 hr", R.drawable.des_12_ube_halaya, "ube halaya", "Serves 6", "Desserts")
    )

    val itemModalList = ArrayList<ItemsModal>()

    var itemsAdapter : ItemsAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desserts)

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
            "Biko" ->
                startActivity(Intent(this@Desserts, DesBiko::class.java))
            "Buko Pandan" ->
                startActivity(Intent(this@Desserts, DesBukoPandan::class.java))
            "Carioca" ->
                startActivity(Intent(this@Desserts, DesCarioca::class.java))
            "Filipino Fruit Salad" ->
                startActivity(Intent(this@Desserts, DesFilipinoFruitSalad::class.java))
            "Ginataang Bilo Bilo" ->
                startActivity(Intent(this@Desserts, DesGinataangBiloBilo::class.java))
            "Halo Halo" ->
                startActivity(Intent(this@Desserts, DesHaloHalo::class.java))
            "Leche Flan" ->
                startActivity(Intent(this@Desserts, DesLecheFlan::class.java))
            "Mais Con Yelo" ->
                startActivity(Intent(this@Desserts, DesMaisConYelo::class.java))
            "Maja Blanca" ->
                startActivity(Intent(this@Desserts, DesMajaBlanca::class.java))
            "Minatamis Na Saging" ->
                startActivity(Intent(this@Desserts, DesMinatamisNaSaging::class.java))
            "Turon" ->
                startActivity(Intent(this@Desserts, DesTuron::class.java))
            "Ube Halaya" ->
                startActivity(Intent(this@Desserts, DesUbeHalaya::class.java))

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