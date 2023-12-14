package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cnc.db.CncDao;
import com.example.cnc.databinding.ActivityCharSheetBinding;

public class CharSheetActivity extends AppCompatActivity {

  private ActivityCharSheetBinding bind;
  private CncDao dao;
  private PlayerChar character;
//  private int charId;

  private TextView charNameLabel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    dao = Static.getDao();
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

  }
}