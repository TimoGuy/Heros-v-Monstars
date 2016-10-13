import java.util.Random;

public class Sorceress extends Hero {
	public Sorceress(String name) {
		super(name, "Sorceress", 75, 5, 25, 45, 0.7, 0.3);
	}

	public void heal() {
		Random rando = new Random();
		
		// Define healing ranges
		int minHeal = 2, maxHeal = 50;
		
		// Calc the next healing
		int difference = maxHeal - minHeal;
		int heal = rando.nextInt(difference + 1) + minHeal;

		// Apply and report
		System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "は HEAL つかった! ": " uses HEAL to heal ") + heal + (HerosVersusMonsters.IsJapanese ? "HPを自分に癒やしやりました!" : "HP on itself!"));
		addHitPts(heal);
	}
}
