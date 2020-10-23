import student.TestCase;

/**
 * Test class for project 1. 
 * 
 * @author Tengis Gantulga and Nikolai Long

 * 
 * @version 2020.09.29
 */
public class TestData extends TestCase {
	String[] existingData;
	String[] newData;
	String[] sdat;
	Data dat;
	Flyweight fly;
	
	public void setUp() {
		fly = new Flyweight();
		sdat = new String[] {"", "", "", "", "", "", "", "", "", ""};
		dat = new Data(fly, sdat);
	}
	
	public void testEgNp() {
		existingData = new String[] {"", "", "", "", "", "", "", "", "B+", ""};
		newData = new String[] {"", "", "", "", "", "", "", "", "B", ""};
		
		assertFalse(dat.existingDataGradeIsLessThan(existingData, newData));
	}
	
	public void testEgN() {
		existingData = new String[] {"", "", "", "", "", "", "", "", "B", ""};
		newData = new String[] {"", "", "", "", "", "", "", "", "B-", ""};
		
		assertFalse(dat.existingDataGradeIsLessThan(existingData, newData));
	}

	public void testEgNm() {
		existingData = new String[] {"", "", "", "", "", "", "", "", "B-", ""};
		newData = new String[] {"", "", "", "", "", "", "", "", "C+", ""};
		
		assertFalse(dat.existingDataGradeIsLessThan(existingData, newData));
	}
	
	public void testElNp() {
		existingData = new String[] {"", "", "", "", "", "", "", "", "B", ""};
		newData = new String[] {"", "", "", "", "", "", "", "", "B+", ""};
		
		assertTrue(dat.existingDataGradeIsLessThan(existingData, newData));
	}
	
	public void testElN() {
		existingData = new String[] {"", "", "", "", "", "", "", "", "B-", ""};
		newData = new String[] {"", "", "", "", "", "", "", "", "B", ""};
		
		assertTrue(dat.existingDataGradeIsLessThan(existingData, newData));
	}

	public void testElNm() {
		existingData = new String[] {"", "", "", "", "", "", "", "", "C+", ""};
		newData = new String[] {"", "", "", "", "", "", "", "", "B-", ""};
		
		assertTrue(dat.existingDataGradeIsLessThan(existingData, newData));
	}
	
	public void testEeN() {
		existingData = new String[] {"", "", "", "", "", "", "", "", "A", ""};
		newData = new String[] {"", "", "", "", "", "", "", "", "A", ""};
		
		assertFalse(dat.existingDataGradeIsLessThan(existingData, newData));
	}
}
