import java.util.Random;
import java.util.Scanner;

public class HerosVersusMonsters {
	private static Hero Player;
	private static Monster Enemy;

	public static boolean IsJapanese = false;
	public static DungeonCharacter DeadChar = null;

	private static void renameToJapanese(Hero player, Monster enemy) {
		// Rename player type
		if (player instanceof Warrior) {
			((Warrior)player).setTypeName("戦士");
		} else if (player instanceof Sorceress) {
			((Sorceress)player).setTypeName("魔女");
		} else if (player instanceof Thief) {
			((Thief)player).setTypeName("泥棒");
		}
		
		// Rename enemy name and type
		if (enemy instanceof Ogre) {
			((Ogre)enemy).setName("シュレックは僕の命と冷やし中華");
			((Ogre)enemy).setTypeName("鬼");
		} else if (enemy instanceof Gremlin) {
			((Gremlin)enemy).setName("外国人さっき言うたでしょう");
			((Gremlin)enemy).setTypeName("グレムリン");
		} else if (enemy instanceof Skeleton) {
			((Skeleton)enemy).setName("骨人でも人魚になったと僕はナット大好きね");
			((Skeleton)enemy).setTypeName("スケルトン");
		}
	}

	public static void main(String... argv) {
		// Select the language
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select a language:\n(E)nglish\t(J)日本語");

		String lang = scan.next();
		if (lang.toLowerCase().startsWith("j")) {
			IsJapanese = true;
		}
		
		// Type name
		System.out.println(IsJapanese ? "君の名前は何ですか?"
				: "What is your name?");
		String name = scan.next();
		
		// Choose the hero
		System.out.println(IsJapanese ? "どっちのヒーロなりますか？\n(W)戦士\t(S)魔女\t(T)泥棒"
				: "Which hero are you?\n(W)arrior\t(S)orceress\t(T)hief");
		String heroChoice = scan.next();
		if (heroChoice.toLowerCase().startsWith("w")) {
			Player = new Warrior(name);
		} else if (heroChoice.toLowerCase().startsWith("s")) {
			Player = new Sorceress(name);
		} else {
			Player = new Thief(name);
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
		
		// Rename to Japanese
		if (IsJapanese) {
			renameToJapanese(Player, Enemy);
		}
		
		// Welcome player and enemy!
		System.out.println(IsJapanese ? "ようこそ " + Player.getName() + " (" + Player.getTypeName() + ")!\n君の敵の名前は" + Enemy.getName() + " (" + Enemy.getTypeName() + ")!!\n\n"
				: "Welcome, " + Player.getName() + " (" + Player.getTypeName() + ")!\nYour opponent\'s name is " + Enemy.getName() + " (" + Enemy.getTypeName() + ")!!\n\n");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(2);
		}
		
		// Game loop
		while (DeadChar == null) {
			// Show stats of fight
			System.out.println("\n\n" + (IsJapanese ? "==新しラウンド==" : "==NEW ROUND=="));
			
			// Player's turn
			if (DeadChar == null) {
				// Show menu options
				System.out.println(IsJapanese ? "何やりますか？\n(A)アタック\t(S)特殊攻撃\t(Q)逃げる" : "What do you do?\n(A)ttack\t(S)pecial move\t(Q)uit/run away");
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
					// Quit by breaking the loop with no winner
					break;
				}
			}
			
			// See if dead yet
			Player.respondToAttack();
			Enemy.respondToAttack();
			
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
			
			// See if dead yet
			Player.respondToAttack();
			Enemy.respondToAttack();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(4);
			}
		}
		
		// Close scanner
		scan.close();
		
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
			System.out.println("\n\n\n" + (IsJapanese ? "なぜ逃げるかな？ 君はほっとけ物！" : "It\'s sad to see you go, loser."));
		}
	}
}
