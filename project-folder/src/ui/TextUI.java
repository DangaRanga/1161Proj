package ui;

import java.io.IOException;
import java.util.Scanner;
import app.*;
import data.*;

public class TextUI {
    //Attribute
    
    Scanner scan = new Scanner(System.in);

    private boolean isParseable(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch(NumberFormatException e){
            return false;
        } 
    }
    
    /**
     * Private helper method when the option to register a birth has been selected
     * <br> 
     * This method takes each line of input for each parameter in the register birth method
     * then calls the register birth method to store the person to the list of records
     * @param appObj The SNIDApp object
     */
    private void registerBirthOption(SNIDApp appObj){
        boolean valid;
        char gender;
        // Do while Block to validate gender
        
        do{
            System.out.println("Enter the person's gender(M/F):" );
            gender = scan.next().charAt(0);
            // Ternary operator that returns true if the gender is M or F and false otherwise
            valid = gender == 'M' || gender == 'F' ? true : false;
        }while(!valid);
        System.out.println("Enter the person's year of birth: ");
        // Validating the year of Birth
        int yob = scan.nextInt();
        System.out.println("Enter the person's first name");
        String firstName = scan.next();
        System.out.println("Enter the person's middle name");
        String middleName = scan.next();
        System.out.println("Enter the person's last name");
        String lastName = scan.next();
        appObj.registerBirth(gender, yob, firstName, middleName, lastName);
        System.out.println("Registration Successful...");
    }

    private void updateParentData(SNIDApp appObj){
        System.out.println("Enter the person's id: ");
        String id = scan.next();
        System.out.println("Enter the person's father's id");
        String fatherId = scan.next();
        System.out.println("Enter the person's mother's id");
        String motherId = scan.next();
        appObj.addParentData(id, fatherId, motherId);
        System.out.println("Parent details have been sucessfully added...");
    }
    
    private void updateCitizenAddress(SNIDApp appObj){
        System.out.println("Enter the Citizen's id: ");
        String id = scan.next();
        System.out.println("Enter the Citizen's street address");
        String street = scan.next();
        System.out.println("Enter the town: ");
        String town = scan.next();
        System.out.println("Enter the parish: ");
        String parish = scan.next();
        System.out.println("Enter the country: ");
        String country = scan.next();
        appObj.updateAddress(id, street, town, parish, country);
        System.out.println("Update Successful...");
    }

    private void registerCitizenDeath(SNIDApp appObj){
        System.out.println("Enter the Citizen's id: ");
        String id = scan.next();
        System.out.println("Enter the Citizen's cause of death: ");
        String causeOfDeath = scan.next();
        System.out.println("Enter the Citizen's date of death in the format - (MM/DD/YYYY): ");
        String dateOfDeath = scan.next();
        System.out.println("Enter the Citizen's place of death: ");  
        String placeOfDeath = scan.next();
        appObj.registerDeath(id, causeOfDeath, dateOfDeath, placeOfDeath);      
    }

    private void registerCitizenMarriage(SNIDApp appObj){
        System.out.println("Enter the groom's id");
        String groomId = scan.next();
        System.out.println("Enter the bride's id");
        String brideId = scan.next();
        System.out.println("Enter the date of the marriage in the format - (MM/DD/YYYY): ");
        String marriageDate = scan.next();
        appObj.registerMarriage(groomId, brideId, marriageDate);        
    }

    private void generateMailingLabel(SNIDApp appObj){
        System.out.println("Enter the Citizen's id");
        String id = scan.next();
        System.out.println("The mailing label goes as follows \n" + appObj.mailingLabel(id));
    }

    private void searchOption(SNIDApp appObj){
        boolean searchBool = true;
        while(searchBool){
            System.out.println("------Search Menu------");
            System.out.println("1. Search by id");
            System.out.println("2. Search by name");
            System.out.println("X. Exit the search menu");
            String choice = scan.next();
            switch(choice){
                case("1"):
                    System.out.println("Enter the id of the Citizen you are searching for: ");
                    String id = scan.next();
                    String details = appObj.search(id);
                    if(!(details.equals(""))){
                        System.out.println("The citizen's details goes as follows:\n" + appObj.search(id));
                    }else{
                        System.out.println("The Citizen could not be found in the database");
                    }
                    break;
                case("X"):
                    searchBool = false;
                    break;
            }


        }
        
    }

    //Method
    public void go (SNIDApp user) {
        System.out.println("Launching System...");
        boolean input = true;
        while (input) {
            System.out.println("+=====================+");
            System.out.println("|     M  E  N   U     |");
            System.out.println("+=====================+"); 
            System.out.println("a. Register a Birth");
            System.out.println("b. Update Parent Data");
            System.out.println("c. Update a Citizen's Address");
            System.out.println("d. Register a Death");
            System.out.println("e. Register a Marriage");
            System.out.println("f. Generate a Mailing Label");
            System.out.println("g. Search");
            System.out.println("h. Exit");
            String option = scan.next().toLowerCase().substring(0,1); // To ensure that only one character is entered
            switch(option){
            case "a":
                registerBirthOption(user);
                break;
            case "b":
                updateParentData(user);
                break;
            case "c":
                updateCitizenAddress(user);
                break;
            case "d":
                registerCitizenDeath(user);
            case "e":
                registerCitizenMarriage(user);
            case "f":
                generateMailingLabel(user);
            case "g":
                searchOption(user);
                break;
            case "h":
                user.shutdown();
                input = false;
                break;
            }
   
        }
    }

public static void main (String[]args){
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the file name and delimiter to continue...");
    System.out.println("File name: ");
    String fileName = input.next();
    System.out.println("Delimiter: ");
    char delimiter = input.next().charAt(0);
    SNIDApp app = new SNIDApp(fileName, delimiter);
    TextUI menu = new TextUI();
    menu.go(app);

}

}
