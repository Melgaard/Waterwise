package waterwise;

public class Incoming extends Order {

    //Incoming specific fields
    private String CustomerEmail;
    
    //Creates an order objects and a new database entry
    public void Incoming() {
    
        FileWrapper.createEntry();
    }
    
    //Creates an order object but no new database entry (for loading from the database)
    public void Incoming(int orderIDToLoad) {
    
    }
    
    
}
