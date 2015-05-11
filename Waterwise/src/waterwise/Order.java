package waterwise;

import java.util.Date;
import java.util.Map;

public abstract class Order extends DataBaseElement {

    //Fields
    private int orderID;
    private Date startDate;
    private Date closedDate;
    private Map<Product, Integer> listOfProducts;
    private double priceTotal;
    private String paymentType;
    private String deliveryType;
    private String orderStatus;

    
    //Methods
    private void CalculatePriceTotal() {
        throw new UnsupportedOperationException();
    }
    
    //Getters
    public int getOrderID() {
        return this.orderID;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getClosedDate() {
        return this.closedDate;
    }

    public Map<Product, Integer> getListofProducts() {
        return this.listOfProducts;
    }

    public double getPriceTotal() {
        return this.priceTotal;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public String getDeliveryType() {
        return this.deliveryType;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    
}
