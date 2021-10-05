package com.example.exam11;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomAdaptar extends ArrayAdapter<Mirror> {

    Context context;
    ArrayList<Mirror> list;
    ArrayList<String> keyValues;
    private LayoutInflater layoutInflater;
    String User;

    public CustomAdaptar(@NonNull Context context, int resource, @NonNull ArrayList<Mirror> objects,@NonNull ArrayList<String> obj,String user) {
        super(context, resource, objects);
        this.context=context;
        this.list=objects;
        this.keyValues=obj;
        this.User=user;
    }


    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        View convertView;
        if(v==null){
            layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.data_map_all,parent,false);
        }else{
            convertView=v;
        }

        TextView icon=convertView.findViewById(R.id.data_icon);
        TextView title=convertView.findViewById(R.id.data_title);
        TextView notemsg=convertView.findViewById(R.id.data_for_note);
        TextView alarmdata=convertView.findViewById(R.id.data_alarm);
        TextView date=convertView.findViewById(R.id.data_date);
        TextView from=convertView.findViewById(R.id.data_from);
        TextView rem=convertView.findViewById(R.id.data_rem_detail);
        TextView to=convertView.findViewById(R.id.Data_to);
        TextView delete=convertView.findViewById(R.id.delete);


        title.setText("");
        notemsg.setText("");
        alarmdata.setText("");
        date.setText("");
        from.setText("");
        rem.setText("");
        to.setText("");


        int val=getItem(position).getType();


        if(val==1){
            icon.setBackgroundResource(R.drawable.note);
            notemsg.setText(getItem(position).getMsg());
            title.setText("Note");

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(User).child(keyValues.get(position));
                    try {
                        databaseReference.removeValue();
                        Toast.makeText(context, "Note Deleted!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(val==2){
            icon.setBackgroundResource(R.drawable.alarm);
            alarmdata.setText(getItem(position).getHour()+" : "+getItem(position).getMinute());
            date.setText(getItem(position).getDate());
            title.setText("Alarm");


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(User).child(keyValues.get(position));
                    try {
                        databaseReference.removeValue();
                        Toast.makeText(context, "Alarm Deleted!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else if(val==3){
            icon.setBackgroundResource(R.drawable.task);
            date.setText(getItem(position).getDate());
            rem.setText(getItem(position).getMsg());
            from.setText(getItem(position).getFromHour()+" : "+getItem(position).getFromMnt());
            to.setText(getItem(position).getToHour()+" : "+getItem(position).getToMnt());
            title.setText("Task");



            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(User).child(keyValues.get(position));
                    try {
                        databaseReference.removeValue();
                        Toast.makeText(context, "Task Deleted!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else if(val==4){
            icon.setBackgroundResource(R.drawable.reminder);
            rem.setText(getItem(position).getMsg());
            alarmdata.setText(getItem(position).getHour()+" : "+getItem(position).getMinute());
            title.setText("Remind");
            date.setText(getItem(position).getDate());



            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(User).child(keyValues.get(position));
                    try {
                        databaseReference.removeValue();
                        Toast.makeText(context, "Remainder Deleted!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        return convertView;
    }
}
