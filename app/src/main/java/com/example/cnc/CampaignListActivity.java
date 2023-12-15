package com.example.cnc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnc.databinding.ActivityCampaignListBinding;
import com.example.cnc.db.CncDao;

/**
 * @author Kyle Stefun
 * @since 2023.12.15
 * Shows a list of all campaigns that belong to the user.
 */

public class CampaignListActivity extends AppCompatActivity {

  private ActivityCampaignListBinding bind;
  private CncDao dao;
  private int userId;
  private SharedPreferences pref;

  private TextView campaignListLabel;
  private Button logoutButton;
  private Button createButton;
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();
    dao = Statics.getDao();
    pref = getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.userIdKey), -1);
    campaignListLabel.setText(
        getString(R.string.campaignListLabel, dao.getUserById(userId).get(0).getUsername()));
//    SharedPreferences prefs = getSharedPreferences("prefkey", Context.MODE_PRIVATE);
//    prefs.getInt("key", -1);
  }

  private void initControls() {
    bind = ActivityCampaignListBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    campaignListLabel = bind.campaignListLabel;
    logoutButton = bind.dmLogoutButton;
    createButton = bind.createCampaignButton;
    recyclerView = bind.campaignListRecyclerView;

    logoutButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        logout();
      }
    });

    createButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        createCampaign();
      }
    });
  }

  private void createCampaign() {
    //todo: campaign creation
    startActivity(Intents.charCreate(this));
  }

  private void logout() {
    SharedPreferences.Editor prefEdit = pref.edit();
    prefEdit.putInt(getString(R.string.userIdKey), -1);
    prefEdit.apply();
    startActivity(Intents.login(this));
  }

}