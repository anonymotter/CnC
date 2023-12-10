package com.example.cnc.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cnc.enums.Race;

/**
 * @author Kyle Stefun
 * @since 2023.12.09
 * Entity representing one character in the database.
 */

@Entity(tableName = CncDb.CHAR_TABLE)
public class Character {

  @PrimaryKey(autoGenerate = true)
  private int charId;
  private String name;
  private Race race;

}
