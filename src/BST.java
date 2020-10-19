import java.util.ArrayList;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
// Names: Tengis Gantulga and Nikolai Long
// PIDs: tengisgan and nikolai

/**
 * BST implementation including: TreeNode, Data, and Flyweight
 * 
 * @author Tengis Gantulga and Nikolai Long
 * @pid tengisgan and nikolai
 * @version 2020.10.12
 */
public class BST {
	public class TreeNode {
		TreeNode left;
		TreeNode right;
		String state;
		ArrayList<Data> data;
		
		public TreeNode() {}
		
		public TreeNode(String[] data) {
			this.left = fly;
			this.right = fly;
			state = data[1];
			this.data = new ArrayList<Data>();
		}
		
		public void sortTreeNode() {
			for (int i = 1; i < this.data.size(); i++) {
				Data current = this.data.get(i);
				for (int j = 0; j < i; j++) {
					if (current.data[0].compareTo(this.data.get(j).data[0]) > 0) {
						Data placeHolder = this.data.get(j);
						this.data.set(j, current);
						this.data.set(i, placeHolder);
					}
				}
			}
		}
	}
	
	public class Data extends TreeNode {
		String[] data;
		
		public Data(String[] data) {
			this.data = data;
		}
	}
	
	public class Flyweight extends TreeNode {
		TreeNode left = null;
		TreeNode right = null;
		String state = null;
		Data[] data = null;
		
		public Flyweight() {
			
		}
	}
	
	/**
	 * BST Fields
	 */
	private TreeNode root;
	private Flyweight fly;
    private int numNodes;
	
	public BST() {
		fly = new Flyweight();
		root = fly;
		numNodes = 0;
	}
	
	public TreeNode find(String state, TreeNode node) {
		if (state.equals(node.state) || (node.left instanceof Flyweight && node.right instanceof Flyweight)) {
			return node;
		}
		else if (state.compareTo(node.state) < 0) {
			return this.find(state, node.left);
		}
		return this.find(state, node.right);
	}
	
	public void insert(String[] data) {
		TreeNode found = this.find(data[1], root);
		if (found instanceof Flyweight) {
			
		}
		else {
			this.find(data[1], root);
		}
	}
	
	
	
	public boolean compareGrades() {
		return false;
	}
	
	public void replace() {
		
	}
	
	public boolean updateData() {
		return false;
	}
	
	public int removeGrade() {
		return 0;
	}
	
	public int sortedPrint() {
		return 0;
	}
	
	public String getLatestDate() {
		return "";
	}
	
	public void printState() {
		
	}
	
	public void printCases() {
		
	}
	
	public void printAverage() {
		
	}
	
	public void printNumber() {
		
	}
	
	public void printQuality() {
		
	}
	
	public void printDate() {
		
	}
}