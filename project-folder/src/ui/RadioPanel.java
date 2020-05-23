package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class RadioPanel extends JPanel{

    // TODO Write getters for these attributes
    private JRadioButton idOption;
    private JRadioButton nameOption;
    private JRadioButton biometricOption;
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
    // BTW don't change anything to private yet
    /**
     * returns the idOption atribute
     * @return the idOption
     */
    public JRadioButton getIDButton(){
        return idOption;
    }
    /**
     * returns the nameOption atribute
     * @return the nameOption
     */
    public JRadioButton getNameButton(){
        return nameOption;
    }
    /**
     * returns the biometricOption atribute
     * @return the biometricOption
     */
    @Deprecated
    public JRadioButton getBiometricButton(){
        return biometricOption;
    }
}