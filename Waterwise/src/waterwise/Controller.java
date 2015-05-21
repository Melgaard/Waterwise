package waterwise;

public class Controller {

    public void saveEditMethod(OrderFrame ofSaveFrom) {

        Order orderSaveTo = ofSaveFrom.orderShown;

        //Check for error in typed data
        System.out.println("something needs doing! - SaveEditButton");

        //Set order data to the orderframe data
        orderSaveTo.setOrderID(ofSaveFrom.orderIDField.getText());
        orderSaveTo.setListOfProducts(ofSaveFrom.listOfProducts);
        orderSaveTo.setDeliveryType(ofSaveFrom.deliveryTypeField.getText());
        orderSaveTo.setPaymentType(ofSaveFrom.paymentTypeField.getText());

        if (orderSaveTo instanceof Outgoing) {
            saveEditOutgoing(ofSaveFrom,(Outgoing) orderSaveTo);
        } else if (orderSaveTo instanceof Incoming) {
            saveEditIncoming(ofSaveFrom,(Incoming) orderSaveTo);
            
            int phone = Integer.parseInt(ofSaveFrom.customerPhonenumberField.getText());
            String email = ofSaveFrom.customerEmailField.getText();
            String name = ofSaveFrom.customerNameField.getText();
            String address = ofSaveFrom.deliveryAddressField.getText();
            String city = ofSaveFrom.deliveryAddressCityField.getText();
            String zip = ofSaveFrom.deliveryAddressZipField.getText();
            String country = ofSaveFrom.deliveryAddressCountryField.getText();
            
            Customer customerOfOrder = new Customer(phone, email, name, address, city, zip, country );
            
            customerOfOrder.Update();
            
        }

        orderSaveTo.Update();
        ofSaveFrom.dispose();
    }
    
    private void saveEditOutgoing(OrderFrame ofSaveFrom, Outgoing orderSaveTo){
        
        orderSaveTo.setSupplierName(ofSaveFrom.supplierNameField.getText());
        orderSaveTo.setSupplierEmail(ofSaveFrom.supplierEmailField.getText());
        orderSaveTo.setOwnAddress(ofSaveFrom.ownAddressField.getText());
        orderSaveTo.setOwnCity(ofSaveFrom.ownCityField.getText());
        orderSaveTo.setOwnZip(ofSaveFrom.ownZipField.getText());
        orderSaveTo.setOwnCountry(ofSaveFrom.ownCountryField.getText());
        orderSaveTo.setOwnPhonenumber(ofSaveFrom.ownPhonenumberField.getText());
    }
    
    private void saveEditIncoming(OrderFrame ofSaveFrom, Incoming orderSaveTo)
    {
        
        orderSaveTo.setCustomerPhonenumber(Integer.parseInt(ofSaveFrom.customerPhonenumberField.getText()));
    }

}
