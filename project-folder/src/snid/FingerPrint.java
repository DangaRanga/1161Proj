package snid;

public class FingerPrint implements Biometric{
    private static final String tag = "F";
    private String value;
    
    public FingerPrint(String value){
        this.value = value;
    }

    @Override
    public String getTag(){
        return FingerPrint.tag;
    }

    @Override
    public String getValue(){
        return value;
    }
    
    @Override
    public int match(Biometric other){
        if (other.getTag().equals(tag) && other.getValue().equals(value)){
            return 0;
        }else{
            return 1;
        }
    }
}
