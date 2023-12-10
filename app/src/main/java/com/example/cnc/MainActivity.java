package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cnc.DB.CncDb;
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

  private CncDao cncDao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bind = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    usernameEdit = bind.usernameEdit;
    passwordEdit = bind.passwordEdit;
    loginButton = bind.loginButton;
    createUserButton = bind.createUserButton;

    cncDao = Room.databaseBuilder(this, CncDb.class, CncDb.DATABASE_NAME)
        .allowMainThreadQueries().build().CnCDao();

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

  private void login() {
    String username = usernameEdit.getText().toString();
    String password = passwordEdit.getText().toString();
    if(fieldsAreEmpty(username, password)) return;
    List<User> queryResult = cncDao.getUserByNameAndPassword(username, password);
    if (queryResult.size() == 1) {
      loginButton.setText(R.string.logging_in);
      playerLogin(queryResult.get(0).getUserId(), queryResult.get(0).getUsername());
    } else {
      loginButton.setText("failure");
    }
  }

  private void createUser() {
    String username = usernameEdit.getText().toString();
    String password = passwordEdit.getText().toString();
    if(fieldsAreEmpty(username, password)) return;
    if (cncDao.getUserByName(username).size() > 0) {
      loginButton.setText("User already exists");
    } else {
      cncDao.insert(new User(username, password, false));
      loginButton.setText("User created");
    }
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

  private void loginSplit(int userId, String username) {

  }

  private void playerLogin(int userId, String username) {
    startActivity(Intents.charList(this, userId, username));
  }

  private boolean userIsDm(int userId) {
    return cncDao.getDmById(userId).size() > 0;
  }
}