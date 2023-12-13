package com.example.cnc;

import android.content.Context;

import androidx.room.Room;

import com.example.cnc.DB.CncDao;
import com.example.cnc.DB.CncDatabase;

/**
 * @author Kyle Stefun
 * @since 2023.12.12
 * A class to initialize the database and hold a reference to the DAO, and maybe other stuff if I
 * think of anything.
 */

public class Static {
  private static CncDao dao;

  public static CncDao getDao() {
    return dao;
  }

  public static CncDao initDatabase(Context context) {
    dao = Room.databaseBuilder(context, CncDatabase.class, CncDatabase.DATABASE_NAME)
        .allowMainThreadQueries().build().CnCDao();
    return dao;
  }
}
