package com.example.exam11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdaptarNote extends ArrayAdapter<Mirror> {

    Context context;
    ArrayList<Mirror> list;
    ArrayList<String> keyValues;
    private LayoutInflater layoutInflater;
    String User;

    public CustomAdaptarNote(@NonNull Context context, int resource, @NonNull ArrayList<Mirror> objects, @NonNull ArrayList<String> obj, String user) {
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


        return convertView;
    }
}
