package waterwise;

import java.util.HashMap;

public abstract class Order extends DataBaseElement {

    //Fields
    private String orderID;
    private String startDate;
    private String closedDate;
    private HashMap<Product, Integer> listOfProducts = new HashMap<>();
    private double priceTotal;
    private String paymentType;
    private String deliveryType;
    private String orderStatus;
    private int customerID;
    

    //Methods
    public void CalculatePriceTotal() {

        double sumPrice = 0.0;
        
        for (Product key : listOfProducts.keySet()) {
            
            Integer value = listOfProducts.get(key);
            
            sumPrice = sumPrice + (key.getUnitPrice() * value);

        }

        priceTotal = sumPrice;
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

    public void setListOfProducts(HashMap<Product, Integer> listOfProducts) {
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

    public HashMap<Product, Integer> getListOfProducts() {
        return this.listOfProducts;
    }

}
