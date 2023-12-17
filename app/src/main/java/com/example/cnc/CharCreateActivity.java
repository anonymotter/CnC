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
import android.widget.Toast;

import com.example.cnc.db.Campaign;
import com.example.cnc.db.CncDao;
import com.example.cnc.databinding.ActivityCharCreateBinding;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.enums.CharClass;
import com.example.cnc.enums.CharRace;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.10
 * Activity for creating a character.
 */

public class CharCreateActivity extends AppCompatActivity {
  private static final int DEFAULT_ATTRIBUTE = 10;

  private ActivityCharCreateBinding bind;
  private CncDao dao;
  private SharedPreferences pref;
  private int userId;
  private List<Campaign> campaignList;
  private int str = DEFAULT_ATTRIBUTE;
  private int dex = DEFAULT_ATTRIBUTE;
  private int con = DEFAULT_ATTRIBUTE;
  private int wis = DEFAULT_ATTRIBUTE;
  private int intelligence = DEFAULT_ATTRIBUTE;
  private int cha = DEFAULT_ATTRIBUTE;

  EditText nameEdit;
  Spinner raceSpinner;
  Spinner classSpinner;
  Spinner campaignSpinner;
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
    dao = Statics.getDao();
    initControls();


    pref = getSharedPreferences(getString(R.string.PreferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.UserIdKey), -1);
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
    classSpinner.setAdapter(classAdapter);
    campaignSpinner = bind.campaignSpinner;
    campaignList = dao.getAllCampaigns();
    ArrayAdapter<Campaign> campaignAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, campaignList);
    campaignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    campaignSpinner.setAdapter(campaignAdapter);
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

    dexSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        dex = progress;
        dexNumber.setText(String.valueOf(dex));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {}
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {}
    });

    conSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        con = progress;
        conNumber.setText(String.valueOf(con));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {}
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {}
    });

    wisSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        wis = progress;
        wisNumber.setText(String.valueOf(wis));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {}
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {}
    });

    intSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        intelligence = progress;
        intNumber.setText(String.valueOf(intelligence));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {}
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {}
    });

    chaSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        cha = progress;
        chaNumber.setText(String.valueOf(cha));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {}
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {}
    });


  }

  private void create() {
    PlayerChar newChar = new PlayerChar(userId,
        campaignList.get(campaignSpinner.getSelectedItemPosition()).getCampaignId(),
        nameEdit.getText().toString(),
        CharRace.byId(raceSpinner.getSelectedItemPosition()),
        CharClass.byId(classSpinner.getSelectedItemPosition()),
        str, dex, con, wis, intelligence, cha);
    if (newChar.getName().length() == 0) {
      Toast.makeText(this, "Enter a name",
          Toast.LENGTH_SHORT).show();
      return;
    }
    if (dao.getCharByName(newChar.getName()).size() > 0) {
      Toast.makeText(this, "Character already exists with that name",
          Toast.LENGTH_SHORT).show();
      return;
    }
    if (dao.getCampaignById(newChar.getCampaignId()).get(0).isNameFilterActive()) {
      String filterResult = newChar.checkName();
      if (filterResult.length() > 0) {
        Toast.makeText(this, filterResult, Toast.LENGTH_SHORT).show();
        return;
      }
    }
    if (dao.getCharById(0).size() == 0) {
      dao.insert(newChar);
      startActivity(Intents.charList(this));
    }
  }
}