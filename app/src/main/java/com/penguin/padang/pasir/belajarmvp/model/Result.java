package com.penguin.padang.pasir.belajarmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jowy on 5/24/16.
 */
public class Result {
    @SerializedName("info")
    @Expose
    private Info info;

    /**
     * @return The info
     */
    public Info getInfo() {
        return info;
    }

    /**
     * @param info The info
     */
    public void setInfo(Info info) {
        this.info = info;
    }
}
