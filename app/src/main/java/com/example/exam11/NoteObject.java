package com.example.exam11;

public class NoteObject {
    public String msg;
    public int type;

    public int hour,minute;

    public int fromHour,fromMnt,toHour,toMnt;
    public int cD,cM,cY;
    public String date;

    public NoteObject(String msg, int type) {
        this.msg = msg;
        this.type = type;
        fromHour=0;
        fromMnt=0;
        toHour=0;
        toMnt=0;
        cD=0;
        cM=0;
        cY=0;
        hour=0;
        minute=0;
        date="";
    }
}
