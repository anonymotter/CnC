package com.example.cnc;

import android.content.Context;
import android.content.Intent;

public class Intents {

  private static final String PACKAGE_NAME = "com.example.cnc";
  public static final String USER_ID_KEY = PACKAGE_NAME + "user_id_key";
  public static final String USERNAME_KEY = PACKAGE_NAME + "username_key";

  public static Intent login(Context context) {
    Intent intent = new Intent (context, MainActivity.class);
    return intent;
  }

  public static Intent charList(Context context, int userId, String username) {
    Intent intent = new Intent (context, CharListActivity.class);
    intent.putExtra(USER_ID_KEY, userId);
    intent.putExtra(USERNAME_KEY, username);
    return intent;
  }

  public static Intent campaignList(Context context, int userId, String username) {
    Intent intent = new Intent (context, CampaignActivity.class);
    intent.putExtra(USER_ID_KEY, userId);
    intent.putExtra(USERNAME_KEY, username);
    return intent;
  }
}