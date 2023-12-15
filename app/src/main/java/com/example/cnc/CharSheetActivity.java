package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cnc.db.CncDao;
import com.example.cnc.databinding.ActivityCharSheetBinding;
import com.example.cnc.db.PlayerChar;

public class CharSheetActivity extends AppCompatActivity {

  private ActivityCharSheetBinding bind;
  private CncDao dao;
  private PlayerChar character;
//  private int charId;

  private TextView charNameLabel;
  private TextView levelRaceClassLabel;
  private TextView hpNumber;
  private ProgressBar hpBar;
  private Button takeDamageButton;
  private Button restButton;
  private Button levelUpButton;
  private Button backButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    dao = Statics.getDao();
    int charId = getIntent().getIntExtra(Intents.CHAR_ID_KEY, -1);
    if(charId == -1) return;
    character = dao.getCharById(charId).get(0);
    initControls();



  }

  private void initControls() {
    bind = ActivityCharSheetBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    charNameLabel = bind.charNameLabel;
    charNameLabel.setText(character.getName());
    levelRaceClassLabel = bind.levelRaceClassLabel;
    levelRaceClassLabel.setText(character.describe());
    hpNumber = bind.hpNumber;
    hpBar = bind.hpBar;
    changeHp(character.getCurrentHp(), character.getMaxHp());
    takeDamageButton = bind.takeDamageButton;
    restButton = bind.restButton;
    levelUpButton = bind.levelUpButton;
    backButton = bind.backButton;
    bind.strLabel.setText(getString(R.string.strDisplay, String.valueOf(character.getStr())));
    bind.dexLabel.setText(getString(R.string.dexDisplay, String.valueOf(character.getDex())));
    bind.conLabel.setText(getString(R.string.conDisplay, String.valueOf(character.getCon())));
    bind.wisLabel.setText(getString(R.string.wisDisplay, String.valueOf(character.getWis())));
    bind.intLabel.setText(getString(R.string.intDisplay, String.valueOf(character.getInt())));
    bind.chaLabel.setText(getString(R.string.chaDisplay, String.valueOf(character.getCha())));

    takeDamageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeHp(character.getCurrentHp()-1);
      }
    });

    restButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeHp(character.getMaxHp());
      }
    });

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

  }

  private void changeHp(int newHp) {
    if(newHp < 0) newHp = 0;
    if(newHp > character.getMaxHp()) newHp = character.getMaxHp();
    character.setCurrentHp(newHp);
    hpBar.setProgress(character.getCurrentHp());
    hpNumber.setText(character.getCurrentHp() + "/" + character.getMaxHp());
    if (character.getCurrentHp() > 0) {
      restButton.setText(R.string.rest);
    } else {
      restButton.setText(R.string.resurrect);
    }
  }

  private void changeHp(int newHp, int newHpMax) {
    character.setMaxHp(newHpMax);
    hpBar.setMax(newHpMax);
    changeHp(newHp);
  }
}