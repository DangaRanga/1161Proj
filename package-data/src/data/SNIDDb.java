package data;

/**
 * testing
 * @author Jason Gayle
 * okay it works
 * the first class is basically this week's lab
 * @version 1.0
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SNIDDb {
    // I
    private char delimiter;
    private String fileName;
    private BufferedReader bReader;
    private BufferedWriter bWriter;
    private String line;

    /**
     * Constructor for the SNIDDb class
     * @param fileName
     * @param delimiter
     */

    public SNIDDb(String fileName, char delimiter){
        this.delimiter=delimiter;
        this.fileName=fileName;
        try{
            bReader = new BufferedReader(new FileReader(fileName));
            bWriter = new BufferedWriter(new FileWriter(fileName));
        }catch(FileNotFoundException e){
            System.out.println("The file could not be found");
        }catch(IOException e){
            System.out.println("An unexpected IO excepton has occured" + e.getMessage());

        }
    }

    /**
     * Method to check if the next line in the file can be read
     * This works by using the readLine method in the BufferedReader Class
     * to check if there are any more lines in the file to read
     * @return A boolean value indicating if the next line in the file
     * can be read
     * @throws IOException Which is later caught in the main for the
     * SNIDDb at the moment.
     */

    public boolean hasNext() throws IOException{
        line = bReader.readLine();
        return line != null;
    }
    /**
     * Method to get the next line in the file
     * @return A String array containing the data from the file
     * @throws IOException Which is currently handled in the main method
     */
    public String[] getNext() throws IOException{
        if(hasNext()){
           // String line = bReader.readLine();
            return line.split(Character.toString(delimiter));
        }else{
            return new String[0];
        }
    }

   /**
    * Method to reopen close and reopen the BufferedWriter to allow for the file
    * to be written to
    */
   public void reWrite(){
       try{
            bWriter.close();
            bWriter = new BufferedWriter(new FileWriter(fileName,true));
        }catch(IOException e){
            System.out.println("An unexpected IOException has occured: " + e.getMessage());
        }
    
   }
   /**
    * Method to append data to the file
    * This works by iterating through the String array, and uses the
    * BufferedWriter to append data separated by the delimeter to the file
    * This method calls on the reWrite() method to reopen the BufferedWriter 
    */
   public void putNext(String[] data){
        try{
           reWrite();
            for(int index = 0; index < data.length; index++){
                if(index != (data.length - 1)){
                    bWriter.write(data[index] + delimiter);
                }else{
                    bWriter.write(data[index]);
                }
            }
           // bWriter.close();
        }catch(IOException e){
            System.out.println("An unexpected IOException has occured: " + e.getMessage());
        }
    }

    public static void main(String[]args){
           // File file = new File("test.txt");
           // Scanner in = new Scanner(System.in);
            SNIDDb test = new SNIDDb("test.txt",',');
            String[] hi = {"User",Integer.toString(5)};
            //System.out.println(Arrays.toString(hi));
            String[] hi2 = {"User",Integer.toString(3)};
            test.putNext(hi);
            test.putNext(hi2);
            try{
            System.out.println(Arrays.toString(test.getNext()));
            System.out.println(Arrays.toString(test.getNext()));
            }catch(IOException e){

            }
        }
            


        //}catch(Exception e){
            // TODO Implement error handling
       // }
}