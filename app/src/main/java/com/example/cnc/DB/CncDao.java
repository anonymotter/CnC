package com.example.cnc.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cnc.User;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.01
 * Data access object for the C&C application.
 */

@Dao
public interface CncDao {

  @Insert
  void insert(User... users);

  @Update
  void upgrade(User... users);

  @Delete
  void delete(User user);

  @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
  List<User> getAllUsers();

  @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username = :username")
  List<User> getUserByName(String username);

  @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE username = :username AND password = :password")
  List<User> getUserByNameAndPassword(String username, String password);

  @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE userId = :userId AND isDm = 1")
  List<User> getDmById(int userId);

}
