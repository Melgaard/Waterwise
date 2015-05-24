/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterwise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorChecker {
	
	
		
		// Metoder til at checke en String 
		public  boolean isDouble(String str) {
                    try {
                        Double.parseDouble(str);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }	

		
		public  boolean isInt(String str) {
                    try {
                        Integer.parseInt(str);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
		}
		
		
		
		public boolean isAmountValid(String amount) {
			if (isInt(amount) == true) {
				return true;
			}
			return false;
		}
                
                public boolean isIDValid(String amount) {
			if (isInt(amount) == true) {
				return true;
			}
			return false;
		}
                
                public boolean isProductReorderValid(String amount) {
			if (isInt(amount) == true) {
				return true;
			}
			return false;
		}
		
		public boolean isPriceValid(String price) {
			if (isDouble(price) == true) {
				return true;
			}
			return false;
		}
                
                public boolean isWeightValid(String weight) {
			if (isDouble(weight) == true) {
				return true;
			}
			return false;
		}
		
		public boolean isNameValid(String name) {
			if (name != "" && !name.isEmpty()) {			
				return true;
			}
			return false;
		}
                
                public boolean isDeliveryValid(String name) {
			if (name != "" && !name.isEmpty()) {			
				return true;
			}
			return false;
		}
                
                public boolean isPaymentValid(String name) {
			if (name != "" && !name.isEmpty()) {			
				return true;
			}
			return false;
		}
                public boolean isDateValid(String date) {
                    
                    if (date != "" && !date.isEmpty()) {                            
                                String zipPattern = "\\d\\d\\d\\d-\\d\\d-\\d\\d";                                
                                Pattern zip = Pattern.compile(zipPattern);                                
                                Matcher m = zip.matcher(date);
                                if(m.find()) {
                                    return true;
                                }
                                else {
                                    return false;
                                }                               
			}
			return false;
		}
                
                public boolean isAddressValid(String address) {
                        if (address != "" && !address.isEmpty()) {                            
                                String zipPattern = "\\d\\d\\d\\d";                                
                                Pattern zip = Pattern.compile(zipPattern);                                
                                Matcher m = zip.matcher(address);
                                if(m.find()) {
                                    return true;
                                }
                                else {
                                    return false;
                                }                               
			}
			return false;
		}
                
                public boolean isSizeValid(String size) {
			if (size.equals("Stor") || size.equals("Lille") || size.equals("Standard")) {			
				return true;
			}
			return false;
		}		
		          

		 // Email format checker
		public boolean isEmailValid(String email) {

			if	(	email.contains(".de") ||
					email.contains(".se") ||
					email.contains(".com") || 
					email.contains(".dk")  &&
					email.contains("@")	) {
				return true;
			}
			return false;

}               //converters
                public String intToString(int number) {
                    String convNumber;
                    convNumber = "" + number;
                    return convNumber;
                }
                public String doubleToString(double number) {
                    String convNumber;
                    convNumber = "" + number;
                    return convNumber;
                }

                
                public int StringToInt(String number) {
                    int convNumber;
                    convNumber = Integer.parseInt(number);
                    return convNumber;
                }
                
                public double StringToDouble(String number) {
                    double convNumber;
                    convNumber = Double.parseDouble(number);
                    return convNumber;
                }
                
              
		public boolean isPhonenumberValid(String phoneNumber) {
                        return phoneNumber.length() == 8;
                }


}
