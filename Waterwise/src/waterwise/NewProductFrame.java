package waterwise;
// @Design author Marcus Melgaard, implementering Jesper Smith
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

// The NewProductFrame Class is used to create new products
public class NewProductFrame extends JFrame {
    //Fields
    ErrorChecker eh;
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
    
    JPanel productPricePanel = new JPanel();
    JLabel productPriceLabel = new JLabel("Pris:");
    JTextField productPriceField = new JTextField();

    
   //ProductSize dropDown
    JPanel productSizePanel = new JPanel();
    JLabel productSizeLabel = new JLabel("Størrelse: ");
    ArrayList<String> productComboList = new ArrayList<>();
    String[] productarray = {"Standard", "Stor", "Lille"};
    JComboBox<String> productSize = new JComboBox<>(productarray);

    
    JPanel reorderPanel = new JPanel();
    JLabel reorderLabel = new JLabel("Genbestil:");
    JTextField reorderField = new JTextField("1");

    //Buttons
    JPanel buttonPanel = new JPanel();
    JButton confirmJButton = new JButton("Opret Vare");
    JButton cancelJButton = new JButton("Annullér");
    

    //frameBuild - builds the frame
    private void frameBuild() {
        this.setTitle("WaterWise DB Project - OrderFrame");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 330);
        this.setLocationRelativeTo(null);
 
        //Panels
        ofPanel.setLayout(new BorderLayout());
        ofPanel.add(topPanel, BorderLayout.NORTH);
        ofPanel.add(middlePanel, BorderLayout.CENTER);
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
            customerPanel.setLayout(null);      // Layout set to null, which enables custom layout via setBounds
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
            // ActionListener calls via the Listener class, calling the corresponding Abstract Class
            cancelJButton.addActionListener(new Listener().new DisposeFrameButton(this));            
            confirmJButton.addActionListener(new Listener().new newProductFrameConfirmButton(this)); 
            

            //productPanelBounds
            productNameLabel.setBounds(8, 30, 75, 15);
            productNameField.setBounds(78, 28, 150, 30);
            productPriceLabel.setBounds(8, 60, 75, 15);
            productPriceField.setBounds(78, 58, 150, 30);
            productAmountLabel.setBounds(8, 90, 75, 15);
            productAmountField.setBounds(78, 88, 150, 30);
            productWeightLabel.setBounds(8, 120, 75, 15);
            productWeightField.setBounds(78, 118, 150, 30);
           reorderLabel.setBounds(8, 150, 75, 15);
            reorderField.setBounds(78, 148, 150, 30);
            productSizeLabel.setBounds(8, 178, 100, 25);
            productSize.setBounds(108, 178, 100, 25);
            confirmJButton.setBounds(25, 210, 100, 25);
            cancelJButton.setBounds(150, 210, 100, 25);


        ofPanel.setVisible(true);
        this.add(ofPanel);
        this.setVisible(true);
    }  

         public void setTextProduct(Product p) {    // Method for setting text in fields, used when editting a product
                ErrorChecker ec = new ErrorChecker();

                productIDField.setText(p.getProductID()+"");
                productNameField.setText(p.getProductName());
                productWeightField.setText(p.getWeight()+ "");
                productAmountField.setText(p.getAmountInStorage()+ "");                
                reorderField.setText(p.getReorderAmount()+ "");
                productSize.setSelectedItem(p.getSize());
                productPriceField.setText(p.getUnitPrice()+"");
       }
    
    public NewProductFrame() {

        frameBuild();

    }
}
