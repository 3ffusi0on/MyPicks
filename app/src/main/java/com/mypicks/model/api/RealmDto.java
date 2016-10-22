package com.mypicks.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealmDto {

  @SerializedName("v")
  @Expose
  private String v;
  @SerializedName("dd")
  @Expose
  private String dd;
  @SerializedName("cdn")
  @Expose
  private String cdn;
  @SerializedName("lg")
  @Expose
  private String lg;
  @SerializedName("n")
  @Expose
  private N n;
  @SerializedName("profileiconmax")
  @Expose
  private Integer profileiconmax;
  @SerializedName("l")
  @Expose
  private String l;
  @SerializedName("css")
  @Expose
  private String css;

  /**
   *
   * @return
   * The v
   */
  public String getV() {
    return v;
  }

  /**
   *
   * @param v
   * The v
   */
  public void setV(String v) {
    this.v = v;
  }

  /**
   *
   * @return
   * The dd
   */
  public String getDd() {
    return dd;
  }

  /**
   *
   * @param dd
   * The dd
   */
  public void setDd(String dd) {
    this.dd = dd;
  }

  /**
   *
   * @return
   * The cdn
   */
  public String getCdn() {
    return cdn;
  }

  /**
   *
   * @param cdn
   * The cdn
   */
  public void setCdn(String cdn) {
    this.cdn = cdn;
  }

  /**
   *
   * @return
   * The lg
   */
  public String getLg() {
    return lg;
  }

  /**
   *
   * @param lg
   * The lg
   */
  public void setLg(String lg) {
    this.lg = lg;
  }

  /**
   *
   * @return
   * The n
   */
  public N getN() {
    return n;
  }

  /**
   *
   * @param n
   * The n
   */
  public void setN(N n) {
    this.n = n;
  }

  /**
   *
   * @return
   * The profileiconmax
   */
  public Integer getProfileiconmax() {
    return profileiconmax;
  }

  /**
   *
   * @param profileiconmax
   * The profileiconmax
   */
  public void setProfileiconmax(Integer profileiconmax) {
    this.profileiconmax = profileiconmax;
  }

  /**
   *
   * @return
   * The l
   */
  public String getL() {
    return l;
  }

  /**
   *
   * @param l
   * The l
   */
  public void setL(String l) {
    this.l = l;
  }

  /**
   *
   * @return
   * The css
   */
  public String getCss() {
    return css;
  }

  /**
   *
   * @param css
   * The css
   */
  public void setCss(String css) {
    this.css = css;
  }

}