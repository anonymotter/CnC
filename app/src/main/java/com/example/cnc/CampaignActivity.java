package com.example.cnc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cnc.DB.CncDao;
import com.example.cnc.databinding.ActivityCampaignBinding;

public class CampaignActivity extends AppCompatActivity {

  private ActivityCampaignBinding bind;
  private CncDao cncDao;
  private String username;
  private int userId;

  private TextView campaignListLabel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    bind = ActivityCampaignBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    campaignListLabel = bind.campaignListLabel;
    username = getIntent().getStringExtra(Intents.USERNAME_KEY);
    userId = getIntent().getIntExtra(Intents.USER_ID_KEY, -1);
    StringBuilder sb = new StringBuilder();
    sb.append(username).append(R.string.charListLabel);
    campaignListLabel.setText(getString(R.string.campaignListLabel, username));
//    SharedPreferences prefs = getSharedPreferences("prefkey", Context.MODE_PRIVATE);
//    prefs.getInt("key", -1);
  }


}