import java.util.Scanner;

public class HerosVersusMonsters {
	private static Hero Player;
	private static Monster Enemy;
	
	private static void renameToJapanese() {
		Warrior.warName = "正義の退化";
		Sorceress.soreName = "何でこの人は女性";
		Thief.thiName = "失敗から僕のおなら";
		
		Ogre.ogName = "シュレックは僕の命と冷やし中華";
		Gremlin.gremName = "外国人さっき言うたでしょう";
		Skeleton.skelName = "骨人でも人魚になったと僕はナット大好きね";
	}
	
	public static void main(String... argv) {
		// Select the language
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select a language:\n(E)nglish\t(J)apanese");
		
		String lang = scan.next();
		if (lang.toLowerCase().startsWith("j")) {
			renameToJapanese();
		}
	}
}
