package com.example.exam11;

public class TaskObject {
    public String msg;
    public int fromHour,fromMnt,toHour,toMnt,type;
    public int cD,cM,cY;
    public String date;

    public int hour,minute;

    public TaskObject(String msg, int fromHour, int fromMnt, int toHour, int toMnt, int cD, int cM, int cY,String date,int type) {
        this.msg = msg;
        this.fromHour = fromHour;
        this.fromMnt = fromMnt;
        this.toHour = toHour;
        this.toMnt = toMnt;
        this.cD = cD;
        this.cM = cM;
        this.cY = cY;
        this.date=date;
        this.type=type;
        hour=0;
        minute=0;
    }
}
