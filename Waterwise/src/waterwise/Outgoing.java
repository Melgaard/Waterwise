package waterwise;

public class Outgoing extends Order {

    

    //Outgoing specific fields
    
    private String supplier;
    
    //Override of databaseelement method to call the correct update method
    @Override
    public void Update() {
//        FileWrapper.updateOutgoing(this);
    }

    //Creates an order objects and a new database entry
    public Outgoing() {

        OrderFrame tempOF = new OrderFrame(this);
        this.setStartDate(this.getDateTime());
    }
    
    //Creates an order object from the given parameters, this should be called automatically only
    //Parameters still not correct
    public Outgoing(String orderID, String paymentType) {

        throw new UnsupportedOperationException();
        
        
    }

    //Creates an order object but no new database entry (for loading from the database)
    public Outgoing(int orderIDToLoad) {

//        FileWrapper.loadEntry(orderIDToLoad);
    }
    
    //Setter
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    //Getter
    public String getSupplier() {
        return supplier;
    }
    
}
