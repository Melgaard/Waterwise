package waterwise;

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
        
        Order test1 = new Incoming("D8W8", "DANKORT LOL KAPPA AKKLAD");
       

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
