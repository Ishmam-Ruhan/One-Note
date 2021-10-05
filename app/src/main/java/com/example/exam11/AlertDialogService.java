package com.example.exam11;


import android.app.Application;
import android.content.Context;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;;import androidx.constraintlayout.widget.ConstraintLayout;

public class AlertDialogService extends Application {
    protected Context con;
    protected String title, container;
    protected int corner_icon;
    protected AlertDialog alertDialog;
    protected ViewGroup v;
    protected ConstraintLayout constraintLayout;

    public AlertDialogService(Context con, String title, String container, int corner_icon) {
        this.con = con;
        this.title = title;
        this.container = container;
        this.corner_icon = corner_icon;

    }

    public AlertDialogService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    private void callService() {
        AlertDialog.Builder builder = new AlertDialog.Builder(con, R.style.AlertDialogueTheme);

        View view = LayoutInflater.from(con).inflate(
                R.layout.error_dialog, constraintLayout);
        builder.setView(view);

        alertDialog = builder.create();

        if (alertDialog.isShowing()==false) {
            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
        }

        ((TextView) view.findViewById(R.id.header)).setText(title);
        ((TextView) view.findViewById(R.id.content)).setText(container);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource(corner_icon);

        ((ImageView) view.findViewById(R.id.icon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
    }

    public void showAlert(){
        callService();
    }
}
