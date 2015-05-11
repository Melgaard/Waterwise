package waterwise;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Gui extends JFrame {
    
    final static String ROOT = "ROOT";
    final static String ORDRER = "ORDRER";
    final static String LAGER = "LAGER";
    final static String KUNDER = "KUNDER";
    final static String INDSTILLINGER = "INDSTILLINGER";
    final static int extraWindowWidth = 200;

    //CARDS
    JPanel cardRoot = new JPanel(); // Root
    JPanel cardOrder = new JPanel(); // Ordrer
    JPanel cardProducts = new JPanel(); // Lager
    JPanel cardCustomers = new JPanel(); // Kunder
    JPanel cardSettings = new JPanel(); // Indstillinger
    

    public Gui() {

        setSize(1000, 700);
        setTitle("WaterWise DB Project");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        this.addComponentToPane();
        setVisible(true);

    }

    private void addComponentToPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab(ROOT, cardRoot);
        tabbedPane.addTab(ORDRER, cardOrder);
        tabbedPane.addTab(LAGER, cardProducts);
        tabbedPane.addTab(KUNDER, cardCustomers);
        tabbedPane.addTab(INDSTILLINGER, cardSettings);

        this.add(tabbedPane, BorderLayout.CENTER);
    }

}