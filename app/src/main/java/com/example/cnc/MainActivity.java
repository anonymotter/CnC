package com.example.cnc;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cnc.db.Campaign;
import com.example.cnc.db.CncDao;
import com.example.cnc.databinding.ActivityMainBinding;
import com.example.cnc.db.User;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.01
 * First activity that a user sees, prompting them to log in or create an account. Ampersand image
 * presumably property of Wizards of the Coast.
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
    initControls();
    dao = Statics.initDatabase(this);
    pref = getSharedPreferences(getString(R.string.PreferenceKey),
        Context.MODE_PRIVATE);

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

  private void initControls() {
    bind = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    usernameEdit = bind.usernameEdit;
    passwordEdit = bind.passwordEdit;
    loginButton = bind.loginButton;
    createUserButton = bind.createUserButton;
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
    int userId = pref.getInt(getString(R.string.UserIdKey), -1);
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

  private void login() {
    String username = usernameEdit.getText().toString();
    String password = passwordEdit.getText().toString();
    if(fieldsAreEmpty(username, password)) return;
    List<User> query = dao.getUserByNameAndPassword(username, password);
    if (query.size() == 1) {
      loginButton.setText(R.string.loggingIn);
      SharedPreferences.Editor prefEdit = pref.edit();
      prefEdit.putInt(getString(R.string.UserIdKey), query.get(0).getUserId());
      prefEdit.apply();
      loginSplit(query.get(0).getUserId(), query.get(0).getUsername());
    } else {
      Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }
  }

  private void loginSplit(int userId, String username) {
    if (userIsDm(userId)) {
      startActivity(Intents.campaignList(this));
    } else {
      startActivity(Intents.charList(this));
    }
  }

  private boolean userIsDm(int userId) {
    return dao.getDmById(userId).size() > 0;
  }
}