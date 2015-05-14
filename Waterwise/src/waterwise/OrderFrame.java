
package waterwise;

import java.awt.FlowLayout;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author BlottoG
 */
public class OrderFrame extends JFrame{

    //Fields
    //this will be set to the order we are working on
    Order orderShown;
    
    String orderID;
    String deliveryType;
    String paymentType;    
    String customerEmail;
    String deliveryAddress;
    String customerName;
    String customerPhonenumber;
    
    //These two not done yet (not in actionlistener either
    String orderStatus;
    Map<Product, Integer> listOfProducts;
    
    
    JPanel ofPanel = new JPanel();
    
    JPanel orderIDPanel = new JPanel();
    JLabel orderIDLabel = new JLabel("OrdreID: ");
    JTextField orderIDField = new JTextField("", 15);
    
    JPanel deliveryTypePanel = new JPanel();    
    JLabel deliveryTypeLabel = new JLabel("Leveringstype: ");
    JTextField deliveryTypeField = new JTextField("", 15);
    
    JPanel paymentTypePanel = new JPanel();
    JLabel paymentTypeLabel = new JLabel("Betalingsmåde: ");
    JTextField paymentTypeField = new JTextField("", 15);
    
    JPanel customerEmailPanel = new JPanel();
    JLabel customerEmailLabel = new JLabel("Kundens Email: ");
    JTextField customerEmailField = new JTextField("", 15);
    
    JPanel deliveryAddressPanel = new JPanel();
    JLabel deliveryAddressLabel = new JLabel("Kundens adresse: ");
    JTextField deliveryAddressField = new JTextField("", 15);
    
    JPanel customerNamePanel = new JPanel();
    JLabel customerNameLabel = new JLabel("Kundens navn: ");
    JTextField customerNameField = new JTextField("", 15);
    
    JPanel customerPhonenumberPanel = new JPanel();
    JLabel customerPhonenumberLabel = new JLabel("Kundens telefon: ");
    JTextField customerPhonenumberField = new JTextField("", 15);
    
    
    //Block code that is run before the constructor
    {
        this.setTitle("WaterWise DB Project - OrderFrame");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        
        
        ofPanel.setLayout(new FlowLayout());
        
        
        
        orderIDField.setEditable(true);
        orderIDPanel.add(orderIDLabel);
        orderIDPanel.add(orderIDField);        
        
        deliveryTypeField.setEditable(true);
        deliveryTypePanel.add(customerEmailLabel);
        deliveryTypePanel.add(customerEmailField);        
        
        paymentTypeField.setEditable(true);
        paymentTypePanel.add(customerEmailLabel);
        paymentTypePanel.add(customerEmailField);
        
        customerEmailField.setEditable(true);
        customerEmailPanel.add(customerEmailLabel);
        customerEmailPanel.add(customerEmailField);
        
        deliveryAddressField.setEditable(true);
        deliveryAddressPanel.add(customerEmailLabel);
        deliveryAddressPanel.add(customerEmailField);
        
        customerNameField.setEditable(true);
        customerNamePanel.add(customerEmailLabel);
        customerNamePanel.add(customerEmailField);
        
        customerPhonenumberField.setEditable(true);
        customerPhonenumberPanel.add(customerEmailLabel);
        customerPhonenumberPanel.add(customerEmailField);
        
        
        
        ofPanel.add(orderIDPanel);
        
        
        ofPanel.add(deliveryTypePanel);
        ofPanel.add(paymentTypePanel);
        
        
        ofPanel.add(customerEmailPanel);
        
        
        ofPanel.add(deliveryAddressPanel);
        ofPanel.add(customerNamePanel);
        ofPanel.add(customerPhonenumberPanel);
        
        
        ofPanel.setVisible(true);
        this.add(ofPanel);
        this.setVisible(true);   
    }
    
    public OrderFrame(Order orderToShow){
        
        orderShown = orderToShow;
        
        
    }
    
}
