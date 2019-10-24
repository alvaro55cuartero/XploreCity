package com.example.xplorecity.eventCarpa;

import com.google.gson.annotations.SerializedName;

class CurrentLayoutRequest {

    @SerializedName("imei")
    private String imei;

    public CurrentLayoutRequest(String imei) {
        this.imei = imei;
    }

    public String getImei(){
        return imei;
    }
}
