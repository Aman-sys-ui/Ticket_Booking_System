package org.example.entities;

import org.example.util.UserServiceUtil;

import java.util.List;

public class User {
    private String uid;
    private String name;
    private String userName;
    private String password;
    private byte[] salt;

    public User(String uid, String name, String userName, String password) {
        this.uid = uid;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public User() {

    }

    public User(String userName, String password ) {
        this.userName = userName;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


