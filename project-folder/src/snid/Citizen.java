package snid;
/**
 * @author Jason Gayle
 * @version 1.0
 */
public class Citizen extends Person implements Comparable<Citizen>{
    private Name name;
    private Address address;
    private CivicDoc papers;

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
}