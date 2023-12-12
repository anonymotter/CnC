package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cnc.DB.CncDao;
import com.example.cnc.DB.CncDatabase;
import com.example.cnc.databinding.ActivityCharCreateBinding;
import com.example.cnc.databinding.ActivityCharSheetBinding;
import com.example.cnc.enums.CharClass;
import com.example.cnc.enums.CharRace;

/**
 * @author Kyle Stefun
 * @since 2023.12.10
 */

public class CharCreateActivity extends AppCompatActivity {

  private ActivityCharCreateBinding bind;
  private CncDao dao;

  Button createButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();
    dao = Room.databaseBuilder(this, CncDatabase.class, CncDatabase.DATABASE_NAME)
        .allowMainThreadQueries().build().CnCDao();

  }

  private void initControls() {
    bind = ActivityCharCreateBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    createButton = bind.createFinishButton;

    createButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        create();
      }
    });
  }

  private void create() {
    if (dao.getCharById(0).size() == 0) {
      dao.insert(new PlayerChar("String name", CharRace.HUMAN, CharClass.MATHEMATICIAN, 1,
      10, 10, 11, 11, 11, 11, 11, 11));
      startActivity(Intents.charList(this));
    }
  }
}