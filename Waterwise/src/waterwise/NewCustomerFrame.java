package waterwise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
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
    JTextField customerIDField = new JTextField("", 5);    

    JLabel customerNameLabel = new JLabel("Navn:");
    JTextField customerNameField = new JTextField();

    JLabel customerPhoneLabel = new JLabel("Telefon:");
    JTextField customerPhoneField = new JTextField("");

    //VareName
    JPanel customerMailPanel = new JPanel();
    JLabel customerMailLabel = new JLabel("Email");
    JTextField customerMailField = new JTextField();
    
        //VarePris
    JPanel customerAddressPanel = new JPanel();
    JLabel customerAddressLabel = new JLabel("Adresse:");
    JTextField customerAddressField = new JTextField();

    //Buttons
    JPanel buttonPanel = new JPanel();
    JButton confirmJButton = new JButton("Opret kunde");
    JButton cancelJButton = new JButton("Annullér");


    //method that builds the frame and buttons
    private void frameBuild() {
        this.setTitle("WaterWise - Tilføj Kunde");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);

        //ProductArrayList
        //ScrollPane initialiseres
        updateProductList();

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
            customerPanel.add(customerPhoneLabel);
            customerPanel.add(customerPhoneField);
            customerPanel.add(customerMailLabel);
            customerPanel.add(customerMailField);
            customerPanel.add(customerAddressLabel);
            customerPanel.add(customerAddressField);
            customerPanel.add(confirmJButton);
            customerPanel.add(cancelJButton);
            confirmJButton.setPreferredSize(buttonDimension);
            cancelJButton.setPreferredSize(buttonDimension);
            
            // ActionListener for errorchecking and adding a customer to DB
//            confirmJButton.addActionListener(new ActionListener() {  
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                
//                Error er;
//                ErrorChecker eh = new ErrorChecker();
//                int appID = 0;
//                int appAmount = 0;
//                double appPrice = 0.0;
//                double appWeight = 0.0;
//                int appReorder = 0;
//                String tempID       = customerPhoneField.getText();
//                String tempName     = customerNameField.getText();
//                String tempPhone    = customerPhoneField.getText();
//                String tempEmails   = customerMailField.getText();
//                String tempAddress  = customerAddressField.getText();
//                
//                Product tempProduct = new Product(tempProductID, tempProductName, tempProductAmount, tempProductWeight,tempProductSize, tempProductPrice,tempProductReorder, "UpdateDB" );
//               
//                if (eh.isNameValid(tempProductName)) {                    
//                    if(eh.isPriceValid(tempProductPrice)) {
//                        appPrice = eh.StringToDouble(tempProduct.getTempPrice());
//                        if(eh.isAmountValid(tempProductAmount)) {
//                            appAmount = eh.StringToInt(tempProduct.getTempAmount());
//                            if(eh.isWeightValid(tempProductWeight)) {
//                                appWeight = eh.StringToDouble(tempProduct.getTempWeight()); 
//                                if(eh.isIDValid(tempProductID)) {
//                                    appID = eh.StringToInt(tempProduct.getTempID());
//                                    if(eh.isSizeValid(tempProductSize)) {                                        
//                                        if(eh.isProductReorderValid(tempProductReorder)) {
//                                            appReorder = eh.StringToInt(tempProduct.getTempReorder());
//                                            System.out.println("Alt godkendt - der kan konverteres");
//                                            Product appProduct = new Product(appID, tempProductName, appAmount, appWeight, tempProductSize, appPrice, appReorder, true);
//                                            System.out.println("Produkt konverteret - der kan nu skrives til DB");
//                                        } else {
//                                        System.out.println("Genbestil ikke godkendt " + tempProductReorder + " " );
//                                        er = new Error(tempProductReorder, "Genbestil");
//                                       // ef = new ErrorFrame(tempProductReorder, "Genbestil");
//                                        
//                                    }
//                                    } else {
//                                        System.out.println("Størrelse ikke godkendt " + tempProductSize + " " );
//                          //              ef = new ErrorFrame(tempProductSize, "Størrelse");
//                                    }
//                                } else {
//                                    System.out.println("ID ikke godkendt " + tempProductID + " " );
//                                    er = new Error(tempProductID, "ID");
//                                }
//                            } else {
//                                System.out.println("vægt ikke godkendt " + tempProductWeight + " " );
//                                er = new Error(tempProductWeight, "Vægt");
//                            }
//                        } else {
//                            System.out.println("antal ikke godkendt " + tempProductAmount );
//                            er = new Error(tempProductAmount, "Antal");
//                        }
//                    } else {
//                        System.out.println("pris ikke godkendt " + tempProductPrice );
//                        er = new Error(tempProductPrice, "Pris");
//                    }
//                } else {
//                    System.out.println("navn ikke godkendt " + tempProductPrice);
//                    er = new Error(tempProductName, "Navn");
//                }
//            }
//	});
//            

            //productPanelBounds
            customerNameLabel.setBounds(8, 30, 75, 15);
            customerNameField.setBounds(78, 28, 150, 18);
            customerPhoneLabel.setBounds(8, 60, 75, 15);
            customerPhoneField.setBounds(78, 58, 150, 18);
            customerMailLabel.setBounds(8, 90, 75, 15);
            customerMailField.setBounds(78, 88, 150, 18);
            customerAddressLabel.setBounds(8, 120, 75, 15);
            customerAddressField.setBounds(78, 118, 150, 18);
            confirmJButton.setBounds(25, 180, 120, 25);
            cancelJButton.setBounds(150, 180, 120, 25);

     
        ofPanel.setVisible(true);
        this.add(ofPanel);
        this.setVisible(true);
    }

    private void updateProductList() {
        DefaultTableModel chosenProductsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

       
    }

    
    public NewCustomerFrame() {

        frameBuild();

    }
}
