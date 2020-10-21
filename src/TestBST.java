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
    private String[] data1;
    private String[] data2;
    
    public void setUp() {
        bst = new BST();
        data1 = new String[] {"1", "VA"};
        data2 = new String[] {"2", "VA"};
    }
    
    public void testInsert() {
        
        bst.root = bst.insert(bst.root, data1);
        
        System.out.println(bst.root);
        
        bst.insert(bst.root, data2);
        System.out.println(bst.root.right.getData().getState());
        System.out.println(bst.root.right.getData().getDate());
        
        bst.insert(bst.root, new String[] {"0", "VA"});
        
        System.out.println(bst.root.left.getData().getDate());
        
        
        
//       bst.insert(dataNode);
//       assertFalse(bst.isEmpty());
//       assertEquals(bst.root.getData().getDate(), "1");
//       
//       assertEquals(bst.numNodes, 1);
//       bst.insert(dataNode2);
//       assertEquals(bst.numNodes, 2);
    }
    
    public void testFind() {
//        bst.insert(dataNode);
//        TreeNode foundNode = bst.find("VA", bst.root);
//        assertEquals(foundNode, bst.root);
    }
    
    
}