import java.util.Iterator;

/*
 * NOTE TO STUDENT:
 * Comment and implement all incomplete methods.
 * Any methods that have comments are already complete and
 * you must not change them.
 * You may add methods that you find helpful, remembering
 * that no public method allows acces to a TreeNode directly.
 * Remove this comment an provide your own header.
 */

/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree. 
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	public void attachRightSubtree(BinaryTree<E> right) {
		throw new UnsupportedOperationException();
	}

	public void insert(E item) {
		root = insert(root,item);
	}
	
	private TreeNode<E> insert(TreeNode<E> node, E item){
		if(node == null){
			return new TreeNode<E>(item);
		}
		if(item.compareTo(node.item) < 0){//"<0 means that item comes before node.item in a dictionary
			node.left = insert(node.left,item);
			return node;
		}
		else{
			node.right = insert(node.right,item);
			return node;
		}
	}
	
	public E retrieve(E item) {
		return search(root, item).item;
	}
	
	private TreeNode<E> search(TreeNode<E> node, E item){
		if(node == null)
			return null;
		if(item.compareTo(node.item) < 0)//"<0 means that item comes before node.item in a dictionary
			return search(node.left,item);
		
		else if(item.compareTo(node.item) > 0)
			return search(node.right,item);		
		
		else
			return node;
			
	}

	public E delete(E item) {
		return delete(root, item);
	}
	
	private E delete(TreeNode<E> node, E item){
		TreeNode<E> targetNode = search(node,item);
		if(targetNode == null)
			return null;
		
		TreeNode<E> P = targetNode.parent;
		
		if(targetNode.left == null && targetNode.right == null){
			E i = targetNode.item;
			targetNode = null;
			return i;
		}
		
		if(targetNode.left != null && targetNode.right == null){
					
			targetNode.left.parent = P;
			return targetNode.item;
			
		}
		if(targetNode.left == null && targetNode.right != null){
			
			targetNode.right.parent = P;
			return targetNode.item;

		}
		
	    TreeNode<E> M = findLeftmost(targetNode);
		E m = M.item;
		
		targetNode.item = m;
		delete(node,m);
		return m;
		
	}
	
	private TreeNode<E> findLeftmost(TreeNode<E> node){
		if(node.left == null)
			return node;
		else
			return findLeftmost(node.left);
	}

	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		
		//testing the height method
		System.out.println("height of tree is: " + tree.height()+"\n");
		
		String s1 = new String("one");
		String s2 = new String("two");
		String s3 = new String("three");
		String s4 = new String("four");
        String s5 = new String("five");
		String s6 = new String("six");
		String s7 = new String("seven");
		String s8 = new String("eight");
		String s9 = new String("nine");
		String s10 = new String("ten");
		
		// testing the insert method
		tree.insert(s9);
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		tree.insert(s5);
		tree.insert(s6);
		tree.insert(s7);
		tree.insert(s8);
		tree.insert(s9);
		tree.insert(s10);
		
		// testing the retrieve method
		String test = tree.retrieve("seven");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s7);
			if (test.equals(s7)) {
				System.out.println("Confirmed"+"\n");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}

		// testing the inorderIterator
		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order(infix):");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println("\n");
		
		
		// testing the preorderIterator
		it = tree.preorderIterator();
		System.out.println("printing out the contents of the tree in sorted order(prefix):");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println("\n");
		
		// testing the postorderIterator
		it = tree.postorderIterator();
		System.out.println("printing out the contents of the tree in sorted order(postfix):");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println("\n");
		
        //testing the delete method		
		System.out.println("deleting two: " + tree.delete("two")+"\n");
		System.out.println("deleting 7: " + tree.delete("seven"));
		System.out.println("deleting 9: " + tree.delete("nine"));
		System.out.println("deleting 0: " + tree.delete("zero"));
		System.out.println("deleting 3: " + tree.delete("three"));
		
		//testing the height method
		System.out.println("height of tree is: " + tree.height()+"\n");
		//testing isEmpty method
		System.out.println("Is tree empty: " + tree.isEmpty()+"\n");
		
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();
	}
}

	

	
