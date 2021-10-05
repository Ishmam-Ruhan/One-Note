package com.example.exam11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RemaindFragment extends Fragment {


    public RemaindFragment() {
        // Required empty public constructor
    }


    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    ArrayList<Mirror> list;
    ArrayList<String> keys;
    CustomAdaptarRemind adaptar;
    boolean test=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_remaind, container, false);

        list=new ArrayList<>();
        keys=new ArrayList<>();
        listView=view.findViewById(R.id.listview);
        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference=firebaseDatabase.getReference(user.getUid());

        adaptar=new CustomAdaptarRemind(getContext(),R.layout.data_map_all,list,keys,user.getUid());



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(test)list.clear();
                if(test)keys.clear();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Mirror mirror=ds.getValue(Mirror.class);
                    if(mirror.getType()==4){
                        String key=ds.getKey();
                        list.add(mirror);
                        keys.add(key);
                    }
                }
                //Toast.makeText(getContext(), ""+keys.size(), Toast.LENGTH_SHORT).show();
                listView.setAdapter(adaptar);
                test=true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}