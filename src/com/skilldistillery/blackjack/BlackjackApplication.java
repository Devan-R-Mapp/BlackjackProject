package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.blackjack.cards.Card;
import com.skilldistillery.blackjack.cards.Dealer;
import com.skilldistillery.blackjack.cards.Deck;
import com.skilldistillery.blackjack.cards.Player;

public class BlackjackApplication {
	private static boolean debug = false;

	public static void main(String[] args) {
		BlackjackApplication BJapp = new BlackjackApplication();
		BJapp.run();

	}// public static void main(String[] args)

	public void run() {
		Deck runnerDeck = new Deck();
		ArrayList<Player> BJtable = new ArrayList<Player>();
		runnerDeck = gameStartup(runnerDeck);
		boolean gameIsRunning = true;
		Dealer DL = new Dealer();
		DL.setName("The Dealer");
		BJtable.add(DL);
		Player PL1 = new Player();
		BJtable.add(PL1);
		Scanner sc = new Scanner(System.in);

		while (gameIsRunning) {
			if (runnerDeck.checkDeckSize() > 6) {
				openingDeal(runnerDeck, DL, PL1);
				if (debug) {
					System.out.println(runnerDeck.checkDeckSize());
				}
				player1turn(runnerDeck, PL1, sc);
				if (PL1.getHandValue() > 21) {
					System.out.println("The game is over. " + PL1.getName() + " lost, with a score over 21.");
					gameIsRunning = userchoiceforcontinue(BJtable, gameIsRunning, sc);
					continue;

				} else {
					while (DL.getHandValue() < 17) {
						printPlayerHand(DL);
						if (DL.getHandValue() < 17) {
							DL.addCard(runnerDeck.dealCard());
						}
					}
				}
				gameIsRunning = winChecker(BJtable, gameIsRunning, DL, PL1, sc);
			} else {
				System.out.println("Please restart the game to play more");
				gameIsRunning = false;
			}

		} // while(gameIsRunning)
		if (debug) {
			System.out.println(runnerDeck.checkDeckSize());
			System.out.println("you made it here");
		}
	}// run()

	protected boolean winChecker(ArrayList<Player> BJtable, boolean gameIsRunning, Dealer DL, Player PL1, Scanner sc) {
		if (DL.getHandValue() > 21) {
			System.out.println(
					"The game is over, " + PL1.getName() + " wins as " + DL.getName() + " has a score over 21.");
			gameIsRunning = userchoiceforcontinue(BJtable, gameIsRunning, sc);
		} else if (DL.getHandValue() > PL1.getHandValue()) {
			System.out.println("The game is over, " + PL1.getName() + " has a score of: " + PL1.getHandValue() + " and "
					+ DL.getName() + " has a score of: " + DL.getHandValue());
			System.out.println(DL.getName() + " WINS!");
			gameIsRunning = userchoiceforcontinue(BJtable, gameIsRunning, sc);

		} else if (DL.getHandValue() < PL1.getHandValue()) {
			System.out.println("The game is over, " + PL1.getName() + " has a score of: " + PL1.getHandValue() + " and "
					+ DL.getName() + " has a score of: " + DL.getHandValue());
			System.out.println(PL1.getName() + " WINS!");
			gameIsRunning = userchoiceforcontinue(BJtable, gameIsRunning, sc);
		} else {
			System.out.println("The game is over, " + PL1.getName() + " has a score of: " + PL1.getHandValue() + " and "
					+ DL.getName() + " has a score of: " + DL.getHandValue());
			System.out.println("The game results in a tie.");
			gameIsRunning = userchoiceforcontinue(BJtable, gameIsRunning, sc);

		}
		return gameIsRunning;
	}

	protected boolean userchoiceforcontinue(ArrayList<Player> BJtable, boolean gameIsRunning, Scanner sc) {
		switch (userPlayAgain(sc)) {
		case 1:
			handCleanUp(BJtable);
			return gameIsRunning;
		case 2:
			gameIsRunning = false;
			break;
		default:
			System.out.println("++++++++++++++++++++++++++++++++++");
			System.out.println("Please restart the porgram if you wish to play again.");
			System.out.println("++++++++++++++++++++++++++++++++++");
			break;
		}
		return gameIsRunning;
	}

	protected void handCleanUp(ArrayList<Player> BJtable) {
		for (Player player : BJtable) {
			player.clearHand();
			player.setHandValue(0);
		}
	}

	protected void player1turn(Deck runnerDeck, Player PL1, Scanner sc) {
		boolean playerContinues = true;
		while (PL1.getHandValue() < 21 && playerContinues) {
			printPlayerHand(PL1);
			if (PL1.getHandValue() < 21) {
				switch (userChoice(sc)) {
				case 1:
					PL1.addCard(runnerDeck.dealCard());
					continue;
				case 2:
					playerContinues = false;
					continue;
				default:
					System.out.println("++++++++++++++++++++++++++++++++++");
					System.out.println("Please enter a number on the list.");
					System.out.println("++++++++++++++++++++++++++++++++++");
					continue;
				} // switch
			} // if less than 21
		} // while player one can make a decision
	}

	protected int userPlayAgain(Scanner sc) {
		int userInput;
		try {
			System.out.println("Would you like to play again? ");
			System.out.println("1. Yes");
			System.out.println("2. No");
			userInput = sc.nextInt();
			return userInput;
		} catch (InputMismatchException e) {
//			e.printStackTrace();		
			System.out.println("please enter a number.");
			sc.nextLine();
		}
		return 0;
	}

	protected int userChoice(Scanner sc) {
		int userInput;
		try {
			System.out.println("What would you like to do; ");
			System.out.println("1. Hit");
			System.out.println("2. Stay");
			userInput = sc.nextInt();
			return userInput;
		} catch (InputMismatchException e) {
//			e.printStackTrace();		
			sc.nextLine();
		}

		return 0;
	}

	protected void printPlayerHand(Player Player) {
//		System.out.println("\n" + Player.getName() + "'s hand consists of: ");
		StringBuffer value = new StringBuffer();
		int i = 0;
		Player.setHandValue(0);
		for (Card card : Player.getHand()) {
			i++;
			value.append((i > 1 ? ", " : " ") + card);
			Player.setHandValue(Player.getHandValue() + card.getBlackJackValue());
		}
		System.out.println();
		System.out.println("** " + Player.getName() + " score = " + Player.getHandValue() + " |" + value.toString() + " **");
		System.out.println();

	}

	protected void openingDeal(Deck runnerDeck, Dealer DL, Player PL1) {
		System.out.println("The dealer begins dealing to each player: \n");
		DL.addCardObtusicated(runnerDeck.dealCard());
		PL1.addCard(runnerDeck.dealCard());
		DL.addCard(runnerDeck.dealCard());
		PL1.addCard(runnerDeck.dealCard());
	}

	protected Deck gameStartup(Deck runnerDeck) {
		System.out.println("You approach the Blackjack table as the dealer is shuffling the deck.\n");
		runnerDeck.shuffle();
		System.out.println("You sit down and the dealer begins dealing from a deck of " + runnerDeck.checkDeckSize()
				+ " cards.\n");
		return runnerDeck;
	}// gameStartup()
}// public class BlackjackApplication
