package waterwise;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Gui extends JFrame {

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
    String[] orderSort = {"Alle ordrer", "Seneste 14 dage", "3 mÃ¥neder", "VÃ¦lg Periode"};
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
    ArrayList<Customer> cList = of.getCList();

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
    ArrayList<Order> stockList = of.getStockList();

    //JPanel for stockordersidebar WEST
    JPanel stockOrderWestPanel = new JPanel(new GridLayout(2, 1));
    JPanel stockOrderButtonPanel = new JPanel();

    //StockOrderButotns
    JButton createStockOrder = new JButton("Opret Ordre");
    JButton editStockOrder = new JButton("Rediger Ordre");
    JButton changeStockStatus = new JButton("Skift status");
    JButton printStockLabel = new JButton("Print label");
    JButton deleteStockOrder = new JButton("Slet ordre");

    //Order Sort
    String[] stockOrderSort = {"Alle ordrer", "Seneste 14 dage", "3 mÃ¥neder", "VÃ¦lg Periode"};
    JComboBox stockOrderSorter = new JComboBox(stockOrderSort);

    public Gui() {

        setSize(1000, 700);
        setTitle("WaterWise DB Project");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        this.addComponentToPane();
        updateOrderList();

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
        tabbedPane.addTab(INDSTILLINGER, cardSettings);

        this.add(tabbedPane, BorderLayout.CENTER);

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
        orderProducts.addActionListener(new Listener(). new createNewOutgoing());

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
        
        //StockOrder
        cardStockOrders.add(stockOrderPanel);
        stockOrderPanel.setLayout(new BorderLayout());

        stockOrderPanel.add(stockOrderScrollPane, BorderLayout.CENTER);

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

        stockOrderButtonPanel.add(createStockOrder);
        stockOrderButtonPanel.add(editStockOrder);
        stockOrderButtonPanel.add(changeStockStatus);
        stockOrderButtonPanel.add(printStockLabel);
        stockOrderButtonPanel.add(deleteStockOrder);
        stockOrderButtonPanel.add(stockOrderSorter);

        stockOrderSorter.setSelectedIndex(0);

    }

    public void updateOrderList() {
        //Orders
        DefaultTableModel orderTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int orderRow, int orderCol) {
                return false;
            }
        };

        orderTable.setAutoCreateRowSorter(true);

        orderTableModel.setColumnIdentifiers(new String[]{"OrderID", "StartDato", "SlutDato", "Betalingstype", "Totalpris", "Leveringstype", "OrdreStatus"});
        orderTableModel.setRowCount(oList.size());

        int orderRow = 0;
        for (Order o : oList) {
            orderTableModel.setValueAt(o.getOrderID(), orderRow, 0);
            System.out.println(o.getOrderID() + "");
            orderTableModel.setValueAt(o.getStartDate(), orderRow, 1);
            orderTableModel.setValueAt(o.getClosedDate(), orderRow, 2);
            orderTableModel.setValueAt(o.getPaymentType(), orderRow, 3);
            orderTableModel.setValueAt(o.getPriceTotal(), orderRow, 4);
            orderTableModel.setValueAt(o.getDeliveryType(), orderRow, 5);
            orderTableModel.setValueAt(o.getOrderStatus(), orderRow, 6);

            orderRow++;
        }

        orderTable.setModel(orderTableModel);

//        
//        //Products
//        DefaultTableModel productTableModel = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int col){
//                return false;
//            }
//        };
//        
//        productTable.setAutoCreateRowSorter(true);
//
//        productTableModel.setColumnIdentifiers(new String[]{"OrderID", "StartDato", "SlutDato", "TotalPris", "Betalingstype", "Leveringstype", "OrdreStatus"});
//        productTableModel.setRowCount(oList.size());
//
//        int productRow = 0;
//        for (Product p : pList) {
//            orderTableModel.setValueAt(p.getOrderID(), productRow, 0);
//            orderTableModel.setValueAt(p.getStartDate(), productRow, 1);
//            orderTableModel.setValueAt(p.getClosedDate(), productRow, 2);
//            orderTableModel.setValueAt(p.getPaymentType(), productRow, 3);
//            orderTableModel.setValueAt(p.getPriceTotal(), productRow, 4);
//            orderTableModel.setValueAt(p.getDeliveryType(), productRow, 5);
//            orderTableModel.setValueAt(p.getOrderStatus(), productRow, 6);
//
//            productRow++;
//        }
//
//        productTable.setModel(productTableModel);
//        
//        
//        //Customers
//        DefaultTableModel customerTableModel = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int customerRow, int customerCol){
//                return false;
//            }
//        };
//        
//        customerTable.setAutoCreateRowSorter(true);
//
//        customerTableModel.setColumnIdentifiers(new String[]{"OrderID", "StartDato", "SlutDato", "TotalPris", "Betalingstype", "Leveringstype", "OrdreStatus"});
//        customerTableModel.setRowCount(cList.size());
//
//        int customerRow = 0;
//        for (Customer c : cList) {
//            customerTableModel.setValueAt(c.getOrderID(), customerRow, 0);
//            customerTableModel.setValueAt(c.getStartDate(), customerRow, 1);
//            customerTableModel.setValueAt(c.getClosedDate(), customerRow, 2);
//            customerTableModel.setValueAt(c.getPaymentType(), customerRow, 3);
//            customerTableModel.setValueAt(c.getPriceTotal(), customerRow, 4);
//            customerTableModel.setValueAt(c.getDeliveryType(), customerRow, 5);
//            customerTableModel.setValueAt(c.getOrderStatus(), customerRow, 6);
//
//            customerRow++;
//        }
//
//        customerTable.setModel(customerTableModel);
//        
//        
//        
//        
//        //StockOrders
//        DefaultTableModel stockOrderTableModel = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int col){
//                return false;
//            }
//        };
//        
//        customerTable.setAutoCreateRowSorter(true);
//
//        customerTableModel.setColumnIdentifiers(new String[]{"OrderID", "StartDato", "SlutDato", "TotalPris", "Betalingstype", "Leveringstype", "OrdreStatus"});
//        customerTableModel.setRowCount(oList.size());
//
//        int stockOrderRow = 0;
//        for (Order o : oList) {
//            customerTableModel.setValueAt(o.getOrderID(), stockOrderRow, 0);
//            customerTableModel.setValueAt(o.getStartDate(), stockOrderRow, 1);
//            customerTableModel.setValueAt(o.getClosedDate(), stockOrderRow, 2);
//            customerTableModel.setValueAt(o.getPaymentType(), stockOrderRow, 3);
//            customerTableModel.setValueAt(o.getPriceTotal(), stockOrderRow, 4);
//            customerTableModel.setValueAt(o.getDeliveryType(), stockOrderRow, 5);
//            customerTableModel.setValueAt(o.getOrderStatus(), stockOrderRow, 6);
//
//            customerRow++;
//        }
//
//        stockOrderTable.setModel(stockOrderTableModel);

    }

}
