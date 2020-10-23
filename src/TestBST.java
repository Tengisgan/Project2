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
    private String[] sameData2;
    private String[] emptyData;
    
    public void setUp() {
        bst = new BST();
        
        data1 = new String[] {"1", "VA", "2", "", "", "", "", "", "C", ""};
        data2 = new String[] {"2", "ZA",  "2", "", "", "", "", "", "A", ""};
        data3 = new String[] {"0", "VA",  "3", "", "", "", "", "", "D", ""};
        data4 = new String[] {"4", "VA",  "4", "", "", "", "", "", "F", ""};
        sameData2 = new String[] {"2", "ZA",  "5", "", "", "", "", "", "A", ""};
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
        bst.insert(bst.root, data2);
        
        assertEquals(bst.find("VA", bst.root).getData().data[0], data1[0]);
        assertEquals(bst.findSD("VA", "1", bst.root).getData().data[0], data1[0]);
        assertEquals(bst.findSD("VA", "0", bst.root).getData().data[0], data3[0]);
        assertEquals(bst.findSD("ZA", "2", bst.root).getData().data[0], data2[0]);
    }
    public void testReturnMinValue() {
        bst.root = bst.insert(bst.root, data1);
        bst.insert(bst.root, data2);
        bst.insert(bst.root, data3);
        assertEquals(bst.minValue(bst.root), data3);
    }
    
    public void testRemove() {
        bst.root = bst.insert(bst.root, new String[] {"3", "ZA"});
        bst.insert(bst.root, new String[] {"4", "ZA"});
        bst.insert(bst.root, new String[] {"5", "ZA"});
        bst.insert(bst.root, new String[] {"6", "ZA"});
        bst.remove(new String[] {"4", "ZA"});
        bst.remove(new String[] {"5", "ZA"});
        bst.remove(new String[] {"6", "ZA"});
        bst.remove(new String[] {"3", "ZA"});
        assertEquals(bst.findSD("ZA", "3", bst.root), bst.root);
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
        assertEquals(bst.removeGrade("C"), 3);
//        
//        assertEquals(bst.findSD("ZA", "2", bst.root).getData().getDate(), "2");
//        assertNotNull(bst.findSD("VA", "1", bst.root));
//        assertNull(bst.findSD("VA", "0", bst.root));
//        assertNull(bst.findSD("VA", "4", bst.root));
    }
    
    public void testDumpDataSortedPrint() {
        String[] newData1 = {"20200722","SC","745","100","734","311","342","907","C","335"};
        String[] newData2 = {"20200730","AK","883", "482", "1", "684", "732", "43", "C+", "213"};
        String[] newData3 = {"20200830","AZ","883", "482", "1", "684", "732", "43", "C+", "213"};
        bst.root = bst.insert(bst.root, newData1);
        bst.insert(bst.root, newData2);
        bst.insert(bst.root, newData3);
        bst.sortedPrint(bst.root, 2, 0);
        
    }
    
    public void testLatestDate() {
        bst.root = bst.insert(bst.root, data1);
        bst.insert(bst.root, data3);
        bst.insert(bst.root, data2);
        bst.insert(bst.root, data4);
        //assertEquals(bst.getLatestDate(), "4");
    }
    
}