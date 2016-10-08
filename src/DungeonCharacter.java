import java.util.Random;

public abstract class DungeonCharacter {
	protected String Name;
	protected int HitPts;
	protected int AttackSpeed;
	
	protected int MinDamage, MaxDamage;
	protected double AttackSuccessChance;
	
	public DungeonCharacter(String name, int hitPts, int attackSpeed, int minDamage, int maxDamage,
			double attackSuccessChance) {
		Name = name;
		HitPts = hitPts;
		AttackSpeed = attackSpeed;
		MinDamage = minDamage;
		MaxDamage = maxDamage;
		AttackSuccessChance = attackSuccessChance;
	}

	public String getName() {
		return Name;
	}
	
	public void addHitPts(int deltaHP) {
		HitPts += deltaHP;
	}
	
	/**
	 * Attacks the 'other' dungeon char.
	 * @param other - the char being attacked.
	 */
	public void attack(DungeonCharacter other) {
		Random rando = new Random();
		
		// Check to see if player can attack
		if (rando.nextDouble() <= AttackSuccessChance) {
			// Calc the next attack
			int difference = MaxDamage - MinDamage;
			int damage = rando.nextInt(difference + 1) + MinDamage;
			
			// Apply and report
			other.addHitPts(-damage);
			System.out.println(Name + "\'s attack caused " + damage + " damage on " + other.getName() + "!");
		} else {
			// Say couldn't hit
			System.out.println(Name + "\'s attack missed.");
		}
	}
}
