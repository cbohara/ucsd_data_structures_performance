package practice;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTester {
	BinarySearchTree<Integer> bst;

	@Before
	public void setUp() throws Exception {
	    bst = new BinarySearchTree<Integer>(20);
	    bst.insert(10);
	    bst.insert(5);
	    bst.insert(15);
	    bst.insert(12);
	    bst.insert(30);
	    bst.insert(25);
	    bst.insert(27);
	    bst.insert(32);
	}

	@Test
	public void testGetSize() {
		assertEquals("get size", 9, bst.getSize());
	}

	@Test
	public void testContains() {
		assertEquals("contains value", true, bst.contains(10));
		assertEquals("does not contain value", false, bst.contains(2));
	}
	
	@Test
	public void testInsert() {
		try {
			bst.insert(null);
			fail("Cannot add null data");
		}
		catch (NullPointerException e) {

		}

		assertEquals("size before insert", 9, bst.getSize());
		bst.insert(35);
		assertEquals("size after insert", 10, bst.getSize());
		assertEquals("contains value", true, bst.contains(35));
	}

	@Test
	public void testDeleteValueNotIntBST() {
		assertEquals("return false if value not in bst", false, bst.delete(100));
	}

	@Test
	public void testDeleteRightLeafNode() {
		bst.delete(32);
		assertEquals("no longer contains 32", false, bst.contains(32));
		assertEquals("size after delete", 8, bst.getSize());
	}

	@Test
	public void testDeleteLeftLeafNode() {
		bst.delete(5);
		assertEquals("no longer contains 5", false, bst.contains(5));
		assertEquals("size after delete", 8, bst.getSize());
	}

	@Test
	public void testDeleteHasRightChildOnly() {
		bst.delete(25);
		assertEquals("no longer contains 25", false, bst.contains(25));
		assertEquals("still contains 27", true, bst.contains(27));
		assertEquals("size after delete", 8, bst.getSize());
	}

	@Test
	public void testDeleteHasLeftChildOnly() {
		bst.delete(15);
		assertEquals("no longer contains 15", false, bst.contains(15));
		assertEquals("still contains 12", true, bst.contains(12));
		assertEquals("size after delete", 8, bst.getSize());
	}

	@Test
	public void testDeleteHasLeftAndRightChild() {
		bst.delete(10);
		assertEquals("no longer contains 10", false, bst.contains(10));
		assertEquals("still contains 5", true, bst.contains(5));
		assertEquals("still contains 15", true, bst.contains(15));
		assertEquals("still contains 12", true, bst.contains(12));
		assertEquals("size after delete", 8, bst.getSize());
	}
}