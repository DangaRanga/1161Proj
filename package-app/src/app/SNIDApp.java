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

    private int idSearch(String id){
        int index=-1;
        Collections.sort(records);
        for(int position=0;position<records.size();position++){
            if(records.get(position).getId().equals(id)){
                index = position;
            }
        }
        return index;
        
    }
    public void addParentData(String id,String father, String mother){
        //String citizenID = id;
        //start from the lastindex so we don't get out of bounds
        // For now we can use a linear search okay
        // i'm back check 
        int citizenPosition = idSearch(id);
        // Insert if statment to check if mother and father position aren't negative
        int fatherPosition = idSearch(father);
        int motherPosition = idSearch(mother);
        records.get(citizenPosition).setParent('F', records.get(fatherPosition));
        records.get(citizenPosition).setParent('M', records.get(motherPosition));
        
        




        /*
        * I'm assuming this method will search through the list and add the persons with
        * The corresponding ids as parents
        * I believe a Binary serch might be needed for this.
        */
    }
   

    public void updateAddress(String id,String street, String town, String parish, String country){
        Collections.sort(records); // This might throw a null pointer exception
        Citizen stubCitizen = new Citizen('M',0,null,null,null); // Creating a stub citizen for now
        /* 
        If the ids match up then the address for the user is set
        So once the citizen is found in the arrayList, you'll get them from the arrayList
        Then set the address for the citizen found here. 
        */
        stubCitizen.setAddress(new Address(street+"|"+town+"|"+parish+"|"+country));
    }


}
