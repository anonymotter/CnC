package com.example.cnc.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cnc.Statics;
import com.example.cnc.enums.CharClass;
import com.example.cnc.enums.CharRace;

/**
 * Entity representing one character in the database.
 */

@Entity(tableName = CncDatabase.CHAR_TABLE)
public class PlayerChar {

  @PrimaryKey(autoGenerate = true)
  private int charId;
  private int ownerId;
  private int campaignId;
  private String name;
  private CharRace charRace;
  private CharClass charClass;
  private int level = 1;
  private int currentHp;
  private int maxHp;
  private int str;
  private int dex;
  private int con;
  private int wis;
  private int intelligence;
  private int cha;

  public PlayerChar(int ownerId, int campaignId,
                    String name, CharRace charRace, CharClass charClass,
                    int str, int dex, int con, int wis, int intelligence, int cha) {
    this.ownerId = ownerId;
    this.campaignId = campaignId;
    this.name = name;
    this.charRace = charRace;
    this.charClass = charClass;
    this.str = str;
    this.dex = dex;
    this.con = con;
    this.wis = wis;
    this.intelligence = intelligence;
    this.cha = cha;
    maxHp = calculateMaxHealth();
    currentHp = maxHp;
  }

  private int calculateMaxHealth() {
    return con + level * con/4;
  }

  public void levelUp() {
    level++;
    int maxHpDifference = calculateMaxHealth() - maxHp;
    maxHp += maxHpDifference;
    if(currentHp > 0) currentHp += maxHpDifference;
  }

  public String checkName() {
    switch (name) {
      case "Bilbo Baggins":
        return "Cease and desist received from Middle-earth Enterprises.";
      case "Firebert":
        if(charClass == CharClass.COMMANDO) return "Not a good commando name.";
        break;
      case "John Jacob Jingleheimer Schmidt":
        return "Character already exists with that name";
      case "Obi-Wan Kenobi":
        return "Now that's a name I've not heard in a long time... a long time.";
    }
    if (name.startsWith("King")) {
      return "Well I didn't vote for you.";
    }
    return "";
  }

  public String describeWithCampaign() {
    return "Level " + level + " " + charRace + " " + charClass + " - " + tryGetCampaignName();
  }

  public String describeWithOwner() {
    return "Level " + level + " " + charRace + " " + charClass + " - Owned by " + tryGetOwnerName();
  }

  public String tryGetCampaignName() {
    String campaignName;
    try {
      campaignName = Statics.getDao().getCampaignById(campaignId).get(0).getName();
    } catch (Exception e) {
      return String.valueOf(campaignId);
    }
    return campaignName;
  }

  public String tryGetOwnerName() {
    String ownerName;
    try {
      ownerName = Statics.getDao().getUserById(ownerId).get(0).getUsername();
    } catch (Exception e) {
      return String.valueOf(ownerId);
    }
    return ownerName;
  }

  public int getCharId() {
    return charId;
  }

  public void setCharId(int charId) {
    this.charId = charId;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }

  public int getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(int campaignId) {
    this.campaignId = campaignId;
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
