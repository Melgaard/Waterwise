package waterwise;

public class Incoming extends Order {

    //Incoming specific fields
    private String customerPhonenumber;

    //Override of databaseelement method to call the correct update method
    @Override
    public void Update() {
//        FileWrapper.updateIncoming(this);
    }
    
    //Creates an order objects and a new database entry
    public Incoming() {

        OrderFrame tempOF = new OrderFrame(this);

        
    }
    
    //Creates an order object from the given parameters, this should be called automatically only
    //Parameters still not correct
    public Incoming(String orderID, String paymentType) {

        this.setOrderID(orderID);
        this.setPaymentType(paymentType);
        
        
    }

    //Creates an order object but no new database entry (for loading from the database)
    public Incoming(int orderIDToLoad) {

//        FileWrapper.loadEntry(orderIDToLoad);

    }

    
     //Setter
    public void setCustomerPhonenumber(String customerPhonenumber) {
        this.customerPhonenumber = customerPhonenumber;
    }

    //Getter
    public String getCustomerPhonenumber() {
        return customerPhonenumber;
    }
}
