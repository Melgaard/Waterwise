package waterwise;

import java.awt.BorderLayout;
import java.awt.Color;
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


public class ErrorFrame extends JFrame {
    
    Gui gui;
    String errorShown;
    Listener listen;
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

    //JPanels
    JPanel ofPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel mainPanel = new JPanel();
    
//    JPanel productTablePanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel supplierPanel = new JPanel();

    public String getErrorTxt() {
        return errorTxt;
    }

    public void setErrorTxt(String errorTxt) {
        this.errorTxt = errorTxt;
    }

    public String getErrorCategory() {
        return errorCategory;
    }

    public void setErrorCategory(String errorCategory) {
        this.errorCategory = errorCategory;
    }

    public JLabel getErrorOne() {
        return errorOne;
    }

    public void setErrorOne(JLabel errorOne) {
        this.errorOne = errorOne;
    }

    public JLabel getFixOne() {
        return fixOne;
    }

    // ErrorFrame
    public void setFixOne(JLabel fixOne) {
        this.fixOne = fixOne;
    }
    String errorTxt;
    String errorCategory;
    String formatFix;
    String correctExample;
    
    JPanel errorPanel = new JPanel();
    JLabel errorOne = new JLabel("fejl: " + errorTxt + " i kategorien: " + errorCategory);    
    JLabel fixOne = new JLabel("korrekt format er " + formatFix + " f.eks. " + correctExample);    



    //Buttons
    JPanel buttonPanel = new JPanel();
    JButton confirmJButton = new JButton("Ok");
 


    //method that builds the frame and buttons
    private void frameBuild() {
        this.setTitle("WaterWise DB Project - Error!");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 300);
        this.setLocation(540, 700);


        //Panels
        ofPanel.setLayout(new BorderLayout());

        ofPanel.add(topPanel, BorderLayout.NORTH);
        ofPanel.add(middlePanel, BorderLayout.CENTER);

        //TopPanel
        topPanel.setLayout(new BorderLayout()); 
        
        //MiddlePanel
        middlePanel.setLayout(new BorderLayout());

        
        //Error Panel
        
            middlePanel.add(mainPanel, BorderLayout.CENTER);
            mainPanel.setBorder(new TitledBorder("Fejl - detaljer"));
            mainPanel.setLayout(null);
            errorOne.setForeground(Color.RED);
            fixOne.setForeground(Color.BLUE);
            mainPanel.add(errorOne);
            mainPanel.add(fixOne);
            
            mainPanel.add(confirmJButton);
            confirmJButton.setPreferredSize(buttonDimension);
//            confirmJButton.addActionListener(listen.new showErrorFrame());
            
            //errorPanelBounds
            
            errorOne.setBounds(8, 30, 200, 15);
            fixOne.setBounds(8, 60, 200, 15);
            confirmJButton.setBounds(8, 90, 75, 15);

        ofPanel.setVisible(true);
        this.add(ofPanel);
        this.setVisible(true);
    }
    public ErrorFrame() {
        frameBuild();
    }
    public ErrorFrame(String error, String category, String inputFix, String correctExample) {
        System.out.println(error + category + inputFix + correctExample);
        switch (category) {
            case "Vægt": case "Produkt ID": case "Antal": case "Genbestil": case "Produkt pris" :
            case "Adresse" : case "Telefon" : case "Email" :  
                                  errorOne.setText("fejl: " + error + " i kategorien: " + category); 
                                  fixOne.setText("korrekt format er " + inputFix + " f.eks. " + correctExample);
                                break;           
            case "Navn": case "Størrelse":   errorOne.setText(inputFix); 
                                     fixOne.setText(correctExample);
                                break;
           
        }
        frameBuild();

    }
}
