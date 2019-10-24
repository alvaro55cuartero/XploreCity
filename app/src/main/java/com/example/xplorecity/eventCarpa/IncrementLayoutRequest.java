package com.example.xplorecity.eventCarpa;

import com.google.gson.annotations.SerializedName;

class IncrementLayoutRequest {

    @SerializedName("imei")
    private String imei;

    public IncrementLayoutRequest(String imei){
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }
}
