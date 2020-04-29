package app;
/**
 * @author Jason Gayle
 * @author Mario Anckle
 * @version 1.1
 */
import data.*;
import snid.*;
import java.io.*;
import java.util.*;

public class SNIDApp {
    private SNIDDb database;
    private static int marriageCounter;
    private HashMap<String,String[]> marriages;
    private ArrayList<Citizen> records;

    /**
     * 
     */
    public SNIDApp(String fileName,char delimiter){
        database = new SNIDDb(fileName,delimiter);
        records = new ArrayList<Citizen>();
        marriages = new HashMap<String,String[]>();
    }

    /**
     * Method to register the birth of a Citizen
     * @param gender
     * @param yob
     * @param fName
     * @param mName
     * @param lName
     */
    public void registerBirth(char gender, int yob, String fName, String mName, String lName){
        Citizen newCitizen = new Citizen(gender,yob,fName,mName,lName);
        newCitizen.setLifeStatus(0);
        records.add(newCitizen);
    }

    /**
     * Method to register two Citizens' marriage
     * @param groomId The id of the groom
     * @param brideId The id of the bride
     * @param marriageDate The date of marriage
     */
    public void registerMarriage(String groomId, String brideId, String marriageDate){
        // STUB for register marriage
        String marriageNo = Integer.toString(++marriageCounter);
        String[]marriageArr = {groomId,brideId,marriageDate};
        marriages.put(marriageNo, marriageArr);
    }

    /**
     * Private method to search for the Id of a citizen using a binary search
     * @author Mario Anckle
     * @author Jason Gayle
     * @param id A String representing the id of the Citizen being searched
     * @return An integer representing the index in the arraylist with the desired Citizen
     */
    private int idSearch(String id){
        Collections.sort(records);
        int firstIndex = 0;
        int lastIndex = records.size() -1;
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (records.get(middleIndex).getId().compareTo(id) == 0){
                return middleIndex;
            }
            else if (records.get(middleIndex).getId().compareTo(id) < 0){
                firstIndex = middleIndex + 1;
            }
            else if (records.get(middleIndex).getId().compareTo(id) > 0){
                lastIndex = middleIndex - 1;
            }
        }
        return -1;
    }
    /**
     * Old method for searching an ID
     * @author Mario Anckle
     * @deprecated Replaced by idSearch
     * @param id The id of the Citizen
     * @return An integer representing the index the Citizen is at
     */
    @Deprecated
    private int idSearchOld(String id){
        Collections.sort(records);
        int firstIndex = 0;
        int lastIndex = records.size() -1;

        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
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
    }

    /**
     * Method to add parentData to a citizen
     * @param id The citizen's id
     * @param father The father's id
     * @param mother The mother's id
     */
    public void addParentData(String id,String father, String mother){
        int citizenPosition = idSearch(id);
        // Insert if statment to check if mother and father position aren't negative
        int fatherPosition = idSearch(father);
        int motherPosition = idSearch(mother);
        records.get(citizenPosition).setParent('F', records.get(fatherPosition));
        records.get(citizenPosition).setParent('M', records.get(motherPosition));
    }

    /**
     * Method to get the Citizen's father
     * @param id The id of the Citizen
     * @return A formatted String representing the father's details
     */

    public String getFather(String id){
        int citizenPosition = idSearch(id);
        Citizen father =  (Citizen)records.get(citizenPosition).getParent('F');
        if(father!=null){
            // Stub for now
            return father.getId() + father.getName();
        }
        return "";
    }

    
   
    /**
     * Method to update the address of the citizen
     * <br>
     * This works alongside the private idSearch method to find the Citizen then
     * update their address based on their id
     * <br>
     * @param id A String representing id of the Citizen
     * @param street A String representing the street the Citizen lives on
     * @param town A String representing the town the Citizen resides in
     * @param parish A String representing the parish the Citizen resides in
     * @param country A String representing the country the Citizen resides in
     */

    public void updateAddress(String id,String street, String town, String parish, String country){
        int index = idSearch(id); 
        try{ // There should be a better way to handle this try catch block
            Citizen person = records.get(index);
            person.setAddress(new Address(street+"|"+town+"|"+parish+"|"+country));
        }catch(IndexOutOfBoundsException e){
            System.out.println("There is no user at that position" + e.getMessage());
        }
    }

    /**
     * Method to return a mailing label of a citizen
     * <br>
     * This works by using the idSearch method to find the Citizen, and then the
     * respective getters for the Citizen are utilized to retrieve the information
     * @param id The id of the citizen
     * @return A formatted String containing the individual's name and adddress
     */
    public String mailingLabel(String id){
        Citizen person = records.get(idSearch(id));
        return person.getName() + "\n" + person.getAddress().toString();
    }

    // TODO Implement Unittesting with JUnit
    public static void main(String[]args){
        SNIDApp test = new SNIDApp("test.txt",',');
        test.registerBirth('M',1000,"yes", "no", "why");


        //--------------------------------------------------------------------//
        // Time for id search
        //--------------------------------------------------------------------//
        long startTime1 = System.nanoTime();
        test.idSearch("1");
        long endTime1 = System.nanoTime();
        System.out.println("Time for idSearch: "+(endTime1-startTime1)+" nanoseconds");
        System.out.println("Time in milliseconds "+((endTime1-startTime1)/1000000));
        //--------------------------------------------------------------------//
        // Time for compare to id search
        //--------------------------------------------------------------------//
        long startTime2 = System.nanoTime();
        test.idSearch("1");
        long endTime2 = System.nanoTime();
        System.out.println("Time for idSearchComp: "+(endTime2-startTime2) + " nanoseconds");
        System.out.println("Time in milliseconds "+((endTime2-startTime2)/1000000));
        //--------------------------------------------------------------------//
    }


}
