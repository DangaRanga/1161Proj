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

    public MarriageCertificate(String refNo, String groomId, String brideId, String marriageDate){
        this.groomId = groomId;
        this.brideId = brideId;
        this.marriageDate = marriageDate;
        this.refNo = refNo;
    }

    @Override
    public String getRefNo(){
        return "M" + refNo;
    }

    public String getDate(){
        return marriageDate;
    }

    public String getGroomId(){
        return groomId;
    }

    public String getBrideId(){
        return brideId;
    }
    /**
     * toString method for the MarriageDoc class
     * @return a formatted String of marriage details
     */
    public String toString(){
        return "CivicDoc no.: " + getRefNo() + "\n" + "Type: Marriage " + "\n" +
                "Groom ID: " + getGroomId() + "\n" + "Bride ID: " + getBrideId() + "\n" +
                "Marriage date: " + getDate();
    }
}