package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class ButtonPanel extends JPanel{
    protected JButton search;
    protected JButton clear;
    protected JButton quit;
    public ButtonPanel(){
        BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(layout);
        this.search = new JButton("Search");
        search.setFont(new Font("Sans-serif",Font.BOLD, 14));
        search.setPreferredSize( new Dimension(120,20));
        search.setMaximumSize(new Dimension(90,25));
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        this.clear = new JButton("Clear");
        clear.setFont(new Font("Sans-serif",Font.BOLD, 14));
        clear.setPreferredSize(new Dimension(120,20));
        clear.setMaximumSize(new Dimension(90,25));
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        this.quit = new JButton("Quit");
        quit.setFont(new Font("Sans-serif",Font.BOLD, 14));
        quit.setPreferredSize(new Dimension(100,20));
        quit.setMaximumSize(new Dimension(90,25));
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(search);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(clear);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(quit);

    }
    //getters methods for attributes
    /**
     * returns the search atribute
     * @return the search attribute 
     */
    public JButton getSearch(){
        return search;
    }

    /**
     * returns the clear atribute
     * @return The clear button
     */
    public JButton getClear(){
        return clear;
    }
    /**
     * returns the quit atribute
     * @return The quit button
     */
    public JButton getQuit(){
        return quit;
    }

}