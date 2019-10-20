package com.example.xplorecity.logIn;

class AddUserRequest {

    private String imei, email, username;

    public AddUserRequest(String imei, String email, String nickName) {
        this.email = email;
        this.imei = imei;
        this.username = nickName;
    }
}
