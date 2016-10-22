package com.mypicks.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ChampionListDto {

    @SerializedName("data")
    @Expose
    private Map<String, ChampionDto> data;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * 
     * @return
     *     The data
     */
    public Map<String, ChampionDto> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Map<String, ChampionDto> data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

}
