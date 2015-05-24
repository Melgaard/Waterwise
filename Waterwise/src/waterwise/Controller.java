package waterwise;

import javax.swing.JTable;

public class Controller {

    public void saveEditMethod(OrderFrame ofSaveFrom) {
        ErrorChecker ec = new ErrorChecker();
        Order orderSaveTo = ofSaveFrom.orderShown;

        //Check for error in typed data
        System.out.println("something needs doing! - SaveEditButton");
        if(ec.isIDValid(ofSaveFrom.orderIDField.getText())){
            if(!ofSaveFrom.listOfProducts.isEmpty()){
                if(ec.isDeliveryValid(ofSaveFrom.deliveryTypeField.getText())){
                    if(ec.isDeliveryValid(ofSaveFrom.paymentTypeField.getText())){
                    System.out.println("SaveEditMethod har godkendt");
          

                        //Set order data to the orderframe data
                        orderSaveTo.setOrderID(ofSaveFrom.orderIDField.getText());
                        orderSaveTo.setListOfProducts(ofSaveFrom.listOfProducts);
                        orderSaveTo.setDeliveryType(ofSaveFrom.deliveryTypeField.getText());
                        orderSaveTo.setPaymentType(ofSaveFrom.paymentTypeField.getText());

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
                    } else { System.out.println("fejl i paymenttype"); }
                } else { System.out.println("fejl i deliverytype"); }
            } else { System.out.println("fejl i listofproducts"); }    
        } else { System.out.println("fejl i orderField"); }        
            
        
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
}
