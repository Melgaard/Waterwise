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
        HashMap<Product, Integer> m = new HashMap<Product, Integer>();
        Order test1 = new Incoming("D8W8", "Startdate", "closeddate", m, "paymenttype", "deliverytype", "Uafsluttet", 26549878, false);
        Order test2 = new Incoming("KLI2", "Startdate", "closeddate", m, "paymenttype", "deliverytype", "Afsluttet", 96544585, false);
        
        Customer c = new Customer(25552552, "emn@lol.dk", "emillol","testadresselol", "string by test", "testzip","test country" );
       
        c.Update();
        
        e.addOrder(test1);
        e.addOrder(test2);
        
        e.addCustomer(c);
        
        e.addPList(h);
        e.addPList(p);
        Gui g = new Gui();
        Order o = new Outgoing();
    }
    
}
