package com.example.cnc.enums;

public enum CharRace {
  HUMAN(0, "Human"),
  DWARF(1, "Dwarf"),
  VULCAN(2, "Vulcan"),
  RODIAN(3, "Rodian"),
  CHEAT(4, "Cheat");

  static int count;
  private final int id;
  private final String name;

  CharRace(int id, String name) {
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
