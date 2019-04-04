package textgen;

import document.EfficientDocument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	private ListNode getNode(String word) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(word)) {
				return node;
			}
		}
		return null;
	}
	
	private void addNextWord(String prevWord, String word) {
		ListNode prevWordNode = getNode(prevWord);
		if (prevWordNode != null) {
			prevWordNode.addNextWord(word);
		} else {
			ListNode newNode = new ListNode(prevWord);
			newNode.addNextWord(word);
			wordList.add(newNode);
		}
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		List<String> words = Arrays.asList(sourceText.split("\\s+"));
		this.starter = words.get(0);
		String prevWord = starter;

		for (int i = 1; i < words.size(); i++) {
			String word = words.get(i);
			addNextWord(prevWord, word);
			prevWord = word;
		}
		addNextWord(prevWord, this.starter);
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		String output = "";
		String currWord = this.starter;
		output += currWord + " ";
		int count = 1;
		while (count < numWords) {
			ListNode currentNode = getNode(currWord);
			if (currentNode.getNextWords().size() > 0) {
				String randomWord = currentNode.getRandomNextWord(this.rnGenerator);
				output += randomWord + " ";
				currWord = randomWord;
				count++;
			} else {
				continue;
			}
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		this.wordList = new LinkedList<ListNode>();
		this.starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(10));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(10));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return this.word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public List<String> getNextWords() 
	{
		return this.nextWords;
	}
	
	public String getRandomNextWord(Random generator)
	{
		int index = generator.nextInt(nextWords.size());
		String randomWord = nextWords.get(index);
		return randomWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


