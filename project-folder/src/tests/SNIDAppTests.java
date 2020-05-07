package tests;
import data.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;

import app.*;

public class SNIDAppTests {
    SNIDApp test;
    @Before
    public void init(){
        test = new SNIDApp("test.txt",',');
        test.registerBirth('M',1000,"Bob", "Andre", "Stevens");
        test.registerBirth('M',999,"Steve","Something","Stevens");
        test.registerBirth('F',420,"Martha","Something","Stevens");
        test.addParentData("1", "2", "3");
    }
   /* @Test
    public void test_getMother(){
        assertEquals("3,Martha,Something,Stevens",test.getMother("1"));
    } */
    @Test
    public void test_search(){
        SNIDApp test2 = new SNIDApp("test.txt",',');
        test.registerBirth('M',1000,"Bob", "Andre", "Stevens");
        test.registerBirth('M',999,"Steve","Something","Stevens");
        test.registerBirth('F',420,"Martha","Something","Stevens");
        assertEquals("1,M,Bob,Andre,Stevens",test.search("1"));
        assertEquals("2,M,Steve,Something,Stevens",test.search("2"));
        assertEquals("3,F,Martha,Something,Stevens",test.search("3"));
    }

    @Test
    public void test_getParents(){
        String father = test.getFather("1");
        assertEquals("3,Martha,Something,Stevens",test.getMother("1"));
        assertEquals("2,Steve,Something,Stevens", test.getFather("1"));
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