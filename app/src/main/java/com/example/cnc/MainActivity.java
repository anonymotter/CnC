package com.example.cnc;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cnc.DB.CncDatabase;
import com.example.cnc.DB.CncDao;
import com.example.cnc.databinding.ActivityMainBinding;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.01
 * First activity that a user sees, prompting them to log in or create an account.
 */

public class MainActivity extends AppCompatActivity {

  private EditText usernameEdit;
  private EditText passwordEdit;
  private Button loginButton;
  private Button createUserButton;

  private ActivityMainBinding bind;

  private SharedPreferences pref;

  private CncDao dao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    bind = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    usernameEdit = bind.usernameEdit;
    passwordEdit = bind.passwordEdit;
    loginButton = bind.loginButton;
    createUserButton = bind.createUserButton;
    dao = Room.databaseBuilder(this, CncDatabase.class, CncDatabase.DATABASE_NAME)
        .allowMainThreadQueries().build().CnCDao();
    pref = getSharedPreferences(getString(R.string.preferenceKey),
        Context.MODE_PRIVATE);
    detectUser();
    initUsers();


    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        login();
      }
    });

    createUserButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        createUser();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    loginButton.setText(R.string.loginVerb);
  }

  private void createUser() {
    String username = usernameEdit.getText().toString();
    String password = passwordEdit.getText().toString();
    if(fieldsAreEmpty(username, password)) return;
    if (dao.getUserByName(username).size() > 0) {
      Toast.makeText(this, "Username already in use", Toast.LENGTH_SHORT).show();
    } else {
      dao.insert(new User(username, password, false));
      Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show();
    }
  }

  private void detectUser() {
    int userId = pref.getInt(getString(R.string.usernameKey), -1);
    if (userId >= 0) {
      List<User> query = dao.getUserById(userId);
      if (query.size() > 0) {
        loginSplit(userId, query.get(0).getUsername());
      }
    }
  }

  private boolean fieldsAreEmpty(String username, String password) {
    if (username.isEmpty()) {
      Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
      return true;
    } else if (password.isEmpty()) {
      Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
      return true;
    }
    return false;
  }

  private void initUsers() {
    if (dao.getUserByName("testuser1").size() == 0) {
      dao.insert(new User("testuser1", "testuser1", false));
    }
    if (dao.getUserByName("admin2").size() == 0) {
      dao.insert(new User("admin2", "admin2", true));
    }
    if (dao.getUserByName("z").size() == 0) {
      dao.insert(new User("z", "z", false));
    }
    if (dao.getUserByName("x").size() == 0) {
      dao.insert(new User("x", "x", true));
    }
  }

  private void login() {
    String username = usernameEdit.getText().toString();
    String password = passwordEdit.getText().toString();
    if(fieldsAreEmpty(username, password)) return;
    List<User> query = dao.getUserByNameAndPassword(username, password);
    if (query.size() == 1) {
      loginButton.setText(R.string.loggingIn);
      SharedPreferences.Editor prefEdit = pref.edit();
      prefEdit.putInt(getString(R.string.usernameKey), query.get(0).getUserId());
      prefEdit.apply();
      loginSplit(query.get(0).getUserId(), query.get(0).getUsername());
    } else {
      Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }
  }

  private void loginSplit(int userId, String username) {
    if (userIsDm(userId)) {
      startActivity(Intents.campaignList(this, userId, username));
    } else {
      startActivity(Intents.charList(this, userId, username));
    }
  }

  private boolean userIsDm(int userId) {
    return dao.getDmById(userId).size() > 0;
  }
}