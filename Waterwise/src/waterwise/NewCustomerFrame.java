package waterwise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

    //Supplier
    JLabel supplierNameLabel = new JLabel("Supplier:");
    JTextField supplierNameField = new JTextField();

    JLabel supplierEmailLabel = new JLabel("Email:");
    JTextField supplierEmailField = new JTextField();

    JLabel ownAddressLabel = new JLabel("Addresse:");
    JTextField ownAddressField = new JTextField();

    JLabel ownCityLabel = new JLabel("By:");
    JTextField ownCityField = new JTextField();

    JLabel ownZipLabel = new JLabel("Postnr:");
    JTextField ownZipField = new JTextField();

    JLabel ownCountryLabel = new JLabel("Land:");
    JTextField ownCountryField = new JTextField();

    JLabel ownPhonenumberLabel = new JLabel("Telefon:");
    JTextField ownPhonenumberField = new JTextField();

    //ScrollPane
    JTable productTable = new JTable();
    JScrollPane productTableScrollPane = new JScrollPane(productTable);
    ArrayList<String> chosenProducts = new ArrayList<>(); // Hvad gør vi her?

    //VareID 
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



    //ProductdropDown

    ArrayList<String> productComboList = new ArrayList<>();
    String[] productarray = {" "};



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
            customerPanel.setBorder(new TitledBorder("Kunde oplysninger"));
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

        

//            middlePanel.add(supplierPanel);
//            supplierPanel.setBorder(new TitledBorder("SupplierOplysninger"));
//            supplierPanel.setLayout(null);
//            supplierPanel.add(supplierNameLabel);
//            supplierPanel.add(supplierNameField);
//            supplierPanel.add(supplierEmailLabel);
//            supplierPanel.add(supplierEmailField);
//            supplierPanel.add(ownPhonenumberLabel);
//            supplierPanel.add(ownPhonenumberField);
//            supplierPanel.add(ownAddressLabel);
//            supplierPanel.add(ownAddressField);
//            supplierPanel.add(ownCityLabel);
//            supplierPanel.add(ownCityField);
//            supplierPanel.add(ownZipLabel);
//            supplierPanel.add(ownZipField);
//            supplierPanel.add(ownCountryLabel);
//            supplierPanel.add(ownCountryField);
//
//            //CustomerPanelBounds
//            supplierNameLabel.setBounds(8, 30, 75, 15);
//            supplierNameField.setBounds(78, 28, 150, 18);
//            supplierEmailLabel.setBounds(8, 60, 75, 15);
//            supplierEmailField.setBounds(78, 58, 150, 18);
//            ownPhonenumberLabel.setBounds(8, 90, 75, 15);
//            ownPhonenumberField.setBounds(78, 88, 150, 18);
//            ownAddressLabel.setBounds(260, 30, 75, 10);
//            ownAddressField.setBounds(318, 28, 150, 18);
//            ownCityLabel.setBounds(260, 60, 75, 15);
//            ownCityField.setBounds(318, 58, 150, 18);
//            ownZipLabel.setBounds(260, 90, 75, 15);
//            ownZipField.setBounds(318, 88, 150, 18);
//            ownCountryLabel.setBounds(260, 120, 75, 15);
//            ownCountryField.setBounds(318, 118, 150, 18);

        

        //ProductPanel
//        stockOverviewPanel.setPreferredSize(productPaneDimension);
//        stockOverviewPanel.setBorder(new TitledBorder("Lager Oversigt"));
//        stockOverviewPanel.setLayout(new BorderLayout());
//        JLabel meh = new JLabel("FUUCK");
//        stockOverviewPanel.add(meh, BorderLayout.SOUTH);
//        stockOverviewPanel.add(productTableScrollPane, BorderLayout.CENTER);
//        productTableScrollPane.setPreferredSize(productTableDimension);
//
//        bottomPanel.add(buttonPanel, BorderLayout.EAST);
//        buttonPanel.setLayout(new FlowLayout());
//        buttonPanel.add(confirmJButton);
//        buttonPanel.add(cancelJButton);
//        buttonPanel.setPreferredSize(buttonPanelDimension);
//        confirmJButton.setPreferredSize(buttonDimension);
//
//        cancelJButton.setPreferredSize(buttonDimension);

//        confirmJButton.addActionListener(new Listener().new SaveEditButton(this));
//        cancelJButton.addActionListener(new Listener().new DisposeFrameButton(this));
//
//
//        for (Product temp : ElementListCollection.getPList()) {
//            productComboList.add(temp.getProductName());
//        }
//        productbox.setModel(new DefaultComboBoxModel(productComboList.toArray()));
//
//        stockOverviewPanel.add(productLabel);
//        stockOverviewPanel.add(productbox);
//
//        stockOverviewPanel.add(amountLabel);
//        stockOverviewPanel.add(amountField);
//        stockOverviewPanel.add(addButton);
//        addButton.setPreferredSize(buttonDimension);

//        //BottomPanel
//        ofPanel.add(bottomPanel, BorderLayout.CENTER);
//        bottomPanel.setLayout(new BorderLayout());
//        bottomPanel.add(productTableScrollPane, BorderLayout.CENTER);
//        productTableScrollPane.setPreferredSize(productTableDimension);
//
//        bottomPanel.add(buttonPanel, BorderLayout.EAST);
//        buttonPanel.setLayout(new FlowLayout());
//        buttonPanel.add(confirmJButton);
//        buttonPanel.add(cancelJButton);
//        buttonPanel.setPreferredSize(buttonPanelDimension);
//        confirmJButton.setPreferredSize(buttonDimension);
//
//        cancelJButton.setPreferredSize(buttonDimension);
//
//        confirmJButton.addActionListener(new Listener().new SaveEditButton(this));
//        cancelJButton.addActionListener(new Listener().new DisposeFrameButton(this));

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

        productTable.setAutoCreateRowSorter(true);

        chosenProductsTableModel.setColumnIdentifiers(new String[]{"ProduktID", "ProduktNavn", "Antal", "Pris"});
        chosenProductsTableModel.setRowCount(200);

        int row = 0;
        for (String products : chosenProducts) {
            chosenProductsTableModel.setValueAt(productComboList.get(row), row, 0);
//            chosenProductsTableModel.setValueAt(products.getStartDate(), row, 1);
//            chosenProductsTableModel.setValueAt(products.getClosedDate(), row, 2);
//            chosenProductsTableModel.setValueAt(products.getPaymentType(), row, 3);

            row++;
        }

        productTable.setModel(chosenProductsTableModel);
    }

    
    public NewCustomerFrame() {

        frameBuild();

    }
}
