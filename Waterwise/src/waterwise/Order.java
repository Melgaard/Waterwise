package waterwise;

import java.util.HashMap;

public abstract class Order extends DataBaseElement {

    //Fields
    private String orderID;
    private String startDate;
    private String closedDate;
    private HashMap<Product, Integer> listOfProducts;
    private double priceTotal;
    private String paymentType;
    private String deliveryType;
    private String orderStatus;
    private int customerID;
    

    //Methods
    private double CalculatePriceTotal() {

        double sumPrice = 0.0;
        
        for (HashMap.Entry<Product, Integer> entrySet : listOfProducts.entrySet()) {
            Product key = entrySet.getKey();
            Integer value = entrySet.getValue();
            
            sumPrice = sumPrice + (key.getUnitPrice() * value);

        }

        return sumPrice;
    }
    public Order() {
        
    }
    public Order(HashMap listOfProducts, String startDate, String closeDate, double priceTotal, String paymentType, String deliveryType, String status, int CustomerID ) {
      this.orderID      = orderID;
      this.startDate    = startDate;
      this.closedDate   = closeDate;
      this.priceTotal   = priceTotal;
      this.paymentType  = paymentType;
      this.deliveryType = deliveryType;
      this.orderStatus  = status;
      
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
