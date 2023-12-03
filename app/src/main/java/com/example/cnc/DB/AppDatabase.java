package com.example.cnc.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.cnc.User;

/**
 * @author Kyle Stefun
 * @since 2023.12.01
 * Database class for the C&C application.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public static final String DATABASE_NAME = "CnC.db";
  public static final String USER_TABLE = "users_table";

  private static volatile AppDatabase instance;

  private static final Object LOCK = new Object();

  public abstract CncDao CnCDao();

  public static AppDatabase getInstance(Context context) {
    if (instance == null) {
      synchronized (LOCK) {
        if (instance == null) {
          instance = Room.databaseBuilder(context.getApplicationContext(),
              AppDatabase.class,
              DATABASE_NAME).build();
        }
      }
    }
    return instance;
  }
}
