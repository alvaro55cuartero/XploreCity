package com.example.xplorecity.logIn;

class AddUserResponse {

    private String err, msg;

    public AddUserResponse(String err, String msg){
        this.err = err;
        this.msg = msg;
    }

    public String getErr(){ return  err; }

    public String getMsg(){return msg;}
}
