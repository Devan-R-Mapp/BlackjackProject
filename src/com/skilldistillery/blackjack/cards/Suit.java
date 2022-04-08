package com.skilldistillery.blackjack.cards;

public enum Suit {
  HEARTS("Hearts", "♥"), SPADES("Spades", "♠"), CLUBS("Clubs", "♣"), DIAMONDS("Diamonds", "♦");
  private String name;
  private String symbol;

  //clubs (♣), diamonds (♦), hearts (♥), and spades (♠). 
  Suit(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return name;
  }
}