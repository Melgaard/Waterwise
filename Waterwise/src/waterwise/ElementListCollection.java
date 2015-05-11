package waterwise;

import java.util.ArrayList;

/**
 *
 * @author BlottoG
 */
public class ElementListCollection {

    //Field
    private ArrayList<Product> pList;
    private ArrayList<Order> oList;
    private ArrayList<Customer> cList;

    //Getter
    public ArrayList<Product> getPList() {
        return this.pList;
    }

    public ArrayList<Order> getOList() {
        return this.oList;
    }

    public ArrayList<Customer> getCList() {
        return this.cList;
    }

}
