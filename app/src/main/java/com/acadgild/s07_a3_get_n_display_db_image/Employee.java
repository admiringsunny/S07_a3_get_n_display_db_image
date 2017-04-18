package com.acadgild.s07_a3_get_n_display_db_image;

import android.graphics.Bitmap;

public class Employee {
    private int id;
    private String name;
    private String age;
    private byte[] image;

    public Employee() { }

    public Employee(String name, String age, byte[] image) {
        super();
        this.name = name;
        this.age = age;
        this.image = image;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
