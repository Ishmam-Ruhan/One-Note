package com.example.exam11;

public class RemObject {
    public String msg;
    public int hour,minute,type;

    public int fromHour,fromMnt,toHour,toMnt;
    public int cD,cM,cY;
    public String date;

    public RemObject(String msg, int hour, int day, String date,int type) {
        this.msg = msg;
        this.hour = hour;
        this.minute = day;
        this.type = type;
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
