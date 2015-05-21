package waterwise;

import java.util.Map;

public class Incoming extends Order {

    //Incoming specific fields
    private int customerPhonenumber;
    

    //Override of databaseelement method to call the correct update method
    @Override
    public void Update() {
//        FileWrapper.updateIncoming(this);
    }
    
    //Creates an order objects and a new database entry
    //          when confirm is pressed in the orderFrame 
    public Incoming() {

        OrderFrame tempOF = new OrderFrame(this);

        
    }
    
    //Creates an order object from the given parameters
    //The boolean determines whether to save it in the database
    //Therefore it should be false when called from the database
    public Incoming(String orderID, String startDate, String closedDate,
            Map<Product, Integer> productMap, String paymentType,
            String deliveryType, String orderStatus, int phonenumber, 
            boolean updateDB) {

        this.setOrderID(orderID);
        this.setStartDate(startDate);
        this.setClosedDate(closedDate);
        this.setListOfProducts(productMap);
        this.setPaymentType(paymentType);
        this.setDeliveryType(deliveryType);
        this.setOrderStatus(orderStatus);
        this.setCustomerPhonenumber(phonenumber);
        
         if(updateDB){
            this.Update();
        }
        
        
        
    }   
    
    
     //Setter
    public void setCustomerPhonenumber(int customerPhonenumber) {
        this.customerPhonenumber = customerPhonenumber;
    }

    //Getter
    public int getCustomerPhonenumber() {
        return customerPhonenumber;
    }
}
