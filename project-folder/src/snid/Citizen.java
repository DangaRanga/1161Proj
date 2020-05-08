package snid;

import java.util.ArrayList;

/**
 * @author Jason Gayle
 * @version 1.0
 */
public class Citizen extends Person implements Comparable<Citizen>{
    private Name name;
    private Address address;
    private ArrayList<CivicDoc> papers;

    /**
     * Constructor to initialise the attributes of a citizen
     * @param gender a character representing a citizen's gender
     * @param yearOfBirth an integer representing an citizen's year of birth
     * @param firstName a String representing a citizen's first name
     * @param middleName a String representing a citizen's middle name
     * @param lastName a String representing a citizen's last name
     */
    public Citizen(char gender, int yearOfBirth, String firstName,
                    String middleName, String lastName){
    
        super(gender, yearOfBirth);
        name = new Name(firstName,middleName,lastName);
        papers = new ArrayList<CivicDoc>();
    }

    public Citizen(String id,String firstName, String middleName,String lastName,
                    char gender,int yearOfBirth,String lifeStatus,
                    String addressOne,String addressTwo,String addressThree,
                    String addressFour,String addressFive,String motherId,String fatherId){
        super(gender,yearOfBirth);
        if(lifeStatus.equals("Alive")){
            super.setLifeStatus(0);
        }else if(lifeStatus.equals("Deceased")){
            super.setLifeStatus(1);
        }
        setAddress(new Address(addressOne + "|" + addressTwo + "|" + 
                                addressThree + "|" + addressFour+"|"+
                                addressFive));
        name = new Name(firstName,middleName,lastName);
        super.setId(id);
    }

    /**
     * Accessor method to get the ID of a Citizen
     * @return A String representing the Citizen's ID number
     */
    public String getId(){
        return super.getId();
    }

    /**
     * Mutator method to change a Citizen's last name
     * @param newLastName A string representing new last name to be set
     */
    public void changeLastName(String newLastName){
        name.setLastName(newLastName);
    }

    /**
     * Setter method to set the address of a Citizen
     * @param citizenAddress The Address of the Citizen to be set
     */
    public void setAddress(Address citizenAddress){
        address = citizenAddress;
    }
    /**
     * Accessor method to retrieve a Citizen's address
     * @return The Address of a Citizen
     */
    public Address getAddress(){
        return address;
    }

    /**
     * Comparable method
     * @return An integer representing how one citizen's ID comapres to another's
     */
    public int compareTo(Citizen other){
        return this.getId().compareTo(other.getId());
    }
    /**
     * Accessor method to get the name of a Citizen
     * @return A string representing a Citizen's name
     */
    public String getName(){
        return name.getLastName().toUpperCase() + ", " + name.getFirstName() + " "
                + name.getMiddleName().charAt(0)+ ".";
    }
    /**
     * Method to add a civic paper to a citizen
     * @param paper The civic doc to be added to a citizen's list of papers
     */
    public void addCivicPaper(CivicDoc paper){
        papers.add(paper);
    }

    /**
     * Method to get a civic doc from a Citizen
     * @param refNo The reference number for the desired paper
     * @return The desired civic document if its found, and null if the civic doc does not exist
     */
    public CivicDoc getCivicDoc(String refNo){
        for(CivicDoc doc:papers){
            if (doc.getRefNo().equals(refNo)){
                return doc;
            }
        }
        return null;
    }
}