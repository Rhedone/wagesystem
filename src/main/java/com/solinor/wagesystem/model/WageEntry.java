package com.solinor.wagesystem.model;

import java.time.LocalDateTime;

/**
 * Created by yolan
 */
public class WageEntry {

    private String name;
    private int id;
    private LocalDateTime start;
    private LocalDateTime end;

    public WageEntry(String name, int id, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
