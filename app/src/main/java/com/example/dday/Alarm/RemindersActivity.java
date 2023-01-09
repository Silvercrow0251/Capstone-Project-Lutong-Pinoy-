package com.example.dday.Alarm;

import static android.view.View.GONE;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dday.AboutApp;
import com.example.dday.Calendar;
import com.example.dday.Food;
import com.example.dday.Notes;
import com.example.dday.Open;
import com.example.dday.R;
import com.example.dday.TutorialScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RemindersActivity extends AppCompatActivity implements ActionMode.Callback, ReminderInterface {

    private List<Reminder> reminderList = new ArrayList<>();
    private RecyclerView reminderRecyclerView;
    private ReminderAdapter reminderLAdapter;
    private ReminderDatabaseAdapter remindersDatabaseAdapter;
    private ActionMode rAActionMode;
    private boolean rAIsMultiSelect = false;
    private List<Integer> rASelectedPositions = new ArrayList<>();
    private FloatingActionButton rAActionButton;
    protected TextView rAESTitleTextView;
    protected TextView rAESContentTextView;
    protected LinearLayout rAESLinearLayout;
    View rARootLayout;

    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        //Status Bar Color
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        //Remove shadow under Action Bar
        getSupportActionBar().setElevation(0);

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
                        startActivity(new Intent(getApplicationContext(), Open.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.mSearch:
                        startActivity(new Intent(getApplicationContext(), Food.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.mNotes:
                        startActivity(new Intent(getApplicationContext(), Notes.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.mCalendar:
                        return true;
                }
                return false;
            }
        });














        Float elevation = 0.0f;
        getSupportActionBar().setElevation(elevation);

        rARootLayout = findViewById(R.id.ch_root_layout);

        remindersDatabaseAdapter = new ReminderDatabaseAdapter(this);
        remindersDatabaseAdapter.open();

        initializeReminderList();

        reminderRecyclerView = (RecyclerView) findViewById(R.id.reminders_recycler_view);
        rAESTitleTextView = findViewById(R.id.ra_empty_state_title_text_view);
        rAESContentTextView = findViewById(R.id.ra_empty_state_text_view);
        rAESLinearLayout = findViewById(R.id.ra_empty_state_linear_layout);

        reminderLAdapter = new ReminderAdapter(this, reminderList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        reminderRecyclerView.setLayoutManager(layoutManager);
        reminderRecyclerView.setItemAnimator(new DefaultItemAnimator());
        reminderRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, reminderRecyclerView, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (rAIsMultiSelect) {
                    multiSelect(position);
                }

                else {
                    openEditDialog(position);
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (!rAIsMultiSelect) {
                    rASelectedPositions = new ArrayList<>();
                    rAIsMultiSelect = true;

                    if (rAActionMode == null) {
                        rAActionButton.hide();
                        rAActionMode = startActionMode(RemindersActivity.this);
                    }
                }

                multiSelect(position);
            }
        }));
//        reminderRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        reminderRecyclerView.setAdapter(reminderLAdapter);

        setNextReminderAlarm();
        setRAEmptyState();

        rAActionButton = findViewById(R.id.new_reminder_fab);
        rAActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewReminderDialog();
            }
        });
    }

    private void openNewReminderDialog(){
        FragmentManager oNRFSD = getSupportFragmentManager();
        ReminderFSD newReminderFSD =  ReminderFSD.newInstance(0,"","","",0,0,false);
        FragmentTransaction transaction = oNRFSD.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.add(android.R.id.content, newReminderFSD).addToBackStack(null).commit();
    }

    //Disable back press
    long back_pressed;
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(RemindersActivity.this, RemindersActivity.class);
        startActivity(intent);

//        finishAffinity();
//        finish();

//        if (pressedTime + 2000 > System.currentTimeMillis()) {
//            super.onBackPressed();
//            finishAffinity();
//        } else {
//            Toast.makeText(getBaseContext(), "Press back again to exit app", Toast.LENGTH_SHORT).show();
//        }
//        pressedTime = System.currentTimeMillis();

    }

    private int getNextReminderAPosition(){
        int nRAPosition = 0;

        int i = 0;
        while (i >= 0 && i<=reminderList.size()-1){
            Reminder bReminder = reminderList.get(i);
            long nowTIM = getNowTIM();
            long bReminderTIM = bReminder.getReminderTIM();

            if(bReminderTIM > nowTIM){
                nRAPosition=i;
                i=reminderList.size();
            }

            ++i;
        }

        return nRAPosition;
    }

    private void setNextReminderAlarm() {

        if (reminderList != null) {

            if (reminderList.size() != 0) {

                int nRAPosition = getNextReminderAPosition();

                Reminder latestReminder = reminderList.get(nRAPosition);

                long nowTIM = getNowTIM();
                long lReminderTIM = latestReminder.getReminderTIM();


                if (lReminderTIM >= nowTIM) {

                    Intent rARIntent = new Intent(this, NotificationReceiver.class);
                    rARIntent.putExtra("lReminderTitle", latestReminder.getReminderTitle());
                    PendingIntent rARPIntent = PendingIntent.getBroadcast(this, 100, rARIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager rAAlarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);


                    int SDK_INT = Build.VERSION.SDK_INT;
                    if (SDK_INT < Build.VERSION_CODES.KITKAT) {
                        assert rAAlarmManager != null;
                        rAAlarmManager.set(AlarmManager.RTC_WAKEUP, lReminderTIM, rARPIntent);

                    } else if (SDK_INT >= Build.VERSION_CODES.KITKAT && SDK_INT < Build.VERSION_CODES.M) {
                        assert rAAlarmManager != null;
                        rAAlarmManager.setExact(AlarmManager.RTC_WAKEUP, lReminderTIM, rARPIntent);

                    } else if (SDK_INT >= Build.VERSION_CODES.M) {
                        assert rAAlarmManager != null;
                        rAAlarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, lReminderTIM, rARPIntent);
                    }


                }


            }

        }

    }

    private void openEditDialog(int reminderPosition){

        Reminder selectedReminder = reminderList.get(reminderPosition);
        int reminderId = selectedReminder.getReminderId();
        String reminderTitle = selectedReminder.getReminderTitle();
        String reminderDOF = selectedReminder.getReminderDOF();
        String reminderTOF = selectedReminder.getReminderTOF();
        long reminderTIM = selectedReminder.getReminderTIM();

        FragmentManager openERFSD = getSupportFragmentManager();
        ReminderFSD editReminderFSD =  ReminderFSD.newInstance(reminderId,reminderTitle,reminderDOF,reminderTOF,reminderTIM,reminderPosition,true);
        FragmentTransaction oEditRFSDTransaction = openERFSD.beginTransaction();
        oEditRFSDTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        oEditRFSDTransaction.add(android.R.id.content, editReminderFSD).addToBackStack(null).commit();
    }

    private int getNewReminderAddPosition(Reminder newReminder) {

        List<Reminder> remindersSortList = reminderList;
        remindersSortList.add(newReminder);

        ReminderComparator reminderComparator = new ReminderComparator();
        Collections.sort(remindersSortList, reminderComparator);

        int newReminderPosition = 0;

        int i = 0;
        while (i >= 0 && i <= (remindersSortList.size() - 1)) {

            int reminderId = reminderList.get(i).getReminderId();

            if (reminderId == newReminder.getReminderId()) {
                newReminderPosition = i;
            }

            ++i;
        }

        return newReminderPosition;
    }

    public void addReminder(final String reminderTitle, final String reminderDTS, final long reminderTIM) {
        final int[] newReminderId = new int[1];

        Runnable addReminderRunnable = new Runnable() {
            @Override
            public void run() {
                newReminderId[0] = remindersDatabaseAdapter.createReminder(reminderTitle, reminderDTS);

            }
        };
        Thread addReminderThread = new Thread(addReminderRunnable);
        addReminderThread.start();
        try {
            addReminderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int reminderId = newReminderId[0];
        String reminderDOF = reminderDTS.substring(7, 17);
        String reminderTOF = reminderDTS.substring(0, 5);
        Reminder newReminder = new Reminder(reminderId, reminderTitle, reminderDOF, reminderTOF, reminderTIM);

        addReminderToList(newReminder);

        setRAEmptyState();
        setNextReminderAlarm();

//        Snackbar.make(rARootLayout, R.string.reminder_set_c,Snackbar.LENGTH_SHORT).show();
        Toast.makeText(this, R.string.reminder_set_c, Toast.LENGTH_SHORT).show();
    }

    public void updateReminder(final String reminderTitle, final String reminderDTS, final long reminderTIM, final int reminderId, final int reminderPosition) {
        Runnable updateReminderRunnable = new Runnable() {
            @Override
            public void run() {
                remindersDatabaseAdapter.updateReminder(reminderId,reminderTitle,reminderDTS);
            }
        };
        Thread updateReminderThread = new Thread(updateReminderRunnable);
        updateReminderThread.start();
        try {
            updateReminderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        updateReminderListItem(reminderTitle,reminderDTS,reminderTIM,reminderPosition);

        setNextReminderAlarm();

//        Snackbar.make(rARootLayout, "Reminder Updated",Snackbar.LENGTH_SHORT).show();
        Toast.makeText(this, "Meal Plan Updated", Toast.LENGTH_SHORT).show();
    }

    public void hideActionBar() {
        getSupportActionBar().hide();
    }

    public void showActionBar() {
        getSupportActionBar().show();
    }

    private void multiSelect(int position) {
        Reminder selectedReminder = reminderLAdapter.getItem(position);
        if (selectedReminder != null) {
            if (rAActionMode != null) {
                int previousPosition = -1;
                if (rASelectedPositions.size() > 0) {
                    previousPosition = rASelectedPositions.get(0);
                }
                rASelectedPositions.clear();
                rASelectedPositions.add(position);

                reminderLAdapter.setSelectedPositions(previousPosition, rASelectedPositions);
            }
        }
    }

    private void initializeReminderList() {
        Runnable initializeRListRunnable = new Runnable() {
            @Override
            public void run() {
                reminderList = remindersDatabaseAdapter.fetchReminders();
            }
        };
        Thread initializeRListThread = new Thread(initializeRListRunnable);
        initializeRListThread.start();
        try {
            initializeRListThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addReminderToList(Reminder newReminder) {
        int newReminderPosition = getNewReminderAddPosition(newReminder);
        reminderLAdapter.notifyDataSetChanged();

        if(newReminderPosition>=0 && newReminderPosition <= (reminderList.size()-1)){
            reminderRecyclerView.scrollToPosition(newReminderPosition);
        }

    }

    private long getNowTIM() {
        Date nowDate = new Date();
        long nowTIM = nowDate.getTime();
        return nowTIM;
    }

    private void deleteReminderListItem(int reminderPosition) {
        reminderList.remove(reminderPosition);
        reminderLAdapter.notifyItemRemoved(reminderPosition);
        setNextReminderAlarm();
    }

    private void updateReminderListItem(String reminderTitle, String reminderDTS, long reminderTIM, int reminderPosition) {
        String reminderDOF = reminderDTS.substring(7,17);
        String reminderTOF = reminderDTS.substring(0,5);

        reminderList.get(reminderPosition).setReminderTitle(reminderTitle);
        reminderList.get(reminderPosition).setReminderDOF(reminderDOF);
        reminderList.get(reminderPosition).setReminderTOF(reminderTOF);
        reminderList.get(reminderPosition).setReminderTIM(reminderTIM);

        ReminderComparator reminderComparator = new ReminderComparator();
        Collections.sort(reminderList,reminderComparator);

        reminderLAdapter.notifyDataSetChanged();
    }

    private void setRAEmptyState() {

        if (reminderList.size() == 0) {

            if (reminderRecyclerView.getVisibility() == View.VISIBLE) {
                reminderRecyclerView.setVisibility(GONE);
            }

            if (rAESLinearLayout.getVisibility() == GONE) {
                rAESLinearLayout.setVisibility(View.VISIBLE);

//                String rAESTitle = getResources().getString(R.string.ra_empty_state_title);
//                String rAESText = getResources().getString(R.string.ra_empty_state_text);
//
//                rAESTitleTextView.setText(rAESTitle);
//                rAESContentTextView.setText(rAESText);
            }


        } else {

            if (rAESLinearLayout.getVisibility() == View.VISIBLE) {
                rAESLinearLayout.setVisibility(GONE);
            }

            if (reminderRecyclerView.getVisibility() == GONE) {
                reminderRecyclerView.setVisibility(View.VISIBLE);
            }

        }


    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.ra_action_view_menu, menu);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ra_action_copy:
                if (rASelectedPositions.size() > 0) {

                    String selectedReminderTitle = reminderList.get(rASelectedPositions.get(0)).getReminderTitle();

                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("l", selectedReminderTitle);
                    clipboard.setPrimaryClip(clip);
                }

                rAActionMode.finish();

                String rACopyConfirmationText = getResources().getString(R.string.ra_copy_confirmation_text);
//                Snackbar.make(rARootLayout,rACopyConfirmationText,Snackbar.LENGTH_SHORT).show();
                Toast.makeText(this, R.string.ra_copy_confirmation_text, Toast.LENGTH_SHORT).show();

                return true;

            case R.id.ra_action_edit:

                if (rASelectedPositions.size() > 0) {
                    int selectedPosition = rASelectedPositions.get(0);
                    openEditDialog(selectedPosition);
                }

                rAActionMode.finish();
                return true;


            case R.id.ra_action_delete:
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                            RemindersActivity.this, R.style.BottomSheetStyle
                    );
                    View bottomSheetView = LayoutInflater.from(getApplicationContext())
                            .inflate(
                                    R.layout.dialog_delete_meal_plan,
                                    (LinearLayout) findViewById(R.id.bottomSheetContainer)
                            );
                    bottomSheetView.findViewById(R.id.delete_yes).setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (rASelectedPositions.size() > 0) {

                                int selectedPosition = rASelectedPositions.get(0);
                                final int sReminderId = reminderList.get(selectedPosition).getReminderId();

                                Runnable deleteRRunnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        remindersDatabaseAdapter.deleteReminder(sReminderId);
                                    }
                                };
                                Thread deleteRThread = new Thread(deleteRRunnable);
                                deleteRThread.start();
                                try {
                                    deleteRThread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                deleteReminderListItem(selectedPosition);
                                setRAEmptyState();
                            }

                            bottomSheetDialog.dismiss();

                            rAActionMode.finish();
                        }
                    });
                    bottomSheetView.findViewById(R.id.delete_no).setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                            rAActionMode.finish();
                        }
                    });
                    bottomSheetDialog.setContentView(bottomSheetView);
                    bottomSheetDialog.show();


















//                AlertDialog.Builder deleteRDialogBuilder = new AlertDialog.Builder(this);
//                deleteRDialogBuilder.setTitle(getResources().getString(R.string.delete_reminder_dialog_title));
//                deleteRDialogBuilder.setMessage(getResources().getString(R.string.delete_reminder_dialog_message));
//                deleteRDialogBuilder.setNegativeButton(getResources().getString(R.string.del_reminder_dialog_negative_button), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                        rAActionMode.finish();
//                    }
//                });
//
//                deleteRDialogBuilder.setPositiveButton(getResources().getString(R.string.delete_reminder_dialog_positive_button), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        if (rASelectedPositions.size() > 0) {
//
//                            int selectedPosition = rASelectedPositions.get(0);
//                            final int sReminderId = reminderList.get(selectedPosition).getReminderId();
//
//                            Runnable deleteRRunnable = new Runnable() {
//                                @Override
//                                public void run() {
//                                    remindersDatabaseAdapter.deleteReminder(sReminderId);
//                                }
//                            };
//                            Thread deleteRThread = new Thread(deleteRRunnable);
//                            deleteRThread.start();
//                            try {
//                                deleteRThread.join();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//
//                            deleteReminderListItem(selectedPosition);
//                            setRAEmptyState();
//                        }
//
//                        dialogInterface.dismiss();
//
//                        rAActionMode.finish();
//                    }
//                });
//                deleteRDialogBuilder.create().show();

                return true;


            default:

        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

        rAActionButton.show();
        rAActionMode = null;
        rAIsMultiSelect = false;

        int previousPosition = -1;
        if (rASelectedPositions.size() > 0) {
            previousPosition = rASelectedPositions.get(0);
        }
        rASelectedPositions = new ArrayList<>();

        reminderLAdapter.setSelectedPositions(previousPosition, new ArrayList<Integer>());
    }

    protected static class ReminderComparator implements Comparator<Reminder> {

        public int compare(Reminder reminderOne, Reminder reminderTwo) {
            if (reminderOne.getReminderTIM() == reminderTwo.getReminderTIM()) {
                return 0;
            } else if (reminderOne.getReminderTIM() > reminderTwo.getReminderTIM()) {
                return 1;
            } else {
                return -1;
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_v2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), TutorialScreen.class));
                overridePendingTransition(0,0);
                return true;

            case R.id.tutorial:
                startActivity(new Intent(getApplicationContext(), AboutApp.class));
                overridePendingTransition(0,0);
                return true;

            case R.id.share:
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


