package snid;

/**
 * This class represents a Death Certificate
 * @author Jason Gayle
 * @version 1.0
 */
public class DeathCertificate implements CivicDoc {
    private static int deathCounter = 0;
    private String refNo;
    private String causeOfDeath;
    private String dateOfDeath;
    private String placeOfDeath;


    public DeathCertificate(String id,String causeOfDeath,String dateofdeath,String placeOfDeath ){
        this.causeOfDeath = causeOfDeath;
        this.dateOfDeath = dateofdeath;
        this.placeOfDeath = placeOfDeath;
        refNo = Integer.toString(++deathCounter);
    }

    public DeathCertificate(String refNo, String id,String causeOfDeath,String dateofdeath,String placeOfDeath ){
        this.causeOfDeath = causeOfDeath;
        this.dateOfDeath = dateofdeath;
        this.placeOfDeath = placeOfDeath;
        this.refNo = refNo;
    }

    public String getCauseOfDeath(){
        return causeOfDeath;
    }

    public String getDateOfDeath(){
        return dateOfDeath;
    }

    public String getPlaceOfDeath(){
        return placeOfDeath;
    }


    @Override
    /**
     * Method to retrieve the reference number of a citizen's death
     * @return A string representing the reference number
     */
    public String getRefNo(){
        return "D" + refNo;
    }
    
    public String toString(){
        return "CivicDoc no.:\n" + getRefNo()  + "\n" + 
                "Cause: " + getCauseOfDeath() + "\n" + 
                "Date: " +  getDateOfDeath() +  "\n" + 
                "Place of death: " + getPlaceOfDeath(); 
        
    }
}