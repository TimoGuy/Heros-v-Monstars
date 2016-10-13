import java.util.Random;

public abstract class DungeonCharacter {
	protected String Name, TypeName;
	protected int HitPts;
	protected int AttackSpeed;

	protected int MinDamage, MaxDamage;
	protected double AttackSuccessChance;

	public DungeonCharacter(String name, String typeName, int hitPts, int attackSpeed, int minDamage, int maxDamage,
			double attackSuccessChance) {
		Name = name;
		TypeName = typeName;
		HitPts = hitPts;
		AttackSpeed = attackSpeed;
		MinDamage = minDamage;
		MaxDamage = maxDamage;
		AttackSuccessChance = attackSuccessChance;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	
	public String getTypeName() {
		return TypeName;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getName() {
		return Name;
	}

	public void addHitPts(int deltaHP) {
		HitPts += deltaHP;
		System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "は今に" : " now has ") + Math.max(0, HitPts) + (HerosVersusMonsters.IsJapanese ? "HPあります!" : "HP!"));
	}

	/**
	 * Attacks the 'other' dungeon char.
	 * 
	 * @param other
	 *            - the char being attacked.
	 */
	public void attack(DungeonCharacter other) {
		Random rando = new Random();

		// Check to see if player can attack
		if (rando.nextDouble() <= AttackSuccessChance) {
			// Calc the next attack
			int difference = MaxDamage - MinDamage;
			int damage = rando.nextInt(difference + 1) + MinDamage;

			// Apply and report
			System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "のアタックが" : "\'s attack caused ") + damage + (HerosVersusMonsters.IsJapanese ? "ダメージを" : " damage on ") + other.getName() + (HerosVersusMonsters.IsJapanese ? "にやりました!" : "!"));
			other.addHitPts(-damage);

			// See if dead yet
			other.respondToAttack();
		} else {
			// Say couldn't hit
			System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "のアタックがミスした" : "\'s attack missed."));
		}
	}

	/**
	 * To tell the game that the character has died
	 */
	private void respondToAttack() {
		// Check if died
		if (HitPts <= 0) {
			HerosVersusMonsters.DeadChar = this;
		}
	}
}
