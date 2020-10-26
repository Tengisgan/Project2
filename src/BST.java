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
	public Flyweight getFly() {
	    return fly;
	}
	public TreeNode find(String state, TreeNode node) {
	    if (node.equals(fly)) {
	        return fly;
	    }
	    
	    if (node.getData().getState().compareTo(state) == 0) {
	        return node; 
	    }
	    
	    else if (node.getData().getState().compareTo(state) > 0) {
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
  
    public TreeNode minValue(TreeNode current) {
        TreeNode minNode = current;
        while (!minNode.left.equals(fly)) {
            minNode = minNode.left;
        }
        return minNode;
    }


    public TreeNode removeHelper(TreeNode current, String[] value) {
        if (current.equals(fly)) {
            return current;
        }

        if (value[1].compareTo(current.getData().getState()) < 0) {
            current.left = removeHelper(current.left, value);
        }
        else if (value[1].compareTo(current.getData().getState()) > 0) {
            current.right = removeHelper(current.right, value);
        }

        // states match

        else {
            if (value[0].compareTo(current.getData().getDate()) < 0) {
                current.left = removeHelper(current.left, value);
            }
            else if (value[0].compareTo(current.getData().getDate()) > 0) {
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

                current.getData().data = minValue(root.right).getData().data;
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
   
   public void replaceDate(String[] existingData, String[] newData) {
	   this.findSDdate(existingData[1], existingData[0], this.root).getData().data = newData;
   }
   
   public TreeNode findSDdate(String state, String date, TreeNode node) {
       if (node.equals(fly)) {
           return fly;
       }

       if (node.getData().getDate().equals(date)) {
           if(node.getData().getState().equals(state)) {
               return node;
           }

           if(node.getData().getState().compareTo(state) > 0) {
               return findSDdate(state, date, node.left);
               }
           else {
               return findSDdate(state, date, node.right);
           }
       }

       else if (node.getData().getDate().compareTo(date) > 0) {
           return findSDdate(state, date, node.left);
       }
       else {
           return findSDdate(state, date, node.right);
       }

   }
   
	
	public boolean updateData(String[] existingData, String[] newData) {
	    this.remove(existingData);
        boolean updated = false;
        for (int i = 0; i < existingData.length; i++) {
            if (existingData[i].equals("") && !newData[i].equals("")) {
                existingData[i] = newData[i];
                updated = true;
            }
        }
        this.insert(this.root, existingData);
        return updated;
	}
	
	private ArrayList<String[]> arrayOfBST; 

    public void putIntoArray(TreeNode current, String grade) {
        if (current.equals(fly)) {
            return;
        }

        if (!current.left.equals(fly)) {
            putIntoArray(current.left, grade); 
        }
        if (getNumericalGrade(current.getData().getGrade()) <= getNumericalGrade(grade)) {
            arrayOfBST.add(current.getData().getArray());
        }
        if (!current.right.equals(fly)) {
            putIntoArray(current.right, grade);
        }
    }
	
	public int removeGrade(String grade) {
        int counter = 0;
        arrayOfBST = new ArrayList<>();
        putIntoArray(this.root, grade);
        for (int i = 0; i < arrayOfBST.size(); i++) {
            remove(arrayOfBST.get(i));
            counter++;
        }
        return counter;
    }
	
    private int getNumericalGrade(String qualityGrade) {
        int grade = (700 - ((int) qualityGrade.charAt(0) * 10));
        return qualityGrade.length() == 1 ? grade : grade + 
            (44 - ((int) qualityGrade.charAt(1)));
    }
	
	public String getSpaces(int amount) {
	    String output = "";
	    for (int i = 0; i < amount; i++) {
	        output += " ";
	    }
	    return output;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	// Nikolai's BST Functions
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	// Latest Date /////////////////////////////////////////////////////////////////////////
	private String latest;
	public String getLatestDate() {
		latest = "01/01/2020";
		traverseLatest(root);
		return latest;
	}
	
    public void traverseLatest(TreeNode current) {
    	if (current.equals(fly)) {
    		return;
    	}
    	// if larger month, replace
    	if (getMonth(current.getData().getDate()) > getMonth(latest)) {
    		latest = current.getData().getDate();
    	}
    	// if same month, larger day, replace
    	else if (getMonth(current.getData().getDate()) == getMonth(latest) &&
    			getDay(current.getData().getDate()) > getDay(latest)) {
    		latest = current.getData().getDate();
    	}
    	// check left
    	if (!current.left.equals(fly)) {
			traverseLatest(current.left);
		}
		// check right
		if (!current.right.equals(fly)) {
			traverseLatest(current.right);
		}
		return;
    }
    
    public int getMonth(String date) {
    	return Integer.parseInt(date.substring(0, 2));
    }
    
    public int getDay(String date) {
    	return Integer.parseInt(date.substring(3, 5));
    }
	
	// Dump Print /////////////////////////////////////////////////////////////////////////
	private int numDump;
	
    public int printDump(int id) {
    	numDump = 0;
    	traverseDump(root, id, 0);
    	return numDump;
    }
	
    public void traverseDump(TreeNode node, int type, int depth) {
        if (type == 1) {
            
            if (node.equals(fly)) {
                String format = getSpaces(depth * 2);
                format += "E";
                System.out.println(format);
                return;
            }
            
            traverseDump(node.left, 1, depth + 1);
            
            String format = getSpaces(depth * 2);
            format += "<" + node.getData().getDate() + ", "
                + node.getData().getState() + "> " + node.getData().getPositive();
            System.out.println(format);
            numDump++;

            if (!node.right.equals(fly)) {
                traverseDump(node.right, 1, depth + 1);
            }
        }

        else if (type == 2) {
                if (node.equals(fly)) {

                    String format = getSpaces(depth * 2);
                    format += "E";
                    System.out.println(format);
                    return; 
                    
                }
                
                traverseDump(node.left, 2, depth + 1);
                String format = getSpaces(depth * 2);
                format += "<" + node.getData().getState() + ", "
                    + convertDate(node.getData().getDate()) + "> " + node.getData().getPositive();
                System.out.println(format);
                numDump++;

                if (!node.right.equals(fly)) {
                    traverseDump(node.right, 2, depth + 1);
                }
        }

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
		if (current.equals(fly)) {
			return;
		}
		// check left
		if (!current.left.equals(fly)) {
			traverseState(current.left, state);
		}
		// if state match, print
		if (current.getData().getState().equals(state)) {
			current.getData().printData();
			stateCount++;
		}
		// check right
		if (!current.right.equals(fly)) {
			traverseState(current.right, state);
		}
		return;
	}
	
	// Continuously print /////////////////////////////////////////////////////////////
	private ArrayList<StateCase> cStates;
	
	public int printCases(int numCases, String startingDate) {
		cStates = new ArrayList<StateCase>();
		ArrayList<String> dates = getLatestDatesNum(startingDate, 7);
		traverseCases(root, dates, numCases);
		return findCases(startingDate, dates.get(6));
	}
	
	private void traverseCases(TreeNode current, ArrayList<String> dates, int numCases) {
		// check null
		if (current.equals(fly)) {
			return;
		}
		
		// check left
		if (!current.left.equals(fly)) {
			traverseCases(current.left, dates, numCases);
		}
		// if date match and numCases match, add
		if (checkDates(dates, current.getData().getDate()) &&
				numCases <= Integer.parseInt(current.getData().getPositive())) {
			boolean found = false;
			for (StateCase st : cStates) {
				if (st.state.equals(current.getData().getState())) {
					st.numDays++;
					found = true;
				}
			}
			if (!found) {
				cStates.add(new StateCase(current.getData().getState()));
			}
		}
		// check right
		if (!current.right.equals(fly)) {
			traverseCases(current.right, dates, numCases);
		}
		return;
	}
	
	private int findCases(String start, String end) {
		int num = 0;
		for (StateCase st : cStates) {
			if (st.numDays == 7) {
				num++;
				st.print();
				System.out.println(start + " - " + end);
			}
		}
		return num;
	}
	
	public class StateCase {
		String state;
		int numDays;
		
		public StateCase(String state) {
			this.state = state;
			numDays = 1;
		}
		
		public void print() {
			System.out.println("State " + state);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	// Average print in the past NUMBER of days starting at the Specified Date
	private ArrayList<String> getLatestDatesNum(String startDate, int numDays) {
		ArrayList<String> dates = new  ArrayList<String>(numDays);
		dates.add(startDate);
		int day;
		int month;
		for (int index = 1; index < numDays; index++) {
			// update the month
			if (startDate.substring(3, 5).equals("01")) {
				month = Integer.parseInt(startDate.substring(0, 2))-1;
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
				month = Integer.parseInt(startDate.substring(0, 2));
			}
			String dayString = Integer.toString(day);
			String monthString = Integer.toString(month);
			if (day < 10) {
				dayString = "0" + dayString;
			}
			if (month < 10) {
				monthString = "0" + monthString;
			}
			startDate = monthString + "/" + dayString +
					startDate.substring(5, 10);
			dates.add(startDate);
		}
		return dates;
	}
	
	public ArrayList<AverageState> avState;
	
	public String[] printAverage(int numDays, String startingDate) {
		avState = new ArrayList<AverageState>();
		ArrayList<String> dates = getLatestDatesNum(startingDate, numDays);
		traverseAverage(root, dates, numDays);
		avState = sortAv();
		int siz = avState.size();
		if (siz > 10) {
			siz = 10;
		}
		return new String[] {Integer.toString(siz), startingDate, dates.get(numDays-1)};
	}
	
	public void pA() {
		for (int index = 0; index < avState.size(); index++) {
			if (index == 10) {
				break;
			}
			System.out.println(avState.get(index).state + " " +
					Integer.toString(avState.get(index).reAverage()));
		}
	}
	
	public class AverageState{
		String state;
		int average;
		int numDays;
		
		public AverageState(String state, int num, int numDays) {
			this.state = state;
			average = num;
			this.numDays = numDays;
		}
		
		public void addAverage(int num) {
			average += num;
		}
		
		public int reAverage() {
			return average/numDays;
		}
	}
	
	private ArrayList<AverageState> sortAv(){
		ArrayList<AverageState> sorted = new ArrayList<AverageState>(0);
		for (AverageState state : avState) {
			if (sorted.size() == 0) {
				sorted.add(state);
				continue;
			}
			boolean added = false;
			for (int index = 0; index < sorted.size(); index++) {
				AverageState sort = sorted.get(index);
				if (state.reAverage() > sort.reAverage()) {
					sorted.add(index, state);
					added = true;
					break;
				}
			}
			if (!added) {
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
	
	private void traverseAverage(TreeNode current, ArrayList<String> dates, int numDays) {
		// check null
		if (current.equals(fly)) {
			return;
		}
		// check left
		if (!current.left.equals(fly)) {
			traverseAverage(current.left, dates, numDays);
		}
		// if date match, print
		if (checkDates(dates, current.getData().getDate())) {
			if (checkAvSt(current.getData().getState()) == null) {
				avState.add(new AverageState(current.getData().getState(), 
						Integer.parseInt(current.getData().getPositive()), numDays));
			}
			else {
				checkAvSt(current.getData().getState()).addAverage(
						Integer.parseInt(current.getData().getPositive()));
			}
		}
		// check right
		if (!current.right.equals(fly)) {
			traverseAverage(current.right, dates, numDays);
		}
		return;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// Print all records in the past NUMBER of days starting at Specified Date
	private int numNum;
	
	public String[] printNumber(int numDays, String startingDate) {
		numNum = 0;
		ArrayList<String> dates = getLatestDatesNum(startingDate, numDays);
		traverseNum(root, dates);
		return new String[] {Integer.toString(numNum), startingDate, dates.get(numDays-1)};
	}
	
	public void traverseNum(TreeNode current, ArrayList<String> dates){
		// check null
		if (current.equals(fly)) {
			return;
		}
		// check left
		if (!current.left.equals(fly)) {
			traverseNum(current.left, dates);
			
		}
		// if date match, print
		if (checkDates(dates, current.getData().getDate())) {
			current.getData().printData();
			numNum++;
		}
		// check right
		if (!current.right.equals(fly)) {
			traverseNum(current.right, dates);
		}
		return;
	}
	
	// Quality print ///////////////////////////////////////////////////////////////////
	private int numQ;
	private ArrayList<String[]> qualityAr;
	public int printQuality(String quality, String state, String date) {
		numQ = 0;
		qualityAr = new ArrayList<String[]>();
		putIntoArrayForPrintingGrade(root, quality);
		traverseQuality(state, date);
		return numQ;
	}
	
	public void traverseQuality(String state, String date) {
		for (String[] ar : qualityAr) {
			if (state.equals("")) {
				if (date.equals("")) {
					printAr(ar);
					numQ++;
				}
				else if (date.equals(ar[0])) {
					printAr(ar);
					numQ++;
				}
			}
			else if (state.equals(ar[1])) {
				if (date.equals("")) {
					printAr(ar);
					numQ++;
				}
				else if (date.equals(ar[0])) {
					printAr(ar);
					numQ++;
				}
			}
		}
	}
	
	public void printAr(String[] ar) {
		System.out.print(ar[1] + " " + ar[0]);
		for (int index = 2; index < ar.length; index++) {
			if (ar[index].equals("")) {
				System.out.print(" 0");
			}
			else {
				System.out.print(" " + ar[index]);
			}
		}
		System.out.print("\n");
	}
	
	
	public void putIntoArrayForPrintingGrade(TreeNode current, String grade) {
        if (current.equals(fly)) {
            return;
        }

        if (!current.left.equals(fly)) {
        	putIntoArrayForPrintingGrade(current.left, grade); 
        }
        if (getNumericalGrade(current.getData().getGrade()) >= getNumericalGrade(grade)) {
        	qualityAr.add(current.getData().getArray());
        }
        if (!current.right.equals(fly)) {
        	putIntoArrayForPrintingGrade(current.right, grade);
        }
    }
	
	
	// Date print //////////////////////////////////////////////////////////////////////
	private int dateCount;
	public int printDate(String date) {
		dateCount = 0;
		traverseDate(root, date);
		return dateCount;
	}
	private void traverseDate(TreeNode current, String date) {
		// check null
		if (current.equals(fly)) {
			return;
		}
		// check left
		if (!current.left.equals(fly)) {
			traverseDate(current.left, date);
		}
		// if date match, print
		if (current.getData().getDate().equals(date)) {
			current.getData().printData();
			dateCount++;
		}
		// check right
		if (!current.right.equals(fly)) {
			traverseDate(current.right, date);
		}
		return;
	}
	///////////////////////////////////////////////////////////////////////////////////
}