package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cnc.DB.CncDao;
import com.example.cnc.databinding.ActivityCharSheetBinding;

public class CharSheetActivity extends AppCompatActivity {

  private ActivityCharSheetBinding bind;
  private CncDao dao;
  private int userId;
  private int charId;

  private TextView charNameLabel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(bind.getRoot());
    createBindings();



  }

  private void createBindings() {
    bind = ActivityCharSheetBinding.inflate(getLayoutInflater());
    charNameLabel = bind.charNameLabel;
    charNameLabel.setText(dao.);
  }
}