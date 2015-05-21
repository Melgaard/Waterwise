package waterwise;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author BlottoG
 */
public class Waterwise {
    
    public static void main(String[] args) {

//        Gui g = new Gui();
        ElementListCollection e = new ElementListCollection();
        Product p = new Product("akklad");
        Product h = new Product("emil");
        Map<Product, Integer> m = new HashMap<Product, Integer>();
        Order test1 = new Incoming("D8W8", "Startdate", "closeddate", 
                m, "paymenttype", "deliverytype", "Uafsluttet", 26549878, false);
       

        e.addOrder(test1);
        e.addOrder(test1);
        e.addOrder(test1);
        e.addOrder(test1);
        e.addOrder(test1);
        e.addOrder(test1);
        
        e.addPList(h);
        e.addPList(p);
        Gui g = new Gui();
    }
    
}
