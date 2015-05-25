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
        Order test1 = new Incoming("D8W8", "23-05-2015", "closeddate", m, "paymenttype", "deliverytype", "Uafsluttet", 26549878, false);
        Order test2 = new Incoming("KLI2", "13-03-2015", "closeddate", m, "paymenttype", "deliverytype", "Afsluttet", 96544585, false);
        Order test3 = new Outgoing("test3", "13-03-2015", null, m, null, null, "Afsluttet", null, null, null, null, null, null, null, false);
        Order test4 = new Outgoing("test4", "23-05-2015", null, m, null, null, "Uafsluttet", null, null, null, null, null, null, null, false);
        
        Customer c = new Customer(25552552, "emn@lol.dk", "emillol","testadresselol", "string by test", "testzip","test country" );
       
        c.Update();
        
        e.addOrder(test1);
        e.addOrder(test2);
        
        e.addStock(test4);
        e.addStock(test3);
        
        e.addCList(c);
        
        e.addPList(h);
        e.addPList(p);
        Gui.getGui();
        
    }
    
}
