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
	    
	    if (node.getData().getState().compareTo(state) == 0) {
	        return node; 
	    }
	    
	    else if (node.getData().getState().compareTo(state) < 0) {
	        return find(state, node.left);
	    }
	    else {
	        return find(state, node.right);
	    }
	    
	}
	
	public TreeNode findSD(String state, String date, TreeNode node) {
	    if (node.equals(fly)) {
	        return fly;
	    }
	    
	    if (node.getData().getState().equals(state)) {
	        if(node.getData().getDate().equals(date)) {
	            return node;
	        }
	        
            if(node.getData().getDate().compareTo(date) > 0) {
                return findSD(state, date, node.left);
	            }
            else {
                return findSD(state, date, node.right);
            }
	    }	    
	    
	    else if (node.getData().getState().compareTo(state) > 0) {
            return findSD(state, date, node.left);
        }
        else {
            return findSD(state, date, node.right);
        }
	    
	}
	
	public TreeNode findGrade(String grade, TreeNode node) {
	        if (node.equals(fly)) {
	            return fly;
	        }
	        
	        if (node.getData().getGrade().equals(grade)) {
	            return node; 
	        }
	        
	        if (node.getData().getGrade().compareTo(grade) < 0) {
	            return findGrade(grade, node.left);
	        }
	        else {
	            return findGrade(grade, node.right);
	        }
	        
	    }
	
	public TreeNode insert(TreeNode current, String[] value) {
	    if (current.equals(fly)) {
	        numNodes++; 
	        current = new Data(fly, value);
	        return current;
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
  
    public String[] minValue(TreeNode current) {
        String[] minValue = current.getData().getArray();
        while (!current.left.equals(fly)) {
            minValue = root.left.getData().getArray();
            current = root.left;
        }
        return minValue;
    }
    
    
    public TreeNode removeHelper(TreeNode current, String[] value) {
    	System.out.println("helper");
        if (current.equals(fly)) {
            return current;
        }
        
        if (value[1].compareTo(current.getData().getState()) < 0) {
            current.left = removeHelper(current.left, value);
        }
        else if (value[1].compareTo(current.getData().getState()) > 0){
            current.right = removeHelper(current.right, value);
        }
        else {
            if (value[0].compareTo(current.getData().getDate()) < 0) {
                current.left = removeHelper(current.left, value);
            }
            else if (value[0].compareTo(current.getData().getDate()) > 0){
                current.right = removeHelper(current.right, value);
            }
            else {
                numNodes--;
                if (current.left.equals(fly)) {
                    return current.right;
                }
                
                else if (current.right.equals(fly)) {
                    return current.left;
                }
                
                current.getData().data = minValue(root.right);
                
                current.right = removeHelper(current.right, current.getData().data);
                
            }
        }
        
//        else if (value[1].equals(current.getData().getState()) && value[0].equals(current.getData().getDate())) {
//            if (current.left.equals(fly)) {
//                return current.right;
//            }
//            
//            else if (current.right.equals(fly)) {
//                return current.left;
//            }
//            
//            current.getData().data = minValue(root.right);
//            
//            current.right = removeHelper(current.right, current.getData().data);
//        }
        
        return current;
    }
	
    public void remove(String[] value) {
        root = removeHelper(root, value);
    }
    
	public String convertDate(String oldDate) {
		String newDate = oldDate.substring(4, 6) + "/" + 
				oldDate.substring(6, 8) + "/" +
				oldDate.substring(0, 4);
		return newDate;
	}
	
   public void replace(String[] existingData, String[] newData) {
        this.findSD(existingData[1], existingData[0], this.root).getData().data = newData;
    }
   
	
	public boolean updateData(String[] existingData, String[] newData) {
        boolean updated = false;
        for (int i = 0; i < existingData.length; i++) {
            if (existingData[i].equals("") && !newData[i].equals("")) {
                existingData[i] = newData[i];
                updated = true;
            }
        }
        return updated;
	}
	
	public TreeNode removeGrade(TreeNode current, String grade) {
		System.out.println("here");
	    
	    if (!current.left.equals(fly)) {
	        removeGrade(current.left, grade);
	    }
        int numericalGrade = getNumericalGrade(current.getData().getGrade());
        
        if (numericalGrade <= getNumericalGrade(grade)) {
            this.remove(current.getData().data);
        }
        if (!current.right.equals(fly)) {
            removeGrade(current.right, grade);
        }
	    return current;
	}
	
	public int removeGrade(String grade) {
	    int numNodesBefore = this.numNodes;
	    removeGrade(root, grade);
	    System.out.println("where");
	    return numNodesBefore - this.numNodes;
	}
	
    private int getNumericalGrade(String qualityGrade) {
        int grade = (700 - ((int) qualityGrade.charAt(0) * 10));
        return qualityGrade.length() == 1 ? grade : grade + 
            (44 - ((int) qualityGrade.charAt(1)));
    }

	public void sortedPrint(TreeNode node, int type, int depth) {
		if (type == 1) {
		    if (node.equals(fly)) {
		        String format = getSpaces(depth * 2);		        
		        format += "E";
		        System.out.println(format);
		        return;
		    }
		    sortedPrint(node.left, 1, depth + 1);
		    
		    String format = getSpaces(depth * 2);
		    format += "<" + convertDate(node.getData().getDate()) + ", "
		        + node.getData().getState() + "> " + node.getData().getPositive();
		    System.out.println(format);
		    
		    sortedPrint(node.right, 1, depth + 1);
		}
		
		else if (type == 2) {
	          if (node.equals(fly)) {
	                String format = getSpaces(depth * 2);
	                format += "E";
	                System.out.println(format);
	                return;
	            }
	            sortedPrint(node.left, 2, depth + 1);
	            //System.out.println(String.format("%"+ 5 +"s", " ") + "HELLO");
	            //String format = "";
	            String format = getSpaces(depth * 2);
	            format += "<" + node.getData().getState() + ", "
	                + convertDate(node.getData().getDate()) + "> " + node.getData().getPositive();
	            System.out.println(format);
	            
	            sortedPrint(node.right, 2, depth + 1);
		}
		
	}
	
	public String getSpaces(int amount) {
	    String output = "";
	    for (int i = 0; i < amount; i++) {
	        output += " ";
	    }
	    return output;
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