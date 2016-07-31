package com.solinor.wagesystem.model;

/**
 * Created by yolan
 */
public class WageEntry {

    private String personName;
    private int personId;
    private String date;
    private String start;
    private String end;

    public WageEntry(String personName, int personId, String date, String start, String end) {
        this.personName = personName;
        this.personId = personId;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
