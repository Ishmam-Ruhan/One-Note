package com.example.exam11;


import android.app.TimePickerDialog;
import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Alarm extends Fragment implements TimePickerDialog.OnTimeSetListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Alarm() {
        // Required empty public constructor
    }

    private TextView timepick;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alarm, container, false);
        timepick=view.findViewById(R.id.time);
        Button cancel=view.findViewById(R.id.cancel);

        timepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker=new TimePicker("alarm");
                timePicker.show(getFragmentManager(),"timepicker");
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.fragment.app.Fragment fragment=new All();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag,fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        //timepick.setText(hourOfDay+" : "+minute);
    }
}