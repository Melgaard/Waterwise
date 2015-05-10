package waterwise;

import java.util.Date;
import java.util.Map;

public class Order extends DataBaseElement {

    //Fields
    private int OrderID;
    private Date StartDate;
    private Date ClosedDate;
    private Map<Product, Integer> ListofProducts;
    private double PriceTotal;
    private String PaymentType;
    private String DeliveryType;
    private String OrderStatus;

    //Constructor
    public Order() {
        throw new UnsupportedOperationException();
    }

    //Methods
    private void CalculatePriceTotal() {
        throw new UnsupportedOperationException();
    }
    
    //Getters
    public int getOrderID() {
        return this.OrderID;
    }

    public Date getStartDate() {
        return this.StartDate;
    }

    public Date getClosedDate() {
        return this.ClosedDate;
    }

    public Map<Product, Integer> getListofProducts() {
        return this.ListofProducts;
    }

    public double getPriceTotal() {
        return this.PriceTotal;
    }

    public String getPaymentType() {
        return this.PaymentType;
    }

    public String getDeliveryType() {
        return this.DeliveryType;
    }

    public String getOrderStatus() {
        return this.OrderStatus;
    }

    
}
