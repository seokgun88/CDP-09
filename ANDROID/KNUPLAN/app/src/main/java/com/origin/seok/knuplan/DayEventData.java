package com.origin.seok.knuplan;

/**
 * Created by Seok on 2016-06-03.
 */
public class DayEventData {
    int day = 0;
    String event = "";

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getEvent() {
        return event;
    }

    public void addEvnet(String event) {
        this.event += "\n" + event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "DayEventData{" +
                "day=" + day +
                ", event='" + event + '\'' +
                '}';
    }
}
