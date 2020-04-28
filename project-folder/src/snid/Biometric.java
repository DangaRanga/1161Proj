package snid;
/**
 * @author Jason Gayle
 * @version 1.0
 */
public interface Biometric {
    /**
     * Method to retrieve the biometric tag
     * @return A string representing the biometric tag
     */
    public String getTag();

    /**
     * Method to get the biometric value
     * @return The biometric value
     */
    public String getValue();

    /**
     * Method to check if there is a match with the biometric data
     * @return An integer representing a match with the biometric data
     */
    public int match(Biometric other); 

}