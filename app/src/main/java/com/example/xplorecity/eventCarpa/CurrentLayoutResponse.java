package com.example.xplorecity.eventCarpa;

class CurrentLayoutResponse {

    private String err;
    private int msg;

    public CurrentLayoutResponse(String err, int msg){
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
