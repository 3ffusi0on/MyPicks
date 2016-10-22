package com.mypicks.model.database;

/**
 * Created by Pierre-Alain SIMON on 21/03/2016.
 * Vivoka
 * simon_p@vivoka.com
 */
public class Preference {
  private Champion champion;
  private String role;

  public Champion getChampion() {
    return champion;
  }

  public void setChampion(Champion champion) {
    this.champion = champion;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
