package com.example.exam11;

import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Note extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Note() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_note, container, false);

        Button save=view.findViewById(R.id.save);
        Button cancel=view.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText note=view.findViewById(R.id.notetxt);
                String text=note.getText().toString().trim();
                if(text.isEmpty()){
                    Toast.makeText(getActivity(),"Please Write Something!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "All Set! Next Step", Toast.LENGTH_SHORT).show();
                }
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
}