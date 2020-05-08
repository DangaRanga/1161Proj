package snid;

public class MarriageCertificate implements CivicDoc{
    private static int counter = 0;
    private String refNo;
    private String groomId;
    private String brideId;
    private String marriageDate;

    public MarriageCertificate(String groomId, String brideId, String marriageDate){
        this.groomId = groomId;
        this.brideId = brideId;
        this.marriageDate = marriageDate;
        refNo = Integer.toString(++counter);
    }

    @Override
    public String getRefNo(){
        return "M" + refNo;
    }
    /**
     * toString method for the MarriageDoc class
     * @return a formatted String of marriage details
     */
    public String toString(){
        return "Marriage no.: " + refNo + " Groom ID: " + groomId +
                " Bride ID: " + brideId + " Marriage date: " + marriageDate;
    }
}