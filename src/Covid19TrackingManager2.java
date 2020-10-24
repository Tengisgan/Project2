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
    private static HashMap<String, String> states = new HashMap<String, String>();
    
    private static void fillMap() {
    	states.put("al", "alabama");
        states.put("ak", "alaska");
        states.put("az", "arizona");
        states.put("ar", "arkansas");
        states.put("ca", "california");
        states.put("co", "colorado");
        states.put("ct", "connecticut");
        states.put("de", "delaware");
        states.put("fl", "florida");
        states.put("ga", "georgia");
        states.put("hi", "hawaii");
        states.put("id", "idaho");
        states.put("il", "illinois");
        states.put("in", "indiana");
        states.put("ia", "iowa");
        states.put("ks", "kansas");
        states.put("ky", "kentucky");
        states.put("la", "louisiana");
        states.put("me", "maine");
        states.put("md", "maryland");
        states.put("ma", "massachusetts");
        states.put("mi", "michigan");
        states.put("mn", "minnesota");
        states.put("ms", "mississippi");
        states.put("mo", "missouri");
        states.put("mt", "montana");
        states.put("ne", "nebraska");
        states.put("nv", "nevada");
        states.put("nh", "new hampshire");
        states.put("nj", "new jersey");
        states.put("nm", "new mexico");
        states.put("ny", "new york");
        states.put("nc", "north carolina");
        states.put("nd", "north dakota");
        states.put("oh", "ohio");
        states.put("ok", "oklahoma");
        states.put("or", "oregon");
        states.put("pa", "pennsylvania");
        states.put("ri", "rhode island");
        states.put("sc", "south carolina");
        states.put("sd", "south dakota");
        states.put("tn", "tennessee");
        states.put("tx", "texas");
        states.put("ut", "utah");
        states.put("vt", "vermont");
        states.put("va", "virginia");
        states.put("wa", "washington");
        states.put("wv", "west virginia");
        states.put("wi", "wisconsin");
        states.put("wy", "wyoming");
        states.put("as", "american samoa");
        states.put("dc", "district of columbia");
        states.put("fm", "federated states of micronesia");
        states.put("gu", "guam");
        states.put("mh", "marshall islands");
        states.put("mp", "northern mariana islands");
        states.put("pw", "palau");
        states.put("pr", "puerto rico");
        states.put("vi", "virgin islands");
        states.put("alabama", "al");
        states.put("alaska", "ak");
        states.put("arizona", "az");
        states.put("arkansas", "ar");
        states.put("california", "ca");
        states.put("colorado", "co");
        states.put("connecticut", "ct");
        states.put("delaware", "de");
        states.put("florida", "fl");
        states.put("georgia", "ga");
        states.put("hawaii", "hi");
        states.put("idaho", "id");
        states.put("illinois", "il");
        states.put("indiana", "in");
        states.put("iowa", "ia");
        states.put("kansas", "ks");
        states.put("kentucky", "ky");
        states.put("louisiana", "la");
        states.put("maine", "me");
        states.put("maryland", "md");
        states.put("massachusetts", "ma");
        states.put("michigan", "mi");
        states.put("minnesota", "mn");
        states.put("mississippi", "ms");
        states.put("missouri", "mo");
        states.put("montana", "mt");
        states.put("nebraska", "ne");
        states.put("nevada", "nv");
        states.put("new hampshire", "nh");
        states.put("new jersey", "nj");
        states.put("new mexico", "nm");
        states.put("new york", "ny");
        states.put("north carolina", "nc");
        states.put("north dakota", "nd");
        states.put("ohio", "oh");
        states.put("oklahoma", "ok");
        states.put("oregon", "or");
        states.put("pennsylvania", "pa");
        states.put("rhode island", "ri");
        states.put("south carolina", "sc");
        states.put("south dakota", "sd");
        states.put("tennessee", "tn");
        states.put("texas", "tx");
        states.put("utah", "ut");
        states.put("vermont", "vt");
        states.put("virginia", "va");
        states.put("washington", "wa");
        states.put("west virginia", "wv");
        states.put("wisconsin", "wi");
        states.put("wyoming", "wy");
        states.put("american samoa", "as");
        states.put("district of columbia", "dc");
        states.put("federated states of micronesia", "fm");
        states.put("guam", "gu");
        states.put("marshall islands", "mh");
        states.put("northern mariana islands", "mp");
        states.put("palau", "pw");
        states.put("puerto rico", "pr");
        states.put("virgin islands", "vi");
    }

    /**
     * Field storing the binary search tree containing the sorted data
    **/
    private BST bst = new BST();
    private BST dateBST = new BST();

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
                
                // if all the elements are blank, continue
                boolean cont = true;
                for (String i : newDataAr) {
                    if (!i.equals("")) {
                        cont = false;
                    }
                }
                if (cont) {
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
                Data existingData = bst.findSD(newState, newDate, bst.root).getData();

                // INSERT NEW DATA
                // if the bst does not contain the data, add the new data
                if (existingData == null) { 
                    if (bst.numNodes == 0) {
                        bst.root = bst.insert(bst.root, newDataAr);
                        dateBST.root = dateBST.insertByDate(dateBST.root, newDataAr);
                    }
                    else {
                        bst.insert(bst.root, newDataAr);
                        dateBST.insertByDate(dateBST.root, newDataAr);
                    }
                    count++;
                    continue;
                }
                String[] existingDataAr = existingData.getArray();
                
                // REPLACE OLD DATA WITH NEW DATA
                // if new data has a better grade than the existing data
                // replace the old with the new
                if (bst.root.getData().existingDataGradeIsLessThan(existingDataAr, newDataAr)) {
                    System.out.println("Data has been updated for "
                        + newState + " " + newDate);
                    bst.replace(existingDataAr, newDataAr);
                    dateBST.replaceDate(existingDataAr, newDataAr);
                    count++;
                    continue;
                }

                // UPDATE OLD DATA WITH ELEMENTS OF NEW DATA
                // if there are elements in the old data that can be updated,
                // update them
                if (bst.updateData(bst.findSD(newState, newDate, bst.root).getData().data, newDataAr)) {
                	dateBST.updateData(dateBST.findSDdate(newState, newDate, dateBST.root).getData().data, newDataAr);
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

    
    private String header = "state   date         positive    negative    hospitalized   onVentilatorCurrently    onVentilatorCumulative   recovered   dataQualityGrade   death   ";
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
        	System.out.println(header);
        	// try to get state from state map
        	String newState = states.get(params[2].toLowerCase());
        	
        	// if return is null, invalid state
        	if (newState == null) {
        		System.out.println("invalid state");
        		return;
        	}
        	
        	// Fix the state
            if (newState.length() != 2) {
                newState = params[2].toUpperCase();
            }
            else {
                newState = newState.toUpperCase();
            }
        	
            int num = bst.printState(newState);
            if (num == -1) return;
            System.out.println(Integer.toString(num) + 
                    " records have been printed for state  " + newState);
            return;
        }
        
        // -C
        if (params[1].equals("-C")) {
        	System.out.println(header);
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
            bst.pA();
            return;
        }
        
        // -N
        if (params[1].equals("-N")) {
        	System.out.println(header);
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
        	System.out.println(header);
            if (params.length >= 5) {
                if (params[3].equals("-S")) {
                	// try to get state from state map
                	String newState = states.get(params[4].toLowerCase());
                	
                	// if return is null, invalid state
                	if (newState == null) {
                		System.out.println("invalid state");
                		return;
                	}
                	
                	// Fix the state
                    if (newState.length() != 2) {
                        newState = params[4].toUpperCase();
                    }
                    else {
                        newState = newState.toUpperCase();
                    }
                    if (params.length == 7) {
                        int num = bst.printQuality(params[2], newState, params[6]);
                        if (num == -1) return;
                        System.out.println(Integer.toString(num) + 
                                " records have been printed with better or equal than quality grade " + 
                                params[2] + " for state " + newState + " on date " + params[6]);
                        return;
                    }
                    int num = bst.printQuality(params[2], newState, "");
                    if (num == -1) return;
                    System.out.println(Integer.toString(num) + 
                            " records have been printed with better or equal than quality grade " + 
                            params[2] + " for state " + newState);
                    return;
                }
                if (params.length == 7) {
                	// try to get state from state map
                	String newState = states.get(params[6].toLowerCase());
                	
                	// if return is null, invalid state
                	if (newState == null) {
                		System.out.println("invalid state");
                		return;
                	}
                	
                	// Fix the state
                    if (newState.length() != 2) {
                        newState = params[6].toUpperCase();
                    }
                    else {
                        newState = newState.toUpperCase();
                    }
                    int num = bst.printQuality(params[2], newState, params[4]);
                    if (num == -1) return;
                    System.out.println(Integer.toString(num) + 
                            " records have been printed with better or equal than quality grade " + 
                            params[2] + " for state " + newState + " on date " + params[4]);
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
            return;
        }
        
        // -D
        if (params[1].equals("-D")) {
        	System.out.println(header);
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
        
        // check the dump type
        int id = Integer.parseInt(params[1]);
        int num;
        if (id == 1) {
        	num = dateBST.printDump(id);
        }
        else {
        	num = bst.printDump(id);
        }
        
        System.out.println(Integer.toString(num) + " records have been printed");
    }

    /**
     * Function to handle the command inputs (from a file)
     * @throws FileNotFoundException 
    **/
    public static void main(String[] args) throws FileNotFoundException {
        fillMap();
        Scanner inpt = new Scanner(new File(args[0]));
        String[] params;

        while (inpt.hasNextLine()){
            params = inpt.nextLine().split(" ");
            if (params[0].length() == 0) {
            	continue;
            }
            if (params[0].substring(0, 1).equals("\t")) {
            	params[0] = params[0].substring(1, params[0].length());
            }
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
        }
        
        inpt.close();
    }
}
