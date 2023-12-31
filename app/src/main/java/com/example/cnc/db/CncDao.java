package com.example.cnc.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Data access object for the C&C application.
 */

@Dao
public interface CncDao {

  // campaigns table
  @Insert
  void insert(Campaign... campaigns);

  @Update
  void update(Campaign... campaigns);

  @Delete
  void delete(Campaign campaigns);

  @Query("SELECT * FROM " + CncDatabase.CAMPAIGN_TABLE)
  List<Campaign> getAllCampaigns();

  @Query("SELECT * FROM " + CncDatabase.CAMPAIGN_TABLE + " WHERE campaignId = :campaignId")
  List<Campaign> getCampaignById(int campaignId);

  @Query("SELECT * FROM " + CncDatabase.CAMPAIGN_TABLE + " WHERE name = :name")
  List<Campaign> getCampaignByName(String name);

  @Query("SELECT * FROM " + CncDatabase.CAMPAIGN_TABLE + " WHERE ownerId = :userId")
  List<Campaign> getCampaignsByUserId(int userId);

  @Query("SELECT * FROM " + CncDatabase.CAMPAIGN_TABLE + " WHERE ownerId = :userId")
  LiveData<List<Campaign>> getCampaignsByUserIdLive(int userId);

  // characters table
  @Insert
  void insert(PlayerChar... playerChars);

  @Update
  void update(PlayerChar... playerChars);

  @Delete
  void delete(PlayerChar playerChars);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE campaignId = :campaignId")
  List<PlayerChar> getCharsByCampaignId(int campaignId);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE campaignId = :campaignId")
  LiveData<List<PlayerChar>> getCharsByCampaignIdLive(int campaignId);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE charId = :charId")
  List<PlayerChar> getCharById(int charId);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE name = :name")
  List<PlayerChar> getCharByName(String name);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE ownerId = :userId")
  List<PlayerChar> getCharsByUserId(int userId);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE ownerId = :userId")
  LiveData<List<PlayerChar>> getCharsByUserIdLive(int userId);
  
  // users table
  @Insert
  void insert(User... users);

  @Update
  void update(User... users);

  @Delete
  void delete(User user);

  @Query("SELECT * FROM " + CncDatabase.USER_TABLE)
  List<User> getAllUsers();

  @Query("SELECT * FROM " + CncDatabase.USER_TABLE + " WHERE userId = :userId")
  List<User> getUserById(int userId);

  @Query("SELECT * FROM " + CncDatabase.USER_TABLE + " WHERE username = :username")
  List<User> getUserByName(String username);

  @Query("SELECT * FROM " + CncDatabase.USER_TABLE + " WHERE username = :username AND password = :password")
  List<User> getUserByNameAndPassword(String username, String password);

  @Query("SELECT * FROM " + CncDatabase.USER_TABLE + " WHERE userId = :userId AND isDm = 1")
  List<User> getDmById(int userId);

}
