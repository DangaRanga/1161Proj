package app;
/**
 * @author Jason Gayle
 * @author Mario Anckle
 * @version 1.0
 */
import data.*;
import snid.*;
import java.io.*;
import java.util.*;

public class SNIDApp {
    private SNIDDb database;
    private ArrayList<Citizen> records;

    public SNIDApp(String fileName,char delimiter){
        database = new SNIDDb(fileName,delimiter);
        records = new ArrayList<Citizen>();
    }

    public void registerBirth(char gender, int yob, String fName, String mName, String lName){
        Citizen newCitizen = new Citizen(gender,yob,fName,mName,lName);
        newCitizen.setLifeStatus(0);
        records.add(newCitizen);

    }
    /*     
   /* private int idSearch(String id){
        int index=-1;
        Collections.sort(records);
        for(int position=0;position<records.size();position++){
            if(records.get(position).getId().equals(id)){
                index = position;
            }
        }
        return index;
    
        
    } */

    /**
     * @author Mario Anckle
     * Private method to search for the Id of a citizen using a binary search
     *  @param id
     * @return An integer representing the index in the arraylist with the user
     */
    private int idSearch(String id){
        Collections.sort(records);
        int firstIndex = 0;
        int lastIndex = records.size() -1;

        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // parseInt to convert the id to an integer
            // It might be best to put this in a try-catch block
            if (Integer.parseInt(records.get(middleIndex).getId()) == Integer.parseInt(id)){
                return middleIndex;
            }
           
            else if (Integer.parseInt(records.get(middleIndex).getId()) < Integer.parseInt(id)){
                firstIndex = middleIndex + 1;
            }
            else if (Integer.parseInt(records.get(middleIndex).getId()) > Integer.parseInt(id)){
                lastIndex = middleIndex - 1;
            }
        }
        return -1;

        // You can just replace what I have for id search

    }
    public void addParentData(String id,String father, String mother){
        int citizenPosition = idSearch(id);
        // Insert if statment to check if mother and father position aren't negative
        int fatherPosition = idSearch(father);
        int motherPosition = idSearch(mother);
        records.get(citizenPosition).setParent('F', records.get(fatherPosition));
        records.get(citizenPosition).setParent('M', records.get(motherPosition));
        
        

    }
   
    /**
     * 
     * @param id
     * @param street
     * @param town
     * @param parish
     * @param country
     */
    public void updateAddress(String id,String street, String town, String parish, String country){
        Collections.sort(records); // This might throw a null pointer exception
        int index = idSearch(id);
        try{
            Citizen person = records.get(index);
            person.setAddress(new Address(street+"|"+town+"|"+parish+"|"+country));
        }catch(IndexOutOfBoundsException e){
            System.out.println("There is no user at that position" + e.getMessage());
        }
    }

    public static void main(String[]args){
        SNIDApp test = new SNIDApp("test.txt",',');
        test.registerBirth('M',1000,"yes", "no", "why");
        System.out.println(test.idSearch("1"));
    }


}
