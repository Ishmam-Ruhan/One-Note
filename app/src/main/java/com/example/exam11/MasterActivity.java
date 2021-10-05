package com.example.exam11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.DialogFragment;

//import android.app.DialogFragment;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.Calendar;

public class MasterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private DrawerLayout drawer;
    private Toolbar top;
    private NavigationView navigation;
    private FirebaseAuth mAuth;
    private Uri imageUri;
    private FloatingActionsMenu menu;
    private Dialog dialog;

    private Button allb,remb,noteb,taskb,alarmb;

    DatabaseReference dbRef;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_master);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        dbRef= FirebaseDatabase.getInstance().getReference().child(user.getUid());
        //---------Hook---------------

        drawer = findViewById(R.id.drawerlayout);
        top = findViewById(R.id.toolbar);
        navigation = findViewById(R.id.navigationWindow);
        menu=findViewById(R.id.floatmenu);

        setNavigation();
        setUserInfoToHeader();

        allb=findViewById(R.id.all);
        taskb=findViewById(R.id.task);
        remb=findViewById(R.id.rem);
        alarmb=findViewById(R.id.alarm);
        noteb=findViewById(R.id.notes);

        allb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));


    }

    private void setUserInfoToHeader() {
        View view = navigation.getHeaderView(0);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();
        String email = user.getEmail();
        String uid = user.getUid();
        String modUid = uid.substring(0, 18);
        String s = "ID:  ";
        String publish = s + modUid;
        imageUri=user.getPhotoUrl();

        String[] arr=email.split("@");




        ((TextView) view.findViewById(R.id.userID)).setText(publish);
        ((TextView) view.findViewById(R.id.userName)).setText(arr[0]);
        Picasso.with(this).load(imageUri).into((ImageView)view.findViewById(R.id.userImage));

    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.nav_optn_tasks:
                allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                remb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));
                alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));

                fragment= new TaskFragment();
                fragmentManager= getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag,fragment);
                fragmentTransaction.commit();

                break;

            case R.id.nav_optn_notes:
                allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                remb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));


                fragment= new NoteFragment();
                fragmentManager= getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag,fragment);
                fragmentTransaction.commit();

                break;

            case R.id.nav_optn_alarms:
                allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                remb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));
                noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));

                fragment= new AlarmFragment();
                fragmentManager= getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag,fragment);
                fragmentTransaction.commit();

                break;

            case R.id.nav_optn_Facebook:
                break;

            case R.id.nav_optn_Whatsapp:
                break;

            case R.id.nav_optn_Telegram:
                break;

            case R.id.nav_optn_remind:
                allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                remb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));
                taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
                noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));

                fragment= new RemaindFragment();
                fragmentManager= getSupportFragmentManager();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag,fragment);
                fragmentTransaction.commit();

                break;

            case R.id.nav_optn_Logout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(MasterActivity.this, MainActivity.class));
                break;

        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    private void setNavigation() {
        //-----------Toolbar------------------

        setSupportActionBar(top);


        //----------------Navigation Drawer Menu--------


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MasterActivity.this, drawer, top, R.string.navStart, R.string.navEnd);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //------make the navigation selectable-------
        navigation.bringToFront();
        navigation.setNavigationItemSelectedListener(this);
    }

    public boolean isAlarm=false,isRemainder=false,isTask=false,to=false,from=false;
    public int remHour,remMnt;
    public int alarmHour,alarmMinute;
    public int taskHour,taskMinute;
    public int toHour,toMinute,fromHour,fromMinute;

    public void noteFunction(View view) {
        menu.collapse();
        dialog=new Dialog(MasterActivity.this);
        dialog.setContentView(R.layout.success_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        Button cancel=dialog.findViewById(R.id.ok);
        Button save=dialog.findViewById(R.id.ok2);
        final EditText msg=dialog.findViewById(R.id.msg);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---------------Database Work Gone Here------------//
                if(!msg.getText().toString().trim().isEmpty()){
                    NoteObject note=new NoteObject(msg.getText().toString().trim(),1);
                    try {
                        dbRef.push().setValue(note);
                        Toast.makeText(MasterActivity.this, "Note Added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }catch (Exception e){
                        Toast.makeText(MasterActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(MasterActivity.this, "Write Something!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
    public TextView remTimeview;
    boolean isClockClick=false;
    String date224;
    public void remFunction(View view) {
        menu.collapse();
        dialog=new Dialog(MasterActivity.this);
        dialog.setContentView(R.layout.remainder_dialogue);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        Button cancel=dialog.findViewById(R.id.ok);
        Button save=dialog.findViewById(R.id.ok2);
        remTimeview=dialog.findViewById(R.id.timeview);
        TextView clock=dialog.findViewById(R.id.selectTime);
        final EditText msg=dialog.findViewById(R.id.msg);



        Calendar c=Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int mnt = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        date224=day+"/"+month+"/"+year;

        remTimeview.setText(hour+" : "+mnt);

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClockClick=true;
                DialogFragment timePicker=new TimePicker("alarm");
                timePicker.show(getSupportFragmentManager(),"time picker");
                isRemainder=true;
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isClockClick){
                    Toast.makeText(MasterActivity.this, "Select Time!", Toast.LENGTH_SHORT).show();
                }else {

                    //---------------Database Work Gone Here------------//
                    RemObject remObject = new RemObject(msg.getText().toString().trim(), remHour, remMnt, date224,4);
                    try {
                        dbRef.push().setValue(remObject);
                        //Toast.makeText(MasterActivity.this, remHour+" : "+remMnt, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MasterActivity.this, "Reminder Added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } catch (Exception e) {
                        Toast.makeText(MasterActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.show();
    }

    public TextView toText,fromText,calenderShow;
    boolean isCalanderClick=false,isClickClockFrom=false,isClickClockTo=false;
    public void taskFunction(View view) {
        menu.collapse();
        dialog=new Dialog(MasterActivity.this);
        dialog.setContentView(R.layout.task_dialogue);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        Button cancel=dialog.findViewById(R.id.ok);
        Button save=dialog.findViewById(R.id.ok2);
        remTimeview=dialog.findViewById(R.id.timeview);

        fromText=dialog.findViewById(R.id.timeviewfrom);
        TextView fromSelectTime=dialog.findViewById(R.id.selectTimeFrom);
        toText=dialog.findViewById(R.id.timeviewTo);
        TextView toSelectTime=dialog.findViewById(R.id.selectTimeTo);
        calenderShow=dialog.findViewById(R.id.calenderView);
        TextView calenderPicker=dialog.findViewById(R.id.calender);
        final EditText msg=dialog.findViewById(R.id.msg);

        Calendar c=Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int mnt = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        fromText.setText(hour+" : "+mnt);
        toText.setText(hour+" : "+mnt);
        calenderShow.setText(day+" / "+month+" / "+year);

        calenderPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCalanderClick=true;
                DialogFragment datePicker=new com.example.exam11.DatePicker();
                datePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        fromSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickClockFrom=true;
                DialogFragment timePicker=new TimePicker("alarm");
                timePicker.show(getSupportFragmentManager(),"time picker");
                isTask=true;
                from=true;
            }
        });

        toSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickClockTo=true;
                DialogFragment timePicker=new TimePicker("alarm");
                timePicker.show(getSupportFragmentManager(),"time picker");
                isTask=true;
                to=true;
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---------------Database Work Gone Here------------//

                if(!isCalanderClick){
                    Toast.makeText(MasterActivity.this, "Select Calendar!", Toast.LENGTH_SHORT).show();
                }else{
                    if(!isClickClockFrom){
                        Toast.makeText(MasterActivity.this, "Select From Time!", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!isClickClockTo){
                            Toast.makeText(MasterActivity.this, "Select To Time!", Toast.LENGTH_SHORT).show();
                        }else{
                            TaskObject taskObject=new TaskObject(msg.getText().toString().trim(),fromHour,fromMinute,toHour,toMinute,calendarDay,calendarMonth,calendarYear,calendarDay+"/"+calendarMonth+"/"+calendarYear,3);
                            try {
                                dbRef.push().setValue(taskObject);
                                Toast.makeText(MasterActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }catch (Exception e){
                                Toast.makeText(MasterActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });

        dialog.show();
    }

    public void alarmFunction(View view) {
        menu.collapse();
        DialogFragment timePicker=new TimePicker("alarm");
        timePicker.show(getSupportFragmentManager(),"time picker");
        isAlarm=true;
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        if(isAlarm){
            isAlarm=false;
            alarmWork(hourOfDay,minute);
        }
        else if(isRemainder){
            isRemainder=false;
            remainderWork(hourOfDay,minute);
            remHour=hourOfDay;
            remMnt=minute;
        }
        else if(isTask && to){
            isTask=false;
            to=false;
            toHour=hourOfDay;
            toMinute=minute;
            toText.setText(hourOfDay+" : "+minute);
        }
        else if(isTask && from){
            isTask=false;
            from=false;
            fromHour=hourOfDay;
            fromMinute=minute;
            fromText.setText(hourOfDay+" : "+minute);
        }
    }

    int calendarDay,calendarMonth,calendarYear;
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calenderShow.setText(dayOfMonth+" / "+month+" / "+year);
        calendarDay=dayOfMonth;
        calendarMonth=month;
        calendarYear=year;
    }

    private void alarmWork(int hour, int minute) {
        //-----------Alarm  Goes Here-------------//
        Calendar c=Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+month+"/"+year;
        alarmHour=hour;
        alarmMinute=minute;
        AlarmObject alarmObject=new AlarmObject(alarmHour,alarmMinute,date,2);
        try {
            dbRef.push().setValue(alarmObject);
            Toast.makeText(MasterActivity.this, "Alarm Added", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }catch (Exception e){
            Toast.makeText(MasterActivity.this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    private void remainderWork(int hour, int minute) {
        //-----------Remainder  Goes Here-------------//
        remHour=hour;
        remMnt=minute;
        remTimeview.setText(remHour+" : "+remMnt);
    }

    private void taskWork(int hour, int minute) {
        //-----------Task  Goes Here-------------//
        taskHour=hour;
        taskMinute=minute;
    }

    androidx.fragment.app.Fragment fragment;
    androidx.fragment.app.FragmentManager fragmentManager;
    androidx.fragment.app.FragmentTransaction fragmentTransaction;

    public void all(View view) {
        allb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));
        remb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));

        fragment= new All();
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
        fragmentTransaction.commit();
    }

    public void allNotes(View view) {
        allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        remb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));


        fragment= new NoteFragment();
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
        fragmentTransaction.commit();

    }

    public void allAlarms(View view) {
        allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        remb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));
        noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));

        fragment= new AlarmFragment();
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
        fragmentTransaction.commit();
    }

    public void allReminds(View view) {
        allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        remb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));
        taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));

        fragment= new RemaindFragment();
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
        fragmentTransaction.commit();
    }

    public void allTasks(View view) {
        allb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        remb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        taskb.setTextColor(getApplication().getResources().getColor(R.color.colorDeepGreen));
        alarmb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));
        noteb.setTextColor(getApplication().getResources().getColor(R.color.colorDark));

        fragment= new TaskFragment();
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
        fragmentTransaction.commit();
    }
}
