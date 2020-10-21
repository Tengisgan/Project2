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
        
        
        bst.insert(bst.root, data2);
        
        bst.insert(bst.root, new String[] {"0", "VA"});

        assertFalse(bst.isEmpty());
        assertEquals(bst.root.getData().getDate(), "1");      
        assertEquals(bst.numNodes, 3);

    }
    
    public void testFind() {
        bst.insert(bst.root, data1);
        TreeNode foundNode = bst.find("VA", bst.root);
        assertEquals(foundNode, bst.root);
        assertEquals(foundNode.left, bst.root.left);
        assertEquals(foundNode.right, bst.root.right);
    }
    
    
}