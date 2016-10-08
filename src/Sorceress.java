import java.util.Random;

public class Sorceress extends Hero {
	public static String soreName = "Sorceress";
	
	public Sorceress() {
		super(soreName, 75, 5, 25, 45, 0.7, 0.3);
	}

	public void heal(DungeonCharacter other) {
		Random rando = new Random();
		
		// Define healing ranges
		int minHeal = 2, maxHeal = 50;
		
		// Calc the next healing
		int difference = maxHeal - minHeal;
		int heal = rando.nextInt(difference + 1) + minHeal;

		// Apply and report
		other.addHitPts(heal);
		System.out.println(Name + " healed " + heal + "HP on " + other.getName() + "!");
	}
}
