package com.example.cnc.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author Kyle Stefun
 * @since 2023.12.14
 * Entity representing one campaign in the database.
 */

@Entity(tableName = CncDatabase.USER_TABLE)
public class Campaign {

  @PrimaryKey(autoGenerate = true)
  private int userId;
  private String name;
  private String description;
  private boolean nameFilterActive;

  public Campaign(String name, String description, boolean nameFilterActive, boolean noDwarves) {
    this.name = name;
    this.description = description;
    this.nameFilterActive = nameFilterActive;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isNameFilterActive() {
    return nameFilterActive;
  }

  public void setNameFilterActive(boolean nameFilterActive) {
    this.nameFilterActive = nameFilterActive;
  }
}