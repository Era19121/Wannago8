package com.example.wannago;

class Polls {
    //add defination of polls
    private int id;
    private String dest;
    private String price;
    private String time;
    private String date;
    private int seat=4;

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

    public void setSeat(int seat) {
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

    public int getSeat() {
        return seat;
    }

    public int getId() {
        return id;
    }


}
