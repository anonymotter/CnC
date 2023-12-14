package com.example.cnc.enums;

public enum CharClass {
  WARRIOR(0, "Warrior"),
  THIEF(1, "Thief"),
  WIZARD(2, "Wizard"),
  COMMANDO(3, "Commando"),
  MATHEMATICIAN(4, "Mathematician"),
  PROBLEM_SLEUTH(5, "Problem Sleuth"),
  PROLETARIAT(6, "Proletariat"),
  MAIN_ACTIVITY(7, "MainActivity"),
  MAMMALIA(8, "Mammalia"),
  CST_338(9, "CST 338");

  private final int id;
  private final String name;

  CharClass(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static CharClass byId(int id) {
    for (CharClass item : values()) {
      if(item.id == id) return item;
    }
    return null;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
