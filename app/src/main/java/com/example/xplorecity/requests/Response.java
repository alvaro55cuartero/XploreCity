package com.example.xplorecity.requests;

public class Response {

    private String err;
    private String msg;

    public Response(String err, String msg){
        this.err = err;
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public String getErr(){
        return err;
    }
}
