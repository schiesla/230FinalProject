import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 *Data base that use Red Black Tree insert and remove methods
 *
 * @author schiesla.
 *         Created Jan 18, 2016.
 * @param <T>
 */
public class RedBlackTree<T extends Comparable<? super T>> implements Iterable<RedBlackTree.BinaryNode> {
	public BinaryNode root;
	private int size;
	private boolean cocurrent;
	private int rotationCount;
	private int height;
	
	public RedBlackTree() {
		this.root = null;
		this.size = 0;
		this.rotationCount = 0;
		this.cocurrent = false;
		this.height = 0;
	}
	
	/**
 	* function to check if the Tree is empty
 	* @returns boolean
 	*/
	public boolean isEmpty() {
		return this.root == null;
	}
	/**
	 * Returns the longest path to a leaf of the tree.
	 * @return int
	 */
	public int height() {
		return (isEmpty()) ? -1 : this.root.getHeight();
	}
	/**
	 * function returns the number of nodes in the tree.
	 * @return int
	 */
	public int size() {
		return this.size;
	}
	/**
	 * converts the array list of nodes to a string.
	 * @return String
	 */
	public String toString() {
		return (isEmpty()) ? "[]" : toArrayList().toString();
	}
	/**
	 * Uses the in order iterator to put the trees nodes into an acending array list.
	 * @return ArrayList<T>
	 */
	public ArrayList<T> toArrayList() {
		boolean state = this.cocurrent;
		ArrayList<T> list = new ArrayList<T>();
		InOrderIterator iterate = new InOrderIterator();
		while(iterate.hasNext()){
			list.add(iterate.next());
		}
		this.cocurrent = state;
		return list;
	}
	/**
	 * converts the array list of nodes to an array.
	 * @return Object[]
	 */
	public Object[] toArray() {
		return  toArrayList().toArray();
	}
	/**
	 * Iterates through the tree in order
	 * @return InOrderIterator<T>
	 */
	public Iterator<T> inOrderterator() {
		InOrderIterator i = new InOrderIterator();
		return i;
	}
	/**
	 * Iterates through the tree in a pre ordered fasion
	 * @return PreOrderIterator<T>
	 */
	public Iterator<RedBlackTree.BinaryNode> iterator() {
		Iterator p = new PreOrderIterator();
		return p;
	}
	/**
	 * calls the insert method on the trees root to insert new element, throws an IllegalArgumnetException if the element is null.
	 * @param poi
	 * @return boolean
	 */
	public boolean insert(T o) {
		this.cocurrent = true;
		if(isEmpty()){
			if(o != null){
				this.root = new BinaryNode(o);
				this.root.color = Color.BLACK;
				this.size++;
				return true;
			}
			throw new IllegalArgumentException();
		}
		if(o != null){
			MyBoolean inserted = new MyBoolean();
			this.root.insert(o, inserted, null, null, null);
			if(inserted.getMyBoolean()){
				this.root.color = Color.BLACK;
				this.size++;
				return true;
			}
			this.root.color = Color.BLACK;
			return false;
		}
		throw new IllegalArgumentException();
	}
	
	public int getRotationCount() {
		return this.rotationCount;
	}
	/**
	 * 
	 * function that checks special root cases for remove, then calls
	 * setUp function giving it the element to remove.
	 *
	 * @param element
	 * @return boolean
	 */
	public boolean remove(T element){
		this.cocurrent = true;
		if(element == null){
			throw new IllegalArgumentException();
		}
		if(isEmpty()){
			return false;
		}
		boolean temp = setUp(element);
		
		if(this.root != null){
			this.root.color = Color.BLACK;
		}
		return temp;
	}
	/**
	 * 
	 * takes element to be removed and if both the roots children are black it makes the root red and checks if it is to be
	 * deleted. If not calls step two on a child. Goes to step 2B if both childern are not red.
	 *
	 * @param element
	 * @return
	 */
	public boolean setUp(T element){
		MyBoolean hasRemoved = new MyBoolean();
		if(((this.root.rightChild == null) ||  this.root.rightChild.color.equals(Color.BLACK)) && 
			((this.root.leftChild == null) ||  this.root.leftChild.color.equals(Color.BLACK))){
				this.root.color = Color.RED;
				if(this.root.element.equals(element)){
					this.root = this.root.stepThree(element, hasRemoved, null, null, null);
					return hasRemoved.getMyBoolean();
				}
				int val = element.compareTo(this.root.element);
				if(this.root.leftChild != null){
					if(val < 0) this.root.leftChild.stepTwoMain(element, hasRemoved, null, this.root, this.root.rightChild);
				}
				if(this.root.rightChild != null){
					if(val > 0) this.root.rightChild.stepTwoMain(element, hasRemoved, null, this.root, this.root.leftChild);
				}
				if(hasRemoved.getMyBoolean() == true){
					this.size--;
					return hasRemoved.getMyBoolean();
				}
		}else{
			this.root.stepTwoB(element, hasRemoved, null, null, null);
		}
		return hasRemoved.getMyBoolean();	
	}
	/**
	 * 
	 * Boolean class used to get and set a global boolean in the recursive remove method.
	 *
	 * @author schiesla.
	 *         Created Dec 10, 2015.
	 */
	private class MyBoolean {
		Boolean b;
		public MyBoolean(){
			this.b = false;
		}
		public boolean getMyBoolean(){
			return this.b;
		}
		public void setMyBoolean(Boolean newState){
			this.b = newState;
		}
	}
	
	public enum Color{RED, BLACK}
	
	/**
	 * Nodes that have an element, a color (red or black), and a right and left child
	 *
	 * @author schiesla.
	 *         Created Dec 6, 2015.
	 */
	public class BinaryNode {
		public T element;
		private BinaryNode leftChild;
		private BinaryNode rightChild;
		private Color color;

		public BinaryNode(T element) {
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;
			this.color = Color.RED;
		}

		/**
		 * 
		 * finds the height of the given node through recursion
		 *
		 * @return max Height
		 */
		public int getHeight() {
			int lHeight = -1;
			int rHeight = -1;
			if (this.leftChild != null) {
				lHeight = this.leftChild.getHeight();
			}
			if (this.rightChild != null) {
				rHeight = this.rightChild.getHeight();
			}
			lHeight++;
			rHeight++;
			if (rHeight > lHeight) {
				return rHeight;
			}
			return lHeight;
		}
		
		public Color getColor(){
			return this.color;
		}
		public T getElement() {
			return this.element;
		}
		public BinaryNode getLeftChild(){
			return this.leftChild;
		}
		public BinaryNode getRightChild(){
			return this.rightChild;
		}

		/**
		 * 
		 * Inserts the nodes and checks for color swaps and rotations
		 *
		 * @param list
		 * @return list
		 */
		public void insert(T newEl, MyBoolean inserted, BinaryNode p, BinaryNode gp, BinaryNode ggp){
			int val = newEl.compareTo(this.element);
			if(val == -1){
				if(this.leftChild != null){
					if(this.rightChild != null){
						if(this.leftChild.successiveReds(this.rightChild)) this.colorSwitch(p, gp, ggp);
					}
					this.leftChild.insert(newEl, inserted, this, p, gp);
					return;
				}
				if(this.leftChild == null){
					this.leftChild = new BinaryNode(newEl);
					inserted.setMyBoolean(true);
					if(gp != null){
						if(this.successiveReds(this.leftChild)) this.leftChild.checkBalance(p, false, gp);
					}
					else if( p!= null){
							if(this.successiveReds(this.leftChild)) this.leftChild.checkBalance(p, true, p);
					}
					return;
				}
			}
			if(val == 1){
				if(this.rightChild != null){
					if(this.leftChild != null){
						if(this.rightChild.successiveReds(this.leftChild)) this.colorSwitch(p, gp, ggp);
					}
					this.rightChild.insert(newEl, inserted, this, p, gp);
					return;
				}
				if(this.rightChild == null){
					this.rightChild = new BinaryNode(newEl);
					inserted.setMyBoolean(true);
					if(gp != null){
						if(this.successiveReds(this.rightChild)) this.rightChild.checkBalance(p, false, gp);
					}
					else if(p != null){
						if(this.successiveReds(this.rightChild)) this.rightChild.checkBalance(p, true, p);
					}
					return;
					}
			}
			if(val == 0){
				if(this.leftChild != null && this.rightChild != null){
					if(this.leftChild.successiveReds(this.rightChild)) this.colorSwitch(p, gp, ggp);
				}
			}
		}
		/**
		 * Takes two nodes and checks if they are both red, returns true if so.
		 *
		 * @param node
		 * @param p
		 * @return true/false
		 */
		private boolean successiveReds(BinaryNode p){
			if(p != null){
				if(this.color.equals(Color.RED) && p.color.equals(Color.RED)){
				return true;
				}
			}
			return false;
		}
		
		/**
		 * swaps the color of a children and parents if both children are red and the parent is black then checks
		 * for rotations
		 */
		private void colorSwitch(BinaryNode p, BinaryNode gp, BinaryNode ggp){
			this.color = Color.RED;
			this.leftChild.color = Color.BLACK;
			this.rightChild.color = Color.BLACK;
			if(ggp != null){
				if(this.successiveReds(p)) this.checkBalance(gp, false, ggp);
			}else{
				if(this.successiveReds(p)) this.checkBalance(p, true, gp);
			}
		}

		/**
		 * 
		 * takes a grandparent, parent, and root indicator boolean and decides what the node setup is and calls
		 * the correct function that looks for the right rotation.
		 *
		 * @param parent
		 * @param isRoot
		 * @param relative
		 */
		private void checkBalance(BinaryNode parent, boolean isRoot, BinaryNode relative) {
			BinaryNode tempP = null;
			if (isRoot) {
				tempP = relative;
			} else {
				tempP = parent;
			}
			// if we are working with right child we then check which of its children are red then rotate
			if (tempP.leftChild == null && tempP.rightChild != null) {
				parentsRightChild(tempP, isRoot, relative);
				return;
			}
			//working with left child
			if (tempP.leftChild != null && tempP.rightChild == null) {
				parentsLeftChild(tempP, isRoot, relative);
				return;
				
			}
			//if both children are there we then have to check which child is the red one
			if (tempP.leftChild != null && tempP.rightChild != null) {
				parentHasTwoChildren(tempP, isRoot, relative);
				return;
			}
		}
		
		/**
		 * 
		 * If the parent of the rotation only has one child (right), 
		 * this function determinds single or double rotation
		 *
		 * @param parent
		 * @param isRoot
		 * @param relative
		 */
		private void parentsRightChild(BinaryNode parent, boolean isRoot, BinaryNode relative) {
			if (parent.rightChild.color.equals(Color.RED)) {
				if (parent.rightChild.rightChild != null) {
					if (parent.rightChild.rightChild.color.equals(Color.RED)) {
						if (isRoot) {
							RedBlackTree.this.root = parent.leftRotation();
							return;
						}
						if(relative.rightChild != null){
							if(relative.rightChild.equals(parent)){
								relative.rightChild = parent.leftRotation();
								return;
							}
						}
						if(relative.leftChild != null){
							if(relative.leftChild.equals(parent)){
								relative.leftChild = parent.leftRotation();
								return;
							}
						}
					}
				}
				if (parent.rightChild.leftChild != null) {
					if (parent.rightChild.leftChild.color.equals(Color.RED)) {
						if (isRoot) {
							RedBlackTree.this.root = parent.rightLeftRotation();
							return;
						}
						if (relative.rightChild != null){
							if (relative.rightChild.equals(parent)) {
								relative.rightChild = parent.rightLeftRotation();
							} else {
								relative.leftChild = parent.rightLeftRotation();
							}
						return;
						}
					}
				}
			}
		}

		/**
		 * 
		 * If the parent of the rotation only has one child (left), this
		 * function determinds single or double rotation
		 *
		 * @param parent
		 * @param isRoot
		 * @param relative
		 */
		private void parentsLeftChild(BinaryNode parent, boolean isRoot, BinaryNode relative) {
			if (parent.leftChild.color.equals(Color.RED)) {
				if (parent.leftChild.leftChild != null) {
					if (parent.leftChild.leftChild.color.equals(Color.RED)) {
						if (isRoot) {
							RedBlackTree.this.root = parent.rightRotation();
							return;
						}
						if(relative.leftChild != null){
							if(relative.leftChild.equals(parent)){
								relative.leftChild = parent.rightRotation();
								return;
							}
						}
						if(relative.rightChild != null){
							if(relative.rightChild.equals(parent)){
								relative.rightChild = parent.rightRotation();
								return;
							}
						}
					}
				}
				if (parent.leftChild.rightChild != null) {
					if (parent.leftChild.rightChild.color.equals(Color.RED)) {
						if (isRoot) {
							RedBlackTree.this.root = parent.leftRightRotation();
							return;
						} 
						if (relative.leftChild != null)
							if (relative.leftChild.equals(parent)) {
								relative.leftChild = parent.leftRightRotation();
							} else {
								relative.rightChild = parent.leftRightRotation();
							}
						return;
						}
					}	
				}
			}
		
		/**
		 * 
		 * if checkBalance finds that the parent of the rotation has two children, this function finds which one is red
		 *
		 * @param parent
		 * @param isRoot
		 * @param relative
		 */
		private void parentHasTwoChildren(BinaryNode parent, boolean isRoot, BinaryNode relative){
			if (parent.rightChild.color.equals(Color.RED)) {
				parentsRightChild(parent, isRoot, relative);
			}
			else if (parent.leftChild.color.equals(Color.RED)) {
				parentsLeftChild(parent, isRoot, relative);
			}
			
		}

		/**
		 * 
		 * Performs a left rotation and updates the node heights.
		 *
		 * @return
		 */
		private BinaryNode leftRotation() {
			RedBlackTree.this.rotationCount++;
			BinaryNode temp = this.rightChild;
			this.rightChild = temp.leftChild;
			temp.leftChild = this;
			Color tempColor = temp.color;
			temp.color = this.color;
			this.color = tempColor;
			return temp;
		}
		
		/**
		 * 
		 * Performs a right rotation and updates the node heights.
		 *
		 * @return
		 */
		private BinaryNode rightRotation() {
			RedBlackTree.this.rotationCount++;
			BinaryNode temp = this.leftChild;
			this.leftChild = temp.rightChild;
			temp.rightChild = this;
			Color tempColor = temp.color;
			temp.color = this.color;
			this.color = tempColor;
			return temp;	
		}
		
		/**
		 * 
		 * Performs a left rotation then a right rotation and updates the node heights.
		 *
		 * @return
		 */
		private BinaryNode leftRightRotation(){
			this.leftChild = this.leftChild.leftRotation();
			return this.rightRotation();
			
		}
		
		/**
		 * 
		 * Performs a right rotation then a left rotation and updates the node heights.
		 *
		 * @return
		 */
		private BinaryNode rightLeftRotation(){
			this.rightChild = this.rightChild.rightRotation();
			return this.leftRotation();
			
		}

		/**
		 * 
		 * if both of node's children are black, calls 2A
		 * else calls 2B
		 *
		 * @param element
		 * @param removed
		 * @param gparent
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoMain(T element, MyBoolean removed, BinaryNode gparent, BinaryNode parent, BinaryNode sibling){
			if((this.rightChild == null || this.rightChild.color.equals(Color.BLACK)) && (this.leftChild == null 
					|| this.leftChild.color.equals(Color.BLACK))){
					this.stepTwoA(element, removed, gparent, parent, sibling);
			}else{
				this.stepTwoB(element, removed, gparent, parent, sibling);
			}
		}
		/**
		 * 
		 * looks at sibling and either calls 2A1, 2A2, or 2A3 on the current node
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoA(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			if(sibling != null)
			if((sibling.rightChild == null || sibling.rightChild.color.equals(Color.BLACK)) && (sibling.leftChild == null 
					|| sibling.leftChild.color.equals(Color.BLACK))){
				//2a1
			this.stepTwoAOne(element, removed, gp, parent, sibling);
			return;
			}
				//2a2
				this.stepTwoATwo(element, removed, gp, parent, sibling);
				//2a3
				this.stepTwoAThree(element, removed, gp, parent, sibling);
				//2a4
				if((sibling.rightChild != null && sibling.leftChild != null) && sibling.rightChild.color.equals(Color.RED) && sibling.leftChild.color.equals(color.RED)){
					this.stepTwoAThree(element, removed, gp, parent, sibling);
				}
			
		}
		/**
		 * 
		 * swaps parents color with childrens colors then calls remove or traverses down the tree
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoAOne(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			Color tempColor = parent.color;
			parent.color = this.color;
			this.color = tempColor;
			sibling.color = tempColor;
			if(this.element.equals(element)){
				if(parent.rightChild != null && parent.rightChild.equals(this)){
					parent.rightChild = this.stepThree(element, removed, gp, parent, sibling);
					return;
				}
				if(parent.leftChild != null && parent.leftChild.equals(this)){
					parent.leftChild = this.stepThree(element, removed, gp, parent, sibling);
					return;
				}
			}else{
				int val = element.compareTo(this.element);
				if(this.rightChild != null){
					if(val > 0) this.rightChild.stepTwoMain(element, removed, parent, this, this.leftChild);
				}
				if(this.leftChild != null){
					if(val < 0) this.leftChild.stepTwoMain(element, removed, parent, this, this.rightChild);
				}
				return;
			}
		}
		/**
		 * 
		 *Checks for double rotations
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoATwo(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			if(parent.rightChild != null && (parent.rightChild.equals(sibling) && (sibling.leftChild != null && sibling.leftChild.color.equals(Color.RED)))){
				//double rotation
				if(parent.equals(RedBlackTree.this.root)){
					RedBlackTree.this.root = parent.rightLeftRotation();
					sibling.color = Color.BLACK;
					this.color = Color.RED;
					parent.color = Color.BLACK;
				}else{
//					if(gp != null){
						if(gp != null && gp.rightChild.equals(parent)) gp.rightChild = parent.rightLeftRotation();
						if(gp != null && gp.leftChild.equals(parent)) gp.leftChild = parent.rightLeftRotation();
//					}//else{
//						if(parent.rightChild != null && parent.rightChild.equals(this)) RedBlackTree.this.root.rightChild = parent.rightLeftRotation();
//					}
					sibling.color = Color.BLACK;
					this.color = Color.RED;
					parent.color = Color.BLACK;
				}
				//3)
				if(this.element.equals(element)){
					if(parent.rightChild != null && parent.rightChild.equals(this)){
						parent.rightChild = this.stepThree(element, removed, gp, parent, sibling);
					}
					if(parent.leftChild != null && parent.leftChild.equals(this)){
						parent.leftChild = this.stepThree(element, removed, gp, parent, sibling);
					}
				}
				else{
					int val = element.compareTo(this.element);
					if(this.rightChild != null){
						if(val < 0) this.rightChild.stepTwoMain(element, removed, parent, this, this.leftChild);
					}
					if(this.leftChild != null){
						if(val > 0) this.leftChild.stepTwoMain(element, removed, parent, this, this.rightChild);
					}
					return;
				}
			}
			if(parent.leftChild != null && (parent.leftChild.equals(sibling) && (sibling.rightChild != null && sibling.rightChild.color.equals(Color.RED)))){
				// double rotation
				if(parent.equals(RedBlackTree.this.root)){
					RedBlackTree.this.root = parent.leftRightRotation();
					sibling.color = Color.BLACK;
					this.color = Color.RED;
					parent.color = Color.BLACK;
				}else{
					if(gp != null && gp.rightChild.equals(parent)) gp.rightChild = parent.leftRightRotation();
					if(gp != null && gp.leftChild.equals(parent)) gp.leftChild = parent.leftRightRotation();
					sibling.color = Color.BLACK;
					this.color = Color.RED;
					parent.color = Color.BLACK;
				}
				//3)
				if(this.element.equals(element)){
					if(parent.rightChild != null && parent.rightChild.equals(this)){
						parent.rightChild = this.stepThree(element, removed, gp, parent, sibling);
					}
					if(parent.leftChild != null && parent.leftChild.equals(this)){
						parent.leftChild = this.stepThree(element, removed, gp, parent, sibling);
					}
				}else{
					int val = element.compareTo(this.element);
					if(this.rightChild != null){
						if(val < 0) this.rightChild.stepTwoMain(element, removed, parent, this, this.leftChild);
					}
					if(this.leftChild != null){
						if(val > 0) this.leftChild.stepTwoMain(element, removed, parent, this, this.rightChild);
					}
					return;
				}
			}
			return;
		}
		/**
		 * 
		 *Checks for single rotations
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoAThree(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			if(parent.rightChild != null && (parent.rightChild.equals(sibling) && sibling.rightChild.color.equals(Color.RED))){
				if(parent.element.equals(RedBlackTree.this.root)){
					RedBlackTree.this.root = parent.leftRotation();
					RedBlackTree.this.root.color = Color.RED;
					this.color = Color.RED;
					parent.color = Color.BLACK;
					sibling.rightChild.color = Color.BLACK;
				}else{
					if(gp != null && gp.rightChild.equals(parent)) gp.rightChild = parent.leftRotation();
					if(gp != null && gp.leftChild.equals(parent)) gp.leftChild = parent.leftRotation();
					sibling.color = Color.RED;
					this.color = Color.RED;
					parent.color = Color.BLACK;
					sibling.rightChild.color = Color.BLACK;
					
				}
				if(this.element.equals(element)){
					if(parent.rightChild != null && parent.rightChild.equals(this)){
						parent.rightChild = this.stepThree(element, removed, gp, parent, sibling);
					}
					if(parent.leftChild != null && parent.leftChild.equals(this)){
						parent.leftChild = this.stepThree(element, removed, gp, parent, sibling);
					}
				}else{
					int val = element.compareTo(this.element);
					if(this.rightChild != null){
						if(val < 0) this.rightChild.stepTwoMain(element, removed, parent, this, this.leftChild);
					}
					if(this.leftChild != null){
						if(val > 0) this.leftChild.stepTwoMain(element, removed, parent, this, this.rightChild);
					}
					return;
				}
			}
			if(parent.leftChild != null && (parent.leftChild.equals(sibling) && sibling.leftChild.color.equals(Color.RED))){
				if(parent.element.equals(RedBlackTree.this.root)){
					RedBlackTree.this.root = parent.rightRotation();
					RedBlackTree.this.root.color = Color.RED;
					this.color = Color.RED;
					parent.color = Color.BLACK;
					sibling.leftChild.color = Color.BLACK;
				}else{
					if(gp != null && gp.rightChild.equals(parent)) gp.rightChild = parent.rightRotation();
					if(gp != null && gp.leftChild.equals(parent)) gp.leftChild = parent.rightRotation();
					sibling.color = Color.RED;
					this.color = Color.RED;
					parent.color = Color.BLACK;
					sibling.leftChild.color = Color.BLACK;
					
				}
				if(this.element.equals(element)){
					if(parent.rightChild != null && parent.rightChild.equals(this)){
						parent.rightChild = this.stepThree(element, removed, gp, parent, sibling);
					}
					if(parent.leftChild != null && parent.leftChild.equals(this)){
						parent.leftChild = this.stepThree(element, removed, gp, parent, sibling);
					}
				}else{
					int val = element.compareTo(this.element);
					if(this.rightChild != null){
						if(val > 0) this.rightChild.stepTwoMain(element, removed, parent, this, this.leftChild);
					}
					if(this.leftChild != null){
						if(val < 0) this.leftChild.stepTwoMain(element, removed, parent, this, this.rightChild);
					}
					return;
				}
			}
		}
		/**
		 * 
		 * checks if this node is the node to be deleted and calls step 3
		 * if not traverses down the tree and calls 2B1 or 2B2 depending on node color
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoB(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			if((this.rightChild != null && this.rightChild.color.equals(Color.RED)) ||
					(this.leftChild != null && this.leftChild.color.equals(Color.RED))){
				if(this.element.equals(element)){
					if(parent == null){
						RedBlackTree.this.root = this.stepThree(element, removed, gp, parent, sibling);
						return;
					}
					if(parent.rightChild != null && parent.rightChild.equals(this)){
						parent.rightChild = this.stepThree(element, removed, gp, parent, sibling);
						return;
					}
					if(parent.leftChild != null && parent.leftChild.equals(this)){
						parent.leftChild = this.stepThree(element, removed, gp, parent, sibling);
						return;
					}
				}else{
					int val = element.compareTo(this.element);
					if(val < 0){
						if(this.leftChild != null){
							if(this.leftChild.color.equals(Color.RED)){
								this.leftChild.stepTwoBOne(element, removed, parent, this, this.rightChild);
								return;
							}
							if(this.leftChild.color.equals(Color.BLACK)){
								this.leftChild.stepTwoBTwo(element, removed, parent, this, this.rightChild);
								return;
							}
						}
					}
					if(val > 0){
						if(this.rightChild != null){
							if(this.rightChild.color.equals(Color.RED)){
								this.rightChild.stepTwoBOne(element, removed, parent, this, this.leftChild);
								return;
							}
							if(this.rightChild.color.equals(Color.BLACK)){
								this.rightChild.stepTwoBTwo(element, removed, parent, this, this.leftChild);
								return;
							}
						}
					}
				}
			}
			
		}
		/**
		 * 
		 *checks if this is the node to be deleted, and calls step 3
		 *if not traverses and calls step 2 main
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoBOne(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			if(this.element.equals(element)){
				if(parent.rightChild != null && parent.rightChild.equals(this)){
					parent.rightChild = this.stepThree(element, removed, gp, parent, sibling);
					return;
				}
				if(parent.leftChild != null && parent.leftChild.equals(this)){
					parent.leftChild = this.stepThree(element, removed, gp, parent, sibling);
					return;
				}
			}else{
				int val = element.compareTo(this.element);
				if(this.rightChild != null){
					if(val > 0) this.rightChild.stepTwoMain(element, removed, parent, this, this.leftChild);
				}
				if(leftChild != null){
					if(val < 0) this.leftChild.stepTwoMain(element, removed, parent, this, this.rightChild);
				}
				return;
			}
		}
		/**
		 * 
		 * rotates sibling around parent, changes their color, then go back to step 2 main
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 */
		public void stepTwoBTwo(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			if(gp == null){
				if(parent.rightChild != null && (sibling != null && parent.rightChild.equals(sibling))){
					RedBlackTree.this.root = RedBlackTree.this.root.leftRotation();
				}
				else if(parent.leftChild != null && (sibling != null && parent.leftChild.equals(sibling))){
					RedBlackTree.this.root = RedBlackTree.this.root.rightRotation();
				}
			}
			else if(gp.rightChild != null && gp.rightChild.equals(parent)){
				if(parent.rightChild != null && parent.rightChild.equals(sibling)){
					gp.rightChild = parent.leftRotation();
				}
				else if(parent.leftChild != null && parent.leftChild.equals(sibling)){
					gp.leftChild = parent.rightRotation();
				}
			}
			else if(gp.leftChild != null && gp.leftChild.equals(parent)){
				if(parent.leftChild != null && parent.leftChild.equals(sibling)){
					gp.leftChild = parent.rightRotation();
				}
				else if(parent.rightChild != null && parent.rightChild.equals(sibling)){
					gp.leftChild = parent.leftRotation();
				}
			}
			parent.color = Color.RED;
			sibling.color = Color.BLACK;
			this.stepTwoMain(element, removed, gp, parent, sibling);
		}
		
		/**
		 * 
		 * function that exicutes the deletion of nodes, returns the proper node after removal.
		 *
		 * @param element
		 * @param removed
		 * @param gp
		 * @param parent
		 * @param sibling
		 * @return
		 */
		public BinaryNode stepThree(T element, MyBoolean removed, BinaryNode gp, BinaryNode parent, BinaryNode sibling){
			//a
			if(this.leftChild != null && this.rightChild != null){
				
				if(this.color.equals(Color.RED)){
					this.element = largest(this.leftChild);
					this.leftChild.stepTwoMain(this.element, removed, parent, this, this.rightChild);
					removed.setMyBoolean(true);
					return this;
				}
				if(this.color.equals(Color.BLACK)){
					T temp = largest(this.leftChild);
					boolean tempB = false;
					if(this.equals(RedBlackTree.this.root)) tempB = true;
					this.stepTwoB(temp, removed, gp, parent, sibling);
					this.element = temp;
					removed.setMyBoolean(true);
					if(tempB)return RedBlackTree.this.root;
					return this;
				}
			}
			//c
			if(this.leftChild != null && this.rightChild == null){
				this.leftChild.color = Color.BLACK;
				removed.setMyBoolean(true);
				return this.leftChild;
			}
			//c
			if(this.leftChild == null && this.rightChild != null){
				this.rightChild.color = Color.BLACK;
				removed.setMyBoolean(true);
				return this.rightChild;
			}
			//b
			removed.setMyBoolean(true);
			return null;
			
		}
		
		
		
		/**
		 *Finds the largest node in the left subtree and returns it
		 * @param node
		 * @return T
		 */
		public T largest(BinaryNode node){
			BinaryNode find = node;
			while(find.rightChild != null){
				find = find.rightChild;
			}
			return find.element;	
		}
	}

	/**
	 * 
	 * Iterates through the BinarySearchTree getting the elements in acending
	 * order
	 *
	 * @author schiesla. Created Dec 2, 2015.
	 */
	private class InOrderIterator implements Iterator<T> {
		BinaryNode node;
		Stack<BinaryNode> stack;
		T last;
		int count;

		public InOrderIterator() {
			RedBlackTree.this.cocurrent = false;
			this.last = null;
			this.count = 0;
			this.node = root;
			this.stack = new Stack<BinaryNode>();
			if (this.node != null) {
				BinaryNode temp = root;
				this.stack.push(this.node);
				while (temp.leftChild != null) {
					this.stack.push(temp.leftChild);
					temp = temp.leftChild;
				}
			}
		}

		/**
		 * uses the classes stack to see if there a next element exists
		 */
		public boolean hasNext() {
			return !(this.stack.empty());
		}

		/**
		 * the constructer adds the left edge of the tree to the stack, this
		 * function (if next exists) pops those elements off and checks for
		 * right children. If right children are found a while loop checks for
		 * left children and are added to the stack. Then the elements are poped
		 * in acending order
		 */
		public T next() {
			this.count = 0;
			if (hasNext()) {
				BinaryNode temp = null;
				BinaryNode pop = null;
				pop = this.stack.pop();
				if (pop.rightChild != null) {
					temp = pop.rightChild;
					while (temp.leftChild != null) {
						this.stack.push(temp);
						temp = temp.leftChild;
					}
					this.stack.push(temp);
				}
				this.last = pop.element;
				return pop.element;
			}
			throw new NoSuchElementException();
		}
		/**
		 * checks to see if next has been called and remove has only been called once, then calls binary search tree's 
		 * remove method on the last element returned by next().
		 */
		public void remove(){
			if(RedBlackTree.this.cocurrent){
				throw new ConcurrentModificationException();
			}
			this.count++;
			if(this.last != null && this.count == 1){
				RedBlackTree.this.remove(this.last);
				RedBlackTree.this.cocurrent = false;
			}else{
				throw new IllegalStateException();
			}
		}
	}

	/**
	 * 
	 * Iterates through the BinarySearchTree getting the elements in acending
	 * order
	 *
	 * @author schiesla. Created Dec 2, 2015.
	 */
	private class PreOrderIterator implements Iterator<BinaryNode> {
		BinaryNode node;
		Stack<BinaryNode> stack;
		T last;
		int count;

		public PreOrderIterator() {
			RedBlackTree.this.cocurrent = false;
			this.node = root;
			this.last = null;
			this.count = 0;
			this.stack = new Stack<BinaryNode>();
			if (this.node != null) {
				this.stack.push(this.node);
			}
		}

		/**
		 * uses the classes stack to see if there a next element exists
		 */
		public boolean hasNext() {
			return !(this.stack.empty());
		}

		/**
		 * After the Constructor pushes the root to the stack, this pops it off
		 * and returns it, then adds elements to the stack in order from left
		 * leafs to right leafs
		 */
		public RedBlackTree.BinaryNode next() {
			this.count = 0;
			if (hasNext()) {
				BinaryNode temp = this.stack.pop();
				if (temp.rightChild != null) {
					this.stack.push(temp.rightChild);
				}
				if (temp.leftChild != null) {
					this.stack.push(temp.leftChild);
				}
				this.last = temp.element;
				return temp;
			}
			throw new NoSuchElementException();
		}
		/**
		 * checks to see if next has been called and remove has only been called once, then calls binary search tree's 
		 * remove method on the last element returned by next().
		 */
		public void remove(){
			if(RedBlackTree.this.cocurrent){
				throw new ConcurrentModificationException();
			}
			this.count++;
			if(this.last != null && this.count == 1){
				RedBlackTree.this.remove(this.last);
				RedBlackTree.this.cocurrent = false;
			}else{
				throw new IllegalStateException();
			}
		}
	}
}


