package waterwise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BlottoG
 */
public class NewCustomerFrame extends JFrame {

    //Fields
    
    //variable to determine wheter it is an incoming or outgoing order - 
    //true is incoming

    String customerEmail;
    String deliveryAddress;
    String addressCity;
    String addressZip;
    String addressCountry;
    String customerName;
    int customerPhonenumber;

    String supplierName;
    String supplierEmail;
    String ownAddress;
    String ownCity;
    String ownZip;
    String ownCountry;
    int ownPhonenumber;

    String orderStatus;
    Map<Product, Integer> listOfProducts;

    //Dimensions
    Dimension buttonDimension = new Dimension(100, 30);
    Dimension productPaneDimension = new Dimension(0, 120);
    Dimension productTableDimension = new Dimension(200, 200);
    Dimension buttonPanelDimension = new Dimension(120, 100);

    //JPanels
    JPanel ofPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel customerPanel = new JPanel();
    JPanel customerPanelWEST = new JPanel();
    JPanel customerPanelEAST = new JPanel();
    JPanel stockOverviewPanel = new JPanel();
    JPanel productTablePanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel supplierPanel = new JPanel();


    // Panels og indhold
    JPanel customerIDPanel = new JPanel();
    JLabel customerIDLabel = new JLabel("KundeID: ");
    JTextField customerIDField = new JTextField("Ny kunde", 6);    

    JLabel customerNameLabel = new JLabel("Navn:");
    JTextField customerNameField = new JTextField();

    JLabel customerPhonenumberLabel = new JLabel("Telefon:");
    JTextField customerPhonenumberField = new JTextField("");

    //VareName
    JPanel customerEmailPanel = new JPanel();
    JLabel customerEmailLabel = new JLabel("Email");
    JTextField customerEmailField = new JTextField();
    
        //Address
    JPanel customerAddressPanel = new JPanel();
    JLabel customerAddressLabel = new JLabel("Vejnavn:");
    JTextField customerAddressField = new JTextField();
    
     JLabel cityLabel = new JLabel("By:");
    JTextField cityField = new JTextField();

    JLabel zipLabel = new JLabel("Postnr:");
    JTextField zipField = new JTextField();

    JLabel countryLabel = new JLabel("Land:");
    JTextField countryField = new JTextField();
    //Buttons
    JPanel buttonPanel = new JPanel();
    JButton confirmJButton = new JButton("Opret kunde");
    JButton cancelJButton = new JButton("Annullér");


    //method that builds the frame and buttons
    private void frameBuild() {
        this.setTitle("WaterWise - Tilføj Kunde");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 280);
        this.setLocationRelativeTo(null);

        //ProductArrayList
        //ScrollPane initialiseres
        

        //Panels
        ofPanel.setLayout(new BorderLayout());

        ofPanel.add(topPanel, BorderLayout.NORTH);
        ofPanel.add(middlePanel, BorderLayout.CENTER);
        ofPanel.add(productTablePanel, BorderLayout.SOUTH);

        //TopPanel
        topPanel.setLayout(new BorderLayout());
        topPanel.add(customerIDPanel, BorderLayout.WEST);
        customerIDPanel.add(customerIDLabel);
        customerIDPanel.add(customerIDField);
        customerIDField.setEditable(false);
        
        //MiddlePanel
        middlePanel.setLayout(new BorderLayout());

        //middlePanel.add(stockOverviewPanel, BorderLayout.SOUTH);

        //CustomerPanel           

                
            
    
            middlePanel.add(customerPanel, BorderLayout.CENTER);
            customerPanel.setBorder(new TitledBorder("Opret Kunde - Oplysninger"));
            customerPanel.setLayout(null);
            customerPanel.add(customerNameLabel);
            customerPanel.add(customerNameField);
            customerPanel.add(customerPhonenumberLabel);
            customerPanel.add(customerPhonenumberField);
            customerPanel.add(customerEmailLabel);
            customerPanel.add(customerEmailField);
            customerPanel.add(customerAddressLabel);
            customerPanel.add(customerAddressField);
            customerPanel.add(zipLabel);
            customerPanel.add(zipField);
            customerPanel.add(countryLabel);
            customerPanel.add(countryField);
            customerPanel.add(cityLabel);
            customerPanel.add(cityField);
            customerPanel.add(confirmJButton);
            customerPanel.add(cancelJButton);
            confirmJButton.setPreferredSize(buttonDimension);
            cancelJButton.setPreferredSize(buttonDimension);
            
            // ActionListener for errorchecking and adding a customer to DB
            
            cancelJButton.addActionListener(new Listener().new DisposeFrameButton(this));
            
            confirmJButton.addActionListener(new Listener().new confirmCustomerButton(this));
            
            
            
            

            //productPanelBounds
            customerNameLabel.setBounds(8, 30, 75, 15);
            customerNameField.setBounds(78, 28, 150, 30);
            customerEmailLabel.setBounds(8, 60, 75, 15);
            customerEmailField.setBounds(78, 58, 150, 30);
            customerPhonenumberLabel.setBounds(260, 30, 75, 10);
            customerPhonenumberField.setBounds(318, 28, 150, 30);
            customerAddressLabel.setBounds(8, 90, 75, 15);
            customerAddressField.setBounds(78, 88, 150, 30);
            cityLabel.setBounds(8, 120, 75, 15);
            cityField.setBounds(78, 118, 150, 30);
            zipLabel.setBounds(260, 90, 75, 15);
            zipField.setBounds(318, 88, 150, 30);
            countryLabel.setBounds(260, 120, 75, 15);
            countryField.setBounds(318, 118, 150, 30);
            confirmJButton.setBounds(100, 158, 120, 25);
            cancelJButton.setBounds(250, 158, 120, 25);


     
        ofPanel.setVisible(true);
        this.add(ofPanel);
        this.setVisible(true);
    }

     
        public void setTextCustomer(Customer c) {
                ErrorChecker ec = new ErrorChecker();
                
                customerIDField.setText(c.getPhoneNumber() + "");
                
                customerNameField.setText(c.getCustomerName());
                customerPhonenumberField.setText(c.getPhoneNumber() + "");
                customerEmailField.setText(c.getCustomerEmail());
                customerAddressField.setText(c.getDeliveryAddress());
                zipField.setText(c.getDeliveryZipAddress());
                countryField.setText(c.getDeliveryCountryAddress());
                cityField.setText(c.getDeliveryCityAddress());
                

       }
    
    public NewCustomerFrame() {

        frameBuild();

    }
}
