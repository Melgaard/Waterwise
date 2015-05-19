package waterwise;

public class Product extends DataBaseElement
{

    //Fields
    private int productID;
    private String productName;
    private int amountInStorage;
    private double weight;
    private String size;
    private double unitPrice;
    private int reorderAmount;

    //Constructor
    public Product(String nameToBe)
    {
        productName = nameToBe;
    }

    Product(int ID, String name, int amount, double weight, String size, double unitPrice, int reOrderAmount)
    {
        this.productID = ID;
        this.productName = name;
        this.amountInStorage = amount;
        this.weight = weight;
        this.size = size;
        this.unitPrice = unitPrice;
        this.reorderAmount = reOrderAmount;
    }

    //Getters
    public int getProductID()
    {
        return this.productID;
    }

    public String getProductName()
    {
        return this.productName;
    }

    public int getAmountInStorage()
    {
        return this.amountInStorage;
    }

    public double getWeight()
    {
        return this.weight;
    }

    public String getSize()
    {
        return this.size;
    }

    public double getUnitPrice()
    {
        return this.unitPrice;
    }

    public int getReorderAmount()
    {
        return this.reorderAmount;
    }

    public void setReorderAmount(int reorderAmount)
    {
        this.reorderAmount = reorderAmount;
    }

}
