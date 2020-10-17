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

import java.io.FileNotFoundException;
import student.TestCase;

/* Test class for Covid19TrackingManager2
 * 
 * @author Tengis Gantulga and Nikolai Long
 * @version 2020.09.29
 */
public class TestManager extends TestCase {


    /* nohting to set up
     */
    public void setUp() {
        // nothing to set up
    }

    /* the one and only test
     * @throws FileNotFoundException 
     */
    public void test1() throws FileNotFoundException {
        String[] params = {"SampleTest1.txt"};
        Covid19TrackingManager2.main(params);
        assertNotNull(params);
    }

    /* test 2
     * @throws FileNotFoundException 
     */
    public void test2() throws FileNotFoundException {
        String[] params = {"SampleTest2.txt"};
        Covid19TrackingManager2.main(params);
        assertNotNull(params);
    }


}

