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
	
	//have to do in order traversal because it will return a tree
	
	public TreeNode find(String state, TreeNode node) {
	    if (node.equals(fly)) {
	        return null;
	    }
	    
	    if (node.getData().getState().equalsIgnoreCase(state)) {
	        return node; 
	    }
	    
	    if (node.getData().getState().compareTo(state) < 0) {
	        return find(state, node.left);
	    }
	    else {
	        return find(state, node.right);
	    }
	    
	}
	
	public void insert(TreeNode current, TreeNode node) {
	    if (current.equals(fly)) {
	        current = node;
	    }
	    
	    else {
	        if (node.getData().getState().compareTo(current.getData().getState()) == 0) {
	            if (node.getData().getDate().compareTo(current.getData().getDate()) < 0) {
	                insert(current.left, node);
	            }
	            else {
	                insert(current.right, node);
	            }
	        }
	        
	        else if (node.getData().getState().compareTo(current.getData().getState()) < 0) {
	            insert(current.left, node);
	        }
	        else {
	            insert(current.right, node);
	        }
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