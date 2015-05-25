package waterwise;

import java.util.HashMap;
import javax.swing.JTable;

public class Controller {

    public void saveEditMethod(OrderFrame ofSaveFrom) {
        
        Error er;
        ErrorChecker ec = new ErrorChecker();
        Order orderSaveTo = ofSaveFrom.orderShown;
        
        
        //Check for error in typed data
        
        if(ec.isOrderIDValid(ofSaveFrom.orderIDField.getText())){
            if(!ofSaveFrom.chosenProducts.isEmpty()){
                if(ec.isDeliveryValid(ofSaveFrom.deliveryTypeField.getText())){
                    if(ec.isPaymentValid(ofSaveFrom.paymentTypeField.getText())){
                        if(ec.isNameValid(ofSaveFrom.customerNameField.getText())){
                            if(ec.isEmailValid(ofSaveFrom.customerEmailField.getText())){
                                if(ec.isAddressValid(ofSaveFrom.deliveryAddressZipField.getText())){
                                    if(ec.isStreetnameValid(ofSaveFrom.deliveryAddressField.getText())){
                                        if(ec.isPhonenumberValid(ofSaveFrom.customerPhonenumberField.getText())){
                                                    System.out.println("Alle felter godkendt - der kan skrives til DB");
                                                    

                                                        //Set order data to the orderframe data
                                                        orderSaveTo.setOrderID(ofSaveFrom.orderIDField.getText());
                                                        orderSaveTo.setListOfProducts(ofSaveFrom.listOfProducts);
                                                        orderSaveTo.setDeliveryType(ofSaveFrom.deliveryTypeField.getText());
                                                        orderSaveTo.setPaymentType(ofSaveFrom.paymentTypeField.getText());
                                                        orderSaveTo.setOrderStatus(ofSaveFrom.statusmenu.getSelectedItem().toString());

                                                        if (orderSaveTo instanceof Outgoing) {
                                                            saveEditOutgoing(ofSaveFrom, (Outgoing) orderSaveTo);
                                                        } else if (orderSaveTo instanceof Incoming) {
                                                            saveEditIncoming(ofSaveFrom, (Incoming) orderSaveTo);

                                                            int phone = Integer.parseInt(ofSaveFrom.customerPhonenumberField.getText());
                                                            String email = ofSaveFrom.customerEmailField.getText();
                                                            String name = ofSaveFrom.customerNameField.getText();
                                                            String address = ofSaveFrom.deliveryAddressField.getText();
                                                            String city = ofSaveFrom.deliveryAddressCityField.getText();
                                                            String zip = ofSaveFrom.deliveryAddressZipField.getText();
                                                            String country = ofSaveFrom.deliveryAddressCountryField.getText();

                                                            Customer customerOfOrder = new Customer(phone, email, name, address, city, zip, country);

                                                            customerOfOrder.Update();

                                                            }

                                            orderSaveTo.Update();
                                            ofSaveFrom.dispose();
                                        } else { er = new Error(ofSaveFrom.customerPhonenumberField.getText(), "Telefon"); }
                                    } else { er = new Error(ofSaveFrom.deliveryAddressField.getText(), "Adresse"); }
                                } else { er = new Error(ofSaveFrom.deliveryAddressZipField.getText(), "Postnummer"); }
                            } else { er = new Error(ofSaveFrom.customerEmailField.getText(), "Email"); }
                        } else { er = new Error(ofSaveFrom.customerNameField.getText(), "Navn"); }
                    } else { er = new Error(ofSaveFrom.paymentTypeField.getText(), "Betalingstype"); }
                } else { er = new Error(ofSaveFrom.deliveryTypeField.getText(), "Lev.Type"); }
            } else { er = new Error("Intet produkt valgt", "Produkter"); }
        } else { er = new Error(ofSaveFrom.orderIDField.getText(), "Ordre ID");}       
            
           
    }

    private void saveEditOutgoing(OrderFrame ofSaveFrom, Outgoing orderSaveTo) {

        orderSaveTo.setSupplierName(ofSaveFrom.supplierNameField.getText());
        orderSaveTo.setSupplierEmail(ofSaveFrom.supplierEmailField.getText());
        orderSaveTo.setOwnAddress(ofSaveFrom.ownAddressField.getText());
        orderSaveTo.setOwnCity(ofSaveFrom.ownCityField.getText());
        orderSaveTo.setOwnZip(ofSaveFrom.ownZipField.getText());
        orderSaveTo.setOwnCountry(ofSaveFrom.ownCountryField.getText());
        orderSaveTo.setOwnPhonenumber(ofSaveFrom.ownPhonenumberField.getText());
    }

    private void saveEditIncoming(OrderFrame ofSaveFrom, Incoming orderSaveTo) {

        orderSaveTo.setCustomerPhonenumber(Integer.parseInt(ofSaveFrom.customerPhonenumberField.getText()));
    }

    public void changeStatusMethod(Order orderToChange) {

        switch (orderToChange.getOrderStatus()) {
            case "Afsluttet":
                orderToChange.setOrderStatus("Uafsluttet");
                break;
            case "Uafsluttet":
                orderToChange.setOrderStatus("Afsluttet");
                break;
            default:
                System.out.println("status error - unknown status in changestatusmethod");
                break;
        }
        
        orderToChange.Update();
    }

    public DataBaseElement getElementFromTable(JTable table, String classToTestFor) {

        int row = table.getSelectedRow();
        DataBaseElement elementToReturn = null;

        String elementID = table.getValueAt(row, 0).toString();
        System.out.println(elementID);

        switch (classToTestFor) {
            case "Incoming":
                for (Order i : ElementListCollection.getOList()) {
                    if (i.getOrderID().equals(elementID)) {
                        elementToReturn = i;
                    }
                }
                break;
            case "Outgoing":
                for (Order i : ElementListCollection.getStockList()) {
                    if (i.getOrderID().equals(elementID)) {
                        elementToReturn = i;
                    }
                }
                break;
            case "Product":
                for (Product i : ElementListCollection.getPList()) {
                    if (i.getProductID() == (Integer.parseInt(elementID))) {
                        elementToReturn = i;
                    }
                }
                break;
            case "Customer":
                System.out.println("Customer not supported in getelementfromtable");
                break;
            default:
                System.out.println("error in getelementfromtable - wrong class");
                break;
        }
        return elementToReturn;
    }
    
    public String packageCustomerAddress(String address, String city, String zip, String country)
    {
        String divide = "§";
        String completeAddress = address + divide + city + divide + zip + divide + country;
        return completeAddress;
    }
    
    public String packageSupplier(String supplierName, String supplierEmail, String ownAddress, String ownCity, String ownZip, String ownCountry, String ownPhonenumber)
    {
        String divide = "§";
        String completeSupplier = supplierName + divide + supplierEmail + divide + ownAddress + divide + ownCity + divide + ownZip + divide + ownCountry + divide + ownPhonenumber;
        return completeSupplier;
    } 
    
    public String[] unpackage(String completeString)
    {
        String[] address = completeString.split("§");
        return address;
    }
    
        public void resetView(){
        Gui g = Gui.getGui();
        
        g.updateCustomerList();
        g.updateOrderList();
        g.updateProductList();
        g.updateStockOrderList();
    }
    
    
}
