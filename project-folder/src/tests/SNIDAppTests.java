/*package tests;
import data.*;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.*;

import app.*;

public class SNIDAppTests {
    SNIDApp test;

    @Before
    public void init(){
        test = new SNIDApp("test.txt",',');
        test.registerBirth('M',1000,"Bob", "Andre", "Stevens");
        test.registerBirth('M',999,"Steve","Something","Stevens");
        test.registerBirth('F',420,"Martha","Something","Stevens");
        
    }
    @After 
    public void clear(){
        test = null;
    }


    @Test
    public void test_search(){
        String[] result = {"2","M","Steve","Something","Stevens"};
        String[] result2 = {"1","M","Bob","Andre","Stevens"};
        assertEquals("1,M,Bob,Andre,Stevens",test.search("1"));
        assertArrayEquals(result,test.search("Steve","Stevens"));
        assertEquals("3,F,Martha,Something,Stevens",test.search("3"));
        assertArrayEquals(result2,test.search("Bob","Stevens"));
    }

    @Test
    public void test_getParents(){
        test.addParentData("1", "2", "3");
        assertEquals("3,Martha,Something,Stevens",test.getMother("1"));
        assertEquals("2,Steve,Something,Stevens", test.getFather("1"));
    }
    
    @Test
    public void test_mailingLabel(){
        assertEquals(2,2);
    }

    public static void main(String[]args){
        SNIDApp test = new SNIDApp("test.txt",',');
        test.registerBirth('M',1000,"Bob", "Andre", "Stevens");
        test.registerBirth('M',999,"Steve","Something","Stevens");
        test.registerBirth('F',420,"Martha","Something","Stevens");
        test.addParentData("1", "2", "3");
        System.out.println(test.getFather("1"));
        System.out.println(test.getMother("1"));
    }
}
*/