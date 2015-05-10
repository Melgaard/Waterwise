package waterwise;

import java.util.Date;

public class Customer extends DataBaseElement {

    //Fields
    private String customerEmail;
    private String deliveryAddress;
    private String customerName;
    private String phonenumber;
    private Date creationDate;

    //Constructor
    public Customer() {
        throw new UnsupportedOperationException();
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
