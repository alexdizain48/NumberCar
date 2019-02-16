package com.alex.numbercar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photocar {

    private int id;
    private String model;
    private String number;
    private String url;

    public Photocar(int id, String model, String number, String url) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
