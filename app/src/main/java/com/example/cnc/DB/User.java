package com.example.cnc;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cnc.DB.CncDb;

/**
 * @author Kyle Stefun
 * @since 2023.12.01
 * Entity representing one user in the database.
 */

@Entity(tableName = CncDb.USER_TABLE)
public class User {

  @PrimaryKey(autoGenerate = true)
  private int userId;
  private String username;
  private String password;
  private boolean isDm;


  public User(String username, String password, boolean isDm) {
    this.username = username;
    this.password = password;
    this.isDm = isDm;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isDm() {
    return isDm;
  }

  public void setDm(boolean dm) {
    isDm = dm;
  }
}