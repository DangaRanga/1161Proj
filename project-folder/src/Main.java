import snid.*;
import data.*;
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
        SNIDApp app = new SNIDApp("Citizens.txt", ',');
        TextUI ui = new TextUI();
        ui.go(app);
    }
}
