package snid;
/**
 * This class represents the name for an individual
 * @author Jason Gayle
 * @version 1.0
 */

public class Name {
    // Attributes for the name class
    private String fn;
    private String mn;
    private String ln;

    /**
     * Constructor to initialize the attributes for the Name class
     * @param fn The first name of the individual
     * @param mn The middle name of the individual
     * @param ln The last name of the individual
     */
    public Name(String fn,String mn,String ln){
        this.fn = fn;
        this.mn = mn;
        this.ln = ln;
    }
    /**
     * Accessor method to get the first name
     * @return A String representing an individual's first name
     */
    public String getFirstName(){
        return fn;
    }
    
    /**
     * Accessor method to get the last name
     * @return A string representing an individual's last name
     */
    public String getLastName(){
        return ln;
    }

    /**
     * Accessor method to get the middle name
     * @return A string representing an individual's middle name
     */
    public String getMiddleName(){
        return mn;
    }

    /**
     * Mutator method to change the last name of an individual
     * @param newLName The new last name to be set
     */
    public void setLastName(String newLName){
        this.ln = newLName;
    }

    /**
     * toString method for the name class
     * @return A formatted string representing an individual's full name
     */
    public String toString(){
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

    /**
     * equals method for the Name class to check if the name entered is correct
     * @param name A Name object representing an individual's name
     * @return A boolean value indicating if the name entered is corrct
     */
    public boolean equals(Name name){
        return (name.getFirstName().equals(fn) &&
                name.getMiddleName().equals(mn) &&
                name.getLastName().equals(ln));
    }
}