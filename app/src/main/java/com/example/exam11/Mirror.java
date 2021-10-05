package com.example.exam11;

public class Mirror {
    public String msg;
    public int fromHour,fromMnt,toHour,toMnt,type;
    public int cD,cM,cY;
    public String date;
    public int hour,minute;

    public Mirror() {
    }

    public Mirror(String msg, int fromHour, int fromMnt, int toHour, int toMnt, int type, int cD, int cM, int cY, String date, int hour, int minute) {
        this.msg = msg;
        this.fromHour = fromHour;
        this.fromMnt = fromMnt;
        this.toHour = toHour;
        this.toMnt = toMnt;
        this.type = type;
        this.cD = cD;
        this.cM = cM;
        this.cY = cY;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFromHour() {
        return fromHour;
    }

    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }

    public int getFromMnt() {
        return fromMnt;
    }

    public void setFromMnt(int fromMnt) {
        this.fromMnt = fromMnt;
    }

    public int getToHour() {
        return toHour;
    }

    public void setToHour(int toHour) {
        this.toHour = toHour;
    }

    public int getToMnt() {
        return toMnt;
    }

    public void setToMnt(int toMnt) {
        this.toMnt = toMnt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getcD() {
        return cD;
    }

    public void setcD(int cD) {
        this.cD = cD;
    }

    public int getcM() {
        return cM;
    }

    public void setcM(int cM) {
        this.cM = cM;
    }

    public int getcY() {
        return cY;
    }

    public void setcY(int cY) {
        this.cY = cY;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
