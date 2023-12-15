package com.example.cnc.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cnc.db.CncDatabase;

/**
 * @author Kyle Stefun
 * @since 2023.12.14
 * Entity representing one campaign in the database.
 */

@Entity(tableName = CncDatabase.CAMPAIGN_TABLE)
public class Campaign {

  @PrimaryKey(autoGenerate = true)
  private int ownerId;
  private String name;
  private String description;
  private boolean nameFilterActive;

  public Campaign(String name, String description, boolean nameFilterActive) {
    this.name = name;
    this.description = description;
    this.nameFilterActive = nameFilterActive;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
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