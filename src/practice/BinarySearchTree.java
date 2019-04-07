package practice;

public class BinarySearchTree<E extends Comparable<? super E>> {
	private TreeNode<E> root;
	private int size;
	
	public BinarySearchTree(E value) {
		this.root = new TreeNode<E>(value, null); 
		this.size = 1;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean contains(E value) {
		TreeNode<E> currNode = this.root;
		while (currNode != null) {
			int compare = value.compareTo(currNode.getValue());
			if (compare < 0) {
				currNode = currNode.getLeft();
			} else if (compare > 0) {
				currNode = currNode.getRight();
			} else {
				return true;
			}
		}
		return false;
	}
	
	public boolean insert(E value) {
		if (value == null) {
			throw new NullPointerException("Cannot add node with null data");
		}
		TreeNode<E> currNode = this.root;
		int compare = value.compareTo(currNode.getValue());
		while (compare < 0 && currNode.getLeft() != null || compare > 0 && currNode.getRight() != null) {
			if(compare < 0) {
				currNode = currNode.getLeft();
			} else if (compare > 0) {
				currNode = currNode.getRight();
			}
			compare = value.compareTo(currNode.getValue());
		}

		if (compare < 0) {
			currNode.setLeft(value, currNode);
		} else if (compare > 0) {
			currNode.setRight(value, currNode);
		} else {
			return false;
		}

		this.size++;
		return true;
	}
	
	public boolean delete(E value) {
		TreeNode<E> currNode = this.root;
		while (currNode != null) {
			int compare = value.compareTo(currNode.getValue());
			if (compare < 0) {
				currNode = currNode.getLeft();
			} else if (compare > 0) {
				currNode = currNode.getRight();
			} else {
				// found value in currNode - now need to figure out how to remove
				// if leaf node
				if (currNode.getLeft() == null && currNode.getRight() == null) {
					TreeNode<E> parent = currNode.getParent();
					if (parent.getLeft().getValue().equals(value)) {
						parent.removeLeft();
					} else if (parent.getRight().getValue().equals(value)) {
						parent.removeRight();
					}
				} else if (currNode.getLeft() == null && currNode.getRight() != null) {
					TreeNode<E> parent = currNode.getParent();
					parent.setLeft(currNode.getRight().getValue(), parent);
				} else if (currNode.getLeft() != null && currNode.getRight() == null) {
					TreeNode<E> parent = currNode.getParent();
					parent.setRight(currNode.getLeft().getValue(), parent);
				} else if(currNode.getLeft() != null && currNode.getRight() != null) {
					TreeNode<E> minNode = currNode.getRight();
					while (minNode.getLeft() != null) {
						minNode = minNode.getLeft();
					}
					currNode.setValue(minNode.getValue());
					TreeNode<E> parent = minNode.getParent();
					parent.removeLeft();
				}
				this.size--;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(20);
		bst.insert(5);
		System.out.println(bst.size);
		System.out.println(bst.contains(5));
		bst.delete(5);
		System.out.println(bst.size);
		System.out.println(bst.contains(5));
	}
}

class TreeNode<E> {
	private E value;
	private TreeNode<E> parent;
	private TreeNode<E> left;
	private TreeNode<E> right;
	
	public TreeNode(E value, TreeNode<E> parent) {
		this.value = value;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}

	public E getValue() {
		return this.value;
	}

	public TreeNode<E> getParent() {
		return this.parent;
	}
	
	public TreeNode<E> getLeft() {
		return this.left;
	}

	public TreeNode<E> getRight() {
		return this.right;
	}

	public void setValue(E value) {
		this.value = value;
	}
	
	public void setLeft(E value, TreeNode<E> parent) {
		this.left = new TreeNode<E>(value, parent);
	}

	public void setRight(E value, TreeNode<E> parent) {
		this.right = new TreeNode<E>(value, parent);
	}
	
	public TreeNode<E> addLeft(E value) {
		this.left = new TreeNode<E>(value, this);
		return this.left;
	}

	public TreeNode<E> addRight(E value) {
		this.right = new TreeNode<E>(value, this);
		return this.right;
	}

	public void removeParent() {
		this.parent = null;
	}

	public void removeLeft() {
		this.left = null;
	}

	public void removeRight() {
		this.right = null;
	}
}