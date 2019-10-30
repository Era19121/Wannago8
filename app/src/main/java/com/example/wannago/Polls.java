package com.example.wannago;

import java.util.List;

class Polls {
    //add other defination of polls
    private int id;
    private String dest;
    private String price;
    private String time;
    private String date;
    private long seat = 4;
    private String status;
    private long creater_uid;
    private List<AddedUserEntity> addedUserEntities;

    public Polls() {
    }

    public Polls(int id, String dest, String price, String time, String date) {
        this.id = id;
        this.dest = dest;
        this.price = price;
        this.time = time;
        this.date = date;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }

    public String getDest() {
        return dest;
    }

    public String getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreater_uid() {
        return creater_uid;
    }

    public void setCreater_uid(long creater_uid) {
        this.creater_uid = creater_uid;
    }

    public List<AddedUserEntity> getAddedUserEntities() {
        return addedUserEntities;
    }

    public void setAddedUserEntities(List<AddedUserEntity> addedUserEntities) {
        this.addedUserEntities = addedUserEntities;
    }

    public long getSeat() {
        return seat;
    }

    public int getId() {
        return id;
    }


}
