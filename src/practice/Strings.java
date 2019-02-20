package strings;

public class Strings {

	public static void main(String[] args) {
		String magic = "Magic";
		char letter = 'b';
		System.out.println(hasLetter(magic, letter));
		
		String replaced = replace(magic, 'a', 'd');
		System.out.println(replaced);
		
		String regexCheck = "123.334.5999.579";
		String[] values = regexCheck.split("\\.");
		for (String v : values) {
			System.out.println(v);
		}
	}
	
	public static int hasLetter(String word, char letter) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				return i;
			}
		}
		return -1;
	}
	
	public static String replace(String word, char oldChar, char newChar) {
		char[] charWord = word.toCharArray();
		char[] charMod = new char[charWord.length];
		for (int i = 0; i < charWord.length; i++) {
			if (charWord[i] == oldChar) {
				charMod[i] = newChar;
			} else {
				charMod[i] = charWord[i];
			}
		}
		return new String(charMod);
	}

}
