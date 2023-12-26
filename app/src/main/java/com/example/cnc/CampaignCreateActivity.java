package com.example.cnc;

/**
 * Activity for creating a campaign.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cnc.databinding.ActivityCampaignCreateBinding;
import com.example.cnc.databinding.ActivityCharCreateBinding;
import com.example.cnc.db.Campaign;
import com.example.cnc.db.CncDao;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.enums.CharClass;
import com.example.cnc.enums.CharRace;

public class CampaignCreateActivity extends AppCompatActivity {

  private ActivityCampaignCreateBinding bind;
  private CncDao dao;
  private SharedPreferences pref;
  private int userId;

  private EditText nameEdit;
  private EditText descEdit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();
    dao = Statics.getDao();
    pref = getSharedPreferences(getString(R.string.PreferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.UserIdKey), -1);
  }

  private void initControls() {
    bind = ActivityCampaignCreateBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    nameEdit = bind.campaignNameEdit;
    descEdit = bind.campaignDescEdit;

    bind.campaignFinishButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        create();
      }
    });
  }

  private void create() {
    if (nameEdit.getText().toString().length() == 0) {
      Toast.makeText(this, "Enter a name",
          Toast.LENGTH_SHORT).show();
      return;
    }
    if (dao.getCampaignByName(nameEdit.getText().toString()).size() > 0) {
      Toast.makeText(this, "Campaign already exists with that name",
          Toast.LENGTH_SHORT).show();
      return;
    }
    if (dao.getCampaignById(0).size() == 0) {
      dao.insert(new Campaign(userId, nameEdit.getText().toString(),
          descEdit.getText().toString(), bind.filterCheckbox.isChecked()));
      startActivity(Intents.campaignList(this));
    }
  }
}