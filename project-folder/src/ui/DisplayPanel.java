package ui;
import app.*;
import java.awt.*;
import javax.swing.*;
/**
 * This class represents a JPanel that handles the displaying of all records
 * @author Jason Gayle
 * @author Mario Anckle
 * @author Tatanya Lynch
 */
public class DisplayPanel extends JPanel{
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
    /**
     * Method to get the records panel
     * @return {@link RecordsPanel}
     */
    public RecordsPanel getRecordsPanel(){
        return recordsPan;
    }

    /**
     * Method to get the searchPanel
     * @return {@link SearchPanel}
     */
    public SearchPanel getSearchPanel(){
        return searchPan;
    }
}