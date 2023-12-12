package com.example.cnc;

import android.content.Context;
import android.content.Intent;

/**
 * @author Kyle Stefun
 * @since 2023.12.04
 */

public class Intents {

  private static final String PACKAGE_NAME = "com.example.cnc";
  public static final String USER_ID_KEY = PACKAGE_NAME + "userIdKey";
  public static final String USERNAME_KEY = PACKAGE_NAME + "usernameKey";
  public static final String CHAR_ID_KEY = PACKAGE_NAME + "charIdKey";

  public static Intent login(Context context) {
    return new Intent(context, MainActivity.class);
  }

  public static Intent charList(Context context) {
    Intent intent = new Intent(context, CharListActivity.class);
//    intent.putExtra(USER_ID_KEY, userId);
//    intent.putExtra(USERNAME_KEY, username);
    return intent;
  }

  public static Intent campaignList(Context context, int userId, String username) {
    Intent intent = new Intent(context, CampaignActivity.class);
    intent.putExtra(USER_ID_KEY, userId);
    intent.putExtra(USERNAME_KEY, username);
    return intent;
  }

  public static Intent charCreate(Context context) {
    return new Intent(context, CharCreateActivity.class);
  }

  public static Intent charSheet(Context context, int charId) {
    Intent intent = new Intent(context, CharSheetActivity.class);
    intent.putExtra(CHAR_ID_KEY, charId);
    return intent;
  }
}