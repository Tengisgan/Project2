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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Covid19TrackingManagers is a class that loads in
 * COVID-19 data. The class also allows for the user
 * to input commands, like remove, search, and dumpBST
 * to get an output they want. 
 * 
 * @author Tengis Gantulga and Nikolai Long
 * @pid tengisgan and nikolai
 * @version 2020.10.12
**/
public class Covid19TrackingManager2 {

    /**
     * Field that stores all data related to an input file's commands
    **/
    private static Covid19TrackingManager2 main = new Covid19TrackingManager2();

    /**
     * Field that stores the state values
    **/
    private static Map<String, String> states = Map.ofEntries(
    	new AbstractMap.SimpleEntry<>("al", "alabama"),
        new AbstractMap.SimpleEntry<>("ak", "alaska"),
        new AbstractMap.SimpleEntry<>("az", "arizona"),
        new AbstractMap.SimpleEntry<>("ar", "arkansas"),
        new AbstractMap.SimpleEntry<>("ca", "california"),
        new AbstractMap.SimpleEntry<>("co", "colorado"),
        new AbstractMap.SimpleEntry<>("ct", "connecticut"),
        new AbstractMap.SimpleEntry<>("de", "delaware"),
        new AbstractMap.SimpleEntry<>("fl", "florida"),
        new AbstractMap.SimpleEntry<>("ga", "georgia"),
        new AbstractMap.SimpleEntry<>("hi", "hawaii"),
        new AbstractMap.SimpleEntry<>("id", "idaho"),
        new AbstractMap.SimpleEntry<>("il", "illinois"),
        new AbstractMap.SimpleEntry<>("in", "indiana"),
        new AbstractMap.SimpleEntry<>("ia", "iowa"),
        new AbstractMap.SimpleEntry<>("ks", "kansas"),
        new AbstractMap.SimpleEntry<>("ky", "kentucky"),
        new AbstractMap.SimpleEntry<>("la", "louisiana"),
        new AbstractMap.SimpleEntry<>("me", "maine"),
        new AbstractMap.SimpleEntry<>("md", "maryland"),
        new AbstractMap.SimpleEntry<>("ma", "massachusetts"),
        new AbstractMap.SimpleEntry<>("mi", "michigan"),
        new AbstractMap.SimpleEntry<>("mn", "minnesota"),
        new AbstractMap.SimpleEntry<>("ms", "mississippi"),
        new AbstractMap.SimpleEntry<>("mo", "missouri"),
        new AbstractMap.SimpleEntry<>("mt", "montana"),
        new AbstractMap.SimpleEntry<>("ne", "nebraska"),
        new AbstractMap.SimpleEntry<>("nv", "nevada"),
        new AbstractMap.SimpleEntry<>("nh", "new hampshire"),
        new AbstractMap.SimpleEntry<>("nj", "new jersey"),
        new AbstractMap.SimpleEntry<>("nm", "new mexico"),
        new AbstractMap.SimpleEntry<>("ny", "new york"),
        new AbstractMap.SimpleEntry<>("nc", "north carolina"),
        new AbstractMap.SimpleEntry<>("nd", "north dakota"),
        new AbstractMap.SimpleEntry<>("oh", "ohio"),
        new AbstractMap.SimpleEntry<>("ok", "oklahoma"),
        new AbstractMap.SimpleEntry<>("or", "oregon"),
        new AbstractMap.SimpleEntry<>("pa", "pennsylvania"),
        new AbstractMap.SimpleEntry<>("ri", "rhode island"),
        new AbstractMap.SimpleEntry<>("sc", "south carolina"),
        new AbstractMap.SimpleEntry<>("sd", "south dakota"),
        new AbstractMap.SimpleEntry<>("tn", "tennessee"),
        new AbstractMap.SimpleEntry<>("tx", "texas"),
        new AbstractMap.SimpleEntry<>("ut", "utah"),
        new AbstractMap.SimpleEntry<>("vt", "vermont"),
        new AbstractMap.SimpleEntry<>("va", "virginia"),
        new AbstractMap.SimpleEntry<>("wa", "washington"),
        new AbstractMap.SimpleEntry<>("wv", "west virginia"),
        new AbstractMap.SimpleEntry<>("wi", "wisconsin"),
        new AbstractMap.SimpleEntry<>("wy", "wyoming"),
        new AbstractMap.SimpleEntry<>("as", "american samoa"),
        new AbstractMap.SimpleEntry<>("dc", "district of columbia"),
        new AbstractMap.SimpleEntry<>("fm", "federated states of micronesia"),
        new AbstractMap.SimpleEntry<>("gu", "guam"),
        new AbstractMap.SimpleEntry<>("mh", "marshall islands"),
        new AbstractMap.SimpleEntry<>("mp", "northern mariana islands"),
        new AbstractMap.SimpleEntry<>("pw", "palau"),
        new AbstractMap.SimpleEntry<>("pr", "puerto rico"),
        new AbstractMap.SimpleEntry<>("vi", "virgin islands")
    );

    /**
     * Field storing the binary search tree containing the sorted data
    **/
    private BST bst = new BST();

    /**
     * Function to load data from a file into the BST
    **/
    public void load(String[] params){
        // if load has been called with more than just the filename
        if (params.length != 2){
            System.out.println("invalid load params");
            return;
        }
        String filename = params[1];

        // try to open the file
        try { 
            Scanner input = new Scanner(new File(filename));
            input.next(); // skip the headder line

            int count = 0;
            while (input.hasNext()) { 
                String strRow = input.next();
                String[] newDataAr = strRow.split(",", -1); // split the csv - including the empty elements

                // if the row is not the right size or blank, continue
                if (newDataAr.length < 10 || strRow.trim().equals("")) { 
                    continue;
                }

                // if state, date, or quality is empty, continue
                if (newDataAr[0].equals("") || newDataAr[1].equals("") ||
                    newDataAr[8].equals("") || newDataAr[0].length() != 8) { 
                    System.out.println("Discard invalid record");
                    continue;
                }
                
                // Save the new state string
                String newState = states.get(newDataAr[1].toLowerCase());

                // if the state is not valid, continue
                if (newState == null) { 
                    System.out.println("State of " + newDataAr[1] +
                        " does not exist");
                    continue;
                }
                
                // Fix the state
                if (newState.length() != 2) {
                	newState = newDataAr[1].toUpperCase();
                }
                else {
                	newState = newState.toUpperCase();
                }
                
                // Save the new date string
                String newDate = bst.convertDate(newDataAr[0]);
                
                // Save the new strings back into new data
                newDataAr[0] = newDate;
                newDataAr[1] = newState;

                // attempt to find the data in the bst
                TreeNode existingData = bst.find(newState, bst.root).getData();

                // INSERT NEW DATA
                // if the bst does not contain the data, add the new data
                if (existingData == null) { 
                	if (bst.numNodes == 0) {
                		bst.root = bst.insert(bst.root, newDataAr);
                	}
                	else {
                		bst.insert(bst.root, newDataAr);
                	}
                    count++;
                    continue;
                }
                
                // REPLACE OLD DATA WITH NEW DATA
                // if new data has a better grade than the existing data
                // replace the old with the new
                if (bst.compareGrades(existingData, newDataAr)) {
                    System.out.println("Data has been updated for "
                        + newState + " " + newDate);
                    bst.replace(existingData, newDataAr);
                    count++;
                    continue;
                }

                // UPDATE OLD DATA WITH ELEMENTS OF NEW DATA
                // if there are elements in the old data that can be updated,
                // update them
                if (bst.updateData(existingData, newDataAr)) {
                    System.out.println("Data has been updated f"
                        + "or the missing data in " 
                            + newState);
                    count++;
                    continue;
                }

                // THROW OUT NEW DATA
                // there are no elements to be updated,
                // so the new data is completely rejected
                System.out.println("Low quality data"
                		+ " rejected for " + newState);
            }

//            System.out.println(bst.root.getData().getState());
            System.out.println("Finished loading " + filename + " file");
            System.out.println(count + " records have been loaded");
            input.close();
            System.out.println(bst.isEmpty());
        }

        // invalid filename parameter
        catch (FileNotFoundException e) {
            System.out.println("File " + filename + " was not found");
        }
    }

    /**
     * Function to remove data of a specified quality from the BST
    **/
    public void remove(String[] params){
    	// if remove was called with more than a grade quality identifier
        if (params.length != 2){
            System.out.println("invalid remove params");
            return;
        }
        
        // remove records from the bst
        String grade = params[1];
        int removed = bst.removeGrade(grade);
        
        System.out.println(Integer.toString(removed) + " records with quality grade lower or equal to " + grade + " have been removed");
    }

    /**
     * Function to seach for and print specified data from the BST 
    **/
    public void search(String[] params){
    	// if search is called with no flags, search for the latest date
    	String latestDate = bst.getLatestDate();
    	String[] newParams = {"search", "-D", latestDate};
    	if (params.length == 1) {
    		params = newParams;
    	}
    	
    	// -S
    	if (params[1].equals("-S")) {
    		String state = states.get(params[2].toLowerCase());
    		int num = bst.printState(state);
    		if (num == -1) return;
    		System.out.println(Integer.toString(num) + 
    				" records have been printed for state  " + state);
    		return;
    	}
    	
    	// -C
    	if (params[1].equals("-C")) {
    		int num = bst.printCases(Integer.parseInt(params[2]), latestDate);
    		if (num == -1) return;
    		System.out.println(Integer.toString(num) + 
    				" states have daily numbers of positive cases greater than or equal to " + 
    				params[2] + " for at least 7 days continuously");
    		return;
    	}
    	
    	// -T
    	if (params[1].equals("-T")) {
    		if (params.length == 5 && params[3].equals("-D")) {
    			latestDate = params[4];
    		}
    		String[] dates = bst.printAverage(Integer.parseInt(params[2]), latestDate);
    		if (dates == null) return;
    		System.out.println("Top " + Integer.parseInt(dates[0]) + 
    				" states with the highest average daily positive cases from " + 
    				dates[1] + " to " + dates[2]);
    		return;
    	}
    	
    	// -N
    	if (params[1].equals("-N")) {
    		if (params.length == 5 && params[3].equals("-D")) {
    			latestDate = params[4];
    		}
    		String[] dates = bst.printNumber(Integer.parseInt(params[2]), latestDate);
    		if (dates == null) return;
    		System.out.println(Integer.parseInt(dates[0]) + 
    				" records have been printed from " + dates[1] + " to " + dates[2]);
    		return;
    	}
    	
    	// -Q
    	if (params[1].equals("-Q")) {
    		if (params.length >= 5) {
    			if (params[3].equals("-S")) {
    				String state = states.get(params[4].toLowerCase());
    				if (params.length == 7) {
    					int num = bst.printQuality(params[2], state, params[6]);
    					if (num == -1) return;
    					System.out.println(Integer.toString(num) + 
    							" records have been printed with better or equal than quality grade " + 
    							params[2] + " for state " + state + " on date " + params[6]);
    					return;
    				}
    				int num = bst.printQuality(params[2], state, "");
    				if (num == -1) return;
    				System.out.println(Integer.toString(num) + 
    						" records have been printed with better or equal than quality grade " + 
    						params[2] + " for state " + state);
    				return;
    			}
    			if (params.length == 7) {
    				String state = states.get(params[6].toLowerCase());
    				int num = bst.printQuality(params[2], state, params[4]);
    				if (num == -1) return;
    				System.out.println(Integer.toString(num) + 
    						" records have been printed with better or equal than quality grade " + 
    						params[2] + " for state " + state + " on date " + params[4]);
    				return;
    			}
    			int num = bst.printQuality(params[2], "", params[4]);
    			if (num == -1) return;
    			System.out.println(Integer.toString(num) + 
    					" records have been printed with better or equal than quality grade " + 
    					params[2] + " on date " + params[4]);
    			return;
    		}
    		int num = bst.printQuality(params[2], "", "");
    		if (num == -1) return;
    		System.out.println(Integer.toString(num) + " records have been printed with better or equal than quality grade " + params[2]);
    	}
    	
    	// -D
    	if (params[1].equals("-D")) {
    		int num = bst.printDate(params[2]);
    		if (num == -1) return;
    		System.out.println(Integer.toString(num) + " records have been printed on date " + params[2]);
    		return;
    	}
    	
    	System.out.println("search error");
    }
    
    /**
     * Function to print the BST
    **/
    public void dumpBST(String[] params){
    	// if dump was called with more than a print identifier
        if (params.length != 2){
            System.out.println("invalid dump params");
            return;
        }
        
        int id = Integer.parseInt(params[1]);
        int records = bst.sortedPrint(id);
        System.out.println(Integer.toString(records) + " records have been printed");
    }

    /**
     * Function to handle the command inputs (from a file)
     * @throws FileNotFoundException 
    **/
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inpt = new Scanner(new File(args[0]));
        String[] params;

        while (inpt.hasNextLine()){
            params = inpt.nextLine().split(" ");
            if (params[0].equals("load")){
                main.load(params);
            }
            else if (params[0].equals("remove")){
                main.remove(params);
            }
            else if (params[0].equals("search")){
                main.search(params);
            }
            else if (params[0].equals("dumpBST")){
                main.dumpBST(params);
            }
            else{
                System.out.println("input parse fail");
            }
        }
        
        inpt.close();
    }
}
