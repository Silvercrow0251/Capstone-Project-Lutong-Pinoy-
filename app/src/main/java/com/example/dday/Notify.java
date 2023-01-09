package com.example.dday;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
//import static com.example.dday.App.CHANNEL_1_ID;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notify extends AppCompatActivity {
//    private NotificationManagerCompat notificationManager;
//    private EditText editTextTitle;
//    private EditText editTextDate;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_notify);
//
//        notificationManager = NotificationManagerCompat.from(this);
//
//        editTextTitle = findViewById(R.id.Add_Title_calendar);
//        editTextDate = findViewById(R.id.Add_Date_calendar);
//    }
//
//    public void sendOnChannel1(View v) {
//        String title = editTextTitle.getText().toString();
//        String date = editTextDate.getText().toString();
//
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
//                .setSmallIcon(R.drawable.ic_cook)
//                .setContentTitle(title)
//                .setContentText(date)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                .build();
//
//        notificationManager.notify(1, notification);
//    }
}
