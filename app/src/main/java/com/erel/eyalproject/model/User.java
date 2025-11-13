package com.erel.eyalproject.model;

public class User {

    protected String id;
    protected String fname;
    protected String lname;
    protected String email;
    protected String phone;
    protected String password;
    protected  double sumRate;
    protected  double rate;
    protected  int numberRate;

    public User(String id, String fname, String lname, String phone, String email, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.password = password;

        this.numberRate = 0;

        this.rate = 0.0;
        this.sumRate = 0.0;
    }

    public User(String id, String fname, String lname, String phone) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;

    }

    public User( User user) {
        this.id = user.id;
        this.fname = user.fname;
        this.lname = user.lname;
        this.phone = user.phone;

    }

    public User(String id, String fname, String lname, String phone, String email, String password, int numberRate, double rate, double sumRate) {
        this.email = email;
        this.fname = fname;
        this.id = id;
        this.lname = lname;
        this.numberRate = numberRate;
        this.password = password;
        this.phone = phone;
        this.rate = rate;
        this.sumRate = sumRate;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumberRate() {
        return numberRate;
    }

    public void setNumberRate(int numberRate) {
        this.numberRate = numberRate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSumRate() {
        return sumRate;
    }

    public void setSumRate(double sumRate) {
        this.sumRate = sumRate;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", sumRate=" + sumRate +
                ", rate=" + rate +
                ", numberRate=" + numberRate +
                '}';
    }
}
