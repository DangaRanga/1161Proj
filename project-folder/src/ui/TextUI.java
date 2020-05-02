package ui;

import java.util.Scanner; 
import app.*;

public class TextUI {
    //Attribute
    
    Scanner scan = new Scanner(System.in);
    boolean input = true;
    
    /**
     * Private helper method when the option to register a birth has been selected
     * <br> 
     * This method takes each line of input for each parameter in the register birth method
     * then calls the register birth method to store the person to the list of records
     * @param appObj The SNIDApp object
     */
    private void registerBirthOption(SNIDApp appObj){

        // ------------------- Reading the gender ----------------------------//
        System.out.println("Enter the person's gender(M/F):" );
        char gender = scan.next().charAt(0);
        System.out.println(gender);

        //----------------- Reading the year of birth ------------------------//

        System.out.println("Enter the person's year of birth: ");
        int yob = Integer.parseInt(scan.next());

        //----------------- Reading the first name ---------------------------//

        System.out.println("Enter the person's first name");
        String firstName = scan.next();

        //----------------- Reading the middle name --------------------------//

        System.out.println("Enter the person's middle name");
        String middleName = scan.next();

        //------------------ Reading the last name ---------------------------//

        System.out.println("Enter the person's last name");
        String lastName = scan.next();

        //------------------ Registering the birth ---------------------------//

        appObj.registerBirth(gender, yob, firstName, middleName, lastName);
        System.out.println("Registration Successful");
        //-----------------------  Method End  -------------------------------//
    }

    //Method
    public void go (SNIDApp user) {
        System.out.println("Launching System...");
        while (input == true) {
            //Display Menu
            System.out.println("+=====================+");
            System.out.println("|     M  E  N   U     |");
            System.out.println("+=====================+"); 
            System.out.println("a. Register a Birth");
            System.out.println("b. Update Parent Data");
            System.out.println("c. Update a Citizen's Address");
            System.out.println("d. Enter a person's death");
            System.out.println("e. Enter your martial status");
            System.out.println("f. Enter your mailing label");
            System.out.println("o. Exit");

           String option = scan.next();

            if (option.equals("a")) {
                registerBirthOption(user);
                

            }
            if (option.equals("b")) {
                
            }

            if (option.equals("c")) {

            }

            if (option.equals("d")) {

            }
       
            if (option.equals("e")) {

            }
       
            if (option.equals("f")) {

            }
            if (option.equals("o")) {
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
