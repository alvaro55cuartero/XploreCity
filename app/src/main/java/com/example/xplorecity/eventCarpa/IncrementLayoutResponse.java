package com.example.xplorecity.eventCarpa;

class IncrementLayoutResponse {

    private String err;
    private String msg;

    public IncrementLayoutResponse(String err, String msg){
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
