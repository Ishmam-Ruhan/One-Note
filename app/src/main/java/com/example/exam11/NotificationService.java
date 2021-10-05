package com.example.exam11;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.net.URI;
import java.net.URL;

public class NotificationService extends Application {
    public static final String Channel_1_Id ="channel 1";
    public static final String Channel_2_Id ="channel 2";
    private String title;
    private String content;
    private int icon;
    private boolean channel1;
    private boolean channel2;
    private Context con;
    private Notification notification;

    public NotificationService() {

    }

    public NotificationService(Context con,String title, String content, int icon) {
        this.title = title;
        this.content = content;
        this.icon = icon;
        this.con = con;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1=new NotificationChannel(Channel_1_Id,Channel_1_Id, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            NotificationChannel channel2=new NotificationChannel(Channel_2_Id,Channel_2_Id, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel2);
        }

    }
    private void channel1Notification(){
        Uri url= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
         notification=new NotificationCompat.Builder(con,Channel_1_Id)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(content)
                .setSound(url)
                .setTicker("New Message Alert!!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setChannelId(Channel_1_Id)
                .build();
    }
    private void channel2Notification(){
        notification=new NotificationCompat.Builder(con,Channel_2_Id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setChannelId(Channel_2_Id)
                .build();
    }

    public void createNotification(Context con,String title, String content, int icon) {
        this.title = title;
        this.content = content;
        this.icon = icon;
        this.con=con;

    }

    public Notification getNotificationByChannel(boolean channel1, boolean channel2){
        this.channel1 = channel1;
        this.channel2 = channel2;
        if(channel1){
            channel1Notification();
        }
        else if(channel2){
            channel2Notification();
        }
        return notification;
    }

}
