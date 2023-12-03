package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cnc.DB.CncDao;
import com.example.cnc.databinding.ActivityCharListBinding;

public class CharListActivity extends AppCompatActivity {

  private ActivityCharListBinding bind;
  private CncDao cncDao;
  private String username;
  private int userId;

  private TextView charListLabel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_char_list);
    bind = ActivityCharListBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    charListLabel = bind.charListLabel;
    username = getIntent().getStringExtra(Intents.USERNAME_KEY);
    userId = getIntent().getIntExtra(Intents.USER_ID_KEY, -1);
    StringBuilder sb = new StringBuilder();
    sb.append(username).append(R.string.charListLabel);
    charListLabel.setText(getString(R.string.charListLabel, username));
//    SharedPreferences prefs = getSharedPreferences("prefkey", Context.MODE_PRIVATE);
//    prefs.getInt("key", -1);
  }


}