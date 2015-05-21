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

    //Methods
    private double CalculatePriceTotal() {

        double sumPrice = 0.0;
        
        for (Map.Entry<Product, Integer> entrySet : listOfProducts.entrySet()) {
            Product key = entrySet.getKey();
            Integer value = entrySet.getValue();
            
            sumPrice = sumPrice + (key.getUnitPrice() * value);

        }

        return sumPrice;
    }

    //Setters
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public void setListOfProducts(Map<Product, Integer> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

}
