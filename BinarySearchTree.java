package javaproject;

import java.util.LinkedList;
import java.util.Queue;

//BinarySearchTree class
//
//CONSTRUCTION: with no initializer
//
//******************PUBLIC OPERATIONS*********************
//void insert( x )       --> Insert x
//void remove( x )       --> Remove x
//boolean contains( x )  --> Return true if x is present
//Comparable findMin( )  --> Return smallest item
//Comparable findMax( )  --> Return largest item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//void printTree( )      --> Print tree in sorted order
//******************ERRORS********************************
//Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree( )
	{
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * @param x the item to insert.
	 */
	public void insert( AnyType x )
	{
		root = insert( x, root );
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * @param x the item to remove.
	 */
	public void remove( AnyType x )
	{
		root = remove( x, root );
	}

	/**
	 * Find the smallest item in the tree.
	 * @return smallest item or null if empty.
	 * @throws Exception 
	 */
	public AnyType findMin( ) throws UnderFlowException
	{
		if( isEmpty( ) )
			throw new UnderFlowException();
		return findMin( root ).element;
	}

	/**
	 * Find the largest item in the tree.
	 * @return the largest item of null if empty.
	 * @throws Exception 
	 */
	public AnyType findMax( ) throws UnderFlowException
	{
		if( isEmpty( ) )
			throw new UnderFlowException();
		return findMax( root ).element;
	}

	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return true if not found.
	 */
	public boolean contains( AnyType x )
	{
		return contains( x, root );
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty( )
	{
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree( )
	{
		if( isEmpty( ) )
			System.out.println( "Empty tree" );
		else
			printTree( root );
	}



	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return new BinaryNode<>( x, null, null );

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = insert( x, t.left );
		else if( compareResult > 0 )
			t.right = insert( x, t.right );
		else
			;  // Duplicate; do nothing
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return t;   // Item not found; do nothing

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = remove( x, t.left );
		else if( compareResult > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = remove( t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	{
		if( t == null )
			return null;
		else if( t.left == null )
			return t;
		return findMin( t.left );
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
	{
		if( t != null )
			while( t.right != null )
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return false;

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			return contains( x, t.left );
		else if( compareResult > 0 )
			return contains( x, t.right );
		else
			return true;    // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * @param t the node that roots the subtree.
	 */
	private void printTree( BinaryNode<AnyType> t )
	{
		if( t != null )
		{
			
			printTree( t.left );
			System.out.println( t.element );
			printTree( t.right );
		}
	}

	//	private void printTree( BinaryNode<AnyType> t )
	//	{
	//		if( t != null )
	//		{
	//			printTree( t.left );
	//			System.out.println( t.element );
	//			printTree( t.right );
	//		}
	//	}

	/**
	 * Internal method to compute height of a subtree.
	 * @param t the node that roots the subtree.
	 */
	private int height( BinaryNode<AnyType> t )
	{
		if( t == null )
			return -1;
		else
			return 1 + Math.max( height( t.left ), height( t.right ) );    
	}
	public int count() {
		return count(root);
	}
	private int count(BinaryNode<AnyType> root) {
		if(root==null) {
			return 0;
		}
		else {
			return 1+count(root.left)+count(root.right);

		}
	}
	public boolean isFull() {
		return isFull(root);
	}

	private boolean isFull(BinaryNode<AnyType> root) {
		if(count()==2*height(root)+1) {
			return true;
		}
		return false;
	}

	public boolean compareStructure(BinarySearchTree<AnyType> t1) {
		return compareStructure(root,t1.root);
	}
	/*compa*/
	private boolean compareStructure(BinaryNode<AnyType> root1,BinaryNode<AnyType> root2 ) {
		if(root1==null && root2==null) {
			return true;
		}
		if(root1!=null && root2!=null) {
			if(compareStructure(root1.left,root2.left)&&compareStructure(root1.right, root2.right)) {
				return true;
			}
		}
		return false;
	}
	/*checks if tree passed is equal to the given tree*/
	public boolean equal(BinarySearchTree<AnyType> t1) throws UnderFlowException {
		if(t1.root==null) {
			throw new UnderFlowException();
		}
		return equal(root,t1.root);
	}

	private boolean equal(BinaryNode<AnyType> root1,BinaryNode<AnyType> root2 ) {
		if(root1==null && root2==null) {
			return true;
		}
		if(root1!=null && root2!=null) {
			if((root1.element==root2.element)&&equal(root1.left,root2.left)&&equal(root1.right, root2.right)) {
				return true;
			}
		}
		return false;
	}

	public BinarySearchTree<AnyType> copy() throws UnderFlowException {

		BinarySearchTree<AnyType> cpy = new BinarySearchTree<>();
		if(root==null) {
			throw new UnderFlowException();
		}
		cpy.root = new BinaryNode<AnyType>(root.element,null,null);
		return copy(root,cpy.root,cpy);
	}
	public BinarySearchTree<AnyType> mirror() throws UnderFlowException{
		BinarySearchTree<AnyType> mir = new BinarySearchTree<>();
		if(root==null) {
			throw new UnderFlowException();
		}
		mir.root = new BinaryNode<AnyType>(root.element,null,null);
		return mirror(root, mir.root,mir);
	}
	private BinarySearchTree<AnyType> mirror(BinaryNode<AnyType> root,BinaryNode<AnyType> mroot, BinarySearchTree<AnyType> mir) {
		if(root==null) {
			return null;
		}
		if(root.left!=null) {
			mroot.right = new BinaryNode<AnyType>(root.left.element,null,null);
			mirror(root.left,mroot.right,mir);

		}
		if(root.right!=null) {
			mroot.left = new BinaryNode<AnyType>(root.right.element,null,null);
			mirror(root.right,mroot.left,mir);

		}
		return mir;
	}

	private boolean isMirror(BinarySearchTree<AnyType> mir) throws UnderFlowException {
		BinarySearchTree<AnyType> z = new BinarySearchTree<>();
		z =	mir.mirror();
		if(equal(z)) {
			return true;
		}
		return false;
	}

	private BinarySearchTree<AnyType> copy(BinaryNode<AnyType> root,BinaryNode<AnyType> croot,BinarySearchTree<AnyType> cpy) {

		if(root==null) {
			return null;
		}

		if(root.left!=null) {
			croot.left = new BinaryNode<AnyType>(root.left.element,null,null);
			copy(root.left,croot.left,cpy);
		}
		if(root.right!=null) {
			croot.right = new BinaryNode<AnyType>(root.right.element,null,null);
			copy(root.right,croot.right,cpy);
		}
		return cpy;
	}

	private void rotateLeft(AnyType data) throws UnderFlowException {
		if(contains(data)) {
			root = rotateLeft(root,data);
		}
		else {
			System.out.println("Given value is not present in the tree");
		}

	}

	private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> root,AnyType data) throws UnderFlowException{
		if(root==null) {
			return null;
		}
		if(root.element==data) {
			root = rotateLeftNode(root,root.right);
			return root;
		}
		if(root.left!=null) {
			if(root.left.element==data) {
				root.left = rotateLeftNode(root.left,root.left.right);
				return root;
			}
			else if(data.compareTo(root.element) < 0) {
				rotateLeft(root.left,data);
			}
		}
		if(root.right!=null) {
			if(root.right.element==data) {
				root.right = rotateLeftNode(root.right,root.right.right);
				return root;
			}
			else if(data.compareTo(root.element)>0) {
				rotateLeft(root.right,data);
			}
		}
		return root;	
	}
	private BinaryNode<AnyType> rotateLeftNode(BinaryNode<AnyType> k2, BinaryNode<AnyType> k1) throws UnderFlowException{
		if(k1==null) {
			System.out.println("here");
			throw new UnderFlowException("Cannot rotate tree at this node");
		}
		if(k1!=null) {
			k2.right = k1.left;
			k1.left = k2;
			return k1;
		}
		return k2;
	}


	private void rotateRight(AnyType data) {
		if(contains(data)) {
			root = rotateRight(root,data);
		}else {
			System.out.println("Given value is not present in the tree");
		}

	}

	private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> root,AnyType data) {
		if(root==null) {
			return null;
		}
		if(root.element==data) {
			root = rotateRightNode(root,root.left);
			return root;
		}
		if(root.left!=null) {
			if(root.left.element==data) {
				root.left = rotateRightNode(root.left,root.left.left);
				return root;
			}
			else if(data.compareTo(root.element) < 0) {
				rotateRight(root.left,data);
			}
		}
		if(root.right!=null) {
			if(root.right.element==data) {
				root.right = rotateRightNode(root.right,root.right.left);
				return root;
			}
			else if(data.compareTo(root.element)>0) {
				rotateRight(root.right,data);
			}
		}
		return root;	
	}
	private BinaryNode<AnyType> rotateRightNode(BinaryNode<AnyType> k2, BinaryNode<AnyType> k1){
		if(k1!=null) {
			k2.left = k1.right;
			k1.right = k2;
			return k1;
		}
		return k2;
	}

	private void levelOrderTransversal() {
		int ht = height(root);
		int i;
        for (i=1; i<=ht+1; i++)
        {
            printGivenLevel(root, i);
            System.out.println("");
        }
	}

	private void printGivenLevel(BinaryNode<AnyType> root, int level) {
		if (root == null)
            return;
        if (level == 1)
            System.out.print(root.element + " ");
        else if (level > 1)
        {
        	//System.out.println("");
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
	}

	// Basic node stored in unbalanced binary search trees
	private static class BinaryNode<AnyType>
	{
		@SuppressWarnings("unused")
		// Constructors
		BinaryNode( AnyType theElement )
		{
			this( theElement, null, null );
		}

		BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
		{
			element  = theElement;
			left     = lt;
			right    = rt;
		}

		AnyType element;            // The data in the node
		BinaryNode<AnyType> left;   // Left child
		BinaryNode<AnyType> right;  // Right child
	}
	/*private void Tree() {
		root = new BinaryNode(100,null,null);
		root.left = new BinaryNode(50,null,null);
		root.left.left = new BinaryNode(40,null,null);
		root.left.left.right = new BinaryNode(45,null,null);
		root.right = new BinaryNode(150,null,null);
	}*/

	/** The tree root. */
	private BinaryNode<AnyType> root;


	// Test program
	public static void main( String [ ] args ) throws Exception
	{
		BinarySearchTree<Integer> t = new BinarySearchTree<>( );
		BinarySearchTree<Integer> t1 = new BinarySearchTree<>( );
		final int NUMS = 39;
		final int GAP  =   37;

		System.out.println( "Checking... (no more output means success)" );

		//		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) {
		//			t.insert( i );System.out.println("i:"+i);
		//		}
		//			
		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
			t1.insert( i );

		t.insert(105);t.insert(155);t.insert(130);t.insert(50);t.insert(65);t.insert(40);t.insert(35);t.insert(45);t.insert(205);
//		for( int i = 1; i < NUMS; i+= 2 )
//			t.remove( i );
//		 		
		//		if( NUMS < 40 )
		//			t.printTree( );
		//		if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
		//			System.out.println( "FindMin or FindMax error!" );
		//
		//		for( int i = 2; i < NUMS; i+=2 )
		//			if( !t.contains( i ) )
		//				System.out.println( "Find error1!" );
		//
		//		for( int i = 1; i < NUMS; i+=2 )
		//		{
		//			if( t.contains( i ) )
		//				System.out.println( "Find error2!" );
		//		}
		int count;
		try {
			
			System.out.println("Original Tree:");
			t.printTree();
			count = t.count();
			System.out.println("a)Number of nodes in the tree is:"+count);
			
			System.out.println();
			boolean isFull;
			isFull= t.isFull();
			if(isFull) {
				System.out.println("b) Tree is full");
			}else {
				System.out.println("b) Tree is not balanced");
			}
			
			
			System.out.println();
			boolean compare = t.compareStructure(t);
			if(compare) {
				System.out.println("c) Trees are Similar");
			}else {
				System.out.println("c) Trees are not similar");
			}
			
			System.out.println();
			BinarySearchTree<Integer> t11 = t.mirror();
			BinarySearchTree<Integer> t12 = t11.mirror();
			boolean equal = t.equal(t12);
			if(equal) {
				System.out.println("d) Trees are equal");
			}else {
				System.out.println("d) Trees are not equal");
			}
			
			
			System.out.println();
			BinarySearchTree<Integer> copyTree =new BinarySearchTree<>();
			copyTree = t.copy();
			System.out.println("e) The copied tree");
			copyTree.printTree();

			
			System.out.println();
			BinarySearchTree<Integer> mirror = new BinarySearchTree<>();
			mirror = t.mirror();
			System.out.println("f) Method Mirrored tree");
			mirror.printTree();
			
			System.out.println();
			System.out.println("g) Method isMirror");
			if(t.isMirror(mirror)) {
				System.out.println("the tree passed is a mirror of given tree");
			}else 
				System.out.println("the tree passed is not a mirror of given tree");
			
			
			System.out.println("Original Tree");
			t.printTree();
			
			int value = 50;
			BinarySearchTree<Integer> rotateRight= new BinarySearchTree<>();
			System.out.println();
			t.rotateRight(value);
			System.out.println("h) Rotated right at value 50");
			t.printTree();
			
			System.out.println();
			t.rotateLeft(value);
			System.out.println("i) Rotated left at value 50");
			t.printTree();
			
			System.out.println();
			System.out.println("Original Tree");
			t.printTree();
			System.out.println("j)  The level ordered tree is:");
			t.levelOrderTransversal();
		} catch (UnderFlowException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}




}

