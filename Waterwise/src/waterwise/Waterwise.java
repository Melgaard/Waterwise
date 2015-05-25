package waterwise;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BlottoG
 */
public class Waterwise {

    public static void main(String[] args) {
        HashMap<Product, Integer> m = new HashMap<>();
        FileWrapper fw = new FileWrapper();
//        try {
//            fw.deleteOrder("outgoingOrder", "ID", "hjkh");
//        } catch (SQLException ex) {
//            Logger.getLogger(Waterwise.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Outgoing order = new Outgoing("hjkh", null, null, m, "meh", "idk", "wut", "wat", "wet", "lut", "let", "lel", "lul", "mygpt", true);
        Gui g = new Gui();
        
        
    }

}
