import java.util.Random;
import java.util.Scanner;

public class HerosVersusMonsters {
	private static Hero Player;
	private static Monster Enemy;

	public static boolean IsJapanese = false;
	public static DungeonCharacter DeadChar = null;

	private static void renameToJapanese() {
		IsJapanese = true;

		Warrior.warName = "正義の退化";
		Sorceress.soreName = "何でこの人は女性";
		Thief.thiName = "失敗から僕のおなら";

		Ogre.ogName = "シュレックは僕の命と冷やし中華";
		Gremlin.gremName = "外国人さっき言うたでしょう";
		Skeleton.skelName = "骨人でも人魚になったと僕はナット大好きね";
	}

	public static void main(String... argv) {
		// Select the language
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select a language:\n(E)nglish\t(J)apanese");

		String lang = scan.next();
		if (lang.toLowerCase().startsWith("j")) {
			renameToJapanese();
		}

		// Choose the hero
		System.out.println(IsJapanese ? "どっちのヒーロなりますか？\n(W)戦士\t(S)魔女\t(T)泥棒"
				: "Which hero are you?\n(W)arrior\t(S)orceress\t(T)hief");
		String heroChoice = scan.next();
		if (heroChoice.toLowerCase().startsWith("w")) {
			Player = new Warrior();
		} else if (heroChoice.toLowerCase().startsWith("s")) {
			Player = new Sorceress();
		} else {
			Player = new Thief();
		}

		// Select an enemy
		int sel = new Random().nextInt(3);
		switch(sel) {
		case 0:
			Enemy = new Ogre();
			break;
			
		case 1:
			Enemy = new Gremlin();
			break;
			
		case 2:
			Enemy = new Skeleton();
			break;
			
		default:
			System.err.println("Error in Random code in the mainclass!");
			System.exit(1);
			break;
		}
		
		// Welcome player and enemy!
		System.out.println(IsJapanese ? "ようこそ" + Player.getName() + "!\n君の敵の名前は" + Enemy.getName() + "!!\n\n"
				: "Welcome, " + Player.getName() + "!\nYour opponent\'s name is " + Enemy.getName() + "!!\n\n");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(2);
		}
		
		// Game loop
		while (DeadChar == null) {
			// Show stats of fight
			System.out.println("\n\n==NEW ROUND==");
			
			// Player's turn
			if (DeadChar == null) {
				// Show menu options
				System.out.println("What do you do?\n(A)ttack\t(S)pecial move\t(Q)uit/run away");
				String attack = scan.next();
				if (attack.toLowerCase().startsWith("a")) {
					// Attack
					Player.attack(Enemy);
				} else if (attack.toLowerCase().startsWith("s")) {
					// Special move
					if (Player instanceof Warrior) {
						((Warrior)Player).crushingBlow(Enemy);
					} else if (Player instanceof Sorceress) {
						((Sorceress)Player).heal();
					} else if (Player instanceof Thief) {
						((Thief)Player).surpriseAttack(Enemy);
					}
				} else if (attack.toLowerCase().startsWith("q")) {
					// Quit
					break;
				}
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(3);
			}
			
			// Enemy's turn
			if (DeadChar == null) {
				Enemy.attack(Player);
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(4);
			}
		}
		
		// See who died
		if (DeadChar instanceof Monster) {
			// Player won (boo)
			System.out.println("\n\n");
			String win = "_____.___.                      .__        \n" +
							"\\__  |   | ____  __ __  __  _  _|__| ____  \n" +
							" /   |   |/  _ \\|  |  \\ \\ \\/ \\/ /  |/    \\ \n" +
							" \\____   (  <_> )  |  /  \\     /|  |   |  \\\n" +
							" / ______|\\____/|____/    \\/\\_/ |__|___|  /\n" +
							" \\/                                     \\/ \n";
			System.out.println(win);
		} else if (DeadChar instanceof Hero) {
			// Monsters win!!!!!!!!!!!!!!!!!!!!
			System.out.println("\n\n");
			String lose = "_____.___.              .__  \n" +                     
							"\\__  |   | ____  __ __  |  |   ____  ______ ____  \n" +
							" /   |   |/  _ \\|  |  \\ |  |  /  _ \\/  ___// __ \\ \n" +
							" \\____   (  <_> )  |  / |  |_(  <_> )___ \\  ___/ \n" +
							" / ______|\\____/|____/  |____/\\____/____  >\\___  >\n" +
							" \\/                                     \\/     \\/ \n";
			System.out.println(lose);
		} else {
			// The player must've quit, then
			System.out.println("\n\n\nIt\'s sad to see you go, loser.");
		}
	}
}
