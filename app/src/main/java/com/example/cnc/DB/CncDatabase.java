package com.example.cnc.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cnc.PlayerChar;
import com.example.cnc.User;

/**
 * @author Kyle Stefun
 * @since 2023.12.01
 * Database class for the C&C application.
 */

@Database(entities = {User.class, PlayerChar.class}, version = 2)
public abstract class CncDatabase extends RoomDatabase {
  public static final String DATABASE_NAME = "CnC.db";
  public static final String USER_TABLE = "users_table";
  public static final String CHAR_TABLE = "characters_table";

  private static volatile CncDatabase instance;

  private static final Object LOCK = new Object();

  public abstract CncDao CnCDao();

  public static CncDatabase getInstance(Context context) {
    if (instance == null) {
      synchronized (LOCK) {
        if (instance == null) {
          instance = Room.databaseBuilder(context.getApplicationContext(),
              CncDatabase.class,
              DATABASE_NAME).build();
        }
      }
    }
    return instance;
  }
}