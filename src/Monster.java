import java.util.Random;

public abstract class Monster extends DungeonCharacter {
	protected double ChanceToHeal;
	protected int MinHeal, MaxHeal;
	
	public Monster(String name, int hitPts, int attackSpeed, int minDamage, int maxDamage, double attackSuccessChance,
			int minHeal, int maxHeal, double chanceToHeal) {
		super(name, hitPts, attackSpeed, minDamage, maxDamage, attackSuccessChance);
		MinHeal = minHeal;
		MaxHeal = maxHeal;
		ChanceToHeal = chanceToHeal;
	}

	@Override
	public void attack(DungeonCharacter other) {
		super.attack(other);
		
		// (Try to) heal if not dead
		if (HitPts > 0) {
			heal();
		}
	}
	
	private void heal() {
		Random rando = new Random();
		
		if (rando.nextDouble() <= ChanceToHeal) {
			// Calc the next healing
			int difference = MaxHeal - MinHeal;
			int heal = rando.nextInt(difference + 1) + MinHeal;
		
			// Apply and report
			System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "は自分に" : " healed ") + heal + (HerosVersusMonsters.IsJapanese ? "HPを癒やしやりました!" : "HP on itself!"));
			addHitPts(heal);
		}
	}
}
