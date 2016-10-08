
public abstract class Hero extends DungeonCharacter {
	protected double BlockChance;
	protected int NumTurns;
	
	public Hero(String name, int hitPts, int attackSpeed, int minDamage, int maxDamage, double attackSuccessChance, double blockChance) {
		super(name, hitPts, attackSpeed, minDamage, maxDamage, attackSuccessChance);
		
		BlockChance = blockChance;
	}
}
