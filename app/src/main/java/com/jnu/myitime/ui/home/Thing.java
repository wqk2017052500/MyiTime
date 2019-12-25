package com.jnu.myitime.ui.home;

import java.io.Serializable;

public class Thing implements Serializable {
    private String title;
    private String data;
    private String more;
    private int thingyear;
    private int thingmonth;
    private int thingday;
    private int thingminute;
    private int thinghour;


    public Thing(String title, String data, String more, int coverResourceId,int thingyear,int thingmonth,int thingday,int thinghour,int thingminute) {
        this.setTitle(title);
        this.setData(data);
        this.setMore(more);
        this.setCoverResourceId(coverResourceId);
        this.setThingyear(thingyear);
        this.setThingmonth(thingmonth);
        this.setThingday(thingday);
        this.setThinghour(thinghour);
        this.setThingminute(thingminute);
    }

    private int coverResourceId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public int getCoverResourceId() {
        return coverResourceId;
    }

    public void setCoverResourceId(int coverResourceId) {
        this.coverResourceId = coverResourceId;
    }

    public int getThingyear() {
        return thingyear;
    }

    public void setThingyear(int thingyear) {
        this.thingyear = thingyear;
    }

    public int getThingmonth() {
        return thingmonth;
    }

    public void setThingmonth(int thingmonth) {
        this.thingmonth = thingmonth;
    }

    public int getThingday() {
        return thingday;
    }

    public void setThingday(int thingday) {
        this.thingday = thingday;
    }

    public int getThingminute() {
        return thingminute;
    }

    public void setThingminute(int thingminute) {
        this.thingminute = thingminute;
    }

    public int getThinghour() {
        return thinghour;
    }

    public void setThinghour(int thinghour) {
        this.thinghour = thinghour;
    }
}
