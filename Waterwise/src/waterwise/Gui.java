package waterwise;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Gui extends JFrame {

    static Gui instance;
    Controller controller = new Controller();

    ElementListCollection of = new ElementListCollection();

    final static String ROOT = "ROOT";
    final static String ORDRER = "ORDRER";
    final static String LAGER = "LAGER";
    final static String KUNDER = "KUNDER";
    final static String INDSTILLINGER = "INDSTILLINGER";
    final static String BESTILLINGER = "BESTILLINGER";
    final static int extraWindowWidth = 200;

    //CARDS
    JPanel cardRoot = new JPanel(); // Root
    JPanel cardOrder = new JPanel(); // Ordrer
    JPanel cardProducts = new JPanel(); // Lager
    JPanel cardCustomers = new JPanel(); // Kunder
    JPanel cardSettings = new JPanel(); // Indstillinger
    JPanel cardStockOrders = new JPanel(); //Genbestillinger 

    //Dimensions
    Dimension tablesize = new Dimension(800, 500);
    Dimension buttonPanelSize = new Dimension(150, 120);
    Dimension buttonSize = new Dimension(120, 30);

    //ROOT
    JPanel rootPanel = new JPanel();
    JLabel imageLabel = new JLabel();
    ImageIcon waterwise = new ImageIcon("waterwise.jpg");
    JLabel info = new JLabel("Waterwise Lagerstyring - v.1.0 - KEA 2015");
    
    ImageIcon icon = new ImageIcon("drop2.png");
    
    
    
    
    //ORDER    
    JPanel orderPanel = new JPanel();

    //Scrollpane
    JTable orderTable = new JTable();
    JScrollPane orderScrollPane = new JScrollPane(orderTable);
    ArrayList<Order> oList = of.getOList();

    //JPanel for ordersidebar WEST
    JPanel orderWestPanel = new JPanel(new GridLayout(2, 1));
    JPanel orderButtonPanel = new JPanel();

    //OrderButtons    
    JButton createOrder = new JButton("Opret Ordre");
    JButton editOrder = new JButton("Rediger Ordre");
    JButton changeStatus = new JButton("Skift status");
    JButton printLabel = new JButton("Print label");
    JButton deleteOrder = new JButton("Slet ordre");

    //Order Sort
    String[] orderSort = {"Alle ordrer", "Seneste 14 dage", "Uafsluttede", "Afsluttede"};
    JComboBox orderSorter = new JComboBox(orderSort);

    //PRODUCT
    JPanel productPanel = new JPanel();

    //Scrollpane
    JTable productTable = new JTable();
    JScrollPane productScrollPane = new JScrollPane(productTable);
    ArrayList<Product> pList = of.getPList();

    //JPanel for productsidebar WEST
    JPanel productWestPanel = new JPanel(new GridLayout(2, 1));
    JPanel productButtonPanel = new JPanel();

    //ProductButtons
    JButton createProduct = new JButton("Opret vare");
    JButton addProducts = new JButton("Tilføj vare");
    JButton editProduct = new JButton("Rediger vare");
    JButton orderProducts = new JButton("Bestil vare");
    JButton deleteProduct = new JButton("Slet vare");

    //Settings
    JPanel settingsPanel = new JPanel();
    JCheckBox checkBox1 = new JCheckBox("Settings 1");
    JCheckBox checkBox2 = new JCheckBox("Settings 2");
    JPanel southSettingsPanel = new JPanel();

    //SettingsButtons
    JButton commit = new JButton("BekrÃ¦ft");
    JButton cancel = new JButton("AnnulÃ©r");

    //Customers
    JPanel customerPanel = new JPanel();

    //ScrollPane
    JTable customerTable = new JTable();
    JScrollPane customerScrollPane = new JScrollPane(customerTable);

    //JPanel for customersidebar WEST
    JPanel customerWestPanel = new JPanel(new GridLayout(2, 1));
    JPanel customerButtonPanel = new JPanel();

    //CustomerButtons
    JButton createCustomer = new JButton("TilfÃ¸j kunde");
    JButton editCustomer = new JButton("Rediger kunde");
    JButton deleteCustomer = new JButton("Slet kunde");

    //StockOrders
    JPanel stockOrderPanel = new JPanel();

    //ScrollPane
    JTable stockOrderTable = new JTable();
    JScrollPane stockOrderScrollPane = new JScrollPane(stockOrderTable);

    //JPanel for stockordersidebar WEST
    JPanel stockOrderWestPanel = new JPanel(new GridLayout(2, 1));
    JPanel stockOrderButtonPanel = new JPanel();

    //StockOrderButotns
    JButton createStockOrder = new JButton("Opret Ordre");
    JButton editStockOrder = new JButton("Rediger Ordre");
    JButton changeStockStatus = new JButton("Skift status");
    JButton printStockLabel = new JButton("Tekst til email");
    JButton deleteStockOrder = new JButton("Slet ordre");

    //Order Sort
    String[] stockOrderSort = {"Alle ordrer", "Seneste 14 dage", "Uafsluttede", "Afsluttede"};
    JComboBox stockOrderSorter = new JComboBox(stockOrderSort);
    
    

    public Gui() {

        instance = this;

        setIconImage(icon.getImage());
        
        setSize(1000, 700);
        setTitle("WaterWise DB Project");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        this.addComponentToPane();
        controller.resetView();

        setVisible(true);

    }

    private void addComponentToPane() {
        
        
        Listener listen = new Listener();
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab(ROOT, cardRoot);
        tabbedPane.addTab(ORDRER, cardOrder);
        tabbedPane.addTab(LAGER, cardProducts);
        tabbedPane.addTab(KUNDER, cardCustomers);
        tabbedPane.addTab(BESTILLINGER, cardStockOrders);
        //tabbedPane.addTab(INDSTILLINGER, cardSettings);

        this.add(tabbedPane, BorderLayout.CENTER);

        //Root
        cardRoot.add(rootPanel);
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(imageLabel, BorderLayout.CENTER);
        imageLabel.setIcon(waterwise);
        rootPanel.add(info, BorderLayout.SOUTH);
        
        
        // Order
        cardOrder.add(orderPanel);
        orderPanel.setLayout(new BorderLayout());

        orderPanel.add(orderScrollPane, BorderLayout.CENTER);

        orderScrollPane.setPreferredSize(tablesize);
        orderPanel.add(orderWestPanel, BorderLayout.WEST);
        orderWestPanel.add(orderButtonPanel);
        orderButtonPanel.setPreferredSize(buttonPanelSize);
        orderButtonPanel.setBorder(new TitledBorder("Handlinger"));

        createOrder.setPreferredSize(buttonSize);
        editOrder.setPreferredSize(buttonSize);
        changeStatus.setPreferredSize(buttonSize);
        printLabel.setPreferredSize(buttonSize);
        deleteOrder.setPreferredSize(buttonSize);
        orderSorter.setPreferredSize(buttonSize);

        createOrder.addActionListener(listen.new createNewIncoming());
        changeStatus.addActionListener(listen.new ChangeStatusButton(this, "Incoming"));
        editOrder.addActionListener(listen.new EditOrderButton(orderTable, "Incoming"));
        printLabel.addActionListener(listen.new PrintLabelButton(this));
        orderSorter.addActionListener(listen.new ResetViewButton());
        deleteOrder.addActionListener(listen.new DeleteElementButton(orderTable, "Incoming"));

        orderButtonPanel.add(createOrder);
        orderButtonPanel.add(editOrder);
        orderButtonPanel.add(changeStatus);
        orderButtonPanel.add(printLabel);
        orderButtonPanel.add(deleteOrder);
        orderButtonPanel.add(orderSorter);

        orderSorter.setSelectedIndex(0);

        //Product
        cardProducts.add(productPanel);
        productPanel.setLayout(new BorderLayout());

        productPanel.add(productScrollPane, BorderLayout.CENTER);
        productScrollPane.setPreferredSize(tablesize);
        productPanel.add(productWestPanel, BorderLayout.WEST);
        productWestPanel.add(productButtonPanel);
        productButtonPanel.setPreferredSize(buttonPanelSize);
        productButtonPanel.setBorder(new TitledBorder("Handlinger"));

        productButtonPanel.add(createProduct);
        productButtonPanel.add(addProducts);
        productButtonPanel.add(editProduct);
        productButtonPanel.add(orderProducts);
        productButtonPanel.add(deleteProduct);

        createProduct.setPreferredSize(buttonSize);
        addProducts.setPreferredSize(buttonSize);
        editProduct.setPreferredSize(buttonSize);
        orderProducts.setPreferredSize(buttonSize);
        deleteProduct.setPreferredSize(buttonSize);

        createProduct.addActionListener(listen.new createNewProduct());
        addProducts.addActionListener(listen.new addProduct());
        orderProducts.addActionListener(new Listener().new createNewOutgoing());
        editProduct.addActionListener(listen.new EditProductButton(productTable, "Product"));
        deleteProduct.addActionListener(listen.new DeleteElementButton(productTable, "Product"));
        
        //Customer
        cardCustomers.add(customerPanel);
        customerPanel.setLayout(new BorderLayout());
        customerPanel.add(customerScrollPane, BorderLayout.CENTER);
        customerScrollPane.setPreferredSize(tablesize);
        customerPanel.add(customerWestPanel, BorderLayout.WEST);
        customerWestPanel.add(customerButtonPanel);
        customerButtonPanel.setPreferredSize(buttonPanelSize);
        customerButtonPanel.setBorder(new TitledBorder("Handlinger"));

        customerButtonPanel.add(createCustomer);
        customerButtonPanel.add(editCustomer);
        customerButtonPanel.add(deleteCustomer);

        createCustomer.setPreferredSize(buttonSize);
        editCustomer.setPreferredSize(buttonSize);
        deleteCustomer.setPreferredSize(buttonSize);

        createCustomer.addActionListener(listen.new newCustomerFrame());
        editCustomer.addActionListener(listen.new EditCustomerButton(customerTable, "Customer"));
        deleteCustomer.addActionListener(listen.new DeleteElementButton(customerTable, "Customer"));

        //Settings
        cardSettings.add(settingsPanel);
        settingsPanel.setLayout(new BorderLayout());

        settingsPanel.add(checkBox1, BorderLayout.CENTER);
        checkBox1.setMnemonic(KeyEvent.VK_G);
        checkBox1.setSelected(true);

        settingsPanel.add(checkBox2, BorderLayout.WEST);
        checkBox2.setMnemonic(KeyEvent.VK_G);
        checkBox2.setSelected(true);

        settingsPanel.add(southSettingsPanel, BorderLayout.SOUTH);
        southSettingsPanel.setLayout(new FlowLayout());

        southSettingsPanel.add(commit);
        commit.setPreferredSize(buttonSize);

        southSettingsPanel.add(cancel);
        cancel.setPreferredSize(buttonSize);

        //StockOrder
        cardStockOrders.add(stockOrderPanel);
        stockOrderPanel.setLayout(new BorderLayout());

        stockOrderPanel.add(stockOrderScrollPane    , BorderLayout.CENTER);

        stockOrderScrollPane.setPreferredSize(tablesize);
        stockOrderPanel.add(stockOrderWestPanel, BorderLayout.WEST);
        stockOrderWestPanel.add(stockOrderButtonPanel);
        stockOrderButtonPanel.setPreferredSize(buttonPanelSize);
        stockOrderButtonPanel.setBorder(new TitledBorder("Handlinger"));

        createStockOrder.setPreferredSize(buttonSize);
        editStockOrder.setPreferredSize(buttonSize);
        changeStockStatus.setPreferredSize(buttonSize);
        printStockLabel.setPreferredSize(buttonSize);
        deleteStockOrder.setPreferredSize(buttonSize);
        stockOrderSorter.setPreferredSize(buttonSize);

        stockOrderSorter.addActionListener(listen.new ResetOutgoingViewButton(this));
        deleteStockOrder.addActionListener(listen.new DeleteElementButton(stockOrderTable, "Outgoing"));
        stockOrderSorter.setSelectedIndex(0);

        stockOrderButtonPanel.add(createStockOrder);
        stockOrderButtonPanel.add(editStockOrder);
        stockOrderButtonPanel.add(changeStockStatus);
        stockOrderButtonPanel.add(printStockLabel);
        stockOrderButtonPanel.add(deleteStockOrder);
        stockOrderButtonPanel.add(stockOrderSorter);

        createStockOrder.addActionListener(new Listener().new createNewOutgoing());
        editStockOrder.addActionListener(new Listener().new EditOrderButton(stockOrderTable, "Outgoing"));
        printStockLabel.addActionListener(new Listener().new PrintEmailButton(this, stockOrderTable, "Outgoing"));

    }

    public void updateOrderList() {
        //Orders
        DefaultTableModel orderTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int orderRow, int orderCol) {
                return false;
            }
        };

        ArrayList<Order> templist = new ArrayList<>();
        String viewSort = orderSorter.getSelectedItem().toString();

        switch (viewSort) {
            case "Uafsluttede":
                for (Order loop : ElementListCollection.getOList()) {
                    if (loop.getOrderStatus().equals("Uafsluttet")) {
                        templist.add(loop);
                    }
                }
                break;
            case "Afsluttede":
                for (Order loop : ElementListCollection.getOList()) {
                    if (loop.getOrderStatus().equals("Afsluttet")) {
                        templist.add(loop);
                    }
                }
                break;
            case "Seneste 14 dage":
                for (Order loop : ElementListCollection.getOList()) {
                    SimpleDateFormat tempdateformat = new SimpleDateFormat("dd-MM-yyyy");
                    Date tempdate;
                    try {
                        tempdate = tempdateformat.parse(loop.getStartDate());

                        Date predt = Calendar.getInstance().getTime();
                        String dt = tempdateformat.format(predt);

                        Calendar c = Calendar.getInstance();
                        c.setTime(tempdateformat.parse(dt));
                        c.add(Calendar.DATE, -14);  // number of days to add

                        Date temp14ago = c.getTime();
                        if (tempdate.after(temp14ago)) {
                            templist.add(loop);
                        }
                    } catch (ParseException ex) {
                        System.out.println("parseException");
                    }

                }
                break;
            default:
                templist = ElementListCollection.getOList();
                break;
        }

        orderTable.setAutoCreateRowSorter(true);

        orderTableModel.setColumnIdentifiers(new String[]{"OrderID", "StartDato", "SlutDato", "Betalingstype", "Totalpris", "Leveringstype", "OrdreStatus"});
        orderTableModel.setRowCount(templist.size());

        int orderRow = 0;
        for (Order o : templist) {
            orderTableModel.setValueAt(o.getOrderID(), orderRow, 0);
            orderTableModel.setValueAt(o.getStartDate(), orderRow, 1);
            orderTableModel.setValueAt(o.getClosedDate(), orderRow, 2);
            orderTableModel.setValueAt(o.getPaymentType(), orderRow, 3);
            orderTableModel.setValueAt(o.getPriceTotal() + " kr", orderRow, 4);
            orderTableModel.setValueAt(o.getDeliveryType(), orderRow, 5);
            orderTableModel.setValueAt(o.getOrderStatus(), orderRow, 6);

            orderRow++;
        }

        orderTable.setModel(orderTableModel);

    }
//Customers

    public void updateCustomerList() {

        DefaultTableModel customerTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int customerRow, int customerCol) {
                return false;
            }
        };

        customerTable.setAutoCreateRowSorter(true);

        ArrayList<Customer> templist = new ArrayList<>();
        templist = ElementListCollection.getCList();

        customerTableModel.setColumnIdentifiers(new String[]{"Telefon", "Email", "Navn", "Vejnavn", "By", "Postnr", "Land"});
        customerTableModel.setRowCount(templist.size());

        int customerRow = 0;
        for (Customer c : templist) {
            customerTableModel.setValueAt(c.getPhoneNumber(), customerRow, 0);
            customerTableModel.setValueAt(c.getCustomerEmail(), customerRow, 1);
            customerTableModel.setValueAt(c.getCustomerName(), customerRow, 2);
            customerTableModel.setValueAt(c.getDeliveryAddress(), customerRow, 3);
            customerTableModel.setValueAt(c.getDeliveryCityAddress(), customerRow, 4);
            customerTableModel.setValueAt(c.getDeliveryZipAddress(), customerRow, 5);
            customerTableModel.setValueAt(c.getDeliveryCountryAddress(), customerRow, 6);

            customerRow++;
        }

        customerTable.setModel(customerTableModel);

    }

//        //StockOrders
    public void updateStockOrderList() {
        DefaultTableModel stockOrderTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        ArrayList<Order> templist = new ArrayList<>();
        String viewSort = stockOrderSorter.getSelectedItem().toString();

        switch (viewSort) {
            case "Uafsluttede":
                for (Order loop : ElementListCollection.getStockList()) {
                    if (loop.getOrderStatus().equals("Uafsluttet")) {
                        templist.add(loop);
                    }
                }
                break;
            case "Afsluttede":
                for (Order loop : ElementListCollection.getStockList()) {
                    if (loop.getOrderStatus().equals("Afsluttet")) {
                        templist.add(loop);
                    }
                }
                break;
            case "Seneste 14 dage":
                for (Order loop : ElementListCollection.getStockList()) {
                    SimpleDateFormat tempdateformat = new SimpleDateFormat("dd-MM-yyyy");
                    Date tempdate;
                    try {
                        tempdate = tempdateformat.parse(loop.getStartDate());

                        Date predt = Calendar.getInstance().getTime();
                        String dt = tempdateformat.format(predt);

                        Calendar c = Calendar.getInstance();
                        c.setTime(tempdateformat.parse(dt));
                        c.add(Calendar.DATE, -14);  // number of days to add

                        Date temp14ago = c.getTime();
                        if (tempdate.after(temp14ago)) {
                            templist.add(loop);
                        }
                    } catch (ParseException ex) {
                        System.out.println("parseException");
                    }

                }
                break;
            default:
                templist = ElementListCollection.getStockList();
                break;
        }

        stockOrderTableModel.setColumnIdentifiers(new String[]{"OrderID", "StartDato", "SlutDato", "Betalingstype", "TotalPris", "Leveringstype", "OrdreStatus"});
        stockOrderTableModel.setRowCount(templist.size());

        int stockOrderRow = 0;
        for (Order o : templist) {
            stockOrderTableModel.setValueAt(o.getOrderID(), stockOrderRow, 0);
            stockOrderTableModel.setValueAt(o.getStartDate(), stockOrderRow, 1);
            stockOrderTableModel.setValueAt(o.getClosedDate(), stockOrderRow, 2);
            stockOrderTableModel.setValueAt(o.getPaymentType(), stockOrderRow, 3);
            stockOrderTableModel.setValueAt(o.getPriceTotal() + " kr", stockOrderRow, 4);
            stockOrderTableModel.setValueAt(o.getDeliveryType(), stockOrderRow, 5);
            stockOrderTableModel.setValueAt(o.getOrderStatus(), stockOrderRow, 6);

            stockOrderRow++;
        }

        stockOrderTable.setModel(stockOrderTableModel);

    }

    public void updateProductList() {
        //Products
        DefaultTableModel productTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        productTable.setAutoCreateRowSorter(true);

        ArrayList<Product> templist = new ArrayList<>();
        templist = ElementListCollection.getPList();

        productTableModel.setColumnIdentifiers(new String[]{"VareID", "VareNavn", "Antal på lager", "Størrelse", "Pris", "Genbestillingsgrænse", "Vægt"});
        productTableModel.setRowCount(templist.size());

        int productRow = 0;
        for (Product p : templist) {
            productTableModel.setValueAt(p.getProductID(), productRow, 0);
            productTableModel.setValueAt(p.getProductName(), productRow, 1);
            productTableModel.setValueAt(p.getAmountInStorage(), productRow, 2);
            productTableModel.setValueAt(p.getSize(), productRow, 3);
            productTableModel.setValueAt(p.getUnitPrice() + " kr", productRow, 4);
            productTableModel.setValueAt(p.getReorderAmount(), productRow, 5);
            productTableModel.setValueAt(p.getWeight() + " kg", productRow, 6);

            productRow++;
        }

        productTable.setModel(productTableModel);

    }

    //PrintFrame "OrderID", "StartDato", "SlutDato", "TotalPris", "Betalingstype", "Leveringstype", "OrdreStatus
    public void printLabelFrame(String orderID, String startDato, Double totalPris, String paymentType, String deliveryType, String status) {

        JFrame printLabelFrame = new JFrame("Text to print");
        JPanel printLabelPanel = new JPanel();
        JTextArea textToPrint = new JTextArea();
        JScrollPane scrollText = new JScrollPane(textToPrint);
        JButton copyText = new JButton("Kopier..");

        printLabelFrame.add(printLabelPanel);
        printLabelPanel.setLayout(null);
        printLabelPanel.add(scrollText);
        printLabelPanel.add(copyText);
        copyText.setBounds(30, 12, 80, 22);
        scrollText.setBounds(30, 40, 320, 300);
        printLabelFrame.setSize(400, 400);
        textToPrint.setLineWrap(true);
        textToPrint.setWrapStyleWord(true);

        //Text
        textToPrint.append(orderID + "\n");
        textToPrint.append(startDato + "\n");
        textToPrint.append(totalPris + "\n");
        textToPrint.append(paymentType + "\n");
        textToPrint.append(deliveryType + "\n");
        textToPrint.append(status + "\n");

        copyText.setToolTipText("Teksten kopieres når der trykkes på knappen.");

        copyText.addActionListener(new Listener().new copyText(textToPrint.getText()));

        printLabelFrame.setLocationRelativeTo(null);
        printLabelFrame.setDefaultCloseOperation(printLabelFrame.DISPOSE_ON_CLOSE);
        printLabelFrame.setVisible(true);

    }
    
     public void printEmailFrame(Order outgoingOrder) {

         
         
        JFrame printLabelFrame = new JFrame("Text to print");
        JPanel printLabelPanel = new JPanel();
        JTextArea textToPrint = new JTextArea();
        JScrollPane scrollText = new JScrollPane(textToPrint);
        JButton copyText = new JButton("Kopier..");

        printLabelFrame.add(printLabelPanel);
        printLabelPanel.setLayout(null);
        printLabelPanel.add(scrollText);
        printLabelPanel.add(copyText);
        copyText.setBounds(30, 12, 80, 22);
        scrollText.setBounds(30, 40, 320, 300);
        printLabelFrame.setSize(400, 400);
        textToPrint.setLineWrap(true);
        textToPrint.setWrapStyleWord(true);

        //Text
        textToPrint.append(outgoingOrder.getOrderID() + "\n");
//        textToPrint.append(startDato + "\n");
//        textToPrint.append(totalPris + "\n");
//        textToPrint.append(paymentType + "\n");
//        textToPrint.append(deliveryType + "\n");
//        textToPrint.append(status + "\n");

        copyText.setToolTipText("Teksten kopieres når der trykkes på knappen.");

        copyText.addActionListener(new Listener().new copyText(textToPrint.getText()));

        printLabelFrame.setLocationRelativeTo(null);
        printLabelFrame.setDefaultCloseOperation(printLabelFrame.DISPOSE_ON_CLOSE);
        printLabelFrame.setVisible(true);

    }

}
