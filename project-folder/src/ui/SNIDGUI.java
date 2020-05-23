package ui;

import app.*;
import data.*;
import snid.Name;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SNIDGUI extends JFrame {
    private SearchPanel searchPanel;
    private ButtonPanel buttonPanel;
    private RadioPanel radioPanel;
    private DisplayPanel displayPanel;
    private RecordsPanel recordsPanel;
    private SNIDApp app;

    public SNIDGUI(String title, SNIDApp app) {
        super(title);
        this.setSize(600, 500);
        this.setLocation(450, 150);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.app = app;
        Container container = this.getContentPane();
        this.radioPanel = new RadioPanel();
        this.buttonPanel = new ButtonPanel();
        this.displayPanel = new DisplayPanel(app);
        searchAndFilter();
        buttonPanelListeners();
        recordPanelListeners();
        container.setLayout(new BorderLayout());
        container.add(buttonPanel, BorderLayout.EAST);
        container.add(radioPanel, BorderLayout.NORTH);
        container.add(displayPanel, BorderLayout.CENTER);
    }

    /**
     * Method to allow the records to be clicked and added to the search bar
     */
    public void recordPanelListeners() {
        JTable table = displayPanel.recordsPan.table;
        DefaultTableModel tableModel = displayPanel.recordsPan.model;
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                int currentRow = table.getSelectedRow();
                if (currentRow > -1) {
                    String id = table.getModel().getValueAt(currentRow, 0).toString();
                    String data = app.mailingLabel(id);
                    displayPanel.recordsPan.detailsArea.setText(data);
                }
            }
        });
    }

    public void searchAndFilter() {
        JTextField searchField = displayPanel.searchPan.searchField;
        TableRowSorter<DefaultTableModel> sorter = displayPanel.recordsPan.sorter;
        JRadioButton idButton = radioPanel.getIDButton();
        JRadioButton nameButton = radioPanel.getNameButton();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent event) {

                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                search(searchField.getText());
            }

            private void search(String value) {
                if (value.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    if(nameButton.isSelected()){
                        sorter.setRowFilter(RowFilter.regexFilter(value,1));
                    } else if(idButton.isSelected()){
                        sorter.setRowFilter(RowFilter.regexFilter(value,0));
                    } else {
                        JOptionPane.showMessageDialog(displayPanel, "Please Select A button", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    public void buttonPanelListeners() {
        buttonPanel.clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                displayPanel.searchPan.searchField.setText("");
                displayPanel.recordsPan.detailsArea.setText("");
            }
        });

        buttonPanel.quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        buttonPanel.search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JTable table = displayPanel.recordsPan.table;
                DefaultTableModel tableModel = displayPanel.recordsPan.model;
                String input = displayPanel.searchPan.searchField.getText();
                JTextArea detailsArea = displayPanel.recordsPan.detailsArea;
                for(int index=0;index < table.getRowCount();index++){
                    System.out.println(table.getRowCount());
                    String id = table.getValueAt(index, 0).toString();
                    System.out.println(String.format("Value: %s",id));
                    System.out.println(String.format("Input: %s:",input));
                    if(id.equals(input)){
                        detailsArea.setText(app.mailingLabel(id));
                    }
                }
                if (detailsArea.getText().equals("")) {
                    JOptionPane.showMessageDialog(displayPanel, "Record not Found", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } 
            }
        });
    }

    public static void main(String[] args) {
        SNIDGUI gui = new SNIDGUI("SYSTEM FOR NATIONAL IDENTIFICATION(SNID)", new SNIDApp("Citizens.txt", ','));
        gui.setVisible(true);

    }
}