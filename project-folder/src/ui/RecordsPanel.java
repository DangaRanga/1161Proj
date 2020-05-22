package ui;

import app.*;
import snid.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

import java.awt.*;
import java.util.ArrayList;

public class RecordsPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -8566509772454481150L;
    protected JTextArea detailsArea;
    protected JTextArea recordsArea;
    protected JScrollPane recordsScroll;
    protected JList<String> recordsList;
    protected DefaultTableModel model;
    protected TableRowSorter<DefaultTableModel> sorter;
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
        table.setDefaultEditor(Object.class,null);
        table.setShowGrid(false);
        table.setTableHeader(null);
        table.setRowSorter(sorter);
        addRecords();
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

}