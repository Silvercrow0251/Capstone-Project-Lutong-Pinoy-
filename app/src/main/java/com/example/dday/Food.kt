package com.example.dday

//  Add Search Feature
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dday.A_FoodAdapter.ItemsAdapter
import com.example.dday.A_FoodAdapter.ItemsModal
import com.example.dday.Alarm.RemindersActivity
import com.example.dday.All_Recipes.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_food.*

class Food : AppCompatActivity(), ItemsAdapter.ClickListener{

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
                startActivity(Intent(applicationContext, RemindersActivity::class.java))
                overridePendingTransition(0, 0)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
//===========================================================================================================

    val imagesName = arrayOf(
        ItemsModal("Adobo", "1hr 5mins", R.drawable.lnd_01_adobo, "adobo", "Serves 4", "Lunch and Dinner"),
        ItemsModal("Arroz Caldo", "1hr", R.drawable.a_01_arroz_caldo, "arroz caldo", "Serves 6", "Breakfast"),
        ItemsModal("Biko", "45mins", R.drawable.des_01_biko, "biko", "Serves 6", "Desserts"),
        ItemsModal("Buko Pandan", "35 mins", R.drawable.des_02_buko_pandan, "buko pandan", "Serves 6", "Desserts"),
        ItemsModal("Bulalo", "2hrs 15mins", R.drawable.lnd_03_bulalo,"bulalo", "Serves 4", "Lunch and Dinner"),
        ItemsModal("Carioca", "30 mins", R.drawable.des_03_carioca, "carioca", "Serves 6", "Desserts"),
        ItemsModal("Chicksilog", "45 mins", R.drawable.a_02_chiksilog, "chicksilog", "Serves 4", "Breakfast"),
        ItemsModal("Cornsilog", "23 mins", R.drawable.a_03_corned_beef_silog, "cornsilog", "Serves 2", "Breakfast"),
        ItemsModal("Daing Na Bangus", "20 mins", R.drawable.a_04_daing_na_bangus, "daing na bangus", "Serves 2", "Breakfast"),
        ItemsModal("Dinuguan", "50 mins", R.drawable.lnd_04_dinuguan,"dinuguan", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Filipino Barbecue", "40 mins", R.drawable.lnd_02_barbecue, "filipino barbecue", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Filipino Fruit Salad", "30 mins", R.drawable.des_04_fruit_salad, "filipino fruit salad", "Serves 6", "Desserts"),
        ItemsModal("Ginataang Bilo Bilo", "1hr", R.drawable.des_05_bilo_bilo, "ginataang bilo bilo", "Serves 6", "Desserts"),
        ItemsModal("Ginisang Kamatis at Itlog", "15 mins", R.drawable.a_05_ginisang_kamatis, "ginisang kamatis at itlog", "Serves 6", "Breakfast"),
        ItemsModal("Halo Halo", "5 mins", R.drawable.des_06_halo_halo, "halo halo", "Serves 1", "Desserts"),
        ItemsModal("Hotsilog", "15 mins", R.drawable.a_06_hotsilog, "hotsilog", "Serves 1", "Breakfast"),
        ItemsModal("Kalderata", "2hrs 30mins", R.drawable.lnd_05_kaldereta,"kalderata", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Kare-kare", "45 mins", R.drawable.lnd_06_kare_kare,"kare-kare", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Leche Flan", "1hr 10mins", R.drawable.des_07_leche_flan, "leche flan", "Serves 3", "Desserts"),
        ItemsModal("Lechon", "1hr 15mins", R.drawable.lnd_07_lechon,"lechon", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Longsilog", "30 mins", R.drawable.a_07_longsilog, "longsilog", "Serves 2", "Breakfast"),
        ItemsModal("Lumpia", "40 mins", R.drawable.lnd_08_lumpia,"lumpia", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Mais Con Yelo", "10 mins", R.drawable.des_08_mais_con_yelo, "mais con yelo", "Serves 1", "Desserts"),
        ItemsModal("Maja Blanca", "35 mins", R.drawable.des_09_maja_blanca, "maja blanca", "Serves 6", "Desserts"),
        ItemsModal("Minatamis Na Saging", "25 mins", R.drawable.des_10_minatamis_na_saging, "minatamis na saging", "Serves 3", "Desserts"),
        ItemsModal("Pancit", "50 mins", R.drawable.lnd_09_pancit,"pancit", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Pinakbet", "35 mins", R.drawable.lnd_10_pinakbet,"pinakbet", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Sinigang", "35 mins", R.drawable.lnd_11_sinigang,"sinigang", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Sisig", "1hr 20mins", R.drawable.lnd_12_sisig,"sisig", "Serves 6", "Lunch and Dinner"),
        ItemsModal("Tapsilog", "35 mins", R.drawable.a_08_tapsilog, "tapsilog", "Serves 4", "Breakfast"),
        ItemsModal("Tortang Talong", "30 mins", R.drawable.a_09_tortang_talong, "tortang Talong", "Serves 2", "Breakfast"),
        ItemsModal("Tosilog", "45 mins", R.drawable.a_10_tosilog, "tosilog", "Serves 3", "Breakfast"),
        ItemsModal("Turon", "25 mins", R.drawable.des_11_turon, "turon", "Serves 6", "Desserts"),
        ItemsModal("Tusilog", "30 mins", R.drawable.a_11_tuyo_at_itlog, "tusilog", "Serves 4", "Breakfast"),
        ItemsModal("Ube Halaya", "1hr", R.drawable.des_12_ube_halaya, "ube halaya", "Serves 6", "Desserts")
    )

    val itemModalList = ArrayList<ItemsModal>()

    var itemsAdapter : ItemsAdapter? = null;

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

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
//        tv.text = "All Recipes" // ActionBar title text
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
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setSelectedItemId(R.id.mSearch)
//===========================================================================================================
    }
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



    override fun ClickedItem(itemsModal: ItemsModal) {
        Log.e("TAG", itemsModal.name);

        when(itemsModal.name){
            "Arroz Caldo" ->
                startActivity(Intent(this@Food, BrArrozCaldo::class.java))
            "Chicksilog" ->
                startActivity(Intent(this@Food, BrChiksilog::class.java))
            "Cornsilog" ->
                startActivity(Intent(this@Food, BrCornsilog::class.java))
            "Daing Na Bangus" ->
                startActivity(Intent(this@Food, BrDaingNaBangus::class.java))
            "Ginisang Kamatis at Itlog" ->
                startActivity(Intent(this@Food, BrGinisangKamatis::class.java))
            "Hotsilog" ->
                startActivity(Intent(this@Food, BrHotsilog::class.java))
            "Longsilog" ->
                startActivity(Intent(this@Food, BrLongsilog::class.java))
            "Tapsilog" ->
                startActivity(Intent(this@Food, BrTapsilog::class.java))
            "Tortang Talong" ->
                startActivity(Intent(this@Food, BrTortangTalong::class.java))
            "Tosilog" ->
                startActivity(Intent(this@Food, BrTosilog::class.java))
            "Tusilog" ->
                startActivity(Intent(this@Food, BrTusilog::class.java))

            "Adobo" ->
                startActivity(Intent(this@Food, LnAdobo::class.java))
            "Filipino Barbecue" ->
                startActivity(Intent(this@Food, LnBarbecue::class.java))
            "Bulalo" ->
                startActivity(Intent(this@Food, LnBulalo::class.java))
            "Dinuguan" ->
                startActivity(Intent(this@Food, LnDinuguan::class.java))
            "Kalderata" ->
                startActivity(Intent(this@Food, LnKalderata::class.java))
            "Kare-kare" ->
                startActivity(Intent(this@Food, LnKareKare::class.java))
            "Lechon" ->
                startActivity(Intent(this@Food, LnLechon::class.java))
            "Lumpia" ->
                startActivity(Intent(this@Food, LnLumpia::class.java))
            "Pancit" ->
                startActivity(Intent(this@Food, LnPancit::class.java))
            "Pinakbet" ->
                startActivity(Intent(this@Food, LnPinakbet::class.java))
            "Sinigang" ->
                startActivity(Intent(this@Food, LnSinigang::class.java))
            "Sisig" ->
                startActivity(Intent(this@Food, LnSisig::class.java))

            "Biko" ->
                startActivity(Intent(this@Food, DesBiko::class.java))
            "Buko Pandan" ->
                startActivity(Intent(this@Food, DesBukoPandan::class.java))
            "Carioca" ->
                startActivity(Intent(this@Food, DesCarioca::class.java))
            "Filipino Fruit Salad" ->
                startActivity(Intent(this@Food, DesFilipinoFruitSalad::class.java))
            "Ginataang Bilo Bilo" ->
                startActivity(Intent(this@Food, DesGinataangBiloBilo::class.java))
            "Halo Halo" ->
                startActivity(Intent(this@Food, DesHaloHalo::class.java))
            "Leche Flan" ->
                startActivity(Intent(this@Food, DesLecheFlan::class.java))
            "Mais Con Yelo" ->
                startActivity(Intent(this@Food, DesMaisConYelo::class.java))
            "Maja Blanca" ->
                startActivity(Intent(this@Food, DesMajaBlanca::class.java))
            "Minatamis Na Saging" ->
                startActivity(Intent(this@Food, DesMinatamisNaSaging::class.java))
            "Turon" ->
                startActivity(Intent(this@Food, DesTuron::class.java))
            "Ube Halaya" ->
                startActivity(Intent(this@Food, DesUbeHalaya::class.java))

            else -> {
                Toast.makeText(this, "No Action", Toast.LENGTH_LONG).show()
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
                startActivity(Intent(this, AboutApp::class.java))
                overridePendingTransition(0, 0)
                return true
            }

            R.id.tutorial -> {
                startActivity(Intent(this, TutorialScreen::class.java))
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