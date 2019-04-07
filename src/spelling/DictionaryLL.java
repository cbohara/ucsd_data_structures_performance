package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		this.dict = new LinkedList<String>();
	}


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	if(isWord(word)) {
    		return false;
    	} else {
    		this.dict.add(word.toLowerCase());
    		return true;
    	}
    }

    /** Return the number of words in the dictionary */
    public int size()
    {
    	return this.dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	if (this.dict.contains(s.toLowerCase())) {
    		return true;
    	} else {
    		return false;
    	}
    }
}