package com.example.cnc.enums;

public enum CharRace {
  HUMAN(0, "Human"),
  DWARF(1, "Dwarf"),
  VULCAN(2, "Vulcan"),
  HUTT(3, "Hutt"),
  URQUAN(4, "Ur-Quan"),
  CHEAT(5, "Cheat"),
  INDY_500(6, "Indianapolis 500");

  static int count;
  private final int id;
  private final String name;

  CharRace(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static CharRace byId(int id) {
    for (CharRace item : values()) {
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
