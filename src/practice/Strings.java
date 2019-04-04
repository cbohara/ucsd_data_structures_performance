package practice;

import java.util.HashSet;
import java.util.Set;

public class Strings {

	public static void main(String[] args) {
//		String magic = "Magic";
//		char letter = 'b';
//		System.out.println(hasLetter(magic, letter));
//		
//		String replaced = replace(magic, 'a', 'd');
//		System.out.println(replaced);
//		
//		String regexCheck = "123.334.5999.579";
//		String[] values = regexCheck.split("\\.");
//		for (String v : values) {
//			System.out.println(v);
//		}
//		
		String testing = "1234|5678|910";
		String[] splits = testing.split("\\|");
		System.out.println(splits[0]);
		
		Set<String> enroll = new HashSet<String>();
		Set<String> unenroll = new HashSet<String>();
		enroll.add("Hello");
		enroll.add("Yup");
		enroll.add("OK!");
		unenroll.add("OK!");
		unenroll.add("Nope");
		
		//expect Hello Yup
		enroll.removeAll(unenroll);
		for (String x : enroll) {
			System.out.println("enroll " + x);
		}
		
		unenroll.removeAll(enroll);
		for (String x : unenroll) {
			System.out.println("unenroll " + x);
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
