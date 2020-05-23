package ui;

import javax.swing.*;
import java.awt.*;
public class SearchPanel extends JPanel {

    // TODO Write Getter for this attribute
    protected JTextField searchField;
    
    public SearchPanel(){
        setLayout(new FlowLayout());
        //setPreferredSize(new Dimension(40,10));
        // Implement action listener to change text
        setPreferredSize(new Dimension(20,35));
        setMaximumSize(new Dimension(20,55));
        searchField = new JTextField("",30);
        this.add(searchField);
    }

    
}