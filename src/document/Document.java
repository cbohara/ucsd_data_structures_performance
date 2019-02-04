package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	

	/** Determine if last char is a given char
	 * @param String word
	 * @return boolean return true if last letter is 'e'
	 */
	private boolean isLastChar(String word, char c) {
		char lastChar = word.charAt(word.length() - 1);
		if (lastChar == c) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Determine index to stop checking if character is a vowel
	 *  because a lone 'e' at the end of the word is not considered
	 *  a vowel
	 * @param String word
	 * @return int representing index to stop iterating through word
	 */
	private int stopIndex(String word) {
		if (isLastChar(word, 'e')) {
			return word.length() - 1;
		} else {
			return word.length();
		}
	}

	/** Determines if a character is a vowel
	 * @param char letter to evaluate
	 * @return boolean returns true if the letter is a vowel
	 */
	private boolean isVowel(char letter) {
		char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'y', 
						  'A', 'E', 'I', 'O', 'U', 'Y' };
		for (char c : vowels) {
			if (letter == c) {
				return true;
			}
		}
		return false;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences 
	 * method here.  The reason we put countSyllables here because we'll 
	 * use it again next week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern 
	 * objects inside this method. Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	protected int countSyllables(String word) {
		if (word.length() == 1 || word.length() == 2) {
			return 1;
		}

		if (word.length() == 3 && isLastChar(word, 'e')) {
			return 1;
		}

		int syllableCount = 0;
		char firstChar = word.charAt(0);
		char previousChar = firstChar;
		char lastChar = word.charAt(word.length() - 1);
		int stopIndex = stopIndex(word);
		for (int i = 1; i < stopIndex; i++) {
			char currentChar = word.charAt(i);
			boolean currentCharIsVowel = isVowel(currentChar);
			boolean previousCharIsVowel = isVowel(previousChar);
			if (!isVowel(firstChar)) {
				if (!previousCharIsVowel && currentCharIsVowel) {
					syllableCount++;
				}
			} else {
				if (previousCharIsVowel && !currentCharIsVowel) {
					syllableCount++;
				}
			}
			previousChar = currentChar;
		}
		if (isVowel(firstChar) && isVowel(lastChar) && !isLastChar(word, 'e')) {
			syllableCount++;
		}
		return syllableCount;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    // TODO: You will play with this method in week 1, and 
		// then implement it in week 2
	    return text.length();
	}
	
	
	
}
