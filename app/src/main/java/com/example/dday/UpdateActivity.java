package com.example.dday;

//import static com.example.dday.App.CHANNEL_1_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextDate;

    EditText Add_Title_calendar, Update_Time_calendar, Add_Time_calendar;
//    Button delete_button;
    FloatingActionButton update_button;

    String id, title, author, pages;

    //Date and Time Picker
    private static final String TAG = "AddActivity";
    ImageView add_date2, add_time2;
    //    Time Picker 12 Hour Format
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    //Add to Google Calendar
    EditText Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.Update_Title_calendar);
        editTextDate = findViewById(R.id.Update_Date_calendar);

        //Add to Google Calendar
        Title = findViewById(R.id.Update_Title_calendar);

        //Status Bar Color
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        getSupportActionBar().setElevation(0);

        Add_Title_calendar = findViewById(R.id.Update_Title_calendar);
        Update_Time_calendar = findViewById(R.id.Update_Date_calendar);
        Add_Time_calendar = findViewById(R.id.Update_Time_calendar);
        update_button = findViewById(R.id.update_button);





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





//        delete_button = findViewById(R.id.delete_button);
//                delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                        UpdateActivity.this, R.style.BottomSheetStyle
//                );
//                View bottomSheetView = LayoutInflater.from(getApplicationContext())
//                        .inflate(
//                                R.layout.dialog_delete_calendar,
//                                (LinearLayout) findViewById(R.id.bottomSheetContainer)
//                        );
//                bottomSheetView.findViewById(R.id.delete_yes).setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                        myDB.deleteOneRow(id);
//                        finish();
//                    }
//                });
//                bottomSheetView.findViewById(R.id.delete_no).setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//            }
//        });

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setTitle(title);
//        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = Add_Title_calendar.getText().toString().trim();
                author = Update_Time_calendar.getText().toString().trim();
                pages = Add_Time_calendar.getText().toString().trim();
                myDB.updateData(id, title, author, pages);

                finish();
//                Intent intent = new Intent(UpdateActivity.this, Calendar.class);
//                startActivity(intent);
            }
        });
//        delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                confirmDialog();
//            }
//        });

        //Date and Time Picker
        add_date2 = findViewById(R.id.add_date2);
        add_time2 = findViewById(R.id.add_time2);
        Update_Time_calendar = findViewById(R.id.Update_Date_calendar);
        Add_Time_calendar = findViewById(R.id.Update_Time_calendar);

        add_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        add_time2.setOnClickListener(new View.OnClickListener() {
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

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setting Intent Data
            Add_Title_calendar.setText(title);
            Update_Time_calendar.setText(author);
            Add_Time_calendar.setText(pages);
            Log.d("stev", title+" "+author+" "+pages);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }



















//    void confirmDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete " + title + " ?");
//        builder.setMessage("Are you sure you want to delete " + title + " ?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                myDB.deleteOneRow(id);
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
//
//    }

//    void confirmDialog() {
//        delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetStyle_v2 = new BottomSheetDialog(
//                        UpdateActivity.this, R.style.BottomSheetDialogTheme
//                );
//                View bottomSheetView = LayoutInflater.from(getApplicationContext())
//                        .inflate(
//                                R.layout.dialog_delete_calendar,
//                                (LinearLayout) findViewById(R.id.bottomSheetContainer)
//                        );
//                bottomSheetStyle_v2.findViewById(R.id.delete_yes).setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                        myDB.deleteOneRow(id);
//                        finish();
//                    }
//                });
//                bottomSheetStyle_v2.setContentView(bottomSheetView);
//                bottomSheetStyle_v2.show();
//            }
//        });
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

                Update_Time_calendar.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();




    }

    private void handleTimeButton() {
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(UpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                            UpdateActivity.this, R.style.BottomSheetStyle
                    );
                    View bottomSheetView = LayoutInflater.from(getApplicationContext())
                            .inflate(
                                    R.layout.dialog_delete_schedule,
                                    (LinearLayout) findViewById(R.id.bottomSheetContainer)
                            );
                    bottomSheetView.findViewById(R.id.delete_yes).setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                            myDB.deleteOneRow(id);
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
        return super.onOptionsItemSelected(item);
    }

    //Add to Google Calendar
    public void AddCalendarEvent(View view) {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_EDIT);
        i.setType("vnd.android.cursor.item/event");
        i.putExtra("beginTime", calendarEvent.getTimeInMillis());
        i.putExtra("allDay", true);
        i.putExtra("rule", "FREQ=YEARLY");
        i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
        i.putExtra("title", Title.getText().toString());
        startActivity(i);
    }

}