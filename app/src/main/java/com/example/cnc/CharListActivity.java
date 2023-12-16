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

import com.example.cnc.db.CncDao;
import com.example.cnc.databinding.ActivityCharListBinding;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.dataview.CharListAdapter;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.05
 * Shows a list of all characters that belong to the user.
 */

public class CharListActivity extends AppCompatActivity {

  private ActivityCharListBinding bind;
  private CncDao dao;
  private Integer userId;

  private SharedPreferences pref;

  private TextView charListLabel;
  private Button logoutButton;
  private Button createButton;
  private RecyclerView recyclerView;

  private boolean deleteEnabled;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initControls();
    dao = Statics.getDao();
//    Statics.setCharListActivity(this);
    pref = getSharedPreferences(getString(R.string.PreferenceKey), Context.MODE_PRIVATE);
    userId = pref.getInt(getString(R.string.UserIdKey), -1);
    charListLabel.setText(getString(R.string.charListLabel, dao.getUserById(userId).get(0).getUsername()));
    initDataView();
  }

//  @Override
//  protected void onDestroy() {
//    Statics.setCharListActivity(null);
//    super.onDestroy();
//  }

  @Override
  protected void onResume() {
    super.onResume();
    initDataView();
  }

  private void initControls() {
    bind = ActivityCharListBinding.inflate(getLayoutInflater());
    setContentView(bind.getRoot());
    charListLabel = bind.charListLabel;
    logoutButton = bind.playerLogoutButton;
    createButton = bind.createCharButton;
    recyclerView = bind.charListRecyclerView;

    logoutButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        logout();
      }
    });

    logoutButton.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        setDeleteEnabled(true);
        return true;
      }
    });

    createButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        createChar();
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

    dao.getCharsByUserIdLive(userId).observe(this, charObserver);
  }

  private void createChar() {
    startActivity(Intents.charCreate(this));
  }

  private void deleteUser() {
    List<PlayerChar> userChars = dao.getCharsByUserId(userId);
    for (PlayerChar pc : userChars) {
      dao.delete(pc);
    }
    dao.delete(dao.getUserById(userId).get(0));
    logout();
  }



  private void logout() {
    SharedPreferences.Editor prefEdit = pref.edit();
    prefEdit.putInt(getString(R.string.UserIdKey), -1);
    prefEdit.apply();
    startActivity(Intents.login(this));
  }

  private void refreshRecyclerView() {
    List<PlayerChar> query = dao.getCharsByUserId(userId);
    recyclerView.setAdapter(new CharListAdapter(query));
  }

  public void selectChar(int index) {
    if (dao.getCharById(0).size() != 0) {
      startActivity(Intents.charSheet(this, index));
    } else {
      Toast.makeText(this, "No characters", Toast.LENGTH_SHORT).show();
    }
  }

  private void setDeleteEnabled(boolean enabled) {
    deleteEnabled = enabled;
    if (enabled) {
      logoutButton.setText(R.string.deleteUser);

      logoutButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          deleteUser();
        }
      });
    }
  }
}