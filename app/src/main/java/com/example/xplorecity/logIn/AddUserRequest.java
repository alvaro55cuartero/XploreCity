package com.example.xplorecity.logIn;

import com.google.gson.annotations.SerializedName;

class AddUserRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("imei")
    private String imei;

    public AddUserRequest(String imei, String email, String username) {
        this.email = email;
        this.imei = imei;
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public String getUsername(){
        return username;
    }

    public String getImei(){
        return imei;
    }
}
