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
	
    public TreeNode insertByDate(TreeNode current, String[] value) {
        if (current.equals(fly)) {
            numNodes++; 
            current = new Data(fly, value);
            return current;
        }
        
        if (value[0].compareTo(current.getData().getDate()) == 0) {
            if(value[1].compareTo(current.getData().getState()) < 0) {
                current.left = insertByDate(current.left, value);
            }
            else {
                current.right = insertByDate(current.right, value);
            }
        }
        else if (value[0].compareTo(current.getData().getDate()) < 0) {
               current.left = insertByDate(current.left, value);
        }
        else {
            current.right = insertByDate(current.right, value);
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	// Nikolai's BST Functions
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	// Dump Print /////////////////////////////////////////////////////////////////////////
	public int dumpPrint(int id) {
    	sortedPrint(root, id, 0);
    	return 0;
    }
	
	// State print ////////////////////////////////////////////////////////////////////////
	private int stateCount;
	
	public int printState(String state) {
		stateCount = 0;
		traverseState(root, state);
		return stateCount;
	}
	
	private void traverseState(TreeNode current, String state) {
		// check null
		if (current.getData().equals(null)) {
			return;
		}
		// if state match, print
		if (current.getData().getState().equals(state)) {
			current.getData().printData();
			stateCount++;
		}
		// check left
		if (!current.getData().left.getData().equals(null)) {
			traverseState(current.getData().left, state);
		}
		// check right
		if (!current.getData().right.getData().equals(null)) {
			traverseState(current.getData().right, state);
		}
		return;
	}
	
	// Continuously print /////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////
	// Average print in the past NUMBER of days starting at the Specified Date
	private ArrayList<String> getLatestDatesNum(String startDate, int numDays) {
		ArrayList<String> dates = new  ArrayList<String>(numDays);
		dates.set(0, startDate);
		int day;
		int month;
		for (int index = 1; index < numDays; index++) {
			// update the month
			if (startDate.substring(3, 5).equals("01")) {
				month = Integer.parseInt(startDate.substring(0, 3))-1;
				if (month == 1 || month == 3 || month == 5 || 
						month == 7 || month == 8 || month == 10 || month == 12) {
					day = 32;
				}
				else {
					day = 31;
				}
			}
			else {
				day = Integer.parseInt(startDate.substring(3, 5))-1;
				month = Integer.parseInt(startDate.substring(0, 3));
			}
			startDate = Integer.toString(month) + "/" + Integer.toString(day) +
					"/" + startDate.substring(5, 10);
			dates.set(index, startDate);
		}
		return dates;
	}
	
	public ArrayList<AverageState> avState;
	
	public String[] printAverage(int numDays, String startingDate) {
		avState = new ArrayList<AverageState>();
		ArrayList<String> dates = getLatestDatesNum(startingDate, numDays);
		traverseAverage(root, dates);
		avState = sortAv();
		int siz = avState.size();
		if (siz > 10) {
			siz = 10;
		}
		return new String[] {Integer.toString(siz), startingDate, dates.get(numDays-1)};
	}
	
	public void pA() {
		for (int index = 0; index < 10; index++) {
			System.out.println(avState.get(index).state + 
					Integer.toString(avState.get(index).reAverage()));
		}
	}
	
	public class AverageState{
		String state;
		int average;
		
		public AverageState(String state, int num) {
			this.state = state;
			average = num;
		}
		
		public void addAverage(int num) {
			average += num;
		}
		
		public int reAverage() {
			return average/7;
		}
	}
	
	private ArrayList<AverageState> sortAv(){
		ArrayList<AverageState> sorted = new ArrayList<AverageState>();
		for (AverageState state : avState) {
			for (int index = 0; index < sorted.size(); index++) {
				AverageState sort = sorted.get(index);
				if (state.reAverage() > sort.reAverage()) {
					sorted.add(index, state);
				}
			}
			if (sorted.size() == 0) {
				sorted.add(state);
			}
		}
		return sorted;
	}
	
	private boolean checkDates(ArrayList<String> dates, String curDate) {
		boolean re = false;
		for (String date : dates) {
			if (date.equals(curDate)) {
				re = true;
				break;
			}
		}
		return re;
	}
	
	private AverageState checkAvSt(String state) {
		for (AverageState st : avState) {
			if (st.state.equals(state)) {
				return st;
			}
		}
		return null;
	}
	
	private void traverseAverage(TreeNode current, ArrayList<String> dates) {
		// check null
		if (current.getData().equals(null)) {
			return;
		}
		// if date match, print
		if (checkDates(dates, current.getData().getDate())) {
			if (checkAvSt(current.getData().getState()).equals(null)) {
				avState.add(new AverageState(current.getData().getState(), 
						Integer.parseInt(current.getData().getPositive())));
			}
			else {
				checkAvSt(current.getData().getState()).addAverage(
						Integer.parseInt(current.getData().getPositive()));
			}
		}
		// check left
		if (!current.getData().left.getData().equals(null)) {
			traverseAverage(current.getData().left, dates);
		}
		// check right
		if (!current.getData().right.getData().equals(null)) {
			traverseAverage(current.getData().right, dates);
		}
		return;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// Print all records in the past NUMBER of days starting at Specified Date
	
	// Quality print ///////////////////////////////////////////////////////////////////
	
	// Date print //////////////////////////////////////////////////////////////////////
	private int dateCount;
	public int printDate(String date) {
		dateCount = 0;
		traverseDate(root, date);
		return dateCount;
	}
	private void traverseDate(TreeNode current, String date) {
		// check null
		if (current.getData().equals(null)) {
			return;
		}
		// if date match, print
		if (current.getData().getDate().equals(date)) {
			current.getData().printData();
			dateCount++;
		}
		// check left
		if (!current.getData().left.getData().equals(null)) {
			traverseDate(current.getData().left, date);
		}
		// check right
		if (!current.getData().right.getData().equals(null)) {
			traverseDate(current.getData().right, date);
		}
		return;
	}
	///////////////////////////////////////////////////////////////////////////////////
}