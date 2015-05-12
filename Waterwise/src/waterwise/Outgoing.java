package waterwise;

public class Outgoing extends Order {

    //Outgoing specific fields
    private String Supplier;

    //Creates an order objects and a new database entry
    public Outgoing() {

        OrderFrame tempOF = new OrderFrame(this);
        
    }
    
    //Creates an order object from the given parameters, this should be called automatically only
    //Parameters still not correct
    public Outgoing(String orderID, String paymentType) {

        throw new UnsupportedOperationException();
        
        
    }

    //Creates an order object but no new database entry (for loading from the database)
    public Outgoing(int orderIDToLoad) {

        FileWrapper.loadEntry(orderIDToLoad);
    }

}
