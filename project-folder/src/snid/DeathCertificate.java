package snid;

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

    @Override
    /**
     * Method to retrieve the reference number of a citizen's death
     * @return A string representing the reference number
     */
    public String getRefNo(){
        return refNo;
    }
    
    public String toString(){
        return "Death Cert: #" + refNo + "\n" + " Cause:" + causeOfDeath + "\n" + " Date: " + dateOfDeath
        + " Place of death: " + placeOfDeath; 
        
    }
}