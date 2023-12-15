package com.example.cnc;

import android.content.Context;

import androidx.room.Room;

import com.example.cnc.db.CncDao;
import com.example.cnc.db.CncDatabase;

/**
 * @author Kyle Stefun
 * @since 2023.12.12
 * A class to initialize the database and hold a reference to the DAO, and maybe other stuff if I
 * think of anything.
 */

public class Statics {
  private static CncDao dao;
  private static CharListActivity charListActivity;

  public void deleteCharById(int charId) { // hey, ever heard of encapsulation?
    dao.delete(dao.getCharById(charId).get(0));
  }



  public static CncDao getDao() {
    return dao;
  }

  public static CncDao initDatabase(Context context) {
    dao = Room.databaseBuilder(context, CncDatabase.class, CncDatabase.DATABASE_NAME)
        .allowMainThreadQueries().build().CnCDao();
    return dao;
  }

  public static CharListActivity getCharListActivity() {
    return charListActivity;
  }

  public static void setCharListActivity(CharListActivity charListActivity) {
    Statics.charListActivity = charListActivity;
  }
}
