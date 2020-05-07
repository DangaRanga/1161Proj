package tests;
import data.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import app.*;
public class SNIDAppTests {
    
    SNIDApp test = new SNIDApp("test.txt",',');
    @Test
    public void test_getFather(){
        test.registerBirth('M',1000,"Jason", "Andre", "Gayle");
        test.registerBirth('M',999,"Steve","Something","Jobs");
        test.registerBirth('F',420,"Martha","Something","Gates");
        test.addParentData("1", "2", "3");
        String father = test.getFather("1");
        assertEquals("2,Steve,Something,Jobs", father);
    }
}