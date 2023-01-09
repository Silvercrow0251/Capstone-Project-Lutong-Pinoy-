package com.example.dday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> Calendar_Card_ID, Calendar_Card_Title, Calendar_Card_Date, Calendar_Card_Time;
    CustomAdapter customAdapter;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

//        ActionBar actionBar = getSupportActionBar();
//        TextView tv = new TextView(getApplicationContext());
//        Typeface typeface = ResourcesCompat.getFont(this, R.font.poppins);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
//                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
//        tv.setLayoutParams(lp);
//        tv.setText("Meal Planner"); // ActionBar title text
//        tv.setTextSize(20);
//        tv.setTextColor(Color.parseColor("#fc6011"));
//        tv.setTypeface(typeface, typeface.BOLD);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(tv);

        //Status Bar Color
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        //Remove shadow under Action Bar
        getSupportActionBar().setElevation(0);

        //Swipe to Refresh Recyclerview
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                startActivity(getIntent());
                customAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.mCalendar);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.mHome:
                        startActivity(new Intent(getApplicationContext(),Open.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.mSearch:
                        startActivity(new Intent(getApplicationContext(),Food.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.mNotes:
                        startActivity(new Intent(getApplicationContext(),Notes.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.mCalendar:
                        return true;
                }
                return false;
            }
        });












        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(Calendar.this);
        Calendar_Card_ID = new ArrayList<>();
        Calendar_Card_Title = new ArrayList<>();
        Calendar_Card_Date = new ArrayList<>();
        Calendar_Card_Time = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(Calendar.this,this, Calendar_Card_ID, Calendar_Card_Title, Calendar_Card_Date,
                Calendar_Card_Time);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Calendar.this));

    }

    long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
//            super.onBackPressed();
            finishAffinity();
        }
        else{
            Toast.makeText(getBaseContext(),
                            "Press back again to exit app", Toast.LENGTH_SHORT)
                    .show();
        }
        back_pressed = System.currentTimeMillis();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                Calendar_Card_ID.add(cursor.getString(0));
                Calendar_Card_Title.add(cursor.getString(1));
                Calendar_Card_Date.add(cursor.getString(2));
                Calendar_Card_Time.add(cursor.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.my_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.delete_all){
//            confirmDialog();
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    void confirmDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete All?");
//        builder.setMessage("Are you sure you want to delete all Data?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(Calendar.this);
//                myDB.deleteAllData();
//                //Refresh Activity
//                Intent intent = new Intent(Calendar.this, Calendar.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        builder.create().show();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_delete){
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    Calendar.this, R.style.BottomSheetStyle
            );
            View bottomSheetView = LayoutInflater.from(getApplicationContext())
                    .inflate(
                            R.layout.dialog_delete_all_schedule,
                            (LinearLayout) findViewById(R.id.bottomSheetContainer)
                    );
            bottomSheetView.findViewById(R.id.delete_yes).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(Calendar.this);
                    myDB.deleteAllData();
                    //Refresh Activity
                    Intent intent = new Intent(Calendar.this, Calendar.class);
                    startActivity(intent);
                    finish();
                }
            });
            bottomSheetView.findViewById(R.id.delete_no).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        }


        if(item.getItemId() == R.id.about){
                startActivity(new Intent(getApplicationContext(), AboutApp.class));
                overridePendingTransition(0,0);
                return true;
        }
        if(item.getItemId() == R.id.tutorial){
            startActivity(new Intent(getApplicationContext(), TutorialScreen.class));
            overridePendingTransition(0,0);
            return true;
        }
        if(item.getItemId() == R.id.share){
            shareApp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void shareApp(){
        Intent sAIntent = new Intent();
        sAIntent.setAction(Intent.ACTION_SEND);
        sAIntent.putExtra(Intent.EXTRA_TEXT,"Lutong Pinoy is a mobile Filipino app for meal planning and meal preparation. Get it from: https://play.google.com/store/apps/details?id="+getPackageName());
        sAIntent.setType("text/plain");
        Intent.createChooser(sAIntent,"Share via");
        startActivity(sAIntent);
    }

}