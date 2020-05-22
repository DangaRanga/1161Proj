package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
public class RadioPanel extends JPanel{
    protected JRadioButton idOption;
    protected JRadioButton nameOption;
    protected JRadioButton biometricOption;
    protected GridBagConstraints constraints;

    public RadioPanel(){
        setLayout(new FlowLayout());
        ButtonGroup radioGroup = new ButtonGroup();
        constraints = new GridBagConstraints();
        idOption = new JRadioButton("Search by Id");
        nameOption = new JRadioButton("Search by Name");
        biometricOption = new JRadioButton("Biometric Search");
        radioGroup.add(idOption);
        radioGroup.add(nameOption);
        radioGroup.add(biometricOption);
        this.add(idOption);
        this.add(nameOption);
        this.add(biometricOption);
        this.setBorder(new EmptyBorder(15,15,15,15));
    }
    
}