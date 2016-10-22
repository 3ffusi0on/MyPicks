package com.mypicks.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ImageDto {

    @SerializedName("w")
    @Expose
    private Integer w;
    @SerializedName("full")
    @Expose
    private String full;
    @SerializedName("sprite")
    @Expose
    private String sprite;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("h")
    @Expose
    private Integer h;
    @SerializedName("y")
    @Expose
    private Integer y;
    @SerializedName("x")
    @Expose
    private Integer x;

    /**
     * 
     * @return
     *     The w
     */
    public Integer getW() {
        return w;
    }

    /**
     * 
     * @param w
     *     The w
     */
    public void setW(Integer w) {
        this.w = w;
    }

    /**
     * 
     * @return
     *     The full
     */
    public String getFull() {
        return full;
    }

    /**
     * 
     * @param full
     *     The full
     */
    public void setFull(String full) {
        this.full = full;
    }

    /**
     * 
     * @return
     *     The sprite
     */
    public String getSprite() {
        return sprite;
    }

    /**
     * 
     * @param sprite
     *     The sprite
     */
    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    /**
     * 
     * @return
     *     The group
     */
    public String getGroup() {
        return group;
    }

    /**
     * 
     * @param group
     *     The group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * 
     * @return
     *     The h
     */
    public Integer getH() {
        return h;
    }

    /**
     * 
     * @param h
     *     The h
     */
    public void setH(Integer h) {
        this.h = h;
    }

    /**
     * 
     * @return
     *     The y
     */
    public Integer getY() {
        return y;
    }

    /**
     * 
     * @param y
     *     The y
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * 
     * @return
     *     The x
     */
    public Integer getX() {
        return x;
    }

    /**
     * 
     * @param x
     *     The x
     */
    public void setX(Integer x) {
        this.x = x;
    }

}
