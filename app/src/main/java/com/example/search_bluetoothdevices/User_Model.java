package com.example.search_bluetoothdevices;

public class User_Model {
    int id;
    String name;
    String Number;
    int status;

    public User_Model(int id, String name, String number, int status) {
        this.id = id;
        this.name = name;
        Number = number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
