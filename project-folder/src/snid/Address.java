package snid;

import java.util.*;

/**
 * This class represents an address
 * 
 * @author Jason Gayle
 * @version 1.0
 */
public class Address {
  //  private String streetLine;
  //  private String townLine;
   // private String countryLine;
    private ArrayList<String> addressList;

    /**
     * Constructor for the Address class to initialize the lines in the address
     * @param lines The lines entered for the address. This includes the street
     * name and number, the town, the parish and the country
     */
    public Address(String lines){
        String addressArr[] = lines.split("\\|");
        addressList = new ArrayList<String>(Arrays.asList(addressArr));
        addressList.removeAll(Arrays.asList(""));
        //streetLine = addressList.get(0);
        //townLine = addressList.get(1);
        //countryLine = addressList.get(addressList.size()-1);
    }

    /**
     * Accessor method to retrieve the country
     * @return A string representing the country
     */

    public String getCountry(){
        return addressList.get(addressList.size()-1);
    }

    /**
     * to String method for the Address class
     * @return a formatted string representing the address details
     */
    public String toString(){
        String addressLine = "";
        for(int i=0;i<addressList.size();i++){
            if(i != (addressList.size()-1)){
               addressLine += addressList.get(i)+ "\n";
            }else{
                addressLine += addressList.get(i);
            }
        }
        return addressLine;
    }
}