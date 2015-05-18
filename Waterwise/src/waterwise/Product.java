package waterwise;

import java.util.Date;

public class Product extends DataBaseElement {

    //Fields
    private int productID;
    private String productName;
    private int amountInStorage;
    private double weight;
    private int size;
    private double unitPrice;
    private Date reorderDeadline;

    //Constructor
    public Product(String nameToBe) {
        productName = nameToBe;
    }

    //Getters

    public int getProductID() {
        return this.productID;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getAmountInStorage() {
        return this.amountInStorage;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getSize() {
        return this.size;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public Date getReorderDeadline() {
        return this.reorderDeadline;
    }

}
