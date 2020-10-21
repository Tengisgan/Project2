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
	
	public TreeNode insert(TreeNode current, String[] value) {
	    System.out.println("LOOKING: " + current);
	    if (current.equals(fly)) {
	        return new Data(fly, value);
	    }
	    
	    if (value[1].compareTo(current.getData().getState()) == 0) {
	        if(value[0].compareTo(current.getData().getDate()) < 0) {
	            current.left = insert(current.left, value);
            }
            else {
                current.right = insert(current.right, value);
            }
	    }
	    else if (value[1].compareTo(current.getData().getState()) < 0) {
	           current.left = insert(current.left, value);
	    }
	    else {
	        current.right = insert(current.right, value);
	    }
	    
	    return current;
	}
	
    public boolean isEmpty() {
        return (root.equals(fly));
    }
	
    
    
    public TreeNode remove(TreeNode current, String[] value) {
        if (current.equals(fly)) {
            return current;
        }
        return null;
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
	
	public int printState(String state) {
		return 0;
	}
	
	public int printCases(int numCases, String date) {
		return 0;
	}
	
	public String[] printAverage(int avg, String date) {
		String[] re = {"0", "-", "-"};
		return re;
	}
	
	public String[] printNumber(int num, String date) {
		String[] re = {"0", "-", "-"};
		return re;
	}
	
	public int printQuality(String quality, String state, String date) {
		return 0;
	}
	
	public int printDate(String date) {
		return 0;
	}
	
}