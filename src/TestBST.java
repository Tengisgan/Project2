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
    private String[] data3;
    private String[] data4;
    private String[] emptyData;
    
    public void setUp() {
        bst = new BST();
        
        data1 = new String[] {"1", "VA", "", "", "", "", "", "", "C", ""};
        data2 = new String[] {"2", "ZA",  "", "", "", "", "", "", "A", ""};
        data3 = new String[] {"0", "VA",  "", "", "", "", "", "", "D", ""};
        data4 = new String[] {"4", "VA",  "", "", "", "", "", "", "F", ""};
        
        emptyData = new String[] {"", "", "", "", "", "", "", "", "", ""};
    }
    
    public void testInsert() {
        
        bst.root = bst.insert(bst.root, data1);
        
        
        bst.insert(bst.root, data1);
        
        bst.insert(bst.root, new String[] {"0", "VA"});
        

        assertFalse(bst.isEmpty());
        assertEquals(bst.root.getData().getDate(), "1");      
        assertEquals(bst.numNodes, 3);

    }
    
    public void testFind() {
        bst.root = bst.insert(bst.root, data1);
        TreeNode foundNode = bst.find("VA", bst.root);
        assertEquals(foundNode, bst.root);
        assertEquals(foundNode.left, bst.root.left);
        bst.insert(bst.root, data2); 
        assertEquals(foundNode.right, bst.root.right);
    }
    
    public void testFindSD() {
        bst.root = bst.insert(bst.root, data1);
        bst.insert(bst.root, data3);
        
        assertEquals(bst.find("VA", bst.root).getData().data[0], data1[0]);
        assertEquals(bst.findSD("VA", "1", bst.root).getData().data[0], data1[0]);
        assertEquals(bst.findSD("VA", "0", bst.root).getData().data[0], data3[0]);
    }
    public void testReturnMinValue() {
        bst.root = bst.insert(bst.root, data1);
        bst.insert(bst.root, data2);
        bst.insert(bst.root, data3);
        assertEquals(bst.minValue(bst.root), data3);
    }
    
    public void testRemove() {
        bst.root = bst.insert(bst.root, data1);
        bst.remove(data1);
        assertEquals(bst.find("Something else", bst.root), bst.root);

    }
    
    public void testReplace() {
        bst.root = bst.insert(bst.root, data1);
        bst.replace(data1, data2);
        TreeNode foundNode = bst.find("ZA", bst.root);
        assertEquals(foundNode, bst.root);
    }
    
    public void testUpdateData() { 
        bst.updateData(emptyData, data1);
        assertEquals(emptyData[0], "1");
    }
    
    public void testRemoveGrade() {
        bst.root = bst.insert(bst.root, data1);
        bst.insert(bst.root, data2);
        bst.insert(bst.root, data3);
        bst.insert(bst.root, data4);
        bst.removeGrade("C");
        //assertEquals(bst.find("ZA", bst.root).getData().data[0], "2");
        //assertNull(bst.find("VA", bst.root).getData());
    }
}