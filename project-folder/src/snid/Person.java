package snid;

import java.util.ArrayList;

/**
 * @author Jason Gayle
 * @version 1.0
 */
public class Person{
    private static int idcounter;
    private int id;
    private char gender;
    private int yearOfBirth;
    private char lifeStatus;
    private Person father;
    private Person mother;
    private ArrayList<Biometric> biodata;


    /**
     * Constructor for the person class to initialise the gender and year of
     * birth
     * @param gender the gender of the person
     * @param yearOfBirth the birth year of the person
     */
    public Person(char gender,int yearOfBirth){
        biodata = new ArrayList<Biometric>();
        this.gender= gender;
        this.yearOfBirth = yearOfBirth;
        id = ++idcounter;
        
    }

    /**
     * Accessor method to get the gender of the Person
     * @return A character representing an individual's gender
     */

    public char getGender(){
        return gender;
    }

    /**
     * Accessor method to get the life status of an individual
     * @return A character representing an individual's life status
     */
    
    public char getLifeStatus(){
        return lifeStatus;
    }

    /**
     * @param lifeValue An integer (either 0 or 1) representing a person's
     * life status
     * @throws IllegalArgumentException if the value entered is not 0 or 1
     */

    public void setLifeStatus(int lifeValue){
        if (lifeValue == 0){
            lifeStatus = 'A';

        }else if(lifeValue == 1){
            lifeStatus = 'D';

        }else{
            throw new IllegalArgumentException("Invalid value entered");
        }
    }

    // TODO - Insert error handling for setParent

    /**
     * Setter method to set the parent
     * @param parentChar A character representing a parent (M for mother,
     * F for father)
     * @param parent The parent to be set as an attribute
     */
    public void setParent(char parentChar, Person parent){
       if (parentChar != 'M' && parentChar != 'F'){
            throw new IllegalArgumentException(String.format("Invalid character %c entered",parentChar));
       }
       else if (parentChar == 'M'){
            mother = parent;
        }else{
            father = parent;
        }
    }

    // TODO - Insert error handling for getParent

    /**
     * Accessor method to retrieve the parent
     * @param parentChar A character representing a parent (M for mother,
     * F for father)
     * @return A Person object representing a mother or a father
     * @throws IllegalArgumentException if the character passed in is not M or F
     */
    public Person getParent(char parentChar){
        if (parentChar == 'M'){
            return mother;
        }else if (parentChar == 'F'){
            return father;
        }else{
            throw new IllegalArgumentException(String.format("Invalid character %c entered",parentChar));
        }
    }

    /**
     * Accessor method to retrieve the ID of a Person
     * @return A string representing an individual's ID
     */
    public String getId(){
        return Integer.toString(id);
    }

    /**
     * Accessor method to get the year of birth of a person
     * @return An integer representing an individual's year of birth
     */
    public int getYOB(){
        return yearOfBirth;
    }

    // Biometric methods


    /**
     * Method stub for the addBiometric method
     */
    public void addBiometric(Biometric data){
       biodata.add(data);
    }

    /**
     * Get Biometric method to return a Biometric object if the 
     * object is found
     * @param String The biometric tag
     * @return A Biometric Object
     * @throws NullPointerException if the desired Biometric does not exist
     */
    public Biometric getBiometric(String tag){
        for(Biometric data:biodata){
            if(data.getTag().equals(tag)){
                return data;
            }
        }
        return null;

    }
}

