import java.util.Random;

public class Thief extends Hero {
	public static String thiName = "Thief";
	
	public Thief() {
		super(thiName, 75, 6, 20, 40, 0.8, 0.4);
	}

	public void surpriseAttack(DungeonCharacter other) {
		Random rando = new Random();
		
		double chanceOfSuccess = rando.nextDouble();
		if (chanceOfSuccess < 0.4) {
			// Successful (40%)
			System.out.println(HerosVersusMonsters.IsJapanese ? "SURPRISE ATTACK は完璧!!!" : "SURPRISE ATTACK is a success!!!");
			attack(other);
			
			System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "がも一つの番あつまりました..." : " gets another turn..."));
			// TODO: add another round for thief
		} else if (chanceOfSuccess < 0.8) {
			// Normal attack (40%)
			System.out.println(Name + (HerosVersusMonsters.IsJapanese ? "は SURPRISE ATTACK やれへんかった、しかし、普通のアタックできた..." : " couldn\'t find an opening for SURPRISE ATTACK, but was able to still land a regular attack..."));
			attack(other);
		} else {
			// Thief is caught (20%)
			System.out.println((HerosVersusMonsters.IsJapanese ? "" : "NOOOO!!! ") + Name + (HerosVersusMonsters.IsJapanese ? "は失敗! 彼と SURPRISE ATTACK がアホね..." : " was caught! SURPRISE ATTACK was a surprising failure..."));
		}
	}
}
