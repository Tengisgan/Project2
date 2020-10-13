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
import static java.util.HashMap.entry; 

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
    private static HashMap<String, String> states = HashMap.ofEntries(
        entry.("al", "alabama"),
        entry.("ak", "alaska"),
        entry.("az", "arizona"),
        entry.("ar", "arkansas"),
        entry.("ca", "california"),
        entry.("co", "colorado"),
        entry.("ct", "connecticut"),
        entry.("de", "delaware"),
        entry.("fl", "florida"),
        entry.("ga", "georgia"),
        entry.("hi", "hawaii"),
        entry.("id", "idaho"),
        entry.("il", "illinois"),
        entry.("in", "indiana"),
        entry.("ia", "iowa"),
        entry.("ks", "kansas"),
        entry.("ky", "kentucky"),
        entry.("la", "louisiana"),
        entry.("me", "maine"),
        entry.("md", "maryland"),
        entry.("ma", "massachusetts"),
        entry.("mi", "michigan"),
        entry.("mn", "minnesota"),
        entry.("ms", "mississippi"),
        entry.("mo", "missouri"),
        entry.("mt", "montana"),
        entry.("ne", "nebraska"),
        entry.("nv", "nevada"),
        entry.("nh", "new hampshire"),
        entry.("nj", "new jersey"),
        entry.("nm", "new mexico"),
        entry.("ny", "new york"),
        entry.("nc", "north carolina"),
        entry.("nd", "north dakota"),
        entry.("oh", "ohio"),
        entry.("ok", "oklahoma"),
        entry.("or", "oregon"),
        entry.("pa", "pennsylvania"),
        entry.("ri", "rhode island"),
        entry.("sc", "south carolina"),
        entry.("sd", "south dakota"),
        entry.("tn", "tennessee"),
        entry.("tx", "texas"),
        entry.("ut", "utah"),
        entry.("vt", "vermont"),
        entry.("va", "virginia"),
        entry.("wa", "washington"),
        entry.("wv", "west virginia"),
        entry.("wi", "wisconsin"),
        entry.("wy", "wyoming"),
        entry.("as", "american samoa"),
        entry.("dc", "district of columbia"),
        entry.("fm", "federated states of micronesia"),
        entry.("gu", "guam"),
        entry.("mh", "marshall islands"),
        entry.("mp", "northern mariana islands"),
        entry.("pw", "palau"),
        entry.("pr", "puerto rico"),
        entry.("vi", "virgin islands")
    );

    /**
     * Field storing the binary search tree containing the sorted data
    **/
    private BST bst = new BST();

    /**
     * Function to load data from a file into the BST
    **/
    public void load(String[] params){
        if (params.length() != 2){
            System.out.println("invalid load params");
            return;
        }
    }

    /**
     * Function to remove data of a specified quality from the BST
    **/
    public void remove(String[] params){
        if (params.length() != 2){
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
        if (params.length() != 2){
            System.out.prinln("invalid dump params");
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
