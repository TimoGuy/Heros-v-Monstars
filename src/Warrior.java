import java.util.Random;

public class Warrior extends Hero {
	public Warrior(String name) {
		super(name, "Warrior", 125, 4, 35, 60, 0.8, 0.2);
	}

	public void crushingBlow(DungeonCharacter other) {
		Random rando = new Random();

		// Define the crushing blow's stats
		double attackSuccessChance = 0.4;
		int minDamage = 75, maxDamage = 175;
		
		// Check to see if player can attack
		if (rando.nextDouble() <= attackSuccessChance) {
			// Calc the next attack
			int difference = maxDamage - minDamage;
			int damage = rando.nextInt(difference + 1) + minDamage;

			// Apply and report
			System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "の CRUSHING BLOW が" : "\'s CRUSHING BLOW caused ") + damage + (HerosVersusMonsters.IsJapanese ? "ダメージを" : " damage on ") + other.getName() + (HerosVersusMonsters.IsJapanese ? "にやりました!" : "!"));
			other.addHitPts(-damage);
		} else {
			// Say couldn't hit
			System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "の CRUSHING BLOW がミスした" : "\'s CRUSHING BLOW missed."));
		}
	}
}
