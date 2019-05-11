/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			list1.remove(-1);
			fail("Cannot remove negative index");
		}
		catch (IndexOutOfBoundsException e) {

		}

		try {
			list1.remove(5);
			fail("Cannot remove index greater than bound");
		}
		catch (IndexOutOfBoundsException e) {

		}

		int sixtyFive = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, sixtyFive);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		String a = shortList.remove(0);
		assertEquals("Remove: check a is correct ", "A", a);
		assertEquals("Remove: check element 0 is correct ", "B", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());
	}
	
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			shortList.add(null);
			fail("Cannot add null data");
		}
		catch (NullPointerException e) {

		}

		assertEquals("add C", true, shortList.add("C"));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("shortList size", 2, shortList.size());
		assertEquals("emptyList size", 0, emptyList.size());
		assertEquals("longerList size", 10, longerList.size());
		assertEquals("list1 size", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			shortList.add(null);
			fail("Cannot add null data");
		}
		catch (NullPointerException e) {

		}
		shortList.add(0, "C");
		assertEquals("C added at index 0", "C", shortList.get(0));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.set(0, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.set(-1, "Yolo");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
	    
		}
		String oldValue = shortList.set(0, "Yolo");
		assertEquals("Updated value", "Yolo", shortList.get(0));
	}
	
}