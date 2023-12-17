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
  private PlayerChar pc;
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
    pc = dao.getCharById(charId).get(0);
    initControls();
    changeHp(pc.getCurrentHp(), pc.getMaxHp());
  }

  private void initControls() {
    bind = ActivityCharSheetBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    charNameLabel = bind.charNameLabel;
    charNameLabel.setText(pc.getName());
    levelRaceClassLabel = bind.levelRaceClassLabel;
    levelRaceClassLabel.setText(pc.describeWithCampaign());
    hpNumber = bind.hpNumber;
    hpBar = bind.hpBar;
    takeDamageButton = bind.takeDamageButton;
    restButton = bind.restButton;
    levelUpButton = bind.levelUpButton;
    backButton = bind.backButton;
    bind.strLabel.setText(getString(R.string.strDisplay, String.valueOf(pc.getStr())));
    bind.dexLabel.setText(getString(R.string.dexDisplay, String.valueOf(pc.getDex())));
    bind.conLabel.setText(getString(R.string.conDisplay, String.valueOf(pc.getCon())));
    bind.wisLabel.setText(getString(R.string.wisDisplay, String.valueOf(pc.getWis())));
    bind.intLabel.setText(getString(R.string.intDisplay, String.valueOf(pc.getIntelligence())));
    bind.chaLabel.setText(getString(R.string.chaDisplay, String.valueOf(pc.getCha())));

    takeDamageButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeHp(pc.getCurrentHp()-1);
      }
    });

    restButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeHp(pc.getMaxHp());
        restButton.setText(R.string.rest);
      }
    });

    levelUpButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        levelUp();
      }
    });

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

  }

  private void changeHp(int hp) {
    int newHp = hp < 0 ? 0 : hp;
    pc.setCurrentHp(newHp);
    hpBar.setProgress(pc.getCurrentHp());
    hpNumber.setText(pc.getCurrentHp() + "/" + pc.getMaxHp());
    if (pc.getCurrentHp() > 0) {
      restButton.setText(R.string.rest);
    } else {
      restButton.setText(R.string.resurrect);
    }
    dao.update(pc);
  }

  private void changeHp(int newHp, int newHpMax) {
    pc.setMaxHp(newHpMax);
    hpBar.setMax(newHpMax);
    changeHp(newHp);
  }

  private void levelUp() {
    if(pc.getLevel() >= 20) return;
    pc.levelUp();
    changeHp(pc.getCurrentHp(), pc.getMaxHp());
    levelRaceClassLabel.setText(pc.describeWithCampaign());
  }
}