package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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
      loginButton.setText("User already exists");
    } else {
      dao.insert(new User(username, password, false));
      loginButton.setText("User created");
    }
  }

  private void detectUser() {
    int userId = -1; //todo: implement sharedpreferences
//    if (userId >= 0) loginSplit(); // figure out arguments
  }

  private boolean fieldsAreEmpty(String username, String password) {
    if (username.isEmpty()) {
      loginButton.setText("Enter name");
      return true;
    } else if (password.isEmpty()) {
      loginButton.setText("Enter password");
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
  }

  private void login() {
    String username = usernameEdit.getText().toString();
    String password = passwordEdit.getText().toString();
    if(fieldsAreEmpty(username, password)) return;
    List<User> queryResult = dao.getUserByNameAndPassword(username, password);
    if (queryResult.size() == 1) {
      loginButton.setText(R.string.logging_in);
      loginSplit(queryResult.get(0).getUserId(), queryResult.get(0).getUsername());
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