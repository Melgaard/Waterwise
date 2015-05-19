package waterwise;

import java.util.Date;

public class Customer extends DataBaseElement {

    //Fields
    private String phonenumber;
    private String customerEmail;
    private String deliveryAddress;
    private String customerName;
    private Date creationDate;

    @Override
    public void Update() {
//        FileWrapper.createCustomer(this);
    }
    
    //Constructor
    public Customer(String phone, String email, String deliveryaddress, String name) {
        
        phonenumber = phone;
        customerEmail = email;
        deliveryAddress = deliveryaddress;
        customerName = name;
        
        this.Update();
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

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

}
