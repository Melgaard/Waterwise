package waterwise;

public class Outgoing extends Order {

    //Outgoing specific fields
    private String supplierName;
    private String supplierEmail;
    private String ownAddress;
    private String ownCity;
    private String ownZip;
    private String ownCountry;
    private String ownPhonenumber;

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
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public void setOwnAddress(String ownAddress) {
        this.ownAddress = ownAddress;
    }

    public void setOwnCity(String ownCity) {
        this.ownCity = ownCity;
    }

    public void setOwnZip(String ownZip) {
        this.ownZip = ownZip;
    }

    public void setOwnCountry(String ownCountry) {
        this.ownCountry = ownCountry;
    }

    public void setOwnPhonenumber(String ownPhonenumber) {
        this.ownPhonenumber = ownPhonenumber;
    }

    //Getter
    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public String getOwnAddress() {
        return ownAddress;
    }

    public String getOwnCity() {
        return ownCity;
    }

    public String getOwnZip() {
        return ownZip;
    }

    public String getOwnCountry() {
        return ownCountry;
    }

    public String getOwnPhonenumber() {
        return ownPhonenumber;
    }

}
