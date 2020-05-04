package snid;

public class DNA implements Biometric{
    private static final String tag = "D";
    private String value;

    public DNA(String value){
        this.value = value;
    }

    @Override
    public String getValue(){
        return value;
    }

    @Override
    public String getTag(){
        return DNA.tag;
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