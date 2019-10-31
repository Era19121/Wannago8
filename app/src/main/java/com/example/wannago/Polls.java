package com.example.wannago;

import java.util.List;

class Polls {
    //add other defination of polls
    private int id;
    private String dest;
    private String price;
    private String time;
    private String date;
    private String name;
    private String user;
    private String key;


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

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public void setSeat(long seat) {
        this.seat = seat;
    }

    public int getId() {
        return id;
    }


}
