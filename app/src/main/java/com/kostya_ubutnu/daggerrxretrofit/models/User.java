package com.kostya_ubutnu.daggerrxretrofit.models;

public class User {

    private String name,password;

    public User(String name,String password){
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
}
