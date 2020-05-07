package app;
/**
 * @author Jason Gayle
 * @author Mario Anckle
 * @version 1.1
 */
import data.*;
import snid.*;
import java.util.*;

public class SNIDApp {
    private SNIDDb database;
    private HashMap<String,Name> names;
    private ArrayList<Citizen> records;

    /**
     * @param fileName
     * @param delimiter
     */
    public SNIDApp(String fileName,char delimiter){
        database = new SNIDDb(fileName,delimiter);
        records = new ArrayList<Citizen>();
        names = new HashMap<String,Name>(); // Temporary solution for the names problem
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
        // Temporary Solution to get the names, by storing the name with an id
        names.put(newCitizen.getId(),new Name(fName,mName,lName));
    }

    public void registerDeath(String id,String causeOfDeath,String dateOfDeath,String placeOfDeath){
        CivicDoc deathDetails = new DeathCertificate(id, causeOfDeath, dateOfDeath, placeOfDeath);
        Citizen person = records.get(idSearch(id));
        person.addCivicPaper(deathDetails);
    }
    /**
     * Method to register two Citizens' marriage.
     * <br>
     * The marriage between two individuals is recorded in the papers arraylist
     * in the citizen class
     * <br>
     * Additionally, the bride's last name is set to the groom's last name
     * @param groomId The id of the groom
     * @param brideId The id of the bride
     * @param marriageDate The date of marriage
     */
    public void registerMarriage(String groomId, String brideId, String marriageDate){
        CivicDoc marriageDocument = new MarriageCertificate(groomId,brideId,marriageDate);
        Citizen groom = records.get(idSearch(groomId));
        Citizen bride = records.get(idSearch(brideId));
        String groomLastName = names.get(groomId).getLastName();
        bride.changeLastName(groomLastName);
        groom.addCivicPaper(marriageDocument);
        bride.addCivicPaper(marriageDocument);
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
     * Method to add parentData to a citizen
     * @param id The citizen's id
     * @param fatherId The father's id
     * @param motherId The mother's id
     */
    public void addParentData(String id,String fatherId, String motherId){
        int citizenPosition = idSearch(id);
        int fatherPosition = idSearch(fatherId);
        int motherPosition = idSearch(motherId);
        try{
        records.get(citizenPosition).setParent('F', records.get(fatherPosition));
        records.get(citizenPosition).setParent('M', records.get(motherPosition));
        }catch(IndexOutOfBoundsException e){
            System.out.println("Error, the person you are searching for does not exist");
        }
    }

    /**
     * Method to get the Citizen's father.
     * @param id The id of the Citizen
     * @return A formatted String representing the father's details
     */

    public String getFather(String id){
        int citizenPosition = idSearch(id);
        // Casting father as a Citizen, since a Person doesn't have a name
        Citizen father =  (Citizen)records.get(citizenPosition).getParent('F');
        if(father!=null){
           String fatherId = father.getId();
           Name fatherName = names.get(fatherId); // Since the id is the key
           return fatherId + "," + fatherName.getFirstName() + "," +
                    fatherName.getMiddleName() + "," + fatherName.getLastName();
        }
        return "";
    }

    /**
     * Method to get the Citizen's mother
     * @param id The id of the Citizen
     * @return A formatted String representing the Citizen's mother's details
     */

    public String getMother(String id){
        int citizenPosition = idSearch(id);
        // Casting mother as a Citizen, since a Person doesn't have a name
        Citizen mother =  (Citizen)records.get(citizenPosition).getParent('M');
        if(mother!=null){
           String motherId = mother.getId();
           Name motherName = names.get(motherId); // Since the id is the key
           return  motherId + "," + motherName.getFirstName() + "," +
                    motherName.getMiddleName() + "," + motherName.getLastName();
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
        Name personName = names.get(id);
        String address;
        try{
            address = person.getAddress().toString();
        }catch(NullPointerException e){
            System.out.println("An address had not been set");
            address = "";
        }
        return personName.getLastName().toUpperCase() + "," +
                personName.getFirstName() + " " + personName.getLastName() +
                "\n" + address;
    }
    
    /**
     * 
     */
    public String search(String id){
        if (records.get(idSearch(id)).equals(null)){
            return "";
        }else{
            Citizen person = records.get(idSearch(id));
            Name personName = names.get(id);
            return id + "," + person.getGender() + "," +
                personName.getFirstName() + "," + 
                personName.getMiddleName() + "," +
                personName.getLastName();
        }
    }

    /**
     * 
     */

    public String[] search(String firstName,String lastName){
        Collections.sort(records);
        Citizen searchCiti = new Citizen('0', 0, firstName,null, lastName);
        int index = Collections.binarySearch(records, searchCiti);
        if (index < 0){
            return new String[0];
        }else{
            Citizen person = records.get(index);
            Name personName = names.get(person.getId());
            String[] citiArr = {person.getId() + ",",person.getGender() + ",", 
                            personName.getFirstName() + ",",
                            personName.getMiddleName() + ",",
                            personName.getLastName()
                            };
            return citiArr;
        }
    }

    /**
     * 
     * @param id
     * @param data
     */

    public void addBiometric(String id,String data){
        Citizen person = records.get(idSearch(id));
        // To get the tag
        if(data.charAt(0)=='D'){
            Biometric DNA = new DNA(data.substring(1));
            person.addBiometric(DNA);
        }else{
            Biometric fingerPrint = new FingerPrint(data.substring(1));
            person.addBiometric(fingerPrint);
        }

    }

    /**
     * 
     */

    public String getBiometric(String id,String tag){
        Citizen person = records.get(idSearch(id));
        return person.getBiometric(tag).toString();

    }

    /**
     * 
     * @param tag
     * @param value
     * @return
     */

    public String[] search(char tag,String value){
        for(int index =0;index<records.size();index++){
            Citizen person = records.get(index);
            Biometric biodata = person.getBiometric(Character.toString(tag));

            try{
                if(biodata.toString().equals(tag+value)){
                    Name personName = names.get(person.getId());
                    String[] citiArr = {person.getId(),
                                Character.toString(person.getGender()), 
                                personName.getFirstName(),
                                personName.getMiddleName(),
                                personName.getLastName()
                                };
                    return citiArr; 
                }
            }catch(NullPointerException e){
                // If some records aren't set a null pointer exception will be thrown
            }
        }
        return new String[0]; // Returns empty array if the person isn't found

    }


    public void shutdown(){
        for(Citizen citizen:records){

        }
    }



    // TODO Implement Unittesting with JUnit
    public static void main(String[]args){
        SNIDApp test = new SNIDApp("test.txt",',');
        test.registerBirth('M',1000,"Jason", "Andre", "Gayle"); // id = 1
        test.registerBirth('M',999,"Steve","Something","Jobs");
        test.registerBirth('F',420,"Martha","Something","Gates");
        test.addParentData("1", "2", "3");
        test.addBiometric("1", "D420");
        test.addBiometric("2","F420");
        test.addBiometric("3","F320");
        System.out.println(test.search("2"));

        Citizen person2 = test.records.get(test.idSearch("2"));
        System.out.println(person2.getBiometric("F"));
        System.out.println(Arrays.toString(test.search('F',"520")));
        System.out.println(Arrays.toString(test.search('D',"420")));
        System.out.println(test.mailingLabel("1"));
      //  System.out.println("Biometric: " + test.getBiometric("1","D"));
    
        System.out.println("Father: " + test.getFather("1"));
        System.out.println("Mother: " + test.getMother("1"));



        //--------------------------------------------------------------------//
        // Time for id search
        //--------------------------------------------------------------------//
       /* long startTime1 = System.nanoTime();
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
        */
    }


}
