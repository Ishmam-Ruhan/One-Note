package com.example.exam11;

public class AlarmObject {
    public int hour,minute,type;

    public String msg;
    public int fromHour,fromMnt,toHour,toMnt;
    public int cD,cM,cY;
    public String date;

    public AlarmObject(int hour, int minute, String date,int type) {
        this.hour = hour;
        this.minute = minute;
        this.type = type;
        msg="";
        fromHour=0;
        fromMnt=0;
        toHour=0;
        toMnt=0;
        cD=0;
        cM=0;
        cY=0;
        this.date=date;
    }
}
