package com.example.managementsystem;

public class User {
    String Fname, email;

    public User() {
    }

    public User(String fname, String email) {
        Fname = fname;
        this.email = email;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
