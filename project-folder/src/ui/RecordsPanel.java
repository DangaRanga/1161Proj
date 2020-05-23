package ui;

import app.*;
import snid.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

import java.awt.*;
import java.util.ArrayList;

public class RecordsPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -8566509772454481150L;
    // TODO Write Getters for these attributes
    protected JTextArea detailsArea;
    protected JTextArea recordsArea;
    protected JScrollPane recordsScroll;
    protected JList<String> recordsList;
    protected DefaultTableModel model;
    protected TableRowSorter<DefaultTableModel> sorter;
    protected TableColumnModel tableColModel;
    protected JTable table;
    private SNIDApp app;

    
    /**
     * Constructor for the records Panel class
     * @param app A SNIDApp object to access the records from the snid app  
     */
    public RecordsPanel(SNIDApp app) {
        this.app = app;
        setPreferredSize(new Dimension(200, 200));
        setLayout(new FlowLayout());
        detailsArea = new JTextArea("details", 18, 20);
        model = new DefaultTableModel(0, 2);
        sorter = new TableRowSorter<>(model);
        table = new JTable(model);
        tableColModel = table.getColumnModel();
        table.setDefaultEditor(Object.class,null);
        table.setShowGrid(false);
        table.setTableHeader(null);
        table.setRowSorter(sorter);
        addRecords();
        tableColModel.removeColumn(tableColModel.getColumn(1));
        recordsScroll = new JScrollPane(table);
        recordsScroll.getViewport().setPreferredSize(new Dimension(170, 290));
        recordsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recordsScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        add(recordsScroll);
        add(detailsArea);
    }

    public void addRecords() {
        ArrayList<Citizen> records = app.getRecords();
        for (int i = 0; i < records.size(); i++) {
            model.addRow(new Object[] { records.get(i).getId(),records.get(i).getNameObj().getLastName() });
        }
    }
    /**
     * returns the detailArea atribute
     * @return the detailsArea
     */
    public JTextArea getDetailsArea(){
        return detailsArea;
    }
    /**
     * returns the recordsArea atribute
     * @return the recordsArea
     */
    public JTextArea getRecordsArea(){
        return recordsArea;
    }
    /**
     * returns the recordsScroll atribute
     * @return the recordsScroll
     */
    public JScrollPane getRecordsScroll(){
        return recordsScroll;
    }
    /**
     * returns the recordsList atribute
     * @return the recordsList
     */
    public JList<String> getRecordsList(){
        return recordsList;
    }
    /**
     * Getter for the default table model
     * @return The table model
     */
    public DefaultTableModel getModel(){
        return model;
    }
    /**
     * Getter for the Table Row Sorter
     * @return the sorter
     */
    public TableRowSorter<DefaultTableModel> getSorter(){
        return sorter;
    }
    /**
     * returns the tableColModel atribute
     * @return the tableColModel
     */
    public TableColumnModel getTableColModel(){
        return tableColModel;
    }
    /**
     * returns the idOpt atribute
     * @return the table
     */
    public JTable getTable(){
        return table;
    }
    /**
     * returns the app atribute
     * @return the app
     */



}