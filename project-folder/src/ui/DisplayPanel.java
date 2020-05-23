package ui;
import app.*;
import java.awt.*;
import javax.swing.*;
public class DisplayPanel extends JPanel{
    // TODO Write getters for these attributes
    protected SearchPanel searchPan;
    protected RecordsPanel recordsPan;
    private SNIDApp app;
    public DisplayPanel(SNIDApp app){
        this.app = app;
        this.setLayout(new BorderLayout());
        searchPan = new SearchPanel();
        recordsPan = new RecordsPanel(app);
        this.add(searchPan,BorderLayout.NORTH);
        this.add(recordsPan);
    }
    
}