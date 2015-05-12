package waterwise;

import java.util.Map;

public abstract class Order extends DataBaseElement {

    //Fields
    private String orderID;
    private String startDate;
    private String closedDate;
    private Map<Product, Integer> listOfProducts;
    private double priceTotal;
    private String paymentType;
    private String deliveryType;
    private String orderStatus;
    private String customerEmail;

    //Methods
    private void CalculatePriceTotal() {
        throw new UnsupportedOperationException();
    }

    //Getters
    public String getOrderID() {
        return this.orderID;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getClosedDate() {
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

    public Map<Product, Integer> getListOfProducts() {
        return listOfProducts;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

}
