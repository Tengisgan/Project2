import java.io.FileNotFoundException;
import student.TestCase;

/**
 * Test class for project 1. 
 * 
 * @author Tengis Gantulga and Nikolai Long

 * 
 * @version 2020.09.29
 */
public class TestBST extends TestCase {
    
    private BST bst; 
    private Data dataNode;
    private Data dataNode2;
    
    public void setUp() {
        bst = new BST();
        String[] data1 = {"1", "VA"};
        String[] data2 = {"2", "AZ"};
        dataNode = new Data(data1);
        dataNode2 = new Data(data2);
    }
    
    public void testInsert() {
       bst.insert(dataNode);
       assertFalse(bst.isEmpty());
       assertEquals(bst.root.getData().getDate(), "1");
       
       assertEquals(bst.numNodes, 1);
       System.out.println("second insert");
       bst.insert(dataNode2);
       assertEquals(bst.numNodes, 2);
    }
    
    public void testFind() {
        bst.insert(bst.root, dataNode);
        TreeNode foundNode = bst.find("VA", bst.root);
        assertEquals(foundNode, bst.root);
    }
    
    
}