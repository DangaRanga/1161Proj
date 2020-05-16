package data;

/**
 * This class represents the SNIDDb
 * @author Jason Gayle
 * @version 1.0
 */

import java.io.*;
import java.util.Arrays;

// TODO Implement boolean to check if its the first time the file is being written to

public class SNIDDb {
    // Attributes for the SNIDDb class
    private char delimiter;
    private String fileName;
    private BufferedReader bReader;
    private BufferedWriter bWriter;
    private String line;
    private File file;

    /**
     * Constructor for the SNIDDb class
     * To set the delimiter and file name, and to instantiate the BufferedReader and BufferedWriter
     * @param fileName A String representing the name of the file to be read and written to
     * @param delimiter A character representing the splits the lines in the file into tokens
     */

    public SNIDDb(String fileName, char delimiter){
        this.delimiter=delimiter;
        this.fileName=fileName;
        try{
            file = new File(fileName);
            bReader = new BufferedReader(new FileReader(file));
            bWriter = new BufferedWriter(new FileWriter(fileName,true));
        }catch(FileNotFoundException e){
            System.out.println("The file could not be found");
            System.out.println("Creating the file...");
            file = new File(fileName);
            try{
                file.createNewFile();
            }catch(IOException f){
                System.out.println("An unexpected IOException has occured while making file");
            }
        }catch(IOException e){
            System.out.println("An unexpected IOException has occured while constructing: "+e.getMessage());
        }
    }


    public boolean isEmptyFile(){
        return file.length() == 0;
    }
    /**
     * Method to check if the next line in the file can be read
     * This works by using the readLine method in the BufferedReader Class
     * to check if there are any more lines in the file to read
     * @return A boolean value indicating if the next line in the file
     * can be read
     */

    public boolean hasNext(){
        try{
            line = bReader.readLine();
        }catch(IOException e){
            System.out.println("Handling unexpected IOException");
        }
        return line != null;
    }

    /**
     * Method to get the next line in the file
     * <br>
     * This works by using the hasNext() method and the readLine() method
     * fromthe buffered reader clas to check if the line can be retrieved.
     * @return A string array representing a line in the file
     */
    public String[] getNext(){
        return line.split(Character.toString(delimiter));
    }

   /**
    * This method is used to close and reopen the file for writing
    * <br>
    * This works by instantiating a new BufferedWriter, with the FileWriter set to append mode
    */

   public void reWrite(){
       try{
           // Closing the buffered writer opened in the constructor
            bWriter.close();
            bWriter = new BufferedWriter(new FileWriter(fileName));
        }catch(FileNotFoundException e){
            System.out.println("The file could not be found");
        }catch(IOException e){
            System.out.println("An inexpected IOException has occured: " + e.getMessage());
        }finally{
            if(bWriter != null){
                try{
                    bWriter.close();
                }catch(IOException e){

                }
            }
        }
   }

   /**
    * Method to add a new line to the file
    * <br>
    * This works by Iterating through a String array, then writing each
    *component of the array, separated by the delimiter set in the constructor 
    * @param data This is a String array representing the data to be written to the file
    * 
    */
    // Do not insert reWrite into putNext()
   public void putNext(String[] data){
        // reWrite();
        try{
            bWriter = new BufferedWriter(new FileWriter(fileName,true));
            for(int index = 0; index < data.length; index++){
                if(index!=(data.length-1)){
                    bWriter.write(data[index]+delimiter);
                }else{
                    bWriter.write(data[index]);
                    bWriter.newLine();
                }
            }
        // Exception handling to handle the IOException thrown by the BufferedWriter
        }catch(IOException e){
            System.out.println("An unexpected IOException has occured: " + e.getMessage());
        }finally{
            if (bWriter != null){
                // Try-catch block to close the BufferedWriter
                try{
                    bWriter.close();
                }catch(IOException e){
                    System.out.println("An unexpected IOException has occured while closing: "+ e.getMessage());
                }
            }
        }
   }
   public static void main(String[]args){
       SNIDDb test = new SNIDDb("Citizens.txt",',');
        System.out.println(Arrays.toString(test.getNext()));
        System.out.println(Arrays.toString(test.getNext()));
        System.out.println(Arrays.toString(test.getNext()));
   }
}