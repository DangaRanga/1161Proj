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
            
        }catch(IOException e){

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
            bWriter.newLine();
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace(); // TODO Insert proper error message
        }finally{
            try{
                bWriter.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[]args){
           // File file = new File("test.txt");
           // Scanner in = new Scanner(System.in);
            SNIDDb test = new SNIDDb("test.txt",',');
            String[] hi = {"User",Integer.toString(5)};
            System.out.println(Arrays.toString(hi));
            String[] hi2 = {"User",Integer.toString(3)};
            test.putNext(hi);
            test.putNext(hi2);
            }
            


        //}catch(Exception e){
            // TODO Implement error handling
       // }
}