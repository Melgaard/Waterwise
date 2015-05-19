package waterwise;

import java.util.Date;

public class Customer extends DataBaseElement {

    //Fields
    private int phoneNumber;
    private String customerEmail;
    private String deliveryAddress;
    private String customerName;
    private Date creationDate;

    

    @Override
    public void Update() {
//        FileWrapper.createCustomer(this);
    }
    
    //Constructor
    public Customer(int phone, String email, String deliveryaddress, String name) {
        
        phoneNumber = phone;
        customerEmail = email;
        deliveryAddress = deliveryaddress;
        customerName = name;
        
        this.Update();
    }
    
    public Customer(int phoneNumber, String email, String deliveryAddress, String name, Date creationDate)
	{
		this.setPhoneNumber(phoneNumber);
		this.setCustomerEmail(email);
		this.setDeliveryAddress(deliveryAddress);
		this.setCustomerName(name);
		this.setCreationDate(creationDate);
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

    public Date getCreationDate() {
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

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }
}
