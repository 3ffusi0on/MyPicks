package com.mypicks.model.database;

/**
 * Created by Pierre-Alain SIMON on 21/03/2016.
 * Vivoka
 * simon_p@vivoka.com
 */
public class Champion {
  private String championName;
  private String championTitle;
  private String championKey;

  public String getChampionName() {
    return championName;
  }

  public String getChampionTitle() {
    return championTitle;
  }

  public String getChampionKey() {
    return championKey;
  }

  public void setChampionName(String championName) {
    this.championName = championName;
  }

  public void setChampionTitle(String championTitle) {
    this.championTitle = championTitle;
  }

  public void setChampionKey(String championKey) {
    this.championKey = championKey;
  }
}
