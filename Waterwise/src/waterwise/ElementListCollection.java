package waterwise;

import java.util.ArrayList;

/**
 *
 * @author BlottoG
 */
public class ElementListCollection {

    //Field
    private static ArrayList<Product> pList = new ArrayList<>();
    private static ArrayList<Order> oList = new ArrayList<>();
    private static ArrayList<Customer> cList = new ArrayList<>();
    private static ArrayList<Order> stockList = new ArrayList<>();

    //Setter
    public static void addPList(Product pToAdd) {
        pList.add(pToAdd);
    }
    
    public void addOrder(Order newOrder) {
        oList.add(newOrder);
    }

    //Getter
    public static ArrayList<Product> getPList() {
        return pList;
    }

    public static ArrayList<Order> getOList() {
        return oList;
    }

    public static ArrayList<Customer> getCList() {
        return cList;
    }

    public static ArrayList<Order> getStockList() {
        return stockList;
    }

    

}
