package ui;
import app.*;
import data.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;
public class SNIDGUI extends JFrame{
    private SearchPanel searchPanel;
    private ButtonPanel buttonPanel;
    private JPanel radioPanel;
    private DisplayPanel displayPanel;
    private RecordsPanel recordsPanel;
    private SNIDApp app;

    public SNIDGUI(String title, SNIDApp app){
        super(title);
        this.setSize(600,500);
        this.setLocation(450,150);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.app = app;
        Container container = this.getContentPane();    
        this.radioPanel = new RadioPanel();
        this.buttonPanel = new ButtonPanel();
        this.displayPanel = new DisplayPanel(app);
        buttonPanelListeners();
        recordPanelListeners();
        container.setLayout(new BorderLayout());
        container.add(buttonPanel,BorderLayout.EAST);
        container.add(radioPanel,BorderLayout.NORTH);
        container.add(displayPanel,BorderLayout.CENTER);
        }
    /**
     * Method to allow the records to be clicked and added to the search
     * bar
     */
    public void recordPanelListeners(){
        JList<String> records = displayPanel.recordsPan.recordsList;
        JTextField searchField = displayPanel.searchPan.searchField;
        records.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent mouseEvent){
                JList list = (JList) (mouseEvent.getSource());
                if(mouseEvent.getClickCount() == 1){
                    int index = list.locationToIndex(mouseEvent.getPoint());
                    if(index >= 0){
                        Object obj = list.getModel().getElementAt(index);
                        searchField.setText(obj.toString());
                    }
                }

            }
        });
    }

    public void buttonPanelListeners(){
        buttonPanel.clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                displayPanel.searchPan.searchField.setText("");
                displayPanel.recordsPan.detailsArea.setText("");
            }
        });
        
        buttonPanel.quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });

        buttonPanel.search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String input = displayPanel.searchPan.searchField.getText();
                ListModel<String> model = displayPanel.recordsPan.listModel;
                JTextArea detailsArea = displayPanel.recordsPan.detailsArea;
                for(int index =0; index < model.getSize(); index++){
                    if (input.equals(model.getElementAt(index))){
                        detailsArea.setText(String.format("Id no: %s\nDetails: ",(input)));
                        break;
                    }else{
                        detailsArea.setText("");
                    }
                }
                if(detailsArea.getText().equals("")){
                    JOptionPane.showMessageDialog(displayPanel, "Record not Found", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } 
        });
    }

    public static void main(String[]args){
        SNIDGUI gui = new SNIDGUI("SYSTEM FOR NATIONAL IDENTIFICATION(SNID)",new SNIDApp("Citizens.txt",','));
        gui.setVisible(true);

    }
}