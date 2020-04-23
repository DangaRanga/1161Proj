package data;

/**
 * testing
 * @author Jason Gayle
 * okay it works
 * the first class is basically this week's lab
 * @version 1.0
 */

import java.io.*;

public class SNIDDb {
    // FileReader fReader;
    private char delimiter;
    private String fileName;
    BufferedReader bReader;

    /**
     * Constructor for the SNIDDb class
     * @param fileName
     * @param delimiter
     */

    public SNIDDb(String fileName, char delimiter){
        this.delimiter=delimiter;
        try{
            bReader = new BufferedReader(new FileReader(fileName));
        }catch(FileNotFoundException e){
        }
    }

    /**
     * Method to check if the next line in the file can be read
     * This works by using the readLine method in the BufferedReader Class
     * to check if there are any more lines in the file to read
     * @return A boolean value indicating if the next line in the file
     * can be read
     */

    public boolean hasNext() throws IOException{
        return bReader.readLine() != null;
    }

    public String[] getNext() throws IOException{
        String line = bReader.readLine();
        return line.split(Character.toString(delimiter));
    }
   
}