package com.example.xplorecity.mainScreen;

class ImeiResponse {

    private String err;
    private int msg;

    public ImeiResponse(String err, int msg){
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
