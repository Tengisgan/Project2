
public class BSTtemp {
	/** 
	 * methods for Data Class
	 */
	public void printData() {
		for (String element : data) {
			System.out.print(element + " ");
		}
		System.out.print("\n");
	}
		
	/**
	 * methods for BST CLass
	 */
	
	// Dump Print
	public int dumpPrint(int id) {
    	sortedPrint(root, id, 0);
    	return 0;
    }
	
	// State print
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
	
	// Continuously print
	
	
	// Average print in the past NUMBER of days starting at the Specified Date
	private String[] getLatestDatesNum(String startDate, int numDays) {
		String[] dates = (String[]) new  ArrayList<String>(numDays);
		dates[0] = startDate;
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
			dates[index] = startDate;
		}
		return dates;
	}
	
	// Print all records in the past NUMBER of days starting at Specified Date
	
	// Quality print
	
	// Date print
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
	
}
