package com.example.cnc.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cnc.db.CncDatabase;
import com.example.cnc.enums.CharClass;
import com.example.cnc.enums.CharRace;

/**
 * @author Kyle Stefun
 * @since 2023.12.09
 * Entity representing one character in the database.
 */

@Entity(tableName = CncDatabase.CHAR_TABLE)
public class PlayerChar {

  @PrimaryKey(autoGenerate = true)
  private int charId;
  private int userId;
  private String name;
  private CharRace charRace;
  private CharClass charClass;
  private int level;
  private int currentHp;
  private int maxHp;
  private int str;
  private int dex;
  private int con;
  private int wis;
  private int intelligence;
  private int cha;

//  public PlayerChar() {};

  public PlayerChar(int userId, String name, CharRace charRace, CharClass charClass, int level, int currentHp,
                    int maxHp, int str, int dex, int con, int wis, int intelligence, int cha) {
    this.userId = userId;
    this.name = name;
    this.charRace = charRace;
    this.charClass = charClass;
    this.level = level;
    this.currentHp = currentHp;
    this.maxHp = maxHp;
    this.str = str;
    this.dex = dex;
    this.con = con;
    this.wis = wis;
    this.intelligence = intelligence;
    this.cha = cha;
  }

  public int getCharId() {
    return charId;
  }

  public void setCharId(int charId) {
    this.charId = charId;
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

  public CharRace getCharRace() {
    return charRace;
  }

  public void setCharRace(CharRace charRace) {
    this.charRace = charRace;
  }

  public CharClass getCharClass() {
    return charClass;
  }

  public void setCharClass(CharClass charClass) {
    this.charClass = charClass;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getCurrentHp() {
    return currentHp;
  }

  public void setCurrentHp(int currentHp) {
    this.currentHp = currentHp;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
  }

  public int getStr() {
    return str;
  }

  public void setStr(int str) {
    this.str = str;
  }

  public int getDex() {
    return dex;
  }

  public void setDex(int dex) {
    this.dex = dex;
  }

  public int getCon() {
    return con;
  }

  public void setCon(int con) {
    this.con = con;
  }

  public int getWis() {
    return wis;
  }

  public void setWis(int wis) {
    this.wis = wis;
  }

  public int getIntelligence() {
    return intelligence;
  }

  public void setIntelligence(int intelligence) {
    this.intelligence = intelligence;
  }

  public int getCha() {
    return cha;
  }

  public void setCha(int cha) {
    this.cha = cha;
  }
}
