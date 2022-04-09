package com.skilldistillery.blackjack.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dealer extends Player{
	private static String Name;
	private static boolean printouts = false; 
	
	
	static {
		Name = "The Dealer";
	}


	public Dealer() {
		super();
		this.hand = new ArrayList<Card>();

	}

	public Dealer(List<Card> hand) {
		super();
		this.hand = hand;
	}

	public void addCardObtusicated(Card dealtcard) {
		this.hand.add(0, dealtcard);
		if (printouts) {
			System.out.println("-------------------------------------");
			System.out.println(Name + " was dealt a face down card" );
			System.out.println("-------------------------------------");
		}

	}
	
//	@Override
//	public void addCard(Card dealtcard) {
//		hand.add(dealtcard);
//		System.out.println("-------------------------------------");
//		System.out.println(super.Name + " was dealt a " + dealtcard);
//		System.out.println("-------------------------------------");
//	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dealer [hand=");
		builder.append(hand);
		builder.append("]");
		return builder.toString();
	}

	public void setHand(ArrayList<Card> hand) {
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
		Dealer other = (Dealer) obj;
		return Objects.equals(hand, other.hand);
	}

	public void setName(String name2) {
		super.Name = name2;
		
	}

	}

