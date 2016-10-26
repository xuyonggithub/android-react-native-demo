package com.shengjing.industry.industrydemo.bean;

/**
 * Created by pc on 2016/10/19.
 */

public class Record{
    public Record(String id, String time, boolean status) {
        this.id = id;
        this.time = time;
        this.status = status;
    }
    public String id;
    public String time;
    public boolean status;
}
