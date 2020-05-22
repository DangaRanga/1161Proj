package ui;

import app.*;
import snid.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
public class RecordsPanel extends JPanel{
    protected JTextArea detailsArea;
    protected JTextArea recordsArea;
    protected JScrollPane recordsScroll;
    protected JList<String> recordsList;
    protected DefaultListModel<String> listModel;
    private SNIDApp app;

    public RecordsPanel(SNIDApp app){
        this.app = app;
        setPreferredSize(new Dimension(200,200));
        setLayout(new FlowLayout());
        detailsArea = new JTextArea("details",18,20);
        listModel = new DefaultListModel<String>();
        recordsList = new JList<String> (listModel);
        addRecords();
        // recordsScroll.getViewport().setPreferredSize(new Dimension(170,200));
        recordsList.setVisibleRowCount(16);
        recordsScroll = new JScrollPane(recordsList);
        recordsScroll.getViewport().setPreferredSize(new Dimension(170,290));
        recordsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recordsScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        add(recordsScroll);
        add(detailsArea);
    }

    public void addRecords(){
        ArrayList<Citizen> records = app.getRecords();
        for(int i=0;i<records.size();i++){
            listModel.addElement(records.get(i).getId());
        }
    }
}