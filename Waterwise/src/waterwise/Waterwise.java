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
        
        e.addPList(h);
        e.addPList(p);
        Order o = new Outgoing();
        
    }
    
}
