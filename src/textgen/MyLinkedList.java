package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.size = 0;
		head.next = tail;
		tail.prev = head;
	}
	
	/**
	 * Get the node at a given element
	 * @param index Index where node currently resides
	 */
	private LLNode getNthNode(int index) {
		int current = 0;
		LLNode currentNode = this.head.next;
		while (current <= index) {
			if (current == index) {
				break;
			} else {
				current += 1;
				currentNode = currentNode.next;
			}
		}
		return currentNode;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if (element == null) {
			throw new NullPointerException("Cannot add node with null data");
		}
		// TODO: Implement this method
		LLNode<E> newNode = new LLNode<E>(element, this.tail.prev, this.tail);
		this.tail.prev.next = newNode;
		this.tail.prev = newNode;
		this.size += 1;
		return true;
	}
	
	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		if (this.size <= index || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		LLNode currentNode = getNthNode(index);
		return (E) currentNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("Cannot add node with null data");
		}

		LLNode<E> nodeToMove = getNthNode(index);
		LLNode<E> newNode = new LLNode<E>(element, nodeToMove.prev, nodeToMove);
		nodeToMove.prev.next = newNode;
		nodeToMove.prev = newNode;
		this.size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index out of bounds of list");
		}
		LLNode<E> nodeToRemove = getNthNode(index);
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
		this.size--;
		return (E) nodeToRemove.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("Cannot add node with null data");
		}
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}

		LLNode<E> node = getNthNode(index);
		E oldData = node.data;
		node.data = element;
		return oldData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this(e, null, null);
	}

	public LLNode(E e, LLNode prev, LLNode next)
	{
		this.data = e;
		this.prev = prev;
		this.next = next;
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
}
