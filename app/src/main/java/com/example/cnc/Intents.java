package com.example.cnc;

import android.content.Context;
import android.content.Intent;

/**
 * @author Kyle Stefun
 * @since 2023.12.04
 */

public class Intents {

  private static final String PACKAGE_NAME = "com.example.cnc";
  public static final String CHAR_ID_KEY = PACKAGE_NAME + "charIdKey";
  public static final String CAMPAIGN_ID_KEY = PACKAGE_NAME + "campaignIdKey";

  public static Intent login(Context context) {
    return new Intent(context, MainActivity.class);
  }

  public static Intent campaignCharList(Context context, int campaignId) {
    Intent intent = new Intent(context, CampaignCharListActivity.class);
    intent.putExtra(CAMPAIGN_ID_KEY, campaignId);
    return intent;
  }

  public static Intent campaignCreate(Context context) {
    return new Intent(context, CampaignCreateActivity.class);
  }

  public static Intent campaignList(Context context) {
    Intent intent = new Intent(context, CampaignListActivity.class);
    return intent;
  }

  public static Intent charCreate(Context context) {
    return new Intent(context, CharCreateActivity.class);
  }

  public static Intent charList(Context context) {
    Intent intent = new Intent(context, CharListActivity.class);
    return intent;
  }

  public static Intent charSheet(Context context, int charId) {
    Intent intent = new Intent(context, CharSheetActivity.class);
    intent.putExtra(CHAR_ID_KEY, charId);
    return intent;
  }
}