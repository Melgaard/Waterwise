package waterwise;

public class Outgoing extends Order {

    //OUtgoing specific fields
    private String Supplier;
    
    //Creates an order objects and a new database entry
    public void Outgoing() {
    
        FileWrapper.createEntry();
    }
    
    //Creates an order object but no new database entry (for loading from the database)
    public void Outgoing(int orderIDToLoad) {
    
        FileWrapper.loadEntry(orderIDToLoad);
    }
    
}
