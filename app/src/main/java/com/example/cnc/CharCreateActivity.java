package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.cnc.db.CncDao;
import com.example.cnc.databinding.ActivityCharCreateBinding;
import com.example.cnc.enums.CharClass;
import com.example.cnc.enums.CharRace;

/**
 * @author Kyle Stefun
 * @since 2023.12.10
 */

public class CharCreateActivity extends AppCompatActivity {

  private ActivityCharCreateBinding bind;
  private CncDao dao;
  private SharedPreferences pref;
  private Integer userId;

  Button createButton;
  Spinner raceSpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();

    dao = Static.getDao();
    pref = getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.userIdKey), -1);
  }

  private void initControls() {
    bind = ActivityCharCreateBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    createButton = bind.createFinishButton;
    raceSpinner = bind.raceSpinner;
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.racesArray, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    raceSpinner.setAdapter(adapter);

    createButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        create();
      }
    });
  }

  private void create() {
    if (dao.getCharById(0).size() == 0) {
      dao.insert(new PlayerChar(userId, "Surak", CharRace.VULCAN, CharClass.MATHEMATICIAN, 1,
      10, 10, 11, 11, 11, 11, 11, 11));
//      startActivity(Intents.charList(this));
    }
  }
}