package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cnc.db.CncDao;
import com.example.cnc.databinding.ActivityCharCreateBinding;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.enums.CharClass;
import com.example.cnc.enums.CharRace;

/**
 * @author Kyle Stefun
 * @since 2023.12.10
 */

public class CharCreateActivity extends AppCompatActivity {
  private static final int DEFAULT_ATTRIBUTE = 10;

  private ActivityCharCreateBinding bind;
  private CncDao dao;
  private SharedPreferences pref;
  private int userId;
  private int raceId;
  private int classId;
  private int str = DEFAULT_ATTRIBUTE;
  private int dex = DEFAULT_ATTRIBUTE;
  private int con = DEFAULT_ATTRIBUTE;
  private int wis = DEFAULT_ATTRIBUTE;
  private int intelligence = DEFAULT_ATTRIBUTE;
  private int cha = DEFAULT_ATTRIBUTE;

  EditText nameEdit;
  Spinner raceSpinner;
  Spinner classSpinner;
  SeekBar strSeekbar;
  SeekBar dexSeekbar;
  SeekBar conSeekbar;
  SeekBar wisSeekbar;
  SeekBar intSeekbar;
  SeekBar chaSeekbar;
  TextView strNumber;
  TextView dexNumber;
  TextView conNumber;
  TextView wisNumber;
  TextView intNumber;
  TextView chaNumber;
  Button createButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();

    dao = Statics.getDao();
    pref = getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.userIdKey), -1);
  }

  private void initControls() {
    bind = ActivityCharCreateBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    nameEdit = bind.createNameEdit;
    raceSpinner = bind.raceSpinner;
    ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(
        this, R.array.racesArray, android.R.layout.simple_spinner_item);
    raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    raceSpinner.setAdapter(raceAdapter);
    classSpinner = bind.classSpinner;
    ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(
        this, R.array.classesArray, android.R.layout.simple_spinner_item);
    classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    classSpinner.setAdapter(classAdapter);
    strSeekbar = bind.strSeekBar;
    dexSeekbar = bind.dexSeekBar;
    conSeekbar = bind.conSeekBar;
    wisSeekbar = bind.wisSeekBar;
    intSeekbar = bind.intSeekBar;
    chaSeekbar = bind.chaSeekBar;
    strNumber = bind.strNumber;
    dexNumber = bind.dexNumber;
    conNumber = bind.conNumber;
    wisNumber = bind.wisNumber;
    intNumber = bind.intNumber;
    chaNumber = bind.chaNumber;
    createButton = bind.createFinishButton;

    createButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        create();
      }
    });

    raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        raceId = position;
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        raceId = 0;
      }
    });

    classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        raceId = position;
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        raceId = 0;
      }
    });

    strSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        str = progress;
        strNumber.setText(String.valueOf(str));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {}
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {}
    });


  }

  private void create() {
    if (dao.getCharById(0).size() == 0) {
      dao.insert(new PlayerChar(userId, nameEdit.getText().toString(),
          CharRace.byId((int)raceSpinner.getSelectedItemId()),
          CharClass.byId((int)classSpinner.getSelectedItemId()),
          1, str, dex, con, wis, intelligence, cha));
      startActivity(Intents.charList(this));
    }
  }
}