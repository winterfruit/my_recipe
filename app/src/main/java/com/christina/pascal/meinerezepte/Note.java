package com.christina.pascal.meinerezepte;

public class Note {
    private long ID;
    private String title;
    private String content;
    private String date;
    private String time;
    private String zubereitungszeit;

    Note() {}
    Note(String title, String content, String date, String time, String zubereitungszeit) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.zubereitungszeit = zubereitungszeit;
    }
    Note(long id, String title, String content, String date, String time, String zubereitungszeit) {
        this.ID = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.zubereitungszeit = zubereitungszeit;
    }



    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZubereitungszeit() {
        return zubereitungszeit;
    }

    public void setZubereitungszeit(String zubereitungszeit) {
        this.zubereitungszeit = zubereitungszeit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
