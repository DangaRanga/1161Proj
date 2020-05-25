import snid.*;
import data.*;

import java.util.Scanner;

import app.*;
import ui.*;

public class Main {
    /**
     * Driver for the System for National Ids (SNID).
     * 
     * @author Jason Gayle
     * @author Mario Anckle
     * @author Tatanya Lynch
     * @version 1.0
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the file name and delimiter to continue...");
            System.out.println("File name: ");
            String fileName = input.next();
            System.out.println("Delimiter: ");
            char delimiter = input.next().charAt(0);
            SNIDApp app = new SNIDApp(fileName, delimiter);
            boolean choice = true;
            while (choice) {
                System.out.println("-----SNID UI Manager-----");
                System.out.println("a. Launch the GUI");
                System.out.println("b. Launch the TextUI");
                System.out.println("x. Exit the System");
                System.out.println("Please select the UI you would like to launch: ");
                String option = input.next().toLowerCase().substring(0, 1);
                switch (option) {
                    case "a":
                        SNIDGUI gui = new SNIDGUI("SYSTEM FOR NATIONAL IDENTIFICATION(SNID)", app);
                        gui.setVisible(true);
                        break;
                    case "b":
                        TextUI ui = new TextUI();
                        ui.go(app);
                        break;
                    case "x":
                        choice = false;
                        input.close();
                        break;
                }
            }
        }
    }
}
