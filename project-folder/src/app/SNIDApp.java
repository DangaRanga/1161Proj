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
     * constructor for SNIDApp class
     * <br>
     * @param fileName
     * @param delimiter
     */
    public SNIDApp(String fileName,char delimiter){
        database = new SNIDDb(fileName,delimiter);
        records = new ArrayList<Citizen>();
        names = new HashMap<String,Name>();
    }


    private boolean isValidId(String id){  
        try{
            Integer.parseInt(id);
            return true;
        }catch(NumberFormatException e){
            return false;
        } 
    }
    public void addExisting(SNIDDb database){
        while(database.hasNext()){
            String[] currentLine = database.getNext();
            String id;
            if(isValidId(currentLine[0])){
                id = currentLine[0];
            }else{
                break;
            }
            String firstName = currentLine[2];
            String middleName = currentLine[3];
            String lastName = currentLine[4];
            char gender = currentLine[5].charAt(0);
            String lifeStatus = currentLine[6];
            
            String addressOne = currentLine[7];
            String addressTwo = currentLine[8];
            String addressThree = currentLine[9];
            String addressFive = currentLine[10];
            String motherId = currentLine[11];
            String fatherId = currentLine[12];

        }
    }
    /**
     * Method to register the birth of a Citizen
     * <br>
     * creates a new citizen obj for when they are born
     * sets thier life status to '0' (alive)
     * adds the new citizen to the records arrayList
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
    /**
     * method to register death of a person
     * <br>
     * a CivicDoc is created theat contains the information about the 
     * persons death
     * <br>
     * retreives the Citizen information form records by the use of 
     * thier id
     * <br>
     * then adds the deathDeatails to persons Civicdoc
     * @param id citizen id
     * @param causeOfDeath how they died
     * @param dateOfDeath the date in which they died
     * @param placeOfDeath the place they were pronouced dead
     */
    public void registerDeath(String id,String causeOfDeath,String dateOfDeath,String placeOfDeath){
        CivicDoc deathDetails = new DeathCertificate(id, causeOfDeath, dateOfDeath, placeOfDeath);
        Citizen person = records.get(idSearch(id));
        person.setLifeStatus(1);
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
        Name brideName = names.get(brideId);
        String groomLastName = names.get(groomId).getLastName();
        System.out.println(groomLastName);
        groom.addCivicPaper(marriageDocument);
        bride.addCivicPaper(marriageDocument);
        bride.changeLastName(groomLastName);
        brideName.setLastName(groomLastName); // Setting the name in the hashMap
    }

    /**
     * Private method to search for the Id of a citizen using a binary search
     * <br>
     * the list is sorted then the id is compared againsit others in the list
     * this works by dividing the list into equal halfs and compares the
     * wanted id with the other in the middle of list each iteration
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

    private boolean recordExists(int index){
        return index >= 0;
    }
    /**
     * Method to add parentData to a citizen
     * <br>
     * finds the index of the citizen and their parent(s) id and
     * stores them as obj
     * <br>
     * set the parent of the citizen if the id is found and updates
     * the citezen records, else thorws an IndexOutOfBoundsException
     * @param id The citizen's id
     * @param fatherId The father's id
     * @param motherId The mother's id
     */
    public void addParentData(String id,String fatherId, String motherId){
        int citizenPosition = idSearch(id);
        int fatherPosition = idSearch(fatherId);
        if (recordExists(fatherPosition)){
            records.get(citizenPosition).setParent('F', records.get(fatherPosition));
        }else{
            System.out.println(String.format("There are no existing records for the id %s",(fatherId)));
        }
        
        int motherPosition = idSearch(motherId);
        if(recordExists(motherPosition)){
            records.get(citizenPosition).setParent('M', records.get(motherPosition));
        }else{
            System.out.println(String.format("There are no existing records for the id %s",(motherId)));
        }
    }

    /**
     * Method to get the Citizen's father.
     * <br>
     * get father's position using the idSearch method 
     * and returns  the father's details if his ID exsits
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
     * get mother's position using the idSearch method 
     * and returns  the mother's details if his ID exsits
     * 
     * @param id The id of the Citizen
     * @return A formatted String representing the Citizen's mother's details
     */

    public String getMother(String id){
        int citizenPosition = idSearch(id);
        //if (recordExists(citizenPosition){
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
     * search method 
     * <br>
     * checks to see if the id exist if not the returns and empty string
     * if id is found, it retives the persons info from records arrayList
     * @param id
     * @return A formatted String containing individuals ID, gender and
     * full name
     */
    public String search(String id){
        int index = idSearch(id);
        if (index < 0 ){
            return "";
        }else{
            if (records.get(index).equals(null)){
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
    }


    private int nameSearch(String firstName,String lastName){
        Collections.sort(records);
        Citizen searchCiti = new Citizen('0', 0, firstName,null, lastName);
        int index = Collections.binarySearch(records, searchCiti,new Comparator<Citizen>(){
            public int compare(Citizen citi1, Citizen citi2){
                int firstNameComp = citi1.getNameObj().getFirstName().compareTo(citi2.getNameObj().getFirstName());
                int lastNameComp = citi1.getNameObj().getLastName().compareTo(citi2.getNameObj().getLastName());
                return Integer.toString(lastNameComp).compareTo(Integer.toString(firstNameComp));
            }
        });
    
        System.out.println(Integer.toString(index));
        return index;
    }
    /**
     * search by name method
     * <br>
     * Binary search from nameSearch() is used to find citezen 
     * from list with the name entered  
     * @param firstName
     * @param lastName
     * {@link #nameSearch(String, String) nameSearch}
     * @return A a string list that contains the persons ID, gender
     * and full name
     */
    public String[] search(String firstName,String lastName){
        int index = nameSearch(firstName, lastName);
        if (index < 0){
            return new String[0];
        }else{
            Citizen person = records.get(index);
            Name personName = names.get(person.getId());
            String[] citiArr = {person.getId(), 
                            Character.toString(person.getGender()), 
                            personName.getFirstName(),
                            personName.getMiddleName(),
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
     * @deprecated
     * @since v1.1
     */
    @Deprecated
    public String getBiometric(String id,String tag){
        Citizen person = records.get(idSearch(id));
        return person.getBiometric(tag).toString();

    }

    /**
     * @deprecated
     * @param tag
     * @param value
     * @return
     */
    @Deprecated
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
    /**
     * private method used to split address
     * <br>
     * takes in the address param of and splits it using 
     * the split method and returns it in the toString method form the
     * Address class
     * <br>
     * loops through the address if the index is not equal to the length of the
     * array-1 it creates the address string and add ',' in place of the missing 
     * info else just adds everything to the address string
     * @param address
     * @return An address string containg all parts of the address splited into parts
     */
    private String splitAddress(Address address){
        String[] addressArr;
        try{
            addressArr = address.toString().split("\n");
        }catch(NullPointerException e){
            System.out.println("This person does not have an address");
            return "";
        }
        String addressString = "";
        for(int index=0;index<addressArr.length;index++){
            if(index != (addressArr.length-1) ){
                addressString += addressArr[index] + ",";
            }else{
                addressString += addressArr[index];
            }     
        }
        return addressString;
    }

    /**
     * Private helper method to retrieve the parent ids of an individual for writing
     * if the parent does not exist, an empty string will be set in place
     * @param writeList The arrayList where the other objects to be written to the file
     * are being set
     * @param citizen The citizen who's parents are being retrieved
     */

    private void putParentIds(ArrayList<String> writeList, Citizen citizen){
        Citizen father = (Citizen)citizen.getParent('F');
        Citizen mother = (Citizen)citizen.getParent('M');
        if(father == null){
            writeList.add("");
        }else{
            writeList.add(father.getId());
        }
        if(mother == null){
            writeList.add("");
        }else{
            writeList.add(mother.getId());
        }
    }
    /**
     * private method to wirte citizen information to a list
     * The primary method for building the list to be written to the file
     * @param citizen
     * @return An array representing one line to be written to the file
     */
    private String[] buildList(Citizen citizen){
        ArrayList<String> writeList = new ArrayList<String>();
        String id = citizen.getId();
        Name citizenName = names.get(id);
        writeList.add(id);
        writeList.add(citizenName.getFirstName());
        writeList.add(citizenName.getMiddleName());
        writeList.add(citizenName.getLastName());
        writeList.add(Character.toString(citizen.getGender()));
        writeList.add(Integer.toString(citizen.getYOB()));
        if(citizen.getLifeStatus()=='A'){
            writeList.add("Alive");
        }else{
            writeList.add("Dead");
        }
        writeList.add(splitAddress(citizen.getAddress()));
        putParentIds(writeList,citizen);
        String[] writeArr = new String[writeList.size()];
        return writeList.toArray(writeArr);
    }
    
    public void shutdown(){
        for(Citizen citizen:records){
            database.putNext(buildList(citizen));
        }


    }



    public static void main(String[]args){
        SNIDApp test = new SNIDApp("Citizens.txt",',');
        test.registerBirth('M',1000,"Jason", "Andre", "Gayle"); // id = 1
        test.registerBirth('M',999,"Steve","Something","Jobs");
        test.registerDeath("2", "Cancer", "I forgot", "A hospital");
        test.registerBirth('F',420,"Martha","Something","Gates");
        test.registerBirth('M',2000,"Mario","Alucard","Anckle"); // id = 4
        System.out.println(Arrays.toString(test.search("Mario","Anckle")));
        /*
        test.registerBirth('F',2000,"Jahnika","Something","Blair"); // id = 5
        test.registerMarriage("4","5","5/7/2020");
        test.updateAddress("1", "somestreet", "sometown", "someparish", "somecountry");
        test.updateAddress("2", "somestreet", "sometown", "someparish", "somecountry");
        test.updateAddress("3", "somestreet", "sometown", "someparish", "somecountry");
        test.addParentData("1", "4", "5");
        test.shutdown(); */
        /* System.out.println(test.search("2"));

        Citizen person2 = test.records.get(test.idSearch("2"));
        System.out.println(person2.getBiometric("F"));
        System.out.println(Arrays.toString(test.search('F',"520")));
        System.out.println(Arrays.toString(test.search('D',"420")));
        System.out.println(test.mailingLabel("1")); */



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
