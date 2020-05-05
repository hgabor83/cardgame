import java.util.*;
import java.io.*;
import java.math.*;

class CardPlayers {
	private int playerId;
	private int playerHealth;
	private int playerMana;
	private int playerDeck;
	private int playerRune;
	private int playerDraw;

	CardPlayers(int playerId, int playerHealth, int playerMana, int playerDeck, int playerRune, int playerDraw) {
		this.playerId = playerId;
		this.playerHealth = playerHealth;
		this.playerMana = playerMana;
		this.playerDeck = playerDeck;
		this.playerRune = playerRune;
		this.playerDraw = playerDraw;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public int getPlayerMana() {
		return playerMana;
	}

	public void setPlayerMana(int playerMana) {
		this.playerMana = playerMana;
	}

	public int getPlayerDeck() {
		return playerDeck;
	}

	public void setPlayerDeck(int playerDeck) {
		this.playerDeck = playerDeck;
	}

	public int getPlayerRune() {
		return playerRune;
	}

	public void setPlayerRune(int playerRune) {
		this.playerRune = playerRune;
	}

	public int getPlayerDraw() {
		return playerDraw;
	}

	public void setPlayerDraw(int playerDraw) {
		this.playerDraw = playerDraw;
	}

}

class Cards {
	private int cardNumber;
	private int instanceId;
	private int location;
	private int cardType;
	private int cost;
	private int attack;
	private int defense;
	private StringBuilder abilities = new StringBuilder();
	private int myHealthChange;
	private int opponentHealthChange;
	private int cardDraw;
	private double heuristicCost;
	private double guardValue;
	private double breakThruValue;
	private double chargeValue;
	private double defenseValue;
	private double attackValue;
	private double drainValue;
	private double lethalValue;
	private double wardValue;
	private int priority;
	private boolean used;
	private boolean summoned;

	Cards(int cardNumber, int instanceId, int location, int cardType, int cost, int attack, int defense,
			String abilities, int myHealthChange, int opponentHealthChange, int cardDraw) {
		this.cardNumber = cardNumber;
		this.instanceId = instanceId;
		this.location = location;
		this.cardType = cardType;
		this.cost = cost;
		this.attack = attack;
		this.defense = defense;
		this.abilities = this.abilities.append(abilities);
		this.myHealthChange = myHealthChange;
		this.opponentHealthChange = opponentHealthChange;
		this.cardDraw = cardDraw;
		this.used = false;
		this.summoned = false;

		/*
		 * // predefined card power list List<Integer> priorities = Arrays.asList(68,
		 * 116, 7, 80, 65, 51, 49, 48, 66, 67, 23, 61, 115, 69, 44, 37, 52, 53, 18, 29,
		 * 50, 54, 82, 32, 114, 95, 79, 64, 99, 111, 33, 84, 3, 62, 103, 11, 96, 97, 77,
		 * 139, 59, 36, 109, 81, 19, 9, 87, 105, 121, 6, 8, 85, 93, 5, 91, 137, 70, 88,
		 * 28, 112, 83, 21, 34, 46, 106, 26, 104, 22, 17, 15, 1, 72, 75, 98, 45, 90, 58,
		 * 25, 13, 39, 133, 12, 35, 100, 41, 16, 118, 30, 129, 119, 60, 135, 38, 71, 76,
		 * 134, 94, 126, 74, 43, 122, 56, 128, 27, 73, 127, 40, 4, 14, 125, 20, 2, 47,
		 * 120, 101, 78, 108, 31, 89, 86, 42, 10, 102, 132, 24, 136, 138, 130, 107, 123,
		 * 117, 113, 131, 57, 124, 63, 92, 55, 140, 110, 144, 145, 147, 142, 149, 158,
		 * 146, 152, 155, 154, 151, 157, 143, 156, 159, 153, 141, 150, 148, 160);
		 */

		// predefined card power list..140 dont used

		List<Integer> priorities = Arrays.asList(68, 116, 7, 80, 65, 51, 49, 48, 66, 67, 23, 61, 115, 69, 44, 37, 52,
				53, 18, 29, 50, 54, 82, 32, 114, 95, 79, 64, 99, 111, 33, 84, 3, 62, 103, 11, 96, 97, 77, 139, 59, 36,
				109, 81, 19, 9, 87, 105, 121, 6, 8, 85, 93, 5, 91, 137, 70, 88, 28, 112, 83, 21, 34, 46, 106, 26, 104,
				22, 17, 15, 1, 72, 75, 98, 45, 90, 58, 25, 13, 39, 133, 12, 35, 100, 41, 16, 118, 30, 129, 119, 60, 135,
				38, 71, 76, 134, 94, 126, 74, 43, 122, 56, 128, 27, 73, 127, 40, 4, 14, 125, 20, 2, 47, 120, 101, 78,
				108, 31, 89, 86, 42, 10, 102, 132, 24, 136, 138, 130, 107, 123, 117, 113, 131, 57, 124, 63, 92, 55, 110,
				144, 145, 147, 142, 149, 158, 146, 152, 155, 154, 151, 157, 143, 156, 159, 153, 141, 150, 148, 160,
				140);

		this.priority = priorities.indexOf(this.cardNumber);

	}

	public boolean isSummoned() {
		return summoned;
	}

	public void setSummoned(boolean summoned) {
		this.summoned = summoned;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public String getAbilities() {
		return abilities.toString();
	}

	public void setAbilities(String abilities, boolean remove) {
		if (!remove)
			this.abilities = this.abilities.append(abilities);
		else
			this.abilities = new StringBuilder(this.abilities.toString().replace(abilities, ""));
	}

	public int getMyHealthChange() {
		return myHealthChange;
	}

	public void setMyHealthChange(int myHealthChange) {
		this.myHealthChange = myHealthChange;
	}

	public int getOpponentHealthChange() {
		return opponentHealthChange;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setOpponentHealthChange(int opponentHealthChange) {
		this.opponentHealthChange = opponentHealthChange;
	}

	public int getCardDraw() {
		return cardDraw;
	}

	public void setCardDraw(int cardDraw) {
		this.cardDraw = cardDraw;
	}

	public double getHeuristicCost() {
		return heuristicCost;
	}

}

class Player {

	public static List<Cards> getNextboardCards(int myMana, List<Cards> myHandCards, List<Cards> myHandCards0,
			List<Cards> myHandCards1, List<Cards> myHandCards2, List<Cards> myHandCards3, List<Cards> myHandCards4,
			List<Cards> myHandCards5, List<Cards> myHandCards6, List<Cards> myHandCards7, List<Cards> myHandCards8,
			List<Cards> myHandCards9, List<Cards> myHandCards10, List<Cards> myHandCards11, List<Cards> myHandCards12) {
		List<Cards> toBeBoardCards = new ArrayList<>();
		List<Cards> guardsToBeBoardCards = new ArrayList<>();
		List<Cards> elseToBeBoardCards = new ArrayList<>();
		int manaToSpend = myMana;

		// we got lists of card separated by costs
		// first guard card then rest

		// System.err.println("////////// MANA before Gs: " + manaToSpend);

		for (Cards card : myHandCards12) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards11) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards10) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards9) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards8) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards7) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards6) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards5) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards4) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards3) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards2) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards1) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards0) {
			if (manaToSpend >= card.getCost() && card.getAbilities().contains("G")) {
				card.setSummoned(true);
				guardsToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}

		// System.err.println("////////// MANA after Gs: " + manaToSpend);

		for (Cards card : myHandCards12) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards11) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards10) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards9) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards8) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards7) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards6) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards5) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards4) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards3) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards2) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards1) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}
		for (Cards card : myHandCards0) {
			if (manaToSpend >= card.getCost() && !card.isSummoned()) {
				elseToBeBoardCards.add(card);
				// manaToSpend = manaToSpend - card.getCost();
			}
		}

		Comparator<Cards> compareByPriority = (Cards c1, Cards c2) -> Integer.compare(c1.getPriority(),
				c2.getPriority());

		Collections.sort(guardsToBeBoardCards, compareByPriority);
		Collections.sort(elseToBeBoardCards, compareByPriority);

		// System.err.println("////////// MANA rest after all: " + manaToSpend);

		toBeBoardCards.addAll(guardsToBeBoardCards);
		toBeBoardCards.addAll(elseToBeBoardCards);

		return toBeBoardCards;

	}

	public static int getWeakestId(List<Cards> myBoardCards, List<Cards> mySummonedCards) {
		// myWeakestBoardCard
		int myWeakestId = -1;
		int myWeakestHP = 100;
		List<Cards> myBCards = new ArrayList<Cards>();
		myBCards.addAll(myBoardCards);
		myBCards.addAll(mySummonedCards);

		// just to be sure
		myWeakestId = myBCards.get(0).getInstanceId();

		// first check the guards
		for (Cards card : myBCards) {
			if (card.getDefense() < myWeakestHP && card.getAbilities().contains("G")) {
				myWeakestHP = card.getDefense();
				myWeakestId = card.getInstanceId();
			}
		}
		// if no guard on the board
		if (myWeakestId == -1)
			for (Cards card : myBCards) {
				if (card.getDefense() < myWeakestHP) {
					myWeakestHP = card.getDefense();
					myWeakestId = card.getInstanceId();
				}
			}

		return myWeakestId;

	}

	/*
	 * public static int getAttackerId(List<Cards> myBoardCards, int targetHP,
	 * boolean myGuardsDontAttack) { // myBoardCards.sort((Cards c1, Cards c2) ->
	 * Integer.compare(c1.attack, // c2.attack)); int attackerId = 0; for (Cards
	 * card : myBoardCards) { if (!card.isUsed()) { if (myGuardsDontAttack &&
	 * card.getAbilities().contains("G")) { // Guards are protected } else {
	 * attackerId = card.getInstanceId(); card.setUsed(true); break; } } }
	 * 
	 * return attackerId; }
	 */

	public static int getAttackerId(List<Cards> myBoardCards, int targetHP, boolean myGuardsDontAttack,
			boolean enemyIsWard) {
		// myBoardCards.sort((Cards c1, Cards c2) -> Integer.compare(c1.attack,
		// c2.attack));
		int attackerId = -1;

		if (enemyIsWard) {
			// attack with the smallest one, to remove the shield Lethal to the end
			myBoardCards.sort(Comparator.comparingInt((Cards c) -> c.getAbilities().contains("L") ? 1 : 0)
					.thenComparing((Cards c1, Cards c2) -> Integer.compare(c1.getAttack(), c2.getAttack())));
			for (Cards card : myBoardCards) {
				if (!card.isUsed()) {
					card.setUsed(true);
					// System.err.println("Enemy is a ward, so we attack with the smallest one: " +
					// card.getInstanceId());
					return card.getInstanceId();
				}
			}
		}

		// first attack with the lowest attacker point if it't enough to beat the enemy
		for (Cards card : myBoardCards) {
			if (!card.isUsed()) {
				// System.err.println("My card is not used, can be attacker: " +
				// card.getInstanceId());
				if (myGuardsDontAttack && card.getAbilities().contains("G")) {
					// Guards are protected
					// System.err.println("Guards are protected!!!");
				} else if (card.getAttack() >= targetHP || card.getAbilities().contains("L")) {
					// System.err.println("Now we chose an attaker AP, targetHP: " +
					// card.getAttack() + " " + targetHP);
					// System.err.println("Card chosen: " + card.getInstanceId());
					card.setUsed(true);
					return card.getInstanceId();
				}
			}
		}

		// if we still dont have chosen attacker
		// choose the first biggest one to attack, Lethal first
		// guards should be protected?
		myBoardCards.sort(Comparator.comparingInt((Cards c) -> c.getAbilities().contains("L") ? 0 : 1)
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c2.getAttack(), c1.getAttack())));

		/*
		 * System.err.println("Now my cardorder:");
		 * 
		 * System.err.println("============My current: "); for (Cards card :
		 * myBoardCards) { System.err.println(" "); System.err.println("ID: " +
		 * card.getInstanceId()); System.err.println("A: " + card.getAttack());
		 * System.err.println("D: " + card.getDefense()); System.err.println("Abi: " +
		 * card.getAbilities()); }
		 * 
		 */
//		System.err.println("No suitable attacker found, we find the first one to attack in the row");
		for (Cards card : myBoardCards) {
			if (!card.isUsed()) {
				if (myGuardsDontAttack && card.getAbilities().contains("G")) {
					// Guards are protected
				} else {
					card.setUsed(true);
					return card.getInstanceId();
				}
			}
		}

		// just to be sure
		return 0;
	}

	public static int getAttackerHP(List<Cards> myBoardCards, int attackerId) {
		int attackerHP = 0;
		for (Cards card : myBoardCards) {
			if (card.getInstanceId() == attackerId) {
				attackerHP = card.getAttack();
			}
		}
		return attackerHP;
	}

	public static boolean isEnemyAttackerADrainer(List<Cards> Cards, int attackerId) {
		System.err.println("Attacker id: " + attackerId);
		boolean drainer = false;
		for (Cards card : Cards) {
			if (card.getInstanceId() == attackerId && card.getAbilities().contains("D")) {
				System.err.println("Drainer found");
				drainer = true;
			}
		}
		return drainer;
	}

	public static boolean isEnemyAttackerAWard(List<Cards> Cards, int attackerId) {
		boolean ward = false;
		for (Cards card : Cards) {
			if (card.getInstanceId() == attackerId && card.getAbilities().contains("W")) {
				ward = true;
			}
		}
		return ward;
	}

	public static boolean isEnemyAttackerABreakThru(List<Cards> Cards, int attackerId) {
		boolean breakThru = false;
		for (Cards card : Cards) {
			if (card.getInstanceId() == attackerId && card.getAbilities().contains("B")) {
				breakThru = true;
			}
		}
		return breakThru;
	}

	public static boolean isMyAttackedABreakThru(List<Cards> myBoardCards, int attackedId) {
		boolean breakThru = false;
		for (Cards card : myBoardCards) {
			if (card.getInstanceId() == attackedId && card.getAbilities().contains("B")) {
				breakThru = true;
			}
		}
		return breakThru;
	}

	public static int getEnemyAttackerAttackPoints(List<Cards> Cards, int attackerId) {
		int attackPoints = 0;
		for (Cards card : Cards) {
			if (card.getInstanceId() == attackerId) {
				attackPoints = card.getAttack();
			}
		}
		return attackPoints;
	}

	public static int getEnemyAttackerDefensePoints(List<Cards> Cards, int attackerId) {
		int defPoints = 0;
		for (Cards card : Cards) {
			if (card.getInstanceId() == attackerId) {
				defPoints = card.getDefense();
			}
		}
		return defPoints;
	}

	public static int getmyAttackedDefensePoints(List<Cards> myExBoardCards, int attackedId) {
		// System.err.println("getMyAttackedDefensePoints called with id: " +
		// attackedId);
		int defPoints = 0;
		for (Cards card : myExBoardCards) {
			if (card.getInstanceId() == attackedId) {
				defPoints = card.getDefense();
				// System.err.println("Defi:" + defPoints);
			}
		}
		return defPoints;
	}

	public static int getMyAttackedAttackPoints(List<Cards> myExBoardCards, int attackedId) {
		// System.err.println("getMyAttackedAttackPoints called with id: " +
		// attackedId);
		int attackPoints = 0;
		for (Cards card : myExBoardCards) {
			if (card.getInstanceId() == attackedId) {
				attackPoints = card.getAttack();
				// System.err.println("Ati:" + attackPoints);
			}
		}
		return attackPoints;
	}

	private static void updateEnemyCard(List<Cards> enemyBoardCards, List<Cards> myBoardCards, int targetId,
			int attackerHP, boolean isAttackerLethal) {
		for (Cards card : enemyBoardCards) {
			if (card.getInstanceId() == targetId) {
				if (card.getAbilities().contains("W")) {
					System.err.println("Enemy is ward, so we remove it");
					card.setAbilities("W", true);
					System.err.println("Enemy ward removed: " + card.getAbilities());
				} else {
					if (isAttackerLethal) {
						System.err.println("Bye: " + card.getInstanceId());
						card.setDefense(0);
					} else if ((card.getDefense() - attackerHP) <= 0) {
						System.err.println("Bye: " + card.getInstanceId());
						card.setDefense(0);
					} else
						card.setDefense(card.getDefense() - attackerHP);
				}
			}
		}
		updateBoard(enemyBoardCards, myBoardCards);
	}

	private static int getTargetIdForLethal(List<Cards> enemyBoardCards) {
		enemyBoardCards.sort(Comparator.comparingInt((Cards c) -> c.getAbilities().contains("G") ? 0 : 1)
				.thenComparing((Cards c) -> c.getAbilities().contains("W") ? 1 : 0)
				.thenComparing((Cards c) -> c.getAbilities().contains("L") ? 0 : 1)
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c2.getAttack(), c1.getAttack()))
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c2.getDefense(), c1.getDefense())));
		if (enemyBoardCards.size() > 0)
			return enemyBoardCards.get(0).getInstanceId();
		else
			return -1;
	}

	private static int getTargetId(List<Cards> enemyBoardCards, int attackerHP) {
		boolean enemyHasGuard = false;
		enemyBoardCards.sort(Comparator.comparingInt((Cards c) -> c.getAbilities().contains("G") ? 0 : 1)
				.thenComparing((Cards c) -> c.getAbilities().contains("W") ? 1 : 0)
				.thenComparing((Cards c) -> c.getAbilities().contains("L") ? 0 : 1)
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c2.getAttack(), c1.getAttack()))
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c1.getDefense(), c2.getDefense())));
		// if no enemy boardcard attack the boss
		if (enemyBoardCards.size() > 0) {
			for (Cards card : enemyBoardCards) {
				if (card.getAbilities().contains("G"))
					enemyHasGuard = true;
			}
			// if enemy has guard it should be attacked first
			if (!enemyHasGuard)
				for (Cards card : enemyBoardCards) {
					// if found suitable target, who can be killed
					if (card.getDefense() == attackerHP) {
						System.err.println("Enemy has no guard, attack target, with HP: " + card.getInstanceId() + " "
								+ attackerHP);
						return card.getInstanceId();
					}
				}
			// attack the weakest one
			return enemyBoardCards.get(0).getInstanceId();
		} else
			return -1;
	}

	private static boolean updateBoard(List<Cards> enemyBoardCards, List<Cards> myBoardCards) {
		List<Cards> enemyBoardCardsTemp = new ArrayList<>();
		for (Cards card : enemyBoardCards) {
			if (card.getDefense() != 0) {
				System.err.println("EC: " + card.getInstanceId() + "readded with HP:" + card.getDefense());
				enemyBoardCardsTemp.add(card);
			}
		}
		enemyBoardCards.clear();
		enemyBoardCards.addAll(enemyBoardCardsTemp);

		enemyBoardCards.sort(Comparator.comparingInt((Cards c) -> c.getAbilities().contains("G") ? 0 : 1)
				.thenComparing((Cards c) -> c.getAbilities().contains("W") ? 1 : 0)
				.thenComparing((Cards c) -> c.getAbilities().contains("L") ? 0 : 1)
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c2.getAttack(), c1.getAttack()))
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c1.getDefense(), c2.getDefense())));

		myBoardCards.sort(Comparator.comparingInt((Cards c) -> c.getAbilities().contains("G") ? 1 : 0)
				.thenComparing((Cards c) -> c.getAbilities().contains("W") ? 0 : 1)
				.thenComparing((Cards c) -> c.getAbilities().contains("L") ? 0 : 1)
				.thenComparing((Cards c1, Cards c2) -> Integer.compare(c2.getAttack(), c1.getAttack())));

		// myAPPool, myDPPool, enemyAPPool, enemyDPPool
		int[] pointPools = new int[4];

		for (Cards card : myBoardCards) {
			pointPools[0] = pointPools[0] + card.getAttack();
			pointPools[1] = pointPools[1] + card.getDefense();
		}

		for (Cards card : enemyBoardCards) {
			pointPools[2] = pointPools[2] + card.getAttack();
			pointPools[3] = pointPools[3] + card.getDefense();
		}
		boolean myGuardsDontAttack = false;

		// if I cannot beat the enemy board, my guards wont attack
		// if (!(pointPools[0] >= pointPools[3]))
		// myGuardsDontAttack = true;

		/*
		 * System.err.println("==========EB current: "); for (Cards card :
		 * enemyBoardCards) { System.err.println(" "); System.err.println("ID: " +
		 * card.getInstanceId()); System.err.println("A: " + card.getAttack());
		 * System.err.println("D: " + card.getDefense()); System.err.println("Abi: " +
		 * card.getAbilities()); }
		 * 
		 * System.err.println("============My current: "); for (Cards card :
		 * myBoardCards) { System.err.println(" "); System.err.println("ID: " +
		 * card.getInstanceId()); System.err.println("A: " + card.getAttack());
		 * System.err.println("D: " + card.getDefense()); System.err.println("Abi: " +
		 * card.getAbilities()); System.err.println("Used: " + card.isUsed()); }
		 * 
		 */
		return myGuardsDontAttack;
	}

	private static void updateMyCardWithItem(List<Cards> myBoardCards, List<Cards> enemyBoardCards,
			List<Cards> myHandCards, int weakestId, int instanceId) {
		for (Cards card : myBoardCards) {
			if (card.getInstanceId() == weakestId) {
				for (Cards item : myHandCards) {
					if (item.getInstanceId() == instanceId) {
						card.setAbilities(item.getAbilities(), false);
						card.setAttack(card.getAttack() + item.getAttack());
						card.setDefense(card.getDefense() + item.getDefense());
					}
				}
			}
		}
		updateBoard(enemyBoardCards, myBoardCards);
	}

	private static void updateEnemyCardWithItem(List<Cards> enemyBoardCards, List<Cards> myBoardCards,
			List<Cards> myHandCards, int enemyId, int instanceId) {
		for (Cards card : enemyBoardCards) {
			if (card.getInstanceId() == enemyId) {
				for (Cards item : myHandCards) {
					if (item.getInstanceId() == instanceId) {
						// card.setAbilities(item.getAbilities());
						card.setAttack(card.getAttack() - item.getAttack());
						card.setDefense(card.getDefense() - item.getDefense());
					}
				}
			}
		}
		updateBoard(enemyBoardCards, myBoardCards);
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		List<CardPlayers> cardPlayers = new ArrayList<>();
		List<Cards> cards = new ArrayList<>();
		List<Cards> myCards = new ArrayList<>();
		List<Cards> myHandCards = new ArrayList<>();
		List<Cards> myBoardCards = new ArrayList<>();
		List<Cards> myExBoardCards = new ArrayList<>();
		List<Cards> myToBeSummonedCards = new ArrayList<>();
		List<Cards> mySummonedCards = new ArrayList<>();
		List<Cards> enemyBoardGuardCards = new ArrayList<>();
		List<Cards> enemyBoardCards = new ArrayList<>();
		List<Cards> myHandCards0 = new ArrayList<>();
		List<Cards> myHandCards1 = new ArrayList<>();
		List<Cards> myHandCards2 = new ArrayList<>();
		List<Cards> myHandCards3 = new ArrayList<>();
		List<Cards> myHandCards4 = new ArrayList<>();
		List<Cards> myHandCards5 = new ArrayList<>();
		List<Cards> myHandCards6 = new ArrayList<>();
		List<Cards> myHandCards7 = new ArrayList<>();
		List<Cards> myHandCards8 = new ArrayList<>();
		List<Cards> myHandCards9 = new ArrayList<>();
		List<Cards> myHandCards10 = new ArrayList<>();
		List<Cards> myHandCards11 = new ArrayList<>();
		List<Cards> myHandCards12 = new ArrayList<>();

		int myDeckCards0 = 0;
		int myDeckCards1 = 0;
		int myDeckCards2 = 0;
		int myDeckCards3 = 0;
		int myDeckCards4 = 0;
		int myDeckCards5 = 0;
		int myDeckCards6 = 0;
		int myDeckCards7 = 0;
		int myDeckCards8 = 0;
		int myDeckCards9 = 0;
		int myDeckCards10 = 0;
		int myDeckCards11 = 0;
		int myDeckCards12 = 0;

		// Comparator<Cards> compareByHeuristicCost = (Cards c1, Cards c2) ->
		// Double.compare(c1.getHeuristicCost(),
		// c2.getHeuristicCost());

		// Comparator<Cards> compareByAttackPoints = (Cards c1, Cards c2) ->
		// Integer.compare(c1.getAttack(),
		// c2.getAttack());
		// Comparator<Cards> compareByDefensePoints = (Cards c1, Cards c2) ->
		// Integer.compare(c1.getDefense(),
		// c2.getDefense());
		Comparator<Cards> compareByPriority = (Cards c1, Cards c2) -> Integer.compare(c1.getPriority(),
				c2.getPriority());

		// game loop
		int turn = 0;
		int boardCards = 0;
		double minHeuristicCost;
		int enemyBossHP = 30;
		int opponentHealthChange = 0;
		double cmcActual = 0;
		while (true) {
			turn++;
			System.err.println("=========================================");
			System.err.println("!!!!!!!!TURN: " + turn);
			System.err.println("******Enemy Boss starts with**********: " + enemyBossHP);
			cardPlayers.clear();
			cards.clear();

			for (int i = 0; i < 2; i++) {
				int playerHealth = in.nextInt();
				int playerMana = in.nextInt();
				int playerDeck = in.nextInt();
				int playerRune = in.nextInt();
				int playerDraw = in.nextInt();
				CardPlayers cardPlayer = new CardPlayers(i, playerHealth, playerMana, playerDeck, playerRune,
						playerDraw);
				cardPlayers.add(cardPlayer);

			}
			int opponentHand = in.nextInt();
			int opponentActions = in.nextInt();
			if (in.hasNextLine()) {
				in.nextLine();
			}

			List<String> cardNumberAndActionList = new ArrayList<String>();
			for (int i = 0; i < opponentActions; i++) {
				String cardNumberAndAction = in.nextLine();
				System.err.println("ENEMY: " + cardNumberAndAction);
				cardNumberAndActionList.add(cardNumberAndAction);
			}

			myCards.clear();
			myHandCards.clear();
			myHandCards0.clear();
			myHandCards1.clear();
			myHandCards2.clear();
			myHandCards3.clear();
			myHandCards4.clear();
			myHandCards5.clear();
			myHandCards6.clear();
			myHandCards7.clear();
			myHandCards8.clear();
			myHandCards9.clear();
			myHandCards10.clear();
			myHandCards11.clear();
			myHandCards12.clear();
			myBoardCards.clear();
			enemyBoardGuardCards.clear();
			enemyBoardCards.clear();
			myToBeSummonedCards.clear();
			mySummonedCards.clear();
			boardCards = 0;

			int cardCount = in.nextInt();
			for (int i = 0; i < cardCount; i++) {
				int cardNumber = in.nextInt();
				int instanceId = in.nextInt();
				int location = in.nextInt();
				int cardType = in.nextInt();
				int cost = in.nextInt();
				int attack = in.nextInt();
				int defense = in.nextInt();
				String abilities = in.next();
				int myHealthChange = in.nextInt();
				opponentHealthChange = in.nextInt();

				int cardDraw = in.nextInt();
				Cards card = new Cards(cardNumber, instanceId, location, cardType, cost, attack, defense, abilities,
						myHealthChange, opponentHealthChange, cardDraw);
				cards.add(card);
			}

			for (int i = 0; i < cardNumberAndActionList.size(); i++) {
				String[] enemyActionArray = cardNumberAndActionList.get(i).split(" ", -1);
				// System.err.println("ENEMYAA:" + enemyActionArray[1]);
				if (enemyActionArray[1].contentEquals("SUMMON") || enemyActionArray[1].contentEquals("USE")) {
					int enemyAttackerCardNumber = Integer.parseInt(enemyActionArray[0]);
					System.err.println("Enemy summon related to BOSS HP!");
					switch (enemyAttackerCardNumber) {
					case 1: {
						enemyBossHP = enemyBossHP + 1;
						break;
					}
					case 13: {
						enemyBossHP = enemyBossHP + 1;
						break;
					}
					case 25: {
						enemyBossHP = enemyBossHP - 2;
						break;
					}
					case 27: {
						enemyBossHP = enemyBossHP + 2;
						break;
					}
					case 45: {
						enemyBossHP = enemyBossHP - 3;
						break;
					}
					case 59: {
						enemyBossHP = enemyBossHP + 1;
						break;
					}
					case 73: {
						enemyBossHP = enemyBossHP + 4;
						break;
					}
					case 89: {
						enemyBossHP = enemyBossHP + 2;
						break;
					}
					case 92: {
						enemyBossHP = enemyBossHP + 2;
						break;
					}
					case 107: {
						enemyBossHP = enemyBossHP + 3;
						break;
					}
					case 113: {
						enemyBossHP = enemyBossHP + 4;
						break;
					}
					case 130: {
						enemyBossHP = enemyBossHP + 4;
						break;
					}
					case 153: {
						enemyBossHP = enemyBossHP + 5;
						break;
					}
					case 156: {
						enemyBossHP = enemyBossHP + 3;
						break;
					}
					case 157: {
						enemyBossHP = enemyBossHP + 1;
						break;
					}
					case 159: {
						enemyBossHP = enemyBossHP + 3;
						break;
					}
					case 160: {
						enemyBossHP = enemyBossHP + 2;
						break;
					}

					default:
						break;
					}
					System.err.println("Enemy summon related to BOSS HP changed value: " + enemyBossHP);
				} else if (enemyActionArray[1].contentEquals("ATTACK")) {
					int enemyAttackerId = Integer.parseInt(enemyActionArray[2]);
					// System.err.println("Attacker: " + enemyAttackerId);
					int myAttackedId = Integer.parseInt(enemyActionArray[3]);
					System.err.println("Attacked: " + myAttackedId);
					int enemyAttackPoints = getEnemyAttackerAttackPoints(cards, enemyAttackerId);
					// System.err.println("Enemy AP: " + enemyAttackPoints);
					int enemyDefensePoints = getEnemyAttackerDefensePoints(cards, enemyAttackerId);
					// System.err.println("Enemy DP: " + enemyDefensePoints);
					int myAttackedHP = 0;
					int myAttackedAttackPoints = getMyAttackedAttackPoints(myExBoardCards, myAttackedId);
					// System.err.println("My AP: " + myAttackedAttackPoints);

					System.err.println("Enemy D: " + isEnemyAttackerADrainer(cards, enemyAttackerId));
					System.err.println("Enemy B: " + isEnemyAttackerABreakThru(cards, enemyAttackerId));

					if (myAttackedId != -1) {
						myAttackedHP = getmyAttackedDefensePoints(myExBoardCards, myAttackedId);
						// System.err.println("My DP: " + myAttackedHP);
					}
					if (isEnemyAttackerADrainer(cards, enemyAttackerId)) {
						if (myAttackedId == -1) {
							System.err.println("Enemy Drainer**********: " + enemyAttackPoints);
							enemyBossHP = enemyBossHP + enemyAttackPoints;
							System.err.println("Boss left**********: " + enemyBossHP);
						} else {
							if (isEnemyAttackerABreakThru(cards, enemyAttackerId)) {
								enemyBossHP = enemyBossHP + enemyAttackPoints;
								System.err.println("Enemy is D and B");
								System.err.println("Enemy AP: " + enemyAttackPoints);
								System.err.println("BHP: " + enemyBossHP);
							} else {
								System.err.println("Enemy Drainer attackink nonboss**********: "
										// + Math.min(enemyAttackPoints, myAttackedHP)
										+ enemyAttackPoints);
								enemyBossHP = enemyBossHP +
								// Math.min(enemyAttackPoints, myAttackedHP);
										+enemyAttackPoints;
								System.err.println("Boss left**********: " + enemyBossHP);
							}
						}
					}
					if (isMyAttackedABreakThru(myBoardCards, myAttackedId)) {
						if (myAttackedId != -1) {
							System.err.println("My Attacked is a BT");
							// enemyBossHP = enemyBossHP + enemyDefensePoints - myAttackedAttackPoints;
						}
					}
				}
			}

			System.err.println("---------Cards-------");
			for (Cards card : cards) {
				Cards myCard = new Cards(card.getCardNumber(), card.getInstanceId(), card.getLocation(),
						card.getCardType(), card.getCost(), card.getAttack(), card.getDefense(), card.getAbilities(), 0,
						0, 0);

				if (card.getLocation() != -1) {

					myCards.add(myCard);

					if (card.getLocation() == 0) {
						myHandCards.add(myCard);
						switch (card.getCost()) {
						case 0: {
							myHandCards0.add(myCard);
							break;
						}
						case 1: {
							myHandCards1.add(myCard);
							break;
						}
						case 2: {
							myHandCards2.add(myCard);
							break;
						}
						case 3: {
							myHandCards3.add(myCard);
							break;
						}
						case 4: {
							myHandCards4.add(myCard);
							break;
						}
						case 5: {
							myHandCards5.add(myCard);
							break;
						}
						case 6: {
							myHandCards6.add(myCard);
							break;
						}
						case 7: {
							myHandCards7.add(myCard);
							break;
						}
						case 8: {
							myHandCards8.add(myCard);
							break;
						}
						case 9: {
							myHandCards9.add(myCard);
							break;
						}
						case 10: {
							myHandCards10.add(myCard);
							break;
						}
						case 11: {
							myHandCards11.add(myCard);
							break;
						}
						case 12: {
							myHandCards12.add(myCard);
							break;
						}
						default:
							System.err.print("----UNEXPECTED----- " + card.getCost());
							break;
						}

					}
					if (card.getLocation() == 1) {
						myBoardCards.add(myCard);
					}

				} else {
					enemyBoardCards.add(myCard);
					if (myCard.getAbilities().contains("G"))
						enemyBoardGuardCards.add(myCard);
				}
			}

			Collections.sort(myHandCards0, compareByPriority);
			Collections.sort(myHandCards1, compareByPriority);
			Collections.sort(myHandCards2, compareByPriority);
			Collections.sort(myHandCards3, compareByPriority);
			Collections.sort(myHandCards4, compareByPriority);
			Collections.sort(myHandCards5, compareByPriority);
			Collections.sort(myHandCards6, compareByPriority);
			Collections.sort(myHandCards7, compareByPriority);
			Collections.sort(myHandCards8, compareByPriority);
			Collections.sort(myHandCards9, compareByPriority);
			Collections.sort(myHandCards10, compareByPriority);
			Collections.sort(myHandCards11, compareByPriority);
			Collections.sort(myHandCards12, compareByPriority);

			// Mana classification
			int myMana = cardPlayers.get(0).getPlayerMana();
			// System.err.println("////////////MANA: " + myMana);

			List<Cards> toBeboardCards = getNextboardCards(myMana, myHandCards, myHandCards0, myHandCards1,
					myHandCards2, myHandCards3, myHandCards4, myHandCards5, myHandCards6, myHandCards7, myHandCards8,
					myHandCards9, myHandCards10, myHandCards11, myHandCards12);

			int itemIndex = -1;

			myHandCards.clear();
			myHandCards.addAll(toBeboardCards);

			String[] chargeString = { "", "", "", "", "", "" };
			String[] attackString = { "", "", "", "", "", "" };
			String summonString = "";
			String[] summonedCardString = { "", "", "", "", "", "" };

			int lastSummonedId = 0;

			int summonedC = 0;
			int targetId = -1;
			int targetHP = 0;
			int targetNr = 0;
			int attackerId = 0;
			int attackerHP = 0;

			int enemyGuardsHP = 100;

			// set the card orders
			boolean myGuardsDontAttack = updateBoard(enemyBoardCards, myBoardCards);

			boolean killerMode = false;

			if (turn > 30) {
				// Set target
				targetNr = 0;
				if (enemyBoardGuardCards.size() == 0) {
					System.err.println("---------------------------NO GUARD------------------------------");
					int myAP = 0;

					for (Cards card : myBoardCards) {
						myAP = myAP + card.getAttack();
					}

					System.err.println("my AP, boss HP " + myAP + " " + enemyBossHP);

					// myAP
					if (myAP >= enemyBossHP) {
						killerMode = true;
						System.err.println("---------------------------KILLERMODE------------------------------");
					}
				}

				/*
				 * if (enemyBoardCards.size() > 0 && !killerMode) { targetId =
				 * enemyBoardCards.get(targetNr).getInstanceId();
				 * System.err.println("Actual Target: " + targetId); targetHP =
				 * enemyBoardCards.get(targetNr).getDefense(); if
				 * (enemyBoardCards.get(targetNr).getAbilities().contains("W")) { isEnemyAWard =
				 * true; System.err.println("***Ward: " + targetId); } else isEnemyAWard =
				 * false;
				 * 
				 * }
				 */
				// System.err.println("IN HAND--------------------");
				// for (Cards card : myHandCards) {
				// System.err.println(card.getInstanceId());
				// }

				// Mana calc
				int summonedCardCount = 0;
				for (Cards card : myHandCards) {
					if (myBoardCards.size() != 0) {
						// System.err.println("We have boardcard at summon!");
						// System.err.println("Card to investigate: " + card.getInstanceId());
						// we have boardcard
						if ((card.getCardType() == 0 || card.getCardType() == 1 || card.getCardType() == 3)
								|| (enemyBoardCards.size() != 0 && card.getCardType() == 2))

							// only use special green cards if there is sense
							// +W
							if (card.getCardNumber() == 137) {
								for (Cards bc : myBoardCards) {
									if (!bc.getAbilities().contains("W")) {
										;
										if (myMana >= card.getCost()) {
											myMana = myMana - card.getCost();
											myToBeSummonedCards.add(card);
											// System.err.println("Summoned card added: " + card.getInstanceId());
											break;
										}
									}
								}
								// +G
							} else if (card.getCardNumber() == 138) {
								for (Cards bc : myBoardCards) {
									if (!bc.getAbilities().contains("G")) {
										if (myMana >= card.getCost()) {
											myMana = myMana - card.getCost();
											myToBeSummonedCards.add(card);
											// System.err.println("Summoned card added: " + card.getInstanceId());
											break;
										}
									}
								}
								// +LW
							} else if (card.getCardNumber() == 139) {
								for (Cards bc : myBoardCards) {
									if (!bc.getAbilities().contains("L") || !bc.getAbilities().contains("W")) {
										if (myMana >= card.getCost()) {
											myMana = myMana - card.getCost();
											myToBeSummonedCards.add(card);
											// System.err.println("Summoned card added: " + card.getInstanceId());
											break;
										}
									}
								}
							} else
							// if enemy board not empty item 2 can be used too
							if (myMana >= card.getCost()) {
								myMana = myMana - card.getCost();
								myToBeSummonedCards.add(card);
								// System.err.println("Summoned card added: " + card.getInstanceId());
							}
					} else {
						// System.err.println("We dont have boardcard at summon!");
						// System.err.println("Card to investigate: " + card.getInstanceId());
						// we dont have boardcard
						if ((card.getCardType() == 0 || card.getCardType() == 3)
								// only creature or blue items can be used or
								|| (enemyBoardCards.size() != 0 && card.getCardType() == 2))
							// if enemy board not empty item 2 can be used too
							if (myMana >= card.getCost()) {
								// if no boardcard yet, then green items are useless

								myMana = myMana - card.getCost();
								myToBeSummonedCards.add(card);
								// System.err.println("Summoned card added: " + card.getInstanceId());
							}
					}

				}

				// System.err.println("Summoned card count: " + myToBeSummonedCards.size());
				// System.err.println("Mana left: " + myMana);

				// run again for the green items sake
				for (Cards card : myHandCards) {
					// System.err.println("Lets check again the cards: " + card.getInstanceId());
					if (myToBeSummonedCards.lastIndexOf(card) == -1) {
						// System.err.println("Not summoned yet cards: " + card.getInstanceId());
						// has creature, so green items can come
						if (myToBeSummonedCards.size() != 0 && card.getCardType() == 1) {
							// System.err.println("Not summoned yet green cards: " + card.getInstanceId());
							if (card.getCardNumber() == 137) {
								for (Cards bc : myBoardCards) {
									if (!bc.getAbilities().contains("W")) {
										if (myMana >= card.getCost()) {
											myMana = myMana - card.getCost();
											myToBeSummonedCards.add(card);
											// System.err.println("Summoned card added: " + card.getInstanceId());
											break;
										}
									}
								}
								// +G
							} else if (card.getCardNumber() == 138) {
								for (Cards bc : myBoardCards) {
									if (!bc.getAbilities().contains("G")) {
										if (myMana >= card.getCost()) {
											myMana = myMana - card.getCost();
											myToBeSummonedCards.add(card);
											// System.err.println("Summoned card added: " + card.getInstanceId());
											break;
										}
									}
								}
								// +LW
							} else if (card.getCardNumber() == 139) {
								for (Cards bc : myBoardCards) {
									if (!bc.getAbilities().contains("L") || !bc.getAbilities().contains("W")) {
										if (myMana >= card.getCost()) {
											myMana = myMana - card.getCost();
											myToBeSummonedCards.add(card);
											// System.err.println("Summoned card added: " + card.getInstanceId());
											break;
										}
									}
								}
							} else
							// if enemy board not empty item 2 can be used too
							if (myMana >= card.getCost()) {
								myMana = myMana - card.getCost();
								myToBeSummonedCards.add(card);
								// System.err.println("Summoned card added: " + card.getInstanceId());
							}
						}
					}
				}

				// Chargers
				int i = -1;
				// System.err.println("Summoned count:" + myToBeSummonedCards.size());
				for (Cards sc : myToBeSummonedCards) {
					i++;
					if (sc.getAbilities().contains("C")) {
						// System.err.println("We have a charger: " + sc.getInstanceId());
						attackerHP = sc.getAttack();
						if (enemyBoardCards.size() > 0) {
							targetId = getTargetId(enemyBoardCards, attackerHP);
							for (Cards card : enemyBoardCards) {
								if (card.getInstanceId() == targetId) {
									targetHP = card.getDefense();
									break;
								}
							}
						} else
							targetId = -1;

						if (killerMode) {
							targetId = -1;
						}

						if (myGuardsDontAttack && sc.getAbilities().contains("G")) {
							// protect the guards in danger
						} else {

							if (sc.getAbilities().contains("L")) {
								chargeString[i] = "; ATTACK " + sc.getInstanceId() + " "
										+ getTargetIdForLethal(enemyBoardCards);
								updateEnemyCard(enemyBoardCards, myBoardCards, targetId, attackerHP, true);
								// System.err.println("We have a lethal: " + chargeString[i]);
							} else {
								chargeString[i] = "; ATTACK " + sc.getInstanceId() + " " + targetId;
								// System.err.println("We have a normal: " + chargeString[i]);
								updateEnemyCard(enemyBoardCards, myBoardCards, targetId, attackerHP, false);
							}
						}
						if (!killerMode) {
							if (targetId == -1) {
								enemyBossHP = enemyBossHP - attackerHP;
								System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!ChargerToBoss");
								System.err.println("BossHP: " + enemyBossHP);
								System.err.println("AttackerHP: " + attackerHP);
							} else {
								if (sc.getAbilities().contains("B")) {
									int extractHP = attackerHP - targetHP;
									if (extractHP > 0)
										enemyBossHP = enemyBossHP - extractHP;
									System.err.println("Target: " + targetHP);
									System.err.println("!!!!!!!!!!!!!!!!SummonedBreakTruAttacker: " + extractHP);
									System.err.println("BossHP: " + enemyBossHP);
								}
							}
						}
					}
				}

				// A certain card to summon or item to use
				i = -1;
				for (Cards sc : myToBeSummonedCards) {
					// update enemy HP
					System.err.println("BOSS HP related summon: " + sc.getCardNumber());
					i++;
					switch (sc.getCardNumber()) {
					case 2: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 13: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 24: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 25: {
						enemyBossHP = enemyBossHP - 2;
						break;
					}
					case 26: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 30: {
						enemyBossHP = enemyBossHP - 2;
						break;
					}
					case 31: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 59: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 67: {
						enemyBossHP = enemyBossHP - 2;
						break;
					}
					case 78: {
						enemyBossHP = enemyBossHP - 5;
						break;
					}
					case 91: {
						enemyBossHP = enemyBossHP + 1;
						break;
					}
					case 102: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 146: {
						enemyBossHP = enemyBossHP - 2;
						break;
					}
					case 154: {
						enemyBossHP = enemyBossHP - 2;
						break;
					}
					case 155: {
						enemyBossHP = enemyBossHP - 1;
						break;
					}
					case 156: {
						enemyBossHP = enemyBossHP - 3;
						break;
					}
					case 160: {
						enemyBossHP = enemyBossHP - 2;
						break;
					}

					default:
						break;
					}

					System.err.println("BOSS HP changed related to summon: " + enemyBossHP);

					attackerId = sc.getInstanceId();
					attackerHP = sc.getAttack();

					if (enemyBoardCards.size() > 0)
						// targetId = enemyBoardCards.get(0).getInstanceId();
						targetId = getTargetId(enemyBoardCards, attackerHP);
					else
						targetId = -1;

					if (killerMode) {
						targetId = -1;
					}

					switch (sc.getCardType()) {
					case 0: {

						summonedCardString[i] = "SUMMON " + attackerId + chargeString[i];
						// System.err.println("We have a basic summonstring w charge: " +
						// summonedCardString[i]);

						sc.setUsed(true);
						mySummonedCards.add(sc);
						myExBoardCards.add(sc);

						break;
					}
					case 1: {
						int weakestId = getWeakestId(myBoardCards, mySummonedCards);
						summonedCardString[i] = "USE " + attackerId + " " + weakestId;
						updateMyCardWithItem(myBoardCards, enemyBoardCards, myHandCards, weakestId, attackerId);
						// System.err.println("We have an 1 type item summonstring: " +
						// summonedCardString[i]);
						break;
					}
					case 2: {

						summonedCardString[i] = "USE " + attackerId + " " + targetId;
						updateEnemyCardWithItem(enemyBoardCards, myBoardCards, myHandCards, targetId, attackerId);
						// System.err.println("We have an 2 type item summonstring: " +
						// summonedCardString[i]);
						break;
					}
					case 3: {

						summonedCardString[i] = "USE " + attackerId + " -1";
						// at these items the defense value is the attack value in -
						attackerHP = sc.getDefense();
						enemyBossHP = enemyBossHP + attackerHP;
						System.err.println("!!!!!!!!!!Item to BOSS: " + attackerHP);
						System.err.println("BossHP: " + enemyBossHP);
						// System.err.println("We have an 3 type item summonstring: " +
						// summonedCardString[i]);

						break;
					}
					default:
						summonedCardString[i] = "";
						break;
					}
					summonString = summonString + ";" + summonedCardString[i];
				}

				// Board card attackers
				boolean enemyIsWard = false;
				attackerId = 0;
				boolean isLethal = false, isBreakThru = false;
				System.err.println("000000000000000 ATTACKERS 00000000000000");
				boolean noEnemyBoardGuard = true;
				for (int b = 0; b < myBoardCards.size(); b++) {
					// Cards bc = myBoardCards.get(b);
					// System.err.println("00000 ATTACKER: " + b + ". " + bc.getInstanceId());
					// System.err.println(b + ". " + bc.isUsed());
					noEnemyBoardGuard = true;
					for (Cards c : enemyBoardCards) {
						if (c.getAbilities().contains("G")) {
							System.err.println("%%%%%% EBG: " + c.getInstanceId());
							noEnemyBoardGuard = false;
							break;
						}
					}

					if (noEnemyBoardGuard) {
						System.err.println("---------------------------NO GUARD------------------------------");
						int myAP = 0;

						for (Cards card : myBoardCards) {
							if (!card.isUsed())
								myAP = myAP + card.getAttack();
						}

						System.err.println("my left AP, boss HP " + myAP + " " + enemyBossHP);

						// myAP
						if (myAP >= enemyBossHP) {
							killerMode = true;
							System.err.println("---------------------------KILLERMODE------------------------------");
						}
					}

					if (!killerMode) {
						enemyIsWard = false;
						isLethal = false;
						isBreakThru = false;
						if (enemyBoardCards.size() > 0) {
							targetId = enemyBoardCards.get(0).getInstanceId();
							targetHP = enemyBoardCards.get(0).getDefense();
							// System.err.println("!!!!!!!!!!!!!!Target ID: " + targetId);
							// System.err.println("!!!!!!!!!!!!!!Target HP: " + targetHP);
							enemyIsWard = isEnemyAttackerAWard(enemyBoardCards, targetId);
						} else {
							targetId = -1;
						}

						// just an initialization
						Cards ac = myBoardCards.get(0);

						attackerId = getAttackerId(myBoardCards, targetHP, myGuardsDontAttack, enemyIsWard);
						for (Cards a : myBoardCards) {
							if (a.getInstanceId() == attackerId) {
								ac = a;
							}
						}

						System.err.println("My attacker: " + attackerId);
						attackerHP = getAttackerHP(myBoardCards, attackerId);

						if (ac.getAbilities().contains("L")) {
							isLethal = true;
						}

						if (ac.getAbilities().contains("B")) {
							isBreakThru = true;
						}

						if (isLethal) {
							targetId = getTargetIdForLethal(enemyBoardCards);
						}

						if (myGuardsDontAttack && ac.getAbilities().contains("G")) {
							System.err.println("Protect our guards!: " + attackString[b]);
						} else {
							attackString[b] = ";ATTACK " + attackerId + " " + targetId;
							// System.err.println("We have a normal attackstring: " + attackString[b]);
						}
						if (targetId != -1) {
							updateEnemyCard(enemyBoardCards, myBoardCards, targetId, attackerHP, isLethal);
						}

						if (targetId == -1) {
							enemyBossHP = enemyBossHP - attackerHP;
							System.err.println("!!!!!!!!!!!!!AttackerToBoss: " + attackerHP);
							System.err.println("BossHP: " + enemyBossHP);
							System.err.println("AttackerHP: " + attackerHP);
						} else {
							if (isBreakThru) {
								int extractHP = attackerHP - targetHP;
								if (extractHP > 0)
									enemyBossHP = enemyBossHP - extractHP;
								System.err.println("Target: " + targetHP);
								System.err.println("!!!!!!!!!!!!!!!!BreakTruAttacker: " + extractHP);
								System.err.println("BossHP: " + enemyBossHP);
							}
						}
					} else {
						attackString[b] = ";ATTACK " + myBoardCards.get(b).getInstanceId() + " " + -1;
					}
				}

				System.out.println(summonString + attackString[0] + attackString[1] + attackString[2] + attackString[3]
						+ attackString[4] + attackString[5]);

			} else {
				// Draft phase
				double prio = 1000000;
				int cardNr = 0;
				int cardId = 0;
				int cost = 0;
				int type = 0;
				double priorityOfCard = 0;
				double cmcDiffActual = 100;
				int itemNr = 0;
				for (Cards card : cards) {
					System.err.println("Drafting phase: " + cardNr + " prio: " + card.getPriority());
					System.err.println("Type: " + card.getCardType());
					System.err.println("Cost: " + card.getCost());

					// mana curve
//					if (card.getCardType() != 0 && itemNr == 3) {
					// priorityOfCard = card.getPriority() * 1000.0;
					// } else
					priorityOfCard = (double) card.getPriority();
					// }
					// the big cost creature number got a limit
					// if (card.getCost() >= 7 && ((myDeckCards7 + myDeckCards8 + myDeckCards9 +
					// myDeckCards10
					// + myDeckCards11 + myDeckCards12) >= 6))
					// priorityOfCard = card.getPriority() * 1000.0;
					// else
					// priorityOfCard = (double) card.getPriority();
					// } else
					// priorityOfCard = (double) card.getPriority();
					/*
					 * if (card.getCardType() == 0) { if (card.getCost() >= 6 && ((myDeckCards6 +
					 * myDeckCards7 + myDeckCards8 + myDeckCards9 + myDeckCards10 + myDeckCards11 +
					 * myDeckCards12) >= 2)) priorityOfCard = card.getPriority() * 1000.0; else if
					 * (card.getCost() == 5 && myDeckCards5 >= 2) priorityOfCard =
					 * card.getPriority() * 1000.0; else if (card.getCost() == 4 && myDeckCards4 >=
					 * 5) { priorityOfCard = card.getPriority() * 1000.0; } else if (card.getCost()
					 * == 3 && myDeckCards3 >= 7) priorityOfCard = card.getPriority() * 1000.0; else
					 * if (card.getCost() == 2 && myDeckCards2 >= 10) priorityOfCard =
					 * card.getPriority() * 1000.0; else if (card.getCost() == 1 && myDeckCards1 >=
					 * 4) priorityOfCard = card.getPriority() * 1000.0; else priorityOfCard =
					 * (double) card.getPriority();
					 */
					// } else

					// priorityOfCard = (double) card.getPriority();

					// System.err.println("Card " + cardNr + "corrected prio: " + priorityOfCard);

					// CMC with actual card

					/*
					 * if (turn > 20) { double cmc = (myDeckCards0 * 0 + myDeckCards1 * 1 +
					 * myDeckCards2 * 2 + myDeckCards3 * 3 + myDeckCards4 * 4 + myDeckCards5 * 5 +
					 * myDeckCards6 * 6 + myDeckCards7 * 7 + myDeckCards8 * 8 + myDeckCards9 * 9 +
					 * myDeckCards10 * 10 + myDeckCards11 * 11 + myDeckCards12 * 12 + 1.0 *
					 * card.getCost()) / (myDeckCards1 + myDeckCards2 + myDeckCards3 + myDeckCards4
					 * + myDeckCards5 + myDeckCards6 + myDeckCards7 + myDeckCards8 + myDeckCards9 +
					 * myDeckCards10 + myDeckCards11 + myDeckCards12 + 1.0);
					 * 
					 * System.err.println("CMC for " + cardNr + " card: " + cmc);
					 * 
					 * if (Math.abs(2.77 - cmc) < cmcDiffActual) { System.err.println( "COMPARE: " +
					 * Math.abs(2.77 - cmc) + " is lower than cmcActual: " + cmcDiffActual); cardId
					 * = cardNr; cmcDiffActual = Math.abs(2.77 - cmc); cmcActual = cmc; cost =
					 * card.getCost(); type = card.getCardType(); System.err.println("CMC actual " +
					 * cmc + " card: " + cardNr); } } else
					 */
					if (priorityOfCard < prio) {
						prio = priorityOfCard;
						cardId = cardNr;
						cost = card.getCost();
						type = card.getCardType();
					}
					cardNr++;
				}
				// System.err.println("cost: " + cost);

				// System.err.println("CMCActualAtTheEnd: " + cmcActual);

				System.out.println("PICK " + cardId);

				if (type == 0) {
					// count the cards by cost
					switch (cost) {
					case 0: {
						myDeckCards0++;
						break;
					}
					case 1: {
						myDeckCards1++;
						break;
					}
					case 2: {
						myDeckCards2++;
						break;
					}
					case 3: {
						myDeckCards3++;
						break;
					}
					case 4: {
						myDeckCards4++;
						break;
					}
					case 5: {
						myDeckCards5++;
						break;
					}
					case 6: {
						myDeckCards6++;
						break;
					}
					case 7: {
						myDeckCards7++;
						break;
					}
					case 8: {
						myDeckCards8++;
						break;
					}
					case 9: {
						myDeckCards9++;
						break;
					}
					case 10: {
						myDeckCards10++;
						break;
					}
					case 11: {
						myDeckCards11++;
						break;
					}
					case 12: {
						myDeckCards12++;
						break;
					}
					default:
						break;
					}

					/*
					 * System.err.println("//////////My card pack:"); System.err.println("Pack 0: "
					 * + myDeckCards0); System.err.println("Pack 1: " + myDeckCards1);
					 * System.err.println("Pack 2: " + myDeckCards2); System.err.println("Pack 3: "
					 * + myDeckCards3); System.err.println("Pack 4: " + myDeckCards4);
					 * System.err.println("Pack 5: " + myDeckCards5); System.err.println("Pack 6: "
					 * + myDeckCards6); System.err.println("Pack 7: " + myDeckCards7);
					 * System.err.println("Pack 8: " + myDeckCards8); System.err.println("Pack 9: "
					 * + myDeckCards9); System.err.println("Pack 10: " + myDeckCards10);
					 * System.err.println("Pack 11: " + myDeckCards11);
					 * System.err.println("Pack 12: " + myDeckCards12);
					 */
				}
			}
		}
	}
}