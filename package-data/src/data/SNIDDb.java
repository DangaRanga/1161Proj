package data;

/**
 * This class represents the SNIDDb
 * @author Jason Gayle
 * @version 1.0
 */

import java.io.*;
import java.util.Arrays;

public class SNIDDb {
    // I
    private char delimiter;
    private String fileName;
    private BufferedReader bReader;
    private BufferedWriter bWriter;

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
            bWriter = new BufferedWriter(new FileWriter(fileName,true));
        }catch(FileNotFoundException e){
            System.out.println("The file could not be found");
        }catch(IOException e){
            e.printStackTrace();
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

   /**
    * 
    */
   public void reWrite(){
       try{
            bWriter.close();
            bWriter = new BufferedWriter(new FileWriter(fileName,true));
        }catch(FileNotFoundException e){
            System.out.println("The file could not be found");
        }catch(IOException e){
            System.out.println("An inexpected IOException has occured: " + e.getMessage());
        }
    
   }

   /**
    * 
    */
   public void putNext(String[] data) throws IOException{
        reWrite();
        for(int index = 0; index < data.length; index++){
            if(index!=(data.length-1)){
                bWriter.write(data[index]+delimiter);
            }else{
                bWriter.write(data[index]);
            }
        }
        bWriter.newLine();
        bWriter.close();
   }
 
    public static void main(String[]args) throws IOException{
           try{
           SNIDDb test = new SNIDDb("test.txt",',');
            String[] hi = {"User",Integer.toString(5)};
            System.out.println(Arrays.toString(hi));
            String[] hi2 = {"User",Integer.toString(3)};
            test.putNext(hi);
            test.putNext(hi2);
           }catch(IOException e){
               e.printStackTrace();
           }
        }
}