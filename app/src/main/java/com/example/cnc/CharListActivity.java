package com.example.cnc;

/**
 * @author Kyle Stefun
 * @since 2023.12.05
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnc.DB.CncDao;
import com.example.cnc.databinding.ActivityCharListBinding;

public class CharListActivity extends AppCompatActivity {

  private ActivityCharListBinding bind;
  private CncDao dao;
  private String username;
  private Integer userId;

  private SharedPreferences pref;

  private TextView charListLabel;
  private Button logoutButton;
  private Button selectButton;
  private Button createButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();
    dao = Static.getDao();
    pref = getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.userIdKey), -1);
    username = dao.getUserById(userId).get(0).getUsername();
    Integer numChars = dao.getCharsByUserId(userId).size();
    charListLabel.setText(getString(R.string.charListLabel, username));
    charListLabel.setText(numChars.toString());

  }

  private void initControls() {
    bind = ActivityCharListBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    charListLabel = bind.charListLabel;
    logoutButton = bind.playerLogoutButton;
    selectButton = bind.selectButton;
    createButton = bind.createCharButton;

    logoutButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        logout();
      }
    });

    selectButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        selectChar(0); //todo: incorporate index of current selection
      }
    });

    createButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        createChar();
      }
    });
  }

  private void createChar() {
    startActivity(Intents.charCreate(this));
  }

  private void logout() {
    SharedPreferences.Editor prefEdit = pref.edit();
    prefEdit.putInt(getString(R.string.userIdKey), -1);
    prefEdit.apply();
    startActivity(Intents.login(this));
  }

  private void selectChar(int index) {
    if (dao.getCharById(0).size() != 0) {
      startActivity(Intents.charSheet(this, index));
    } else {
      Toast.makeText(this, "No characters", Toast.LENGTH_SHORT).show();
    }
  }
}