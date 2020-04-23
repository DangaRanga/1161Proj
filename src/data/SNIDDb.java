//package data;

/**
 * testing
 * @author Jason Gayle
 * okay it works
 * the first class is basically this week's lab
 * @version 1.0
 */

import java.io.*;
import java.util.Scanner;

public class SNIDDb {
    // I
    private char delimiter;
    private String fileName;
    private BufferedReader bReader;

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
     * @throws IOException Which is later caught in the constructor for the
     * SNIDDb
     */

    public boolean hasNext() throws IOException{
        return bReader.readLine() != null;
    }
    /**
     * Method to get the next line in the file
     * 
     */
    public String[] getNext() throws IOException{
        try{
            String line = bReader.readLine();
            return line.split(Character.toString(delimiter));
        }catch(NullPointerException e){
            System.out.println("The file is empty");
            return new String[0];
        }
    }

   
   public void reWrite(){
       try{
            bReader.close();
            bReader = new BufferedReader(new FileReader(fileName));
        }catch(FileNotFoundException e){
            System.out.println("The file could not be found");
        }catch(IOException e){
            System.out.println("An inexpected IOException has occured: " + e.getMessage());
        }
    
   }
   public void putNext(String[] data){
       // Temporary bandaid that works
        try(
            BufferedWriter bWrite = new BufferedWriter(new FileWriter(fileName));
        ){
            for(String entry:data){
                bWrite.write(entry+delimiter);
                bWrite.newLine();
            }
        }catch(IOException e){
           System.out.println("Unexpected IOException has been thrown");
        }
    }
    public static void main(String[]args){
        //try{
            try {
                File myObj = new File("test.txt");
                if (myObj.createNewFile()) {
                  System.out.println("File created: " + myObj.getName());
                } else {
                  System.out.println("File already exists.");
                }
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
           // File file = new File("test.txt");
            Scanner in = new Scanner(System.in);
            SNIDDb test = new SNIDDb("test.txt",',');
            for(int i = 0;i<5;i++){
                String[] hi = {"User",Integer.toString(i)};
                test.putNext(hi);
               // test.reWrite();
            }
            


        //}catch(Exception e){
            // TODO Implement error handling
            System.out.println("Testing");
       // }
    }
}