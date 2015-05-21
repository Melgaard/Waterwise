package waterwise;

public class Customer extends DataBaseElement {

    //Fields
    private int phoneNumber;
    private String customerEmail;
    private String customerName;
    private String deliveryAddress;
    private String deliveryCityAddress;
    private String deliveryZipAddress;
    private String deliveryCountryAddress;
    private String creationDate;

    

    @Override
    public void Update() {
        FileWrapper fw = new FileWrapper();
        try {
            fw.createCustomer(this);
        } catch (Exception ex) {
            System.out.println( ex + " thrown from - " + this.getClass().toString());
        }
    }
    
    //Constructor
    //To create an entirely new customer
    public Customer(int phone, String email, String name, String deliveryaddress,  
                     String city, String zip, String country) {
        
        
        this.phoneNumber = phone;
        this.customerEmail = email;
        this.customerName = name;
        this.deliveryAddress = deliveryaddress;
        this.deliveryCityAddress = city;
        this.deliveryZipAddress = zip;
        this.deliveryCountryAddress = country;
        
        this.Update();
    }
    //To create customer objects from database
    public Customer(int phone, String email, String name, String deliveryaddress,  
                     String city, String zip, String country, String creation) {
        
        this.phoneNumber = phone;
        this.customerEmail = email;
        this.customerName = name;
        this.deliveryAddress = deliveryaddress;
        this.deliveryCityAddress = city;
        this.deliveryZipAddress = zip;
        this.deliveryCountryAddress = country;
        creationDate = creation;
        
    }
    
    //Getters
    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getCreationDate() {
        return this.creationDate;
    }
    
    //Setters
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setCustomerEmail(String customerEmail)
    {
        this.customerEmail = customerEmail;
    }

    public void setDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setCreationDate(String creationDate)
    {
        this.creationDate = creationDate;
    }
}
