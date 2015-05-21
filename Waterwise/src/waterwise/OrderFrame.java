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
public class OrderFrame extends JFrame {

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
    JPanel productPanel = new JPanel();
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

    //OrdreID
    JPanel orderIDPanel = new JPanel();
    JLabel orderIDLabel = new JLabel("OrdreID:");
    JTextField orderIDField = new JTextField("", 5);

    //Delivery
    JPanel deliveryTypePanel = new JPanel();
    JLabel deliveryTypeLabel = new JLabel("Lev.type:");
    JTextField deliveryTypeField = new JTextField("", 15);

    //Paymenttype
    JPanel paymentTypePanel = new JPanel();
    JLabel paymentTypeLabel = new JLabel("Btl.type:");
    JTextField paymentTypeField = new JTextField();

    //Customer Email
    JPanel customerEmailPanel = new JPanel();
    JLabel customerEmailLabel = new JLabel("Email:");
    JTextField customerEmailField = new JTextField();

    //Delivery address
    JPanel deliveryAddressPanel = new JPanel();
    JLabel deliveryAddressLabel = new JLabel("Adresse:");
    JTextField deliveryAddressField = new JTextField();

    JLabel deliveryAddressCityLabel = new JLabel("By:");
    JTextField deliveryAddressCityField = new JTextField();

    JLabel deliveryAddressZipLabel = new JLabel("Postnr:");
    JTextField deliveryAddressZipField = new JTextField();

    JLabel deliveryAddressCountryLabel = new JLabel("Land:");
    JTextField deliveryAddressCountryField = new JTextField("Danmark");

    //CustomerName
    JPanel customerNamePanel = new JPanel();
    JLabel customerNameLabel = new JLabel("Kundenavn:");
    JTextField customerNameField = new JTextField();

    //Customer Phone
    JPanel customerPhonenumberPanel = new JPanel();
    JLabel customerPhonenumberLabel = new JLabel("Telefon:");
    JTextField customerPhonenumberField = new JTextField();

    //Status 
    JPanel statusPanel = new JPanel();
    JLabel statusLabel = new JLabel("Status:");
    String[] statusdrop = {"Afsluttet", "Uafsluttet"};
    JComboBox statusmenu = new JComboBox(statusdrop);

    //ProductdropDown
    JPanel productListPanel = new JPanel();
    JLabel productLabel = new JLabel("Produkt:");
    ArrayList<String> productComboList = new ArrayList<>();
    String[] productarray = {" "};
    JComboBox<String> productbox = new JComboBox<String>(productarray);

    //Antal Produkter
    JLabel amountLabel = new JLabel("Antal:");
    JTextField amountField = new JTextField("", 5);

    //Buttons
    JPanel buttonPanel = new JPanel();
    JButton confirmJButton = new JButton("Committest");
    JButton cancelJButton = new JButton("Cancel");
    JButton addButton = new JButton("Add");

    //method that builds the frame and buttons
    private void frameBuild() {
        this.setTitle("WaterWise DB Project - OrderFrame");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
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
        topPanel.add(orderIDPanel, BorderLayout.WEST);
        orderIDPanel.add(orderIDLabel);
        orderIDPanel.add(orderIDField);
        orderIDField.setEditable(false);
        topPanel.add(statusPanel, BorderLayout.EAST);
        statusPanel.add(statusLabel);
        statusPanel.add(statusmenu);

        //MiddlePanel
        middlePanel.setLayout(new BorderLayout());

        middlePanel.add(productPanel, BorderLayout.SOUTH);

        //CustomerPanel
        if (orderShown instanceof Incoming) {

            middlePanel.add(customerPanel);
            customerPanel.setBorder(new TitledBorder("KundeOplysninger"));
            customerPanel.setLayout(null);
            customerPanel.add(customerNameLabel);
            customerPanel.add(customerNameField);
            customerPanel.add(customerEmailLabel);
            customerPanel.add(customerEmailField);
            customerPanel.add(customerPhonenumberLabel);
            customerPanel.add(customerPhonenumberField);
            customerPanel.add(deliveryAddressLabel);
            customerPanel.add(deliveryAddressField);
            customerPanel.add(deliveryAddressCityLabel);
            customerPanel.add(deliveryAddressCityField);
            customerPanel.add(deliveryAddressZipLabel);
            customerPanel.add(deliveryAddressZipField);
            customerPanel.add(deliveryAddressCountryLabel);
            customerPanel.add(deliveryAddressCountryField);
            customerPanel.add(paymentTypeLabel);
            customerPanel.add(paymentTypeField);
            customerPanel.add(deliveryTypeLabel);
            customerPanel.add(deliveryTypeField);

            //CustomerPanelBounds
            customerNameLabel.setBounds(8, 30, 75, 15);
            customerNameField.setBounds(78, 28, 150, 18);
            customerEmailLabel.setBounds(8, 60, 75, 15);
            customerEmailField.setBounds(78, 58, 150, 18);
            customerPhonenumberLabel.setBounds(8, 90, 75, 15);
            customerPhonenumberField.setBounds(78, 88, 150, 18);
            paymentTypeLabel.setBounds(8, 150, 75, 15);
            paymentTypeField.setBounds(78, 148, 150, 18);
            deliveryAddressLabel.setBounds(260, 30, 75, 10);
            deliveryAddressField.setBounds(318, 28, 150, 18);
            deliveryAddressCityLabel.setBounds(260, 60, 75, 15);
            deliveryAddressCityField.setBounds(318, 58, 150, 18);
            deliveryAddressZipLabel.setBounds(260, 90, 75, 15);
            deliveryAddressZipField.setBounds(318, 88, 150, 18);
            deliveryAddressCountryLabel.setBounds(260, 120, 75, 15);
            deliveryAddressCountryField.setBounds(318, 118, 150, 18);
            deliveryTypeLabel.setBounds(260, 150, 75, 15);
            deliveryTypeField.setBounds(318, 148, 150, 18);
        } else if (orderShown instanceof Outgoing) {

            middlePanel.add(supplierPanel);
            supplierPanel.setBorder(new TitledBorder("SupplierOplysninger"));
            supplierPanel.setLayout(null);
            supplierPanel.add(supplierNameLabel);
            supplierPanel.add(supplierNameField);
            supplierPanel.add(supplierEmailLabel);
            supplierPanel.add(supplierEmailField);
            supplierPanel.add(ownPhonenumberLabel);
            supplierPanel.add(ownPhonenumberField);
            supplierPanel.add(ownAddressLabel);
            supplierPanel.add(ownAddressField);
            supplierPanel.add(ownCityLabel);
            supplierPanel.add(ownCityField);
            supplierPanel.add(ownZipLabel);
            supplierPanel.add(ownZipField);
            supplierPanel.add(ownCountryLabel);
            supplierPanel.add(ownCountryField);

            //CustomerPanelBounds
            supplierNameLabel.setBounds(8, 30, 75, 15);
            supplierNameField.setBounds(78, 28, 150, 18);
            supplierEmailLabel.setBounds(8, 60, 75, 15);
            supplierEmailField.setBounds(78, 58, 150, 18);
            ownPhonenumberLabel.setBounds(8, 90, 75, 15);
            ownPhonenumberField.setBounds(78, 88, 150, 18);
            ownAddressLabel.setBounds(260, 30, 75, 10);
            ownAddressField.setBounds(318, 28, 150, 18);
            ownCityLabel.setBounds(260, 60, 75, 15);
            ownCityField.setBounds(318, 58, 150, 18);
            ownZipLabel.setBounds(260, 90, 75, 15);
            ownZipField.setBounds(318, 88, 150, 18);
            ownCountryLabel.setBounds(260, 120, 75, 15);
            ownCountryField.setBounds(318, 118, 150, 18);

        }

        //ProductPanel
        productPanel.setPreferredSize(productPaneDimension);
        productPanel.setBorder(new TitledBorder("Produkter"));
        productPanel.setLayout(new FlowLayout());

        for (Product temp : ElementListCollection.getPList()) {
            productComboList.add(temp.getProductName());
        }
        productbox.setModel(new DefaultComboBoxModel(productComboList.toArray()));

        productPanel.add(productLabel);
        productPanel.add(productbox);

        productPanel.add(amountLabel);
        productPanel.add(amountField);
        productPanel.add(addButton);
        addButton.setPreferredSize(buttonDimension);

        //BottomPanel
        ofPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(productTableScrollPane, BorderLayout.CENTER);
        productTableScrollPane.setPreferredSize(productTableDimension);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(confirmJButton);
        buttonPanel.add(cancelJButton);
        buttonPanel.setPreferredSize(buttonPanelDimension);
        confirmJButton.setPreferredSize(buttonDimension);

        cancelJButton.setPreferredSize(buttonDimension);

        confirmJButton.addActionListener(new Listener().new SaveEditButton(this));
        cancelJButton.addActionListener(new Listener().new DisposeFrameButton(this));

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

    public OrderFrame(Order orderToShow) {

        orderShown = orderToShow;
        if (orderToShow instanceof Outgoing) {
            setTextOutgoing((Outgoing) orderToShow);
        } else if (orderToShow instanceof Incoming) {
            setTextIncoming((Incoming) orderToShow);
        }
        frameBuild();

    }

    private void setTextCommon(Order ots) {
        orderIDField.setText(ots.getOrderID());
        deliveryTypeField.setText(ots.getDeliveryType());
        paymentTypeField.setText(ots.getPaymentType());
        if (ots.getOrderStatus() != null) {

            switch (ots.getOrderStatus()) {
                case "Afsluttet":
                    statusmenu.setSelectedIndex(0);
                    break;
                case "Uafsluttet":
                    statusmenu.setSelectedIndex(1);
                    break;
            }
        }
    }

    private void setTextOutgoing(Outgoing ots) {

        //Supplier
        supplierEmailField.setText(ots.getSupplierEmail());
        supplierNameField.setText(ots.getSupplierName());
        ownAddressField.setText(ots.getOwnAddress());
        ownCityField.setText(ots.getOwnCity());
        ownZipField.setText(ots.getOwnZip());
        ownCountryField.setText(ots.getOwnCountry());
        ownPhonenumberField.setText(ots.getOwnPhonenumber());

        setTextCommon(ots);
    }

    private void setTextIncoming(Incoming ots) {

        //Customer
        customerPhonenumberField.setText("" + ots.getCustomerPhonenumber());

        System.out.println("Figure out how to customer addressfields"
                + "Since they arent in the order object");
        
        setTextCommon(ots);
    }
}
