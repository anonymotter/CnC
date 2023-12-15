package com.example.cnc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnc.databinding.ActivityCharListBinding;
import com.example.cnc.db.CncDao;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.recyclerview.CharListAdapter;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.15
 * Shows a list of all characters that belong to the selected campaign.
 */

public class CampaignCharListActivity extends AppCompatActivity {
  private ActivityCharListBinding bind;
  private CncDao dao;
  private Integer userId;

  private SharedPreferences pref;

  private TextView charListLabel;
  private Button backButton;
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();
    dao = Statics.getDao();
    pref = getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.userIdKey), -1);
    charListLabel.setText(getString(R.string.charListLabel, dao.getUserById(userId).get(0).getUsername()));
    initRecyclerView();
  }

  @Override
  protected void onResume() {
    super.onResume();
    initRecyclerView();
  }

  private void initControls() {
    bind = ActivityCharListBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    charListLabel = bind.charListLabel;
    backButton = bind.playerLogoutButton;
    recyclerView = bind.charListRecyclerView;

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  private void initRecyclerView() {
    List<PlayerChar> query = dao.getCharsByUserId(userId);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new CharListAdapter(query));
  }

  public void selectChar(int index) {
    if (dao.getCharById(0).size() != 0) {
      startActivity(Intents.charSheet(this, index));
    } else {
      Toast.makeText(this, "No characters", Toast.LENGTH_SHORT).show();
    }
  }

}