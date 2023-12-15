package com.example.cnc.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.01
 * Data access object for the C&C application.
 */

@Dao
public interface CncDao {

  // characters table
  @Insert
  void insert(PlayerChar... playerChars);

  @Update
  void update(PlayerChar... playerChars);

  @Delete
  void delete(PlayerChar playerChars);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE charId = :charId")
  List<PlayerChar> getCharById(int charId);

  @Query("SELECT * FROM " + CncDatabase.CHAR_TABLE + " WHERE ownerId = :userId")
  List<PlayerChar> getCharsByUserId(int userId);



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
