package com.mypicks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class N {

  @SerializedName("champion")
  @Expose
  private String champion;
  @SerializedName("profileicon")
  @Expose
  private String profileicon;
  @SerializedName("item")
  @Expose
  private String item;
  @SerializedName("map")
  @Expose
  private String map;
  @SerializedName("mastery")
  @Expose
  private String mastery;
  @SerializedName("language")
  @Expose
  private String language;
  @SerializedName("summoner")
  @Expose
  private String summoner;
  @SerializedName("rune")
  @Expose
  private String rune;

  /**
   *
   * @return
   * The champion
   */
  public String getChampion() {
    return champion;
  }

  /**
   *
   * @param champion
   * The champion
   */
  public void setChampion(String champion) {
    this.champion = champion;
  }

  /**
   *
   * @return
   * The profileicon
   */
  public String getProfileicon() {
    return profileicon;
  }

  /**
   *
   * @param profileicon
   * The profileicon
   */
  public void setProfileicon(String profileicon) {
    this.profileicon = profileicon;
  }

  /**
   *
   * @return
   * The item
   */
  public String getItem() {
    return item;
  }

  /**
   *
   * @param item
   * The item
   */
  public void setItem(String item) {
    this.item = item;
  }

  /**
   *
   * @return
   * The map
   */
  public String getMap() {
    return map;
  }

  /**
   *
   * @param map
   * The map
   */
  public void setMap(String map) {
    this.map = map;
  }

  /**
   *
   * @return
   * The mastery
   */
  public String getMastery() {
    return mastery;
  }

  /**
   *
   * @param mastery
   * The mastery
   */
  public void setMastery(String mastery) {
    this.mastery = mastery;
  }

  /**
   *
   * @return
   * The language
   */
  public String getLanguage() {
    return language;
  }

  /**
   *
   * @param language
   * The language
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   *
   * @return
   * The summoner
   */
  public String getSummoner() {
    return summoner;
  }

  /**
   *
   * @param summoner
   * The summoner
   */
  public void setSummoner(String summoner) {
    this.summoner = summoner;
  }

  /**
   *
   * @return
   * The rune
   */
  public String getRune() {
    return rune;
  }

  /**
   *
   * @param rune
   * The rune
   */
  public void setRune(String rune) {
    this.rune = rune;
  }

}

