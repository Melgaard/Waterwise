/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterwise;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorChecker {

    // Metoder til at checke en String 
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isInt(String str) {
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

    public boolean isProductIDValid(String amount) {
        if (isInt(amount) == true) {
            return true;
        }
        return false;
    }

    public boolean isProductReorderValid(String id) {
        if (id != "" && !id.isEmpty()) {
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

    public boolean isOrderIDValid(String name) {
        if (name != "" && !name.isEmpty()) {
            return true;
        }
        return false;
    }

    public String noProducts(boolean exists) {
        if (exists) {
            return "ingen produkter valgt";
        } else {
            return "der er valgt produkter";
        }

    }

    public boolean isPaymentValid(String name) {
        if (name != "" && !name.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isSuppNameValid(String name) {
        if (name != "" && !name.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isCityValid(String name) {
        if (name != "" && !name.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean doesListExist(HashMap listOfProducts) {
        if (listOfProducts == null) {
            return false;
        }
        return true;
    }

    public boolean isDateValid(String date) {

        if (date != "" && !date.isEmpty()) {
            String zipPattern = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
            Pattern zip = Pattern.compile(zipPattern);
            Matcher m = zip.matcher(date);
            if (m.find()) {
                return true;
            } else {
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
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean isStreetnameValid(String streetname) {
        if (streetname != "" && !streetname.isEmpty()) {
            String zipPattern = "(.)*(\\d)";
            Pattern zip = Pattern.compile(zipPattern);
            Matcher m = zip.matcher(streetname);
            if (m.find()) {
                return true;
            } else {
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

        if (email.contains(".de")
                || email.contains(".se")
                || email.contains(".com")
                || email.contains(".dk")
                && email.contains("@")) {
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

    public boolean incomingCheck(OrderFrame ofcheck) {

        Error er;
        boolean bool = false;

        if (isOrderIDValid(ofcheck.orderIDField.getText())) {
            if (!ofcheck.chosenProducts.isEmpty()) {
                if (isDeliveryValid(ofcheck.deliveryTypeField.getText())) {
                    if (isPaymentValid(ofcheck.paymentTypeField.getText())) {
                        if (isNameValid(ofcheck.customerNameField.getText())) {
                            if (isEmailValid(ofcheck.customerEmailField.getText())) {
                                if (isAddressValid(ofcheck.deliveryAddressZipField.getText())) {
                                    if (isStreetnameValid(ofcheck.deliveryAddressField.getText())) {
                                        if (isPhonenumberValid(ofcheck.customerPhonenumberField.getText())) {

                                            System.out.println("Alle felter godkendt - der kan skrives til DB");

                                            bool = true;
                                            ofcheck.dispose();
                                        } else {
                                            er = new Error(ofcheck.customerPhonenumberField.getText(), "Telefon");
                                        }
                                    } else {
                                        er = new Error(ofcheck.deliveryAddressField.getText(), "Adresse");
                                    }
                                } else {
                                    er = new Error(ofcheck.deliveryAddressZipField.getText(), "Postnummer");
                                }
                            } else {
                                er = new Error(ofcheck.customerEmailField.getText(), "Email");
                            }
                        } else {
                            er = new Error(ofcheck.customerNameField.getText(), "Navn");
                        }
                    } else {
                        er = new Error(ofcheck.paymentTypeField.getText(), "Betalingstype");
                    }
                } else {
                    er = new Error(ofcheck.deliveryTypeField.getText(), "Lev.Type");
                }
            } else {
                er = new Error("Intet produkt valgt", "Produkter");
            }
        } else {
            er = new Error(ofcheck.orderIDField.getText(), "Ordre ID");
        }
        return bool;
    }

    public boolean outgoingCheck(OrderFrame ofcheck) {

        Outgoing oc = (Outgoing) ofcheck.orderShown;
        Error er;
        boolean bool = false;

        String orderID = ofcheck.orderIDField.getText();
        HashMap<Product, Integer> productlist = ofcheck.listOfProducts;
        String paymentType = ofcheck.paymentTypeField.getText();
        String deliveryType = ofcheck.deliveryTypeField.getText();
        String suppname = ofcheck.supplierNameField.getText();
        String suppemail = ofcheck.supplierEmailField.getText();

        if (isOrderIDValid(orderID)) {
            if (!productlist.isEmpty()) {
                if (isPaymentValid(paymentType)) {
                    if (isDeliveryValid(deliveryType)) {
                        if (isSuppNameValid(suppname)) {
                            if (isEmailValid(suppemail)) {
                                System.out.println("Alle felter godkendt - der kan skrives til DB");
                                bool = true;
                                ofcheck.dispose();
                            } else {
                                er = new Error(ofcheck.supplierEmailField.getText(), "Email");
                            }
                        } else {
                            er = new Error(ofcheck.supplierNameField.getText(), "Navn");
                        }
                    } else {
                        er = new Error(ofcheck.deliveryTypeField.getText(), "Lev.Type");
                    }
                } else {
                    er = new Error(ofcheck.paymentTypeField.getText(), "Betalingstype");
                }
            } else {
                er = new Error("Intet produkt valgt", "Produkter");
            }
        } else {
            er = new Error(ofcheck.orderIDField.getText(), "Ordre ID");
        }

        return bool;
    }
}
