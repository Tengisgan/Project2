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
    public TreeNode root;
    private Flyweight fly;
    public int numNodes;
    
	
	
	public BST() {
		fly = new Flyweight();
		root = fly;
		numNodes = 0;
	}
	
	//have to do in order traversal because it will return a tree
	
	public TreeNode find(String state, TreeNode node) {
	    if (node.equals(fly)) {
	        return fly;
	    }
	    
	    if (node.getData().getState().equalsIgnoreCase(state)) {
	        return node; 
	    }
	    
	    if (node.getData().getState().compareTo(state) < 0) {
	    	System.out.println("left");
	        return find(state, node.left);
	    }
	    else {
	        return find(state, node.right);
	    }
	    
	}
	
	public TreeNode insert(TreeNode current, TreeNode node) {
	    System.out.println("enter insert");
	    System.out.println(current);
	    if (current.equals(fly)) {  
	        System.out.println("Enter if statement");
	        current = node;
	        return current;
	    }
	    
	    else {
	        
	        if (node.getData().getState().compareTo(current.getData().getState()) == 0) {
	            if (node.getData().getDate().compareTo(current.getData().getDate()) < 0) {
	                current.left = insert(current.left, node);
	            }
	            else {
	                current.right = insert(current.right, node);
	            }
	        }
	        
	        else if (node.getData().getState().compareTo(current.getData().getState()) < 0) {
	            current.left = insert(current.left, node);
	        }
	        else {
	            
	            current.right = insert(current.right, node);
	        }
	    }
	    return current;
	}
	
	
	public void insert(TreeNode data) {
	    root = insert(root, data);
	    this.numNodes++;
	}
	
    public boolean isEmpty() {
        return (root.equals(fly));
    }
	
    
    
    public void remove(TreeNode current, TreeNode node) {
        
    }
    
	public void replace(TreeNode existingData, TreeNode newData) {
		TreeNode newExistingData = this.find(existingData.getData().getState(), this.root);
		if (newExistingData != fly) {
		    //need remove function
		}
	}
	
	public boolean updateData(TreeNode existingData, TreeNode newData) {
		return false;
	}
	
	public int removeGrade(String quality) {
		return 0;
	}
	
	public int sortedPrint(int type) {
		return 0;
	}
	
	public String getLatestDate() {
		return "";
	}
	
	public void printState(String state) {
		
	}
	
	public void printCases(int numCases) {
		
	}
	
	public void printAverage(int avg, String date) {
		
	}
	
	public void printNumber(int num, String date) {
		
	}
	
	public void printQuality(String quality) {
		
	}
	
	public void printDate(String date) {
		
	}
	
}