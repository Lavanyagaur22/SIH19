package com.codingblocks.sih19;

import java.util.Date;

public class UpdatedClass {

    String Key ,height,weight;
    Date date;

    public UpdatedClass(String key, String height, String weight, Date date) {
        Key = key;
        this.height = height;
        this.weight = weight;
        this.date = date;
    }

    public UpdatedClass() {

    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
