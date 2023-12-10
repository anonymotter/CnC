package com.example.cnc.enums;

public enum CharClass {
  WARRIOR(0, "Warrior"),
  THIEF(1, "Thief"),
  WIZARD(2, "Wizard"),
  COMMANDO(3, "Commando"),
  MATHEMATICIAN(4, "Mathematician"),
  MAIN_ACTIVITY(5, "MainActivity"),
  MAMMALIA(6, "Mammalia"),
  CST_338(7, "CST 338"),
  BOURGEOISIE(8, "Bourgeoisie"),
  PROLETARIAT(9, "Proletariat");

  static int count;
  private final int id;
  private final String name;

  CharClass(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
