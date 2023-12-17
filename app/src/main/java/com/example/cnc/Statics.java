package com.example.cnc;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.cnc.db.Campaign;
import com.example.cnc.db.CncDao;
import com.example.cnc.db.CncDatabase;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.db.User;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.12
 * A class to initialize the database and hold a reference to the DAO, and maybe other stuff if I
 * think of anything.
 */

public class Statics {
  private static CncDao dao;

  public static void deleteCampaign(Campaign campaign) { // ever heard of encapsulation?
    List<PlayerChar> charsInCampaign = dao.getCharsByCampaignId(campaign.getCampaignId());
    for (PlayerChar pc : charsInCampaign) {
      pc.setCampaignId(1); // set all characters in the campaign to no campaign
      dao.update(pc);
    }
    dao.delete(campaign);
  }

  public static CncDao getDao() {
    return dao;
  }

  public static CncDao initDatabase(Context context) {
    dao = Room.databaseBuilder(context, CncDatabase.class, CncDatabase.DATABASE_NAME)
        .allowMainThreadQueries().build().CnCDao();
    if (dao.getUserByName("testuser1").size() == 0) {
      dao.insert(new User("testuser1", "testuser1", false));
    }
    if (dao.getUserByName("admin2").size() == 0) {
      dao.insert(new User("admin2", "admin2", true));
    }
    if (dao.getAllCampaigns().size() == 0) {
      dao.insert(new Campaign(-1, "No campaign", "", false)); // this will have an ID of 1
    }
    return dao;
  }
}
