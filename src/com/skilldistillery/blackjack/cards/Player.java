package com.skilldistillery.blackjack.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
	public List<Card> hand;
	public String Name;
	public int handValue;
	
	//public StringBuffer myHand = new StringBuffer();

//	public StringBuffer xGetMyBJHand() {
//		StringBuffer value = new StringBuffer();
//		int i = 0;
//		for (Card card : hand) {
//			i++;
//			value.append((i > 1 ? ", " : " ") + card);
//			this.handValue = this.handValue + card.getBlackJackValue();
//		}
//
//		this.myHand = new StringBuffer(" score = " + this.handValue + " |" );
//		this.myHand.append(value);
//		return myHand;
//	}
//
//	public StringBuffer getMyHand() {
//		return myHand;
//	}
//
//	public void setMyHand(StringBuffer myHand) {
//		Player.myHand = myHand;
//	}

	public void clearHand() {
		this.hand.clear();
	}

	public Player() {
		this.hand = new ArrayList<Card>();
		Name = "Player";
		handValue = 0;
	}

	public void setName(String name) {
		Name = name;
	}

	public Integer getHandValue() {
		return handValue;
	}

	public void setHandValue(int handValue) {
		this.handValue = handValue;
	}

	public Player(String name) {
		super();
		Name = name;
	}

	public Player(List<Card> hand) {
		super();
		this.hand = hand;
	}

	public List<Card> getHand() {
		return hand;
	}

	public String getName() {
		return Name;
	}

	public void addCard(Card dealtcard) {
		hand.add(dealtcard);
		System.out.println("-------------------------------------");
		System.out.println(this.Name + " was dealt a " + dealtcard);
		System.out.println("-------------------------------------");
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hand);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(hand, other.hand);
	}

}
