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
    private static HashMap<String, String> states = (HashMap<String, String>) Map.ofEntries(
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
        if (params.length != 2){
            System.out.println("invalid load params");
            return;
        }
    }

    /**
     * Function to remove data of a specified quality from the BST
    **/
    public void remove(String[] params){
        if (params.length != 2){
            System.out.println("invalid remove params");
            return;
        }
    }

    /**
     * Function to seach for and print specified data from the BST 
    **/
    public void search(String[] params){

    }
    
    /**
     * Function to print the BST into a file
    **/
    public void dumpBST(String[] params){
        if (params.length != 2){
            System.out.println("invalid dump params");
            return;
        }
    }

    /**
     * Function to handle the command inputs (from a file)
    **/
    public static void main(String[] args) {
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
