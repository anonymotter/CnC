package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnc.databinding.ActivityCampaignCharListBinding;
import com.example.cnc.databinding.ActivityCharListBinding;
import com.example.cnc.dataview.CharListAdapter;
import com.example.cnc.db.CncDao;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.dataview.CampaignCharListAdapter;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.15
 * Shows a list of all characters that belong to the selected campaign.
 */

public class CampaignCharListActivity extends AppCompatActivity {
  private ActivityCampaignCharListBinding bind;
  private CncDao dao;
  private Integer campaignId;

  private SharedPreferences pref;

  private TextView charListLabel;
  private Button backButton;
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();
    dao = Statics.getDao();
    pref = getSharedPreferences(getString(R.string.PreferenceKey), Context.MODE_PRIVATE);
    campaignId = getIntent().getIntExtra(Intents.CAMPAIGN_ID_KEY, -1);
    charListLabel.setText(getString(R.string.campaignCharListLabel,
        dao.getCampaignById(campaignId).get(0).getName()));
    initDataView();
  }

  private void initControls() {
    bind = ActivityCampaignCharListBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    charListLabel = bind.campaignCharListLabel;
    backButton = bind.backButton;
    recyclerView = bind.campaignCharListRecyclerView;
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(Intents.campaignList(v.getContext()));
      }
    });
  }

  private void initDataView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    refreshRecyclerView();

    final Observer<List<PlayerChar>> charObserver = new Observer<List<PlayerChar>>() {
      @Override
      public void onChanged(List<PlayerChar> playerCharList) {
        refreshRecyclerView();
      }
    };

    dao.getCharsByCampaignIdLive(campaignId).observe(this, charObserver);
  }

  private void refreshRecyclerView() {
    List<PlayerChar> query = dao.getCharsByCampaignId(campaignId);
    recyclerView.setAdapter(new CampaignCharListAdapter(query));
  }

  public void selectChar(int index) {
    if (dao.getCharById(0).size() != 0) {
      startActivity(Intents.charSheet(this, index));
    } else {
      Toast.makeText(this, "No characters", Toast.LENGTH_SHORT).show();
    }
  }

}