package com.swaraj.tada;

/**
 * Created by swaraj on 02/11/17
 */

public class Tasks {

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    String data;
    String sTime;
    String eTime;
    String priority;

    public Tasks(String data, String sTime, String eTime, String priority) {
        this.data = data;
        this.sTime = sTime;
        this.eTime = eTime;
        this.priority = priority;
    }


}
