package com.example.xplorecity.eventCarpa;

class IncrementLayoutResponse {

    private String err;
    private int msg;

    public IncrementLayoutResponse(String err, int msg){
        this.err = err;
        this.msg = msg;
    }

    public int getMsg(){
        return msg;
    }

    public String getErr(){
        return err;
    }

}
