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

    
    public void setUp() {
        bst = new BST();
    }
    
    public void testInsert() {
        String[] firstData = {"20200724", "OK",  "442", "40",  "335", "195", "175", "377", "D+",  "343"};
        String[] secondData = {"20200706",  "VT",  "772", "876", "579", "836", "987", "744", "F",   "0"};
        
        bst.insert(firstData);
        assertEquals(bst.getSize(), 1);
        bst.insert(secondData);
        assertEquals(bst.getSize(), 2);
        
    }
    
    public void testFind() {
        String[] firstData = {"20200724", "OK",  "442", "40",  "335", "195", "175", "377", "D+",  "343"};
        String[] secondData = {"20200706",  "VT",  "772", "876", "579", "836", "987", "744", "F",   "0"};
        bst.insert(firstData);
        assertNull(bst.find(secondData));
    }
    
}