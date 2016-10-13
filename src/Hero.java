
public abstract class Hero extends DungeonCharacter {
	protected double BlockChance;
	protected int NumTurns;
	
	public Hero(String name, String typeName, int hitPts, int attackSpeed, int minDamage, int maxDamage, double attackSuccessChance, double blockChance) {
		super(name, typeName, hitPts, attackSpeed, minDamage, maxDamage, attackSuccessChance);
		
		BlockChance = blockChance;
	}
}
