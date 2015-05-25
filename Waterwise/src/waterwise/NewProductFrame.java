package waterwise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
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
public class NewProductFrame extends JFrame {
    ErrorChecker eh;
    ErrorFrame ef;
    Listener listen;
    //Fields
    //this will be set to the order we are working on
    Order orderShown;
    //variable to determine wheter it is an incoming or outgoing order - 
    //true is incoming

    String orderID;
    String startDate;
    String closedDate;
    String deliveryType;
    String paymentType;
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
    HashMap<Product, Integer> listOfProducts;

    //Dimensions
    Dimension buttonDimension = new Dimension(100, 30);
    Dimension productPaneDimension = new Dimension(200, 120);
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

    private String tempProductPrice;
    //Vare
    JPanel productIDPanel = new JPanel();
    JLabel productIDLabel = new JLabel("Vare ID:");
    JTextField productIDField = new JTextField("1", 4);    

    JLabel productWeightLabel = new JLabel("Vægt:");
    JTextField productWeightField = new JTextField();

    JLabel productAmountLabel = new JLabel("Antal:");
    JTextField productAmountField = new JTextField("");

    
    JPanel productNamePanel = new JPanel();
    JLabel productNameLabel = new JLabel("Vare navn:");
    JTextField productNameField = new JTextField();
    
        //VarePris
    JPanel productPricePanel = new JPanel();
    JLabel productPriceLabel = new JLabel("Pris:");
    JTextField productPriceField = new JTextField();

    
   //ProductdropDown
    JPanel productSizePanel = new JPanel();
    JLabel productSizeLabel = new JLabel("Størrelse: ");
    ArrayList<String> productComboList = new ArrayList<>();
    String[] productarray = {"Standard", "Stor", "Lille"};
    JComboBox<String> productSize = new JComboBox<>(productarray);

        //VarePris
    JPanel reorderPanel = new JPanel();
    JLabel reorderLabel = new JLabel("Genbestil:");
    JTextField reorderField = new JTextField("1");

    //Buttons
    JPanel buttonPanel = new JPanel();
    JButton confirmJButton = new JButton("Opret Vare");
    JButton cancelJButton = new JButton("Annullér");
    

    //method that builds the frame and buttons
    private void frameBuild() {
        this.setTitle("WaterWise DB Project - OrderFrame");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 330);
        this.setLocationRelativeTo(null);

        //ProductArrayList


        //Panels
        ofPanel.setLayout(new BorderLayout());

        ofPanel.add(topPanel, BorderLayout.NORTH);
        ofPanel.add(middlePanel, BorderLayout.CENTER);
        ofPanel.add(productTablePanel, BorderLayout.SOUTH);

        //TopPanel
        topPanel.setLayout(new BorderLayout());
        topPanel.add(productIDPanel, BorderLayout.WEST);
        productIDPanel.add(productIDLabel);
        productIDPanel.add(productIDField);
       


        //MiddlePanel
        middlePanel.setLayout(new BorderLayout());

        //CustomerPanel
            

            middlePanel.add(customerPanel, BorderLayout.CENTER);
            customerPanel.setBorder(new TitledBorder("Vare Oplysninger"));
            customerPanel.setLayout(null);
            customerPanel.add(productNameLabel);
            customerPanel.add(productNameField);
            customerPanel.add(productPriceLabel);
            customerPanel.add(productPriceField);
            customerPanel.add(productAmountLabel);
            customerPanel.add(productAmountField);
            customerPanel.add(productWeightLabel);
            customerPanel.add(productWeightField);
            customerPanel.add(reorderLabel);
            customerPanel.add(reorderField);
            customerPanel.add(productSizeLabel);
            customerPanel.add(productSize);
            customerPanel.add(confirmJButton);
            customerPanel.add(cancelJButton);
            confirmJButton.setPreferredSize(buttonDimension);
            cancelJButton.setPreferredSize(buttonDimension);
//            confirmJButton.addActionListener(listen.new showErrorFrame());
            
            cancelJButton.addActionListener(new Listener().new DisposeFrameButton(this));
            
            confirmJButton.addActionListener(new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Error er;
                
                eh = new ErrorChecker();
                
                int appID = 0;
                int appAmount = 0;
                double appPrice = 0.0;
                double appWeight = 0.0;
                int appReorder = 0;
                String tempID = productIDField.getText();
                String tempName = productNameField.getText();
                String tempAmount = productAmountField.getText();
                String tempWeight = productWeightField.getText();
                String tempSize = productSize.getSelectedItem().toString();
                String tempPrice = productPriceField.getText();
                String tempReorder = reorderField.getText();               
               
               
                if (eh.isNameValid(tempName)) {                    
                    if(eh.isPriceValid(tempPrice)) {
                        appPrice = eh.StringToDouble(tempPrice);
                        if(eh.isAmountValid(tempAmount)) {
                            appAmount = eh.StringToInt(tempAmount);
                            if(eh.isWeightValid(tempWeight)) {
                                appWeight = eh.StringToDouble(tempWeight); 
                                if(eh.isProductIDValid(tempID)) {
                                    appID = eh.StringToInt(tempID);
                                    if(eh.isSizeValid(tempSize)) {                                        
                                        if(eh.isProductReorderValid(tempReorder)) {
                                            appReorder = eh.StringToInt(tempReorder);
                                            System.out.println("Alt godkendt - der kan konverteres");
                                            Product appProduct = new Product(appID, tempName, appAmount, appWeight, tempSize, appPrice, appReorder, true);
                                            System.out.println("Produkt konverteret - der kan nu skrives til DB");
                                            dispose();
                                        } else {er = new Error(tempReorder, "Genbestil");}
                                    } else { er = new Error(tempSize, "Størrelse"); }
                                } else { er = new Error(tempID, "Produkt ID"); }
                            } else { er = new Error(tempWeight, "Vægt"); }
                        } else { er = new Error(tempAmount, "Antal"); }
                    } else { er = new Error(tempPrice, "Produkt pris"); }
                } else { er = new Error(tempName, "Navn"); }
            
            
            }
            });

            //productPanelBounds
            productNameLabel.setBounds(8, 30, 75, 15);
            productNameField.setBounds(78, 28, 150, 18);
            productPriceLabel.setBounds(8, 60, 75, 15);
            productPriceField.setBounds(78, 58, 150, 18);
            productAmountLabel.setBounds(8, 90, 75, 15);
            productAmountField.setBounds(78, 88, 150, 18);
            productWeightLabel.setBounds(8, 120, 75, 15);
            productWeightField.setBounds(78, 118, 150, 18);
           reorderLabel.setBounds(8, 150, 75, 15);
            reorderField.setBounds(78, 148, 150, 18);
            productSizeLabel.setBounds(8, 178, 100, 25);
            productSize.setBounds(108, 178, 100, 25);
            confirmJButton.setBounds(25, 210, 100, 25);
            cancelJButton.setBounds(150, 210, 100, 25);


        ofPanel.setVisible(true);
        this.add(ofPanel);
        this.setVisible(true);
    }  

    public String getTempProductPrice() {
        return tempProductPrice;
    }

    public void setTempProductPrice(String tempProductPrice) {
        this.tempProductPrice = tempProductPrice;
    }

    
    public NewProductFrame() {

        frameBuild();

    }
}
