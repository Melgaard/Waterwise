
package waterwise;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
    
    JPanel statusPanel = new JPanel();
    JLabel statusLabel = new JLabel("Status:");
    String[] statusdrop = { "Afsluttet", "Uafsluttet"};
    JComboBox statusmenu = new JComboBox(statusdrop);
    
    JPanel productListPanel = new JPanel();
    JLabel productLabel = new JLabel("produkter:");
    ArrayList<String> productComboList = new ArrayList<>();
    String[] productarray = {" "};
    JComboBox<String> productbox = new JComboBox<String>(productarray);
    
    JPanel buttonPanel = new JPanel();
    JButton confirmJButton = new JButton();
    JButton cancelJButton = new JButton();
    
        
    
    //method that builds the frame and buttons
    private void frameBuild(){
        this.setTitle("WaterWise DB Project - OrderFrame");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        
        
        
        
        ofPanel.setLayout(new GridLayout(9, 1));
        
        
        
        orderIDField.setEditable(true);
        orderIDPanel.add(orderIDLabel);
        orderIDPanel.add(orderIDField);        
        
        deliveryTypeField.setEditable(true);
        deliveryTypePanel.add(deliveryTypeLabel);
        deliveryTypePanel.add(deliveryTypeField);        
        
        paymentTypeField.setEditable(true);
        paymentTypePanel.add(paymentTypeLabel);
        paymentTypePanel.add(paymentTypeField);
        
        customerEmailField.setEditable(true);
        customerEmailPanel.add(customerEmailLabel);
        customerEmailPanel.add(customerEmailField);
        
        deliveryAddressField.setEditable(true);
        deliveryAddressPanel.add(deliveryAddressLabel);
        deliveryAddressPanel.add(deliveryAddressField);
        
        customerNameField.setEditable(true);
        customerNamePanel.add(customerNameLabel);
        customerNamePanel.add(customerNameField);
        
        customerPhonenumberField.setEditable(true);
        customerPhonenumberPanel.add(customerPhonenumberLabel);
        customerPhonenumberPanel.add(customerPhonenumberField);
        
        statusPanel.add(statusLabel);
        statusPanel.add(statusmenu);
        
        for(Product temp : ElementListCollection.getPList()){
            productComboList.add(temp.getProductName());
        }
        productbox.setModel(new DefaultComboBoxModel(productComboList.toArray()));
        
        productListPanel.add(productLabel);
        productListPanel.add(productbox);
        
        confirmJButton.setAction(new Listener().new SaveEditButton(this));
        cancelJButton.setAction(new Listener().new DisposeFrameButton(this));
        confirmJButton.setText("Bekræft");
        cancelJButton.setText("Annuller");
        confirmJButton.setPreferredSize(new Dimension(100, 30));
        cancelJButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(cancelJButton);
        buttonPanel.add(confirmJButton);
        
        
        ofPanel.add(orderIDPanel);        
        ofPanel.add(deliveryTypePanel);
        ofPanel.add(paymentTypePanel);       
        ofPanel.add(customerEmailPanel);
        ofPanel.add(deliveryAddressPanel);
        ofPanel.add(customerNamePanel);
        ofPanel.add(customerPhonenumberPanel);
        
        ofPanel.add(statusPanel);
        ofPanel.add(productListPanel);
        ofPanel.add(buttonPanel);
        
        ofPanel.setVisible(true);
        this.add(ofPanel);
        this.setVisible(true);   
    }
    
    public OrderFrame(Order orderToShow){
        
        orderShown = orderToShow;
        frameBuild();
        
    }
    
}
