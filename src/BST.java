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


public class BST {
    //Node containing data and ability to go left and right
    class Node {
        String[] data;
        Node left;
        Node right;
        
        public Node(String[] data) {
            this.data = data;
            left = null;
            right = null;
            
        }
        
    }
    
    // Fields
    private Node root;
    private int nodes;
    
    //Constructor for an empty tree
    public BST() {
        this.root = null;
        this.nodes = 0;
    }
    
    //make empty
    public void makeEmpty() {
        root = null;
    }
    
    //checking for empty
    public boolean isEmpty() {
       this.nodes = 0;
       return (root == null);
    }
    
    //insertHelper is a helper method to insert
    public Node insertHelper(String[] data, Node root) {
        //if it is empty, simply make the root the new data and return it
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data[1].compareTo(root.data[1]) == 0) {
            if ((data[0]).compareTo(root.data[0]) < 0) {
                root.left = insertHelper(data, root.left);
            }
            //or to the right
            else if ((data[0]).compareTo(root.data[0]) > 0){
                root.right = insertHelper(data, root.right);
            }
        }
        //checking if it is to the left
        else if ((data[1]).compareTo(root.data[1]) < 0) {
            root.left = insertHelper(data, root.left);
        }
        //or to the right
        else if ((data[1]).compareTo(root.data[1]) > 0){
            root.right = insertHelper(data, root.right);
        }
        return root;
    }
    
    //Insert to a tree using the helper insertRecursion method
    public void insert(String[] data) {
        root = insertHelper(data, root);
        this.nodes++;
    }
    
//    //Finding the minimum value
//    public String[] findMinValue(Node root) {
//        Node current = root;
//        
//        while (current.left != null) {
//            current = current.left;
//        }
//        return current.data;
//    }
//    
//    public String[] findMinValue() {
//        return findMinValue(root);
//    }

//    //helper method to delete
//    public Node deleteHelper(String[] data, Node root) {
//        
//        if (root == null) {
//            return root;
//        }
//        
//        else if (data.compareTo(root.data) > 0) {
//            root.right = deleteHelper(data, root.right);
//        }
//        else if (data.compareTo(root.data) < 0) {
//            root.left = deleteHelper(data, root.left);
//        }
//        
//        else {
//            if (root.right == null) {
//                return root.left;
//            }
//            else if (root.left == null) {
//                return root.right;
//            }
//            
//            root.data = findMinValue(root.right);
//            root.right = deleteHelper(root.data, root.right);
//        }
//        
//        return root;
//    }
    
    ////delete method
//    public void delete(String data) {
//        root = deleteHelper(data, root);
//        this.nodes--;
//    }
//    
    //finding method (internal)
    public Node findHelper(String[] data, Node root) {
        
        if (root == null || (root.data[0] == data[0] && root.data[1] == data[1])) {
            return root;
        }
        //checking if it is to the left
        if (data[1].compareTo(root.data[1]) < 0 && data[0].compareTo(root.data[0]) < 0) {
            root.left = findHelper(data, root.left);
        }
        return findHelper(data, root.right);
    }
    
   
//    public boolean find(String[] data) {
//        return findHelper(data, root);
//    }

    //getting the size
    public int getSize() {
        return this.nodes; 
    }
}