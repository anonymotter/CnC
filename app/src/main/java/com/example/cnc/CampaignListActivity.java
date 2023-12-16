package com.example.cnc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnc.databinding.ActivityCampaignListBinding;
import com.example.cnc.dataview.CharListAdapter;
import com.example.cnc.db.Campaign;
import com.example.cnc.db.CncDao;
import com.example.cnc.dataview.CampaignListAdapter;
import com.example.cnc.db.PlayerChar;

import java.util.List;

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
    pref = getSharedPreferences(getString(R.string.PreferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.UserIdKey), -1);
    campaignListLabel.setText(
        getString(R.string.campaignListLabel, dao.getUserById(userId).get(0).getUsername()));
    initDataView();
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

  private void initDataView() {

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    refreshRecyclerView();

    final Observer<List<Campaign>> campaignObserver = new Observer<List<Campaign>>() {
      @Override
      public void onChanged(List<Campaign> playerCharList) {
        refreshRecyclerView();
      }
    };

    dao.getCampaignsByUserIdLive(userId).observe(this, campaignObserver);
  }

  private void createCampaign() {
    startActivity(Intents.campaignCreate(this));
  }

  private void logout() {
    SharedPreferences.Editor prefEdit = pref.edit();
    prefEdit.putInt(getString(R.string.UserIdKey), -1);
    prefEdit.apply();
    startActivity(Intents.login(this));
  }

  private void refreshRecyclerView() {
    List<Campaign> query = dao.getCampaignsByUserId(userId);
    recyclerView.setAdapter(new CampaignListAdapter(query));
  }

}