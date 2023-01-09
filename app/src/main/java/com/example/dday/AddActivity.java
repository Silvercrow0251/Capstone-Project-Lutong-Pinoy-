package com.example.dday;

//import static com.example.dday.App.CHANNEL_1_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextDate;

    EditText Add_Title_calendar, Add_Date_calendar, Add_Time_calendar;
    FloatingActionButton add_button;


    //Date and Time Picker
    private static final String TAG = "AddActivity";
    ImageView add_date, add_time;
    //    Time Picker 12 Hour Format
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.Add_Title_calendar);
        editTextDate = findViewById(R.id.Add_Date_calendar);

        //Status Bar Color
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        //Remove shadow under Action Bar
        getSupportActionBar().setElevation(0);


        Add_Title_calendar = findViewById(R.id.Add_Title_calendar);
        Add_Date_calendar = findViewById(R.id.Add_Date_calendar);
        Add_Time_calendar = findViewById(R.id.Add_Time_calendar);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(Add_Title_calendar.getText().toString().trim(),
                        Add_Date_calendar.getText().toString().trim(),
                        Add_Time_calendar.getText().toString().trim());
                        finish();
                Intent intent = new Intent(AddActivity.this, com.example.dday.Calendar.class);
                startActivity(intent);
//                finish();
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























//Date and Time Picker
        add_date = findViewById(R.id.add_date);
        add_time = findViewById(R.id.add_time);
        Add_Date_calendar = findViewById(R.id.Add_Date_calendar);
        Add_Time_calendar = findViewById(R.id.Add_Time_calendar);

        add_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        add_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

    }
    //Notification Alert
//    public void sendOnChannel1(View v) {
//        String title = editTextTitle.getText().toString();
//        String date = editTextDate.getText().toString();
//
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
//                .setSmallIcon(R.drawable.ic_notification)
//                .setContentTitle(title)
//                .setContentText(date)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                .build();
//
//        notificationManager.notify(1, notification);
//    }

    private void handleDateButton() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int YEAR = calendar.get(java.util.Calendar.YEAR);
        int MONTH = calendar.get(java.util.Calendar.MONTH);
        int DATE = calendar.get(java.util.Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(java.util.Calendar.YEAR, year);
                calendar1.set(java.util.Calendar.MONTH, month);
                calendar1.set(java.util.Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE MMM d, yyyy", calendar1).toString();

                Add_Date_calendar.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();




    }

    private void handleTimeButton() {
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                if (hourOfDay >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                boolean isPM = (hourOfDay >= 12);
                Add_Time_calendar.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minutes, isPM ? "PM" : "AM"));
            }
        }, currentHour, currentMinute, false);

        timePickerDialog.show();

    }
}

