package com.example.exam11;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class PersonalAdapter extends ArrayAdapter<Mirror> {
    private Context context;

    public PersonalAdapter(@NonNull Context context, int resource, @NonNull List<Mirror> objects) {
        super(context, resource, objects);
        this.context=context;
    }
}
