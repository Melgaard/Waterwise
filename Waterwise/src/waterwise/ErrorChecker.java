/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterwise;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ErrorChecker {
	
	private JLabel productNameLbl = new JLabel("Produktnavn:");
	private JTextArea productNameTxt = new JTextArea(1,20);
	private JLabel productPriceLbl = new JLabel("Pris:");
	private JTextArea productPriceTxt = new JTextArea(1,1);
	private JLabel productAmountLbl = new JLabel("Antal:");
	private JTextArea productAmountTxt = new JTextArea(1,1);	
	private String productCategory = "";
	private String productName = "";
	private double productPrice = 0.0;
	private int productAmount = 0;
	private String activeProductID = "";
	
	private JFrame errorFrame = new JFrame();
	private JFrame selectFrame = new JFrame();
	private 		String errorTxt = "fejlindtastning";
	private		 	String correctExample = "korrekt indtastning";
	private boolean errorOccured;
	
private boolean isProductHighlighted;
	
	// Metode der kan bruges, til at tjekke om et produkt er highlighted - S�fremt vi �nsker knapper ikke skal kunne trykkes p�, med mindre et produkt er highlighted
	public void buttonSettings(JButton button) {	
		
		while (isProductHighlighted == false)
		{
			button.setEnabled(false);		
		}
	}
		// Metode til at resette evt. farvekode �ndringer p� labels
	public void resetProductLabels() {
		productNameLbl.setForeground(Color.BLACK);
		productPriceLbl.setForeground(Color.BLACK);
		productAmountLbl.setForeground(Color.BLACK);
	}
		// metode der t�mmer TextFields
	public void clearProductFields() {
		productNameTxt.setText("");
		productPriceTxt.setText("");
		productAmountTxt.setText("");
	}
		// ErrorFrame UI - Meget simpel :)
	public void errorFrameUI() {
		errorFrame.setLocation(selectFrame.getX() , selectFrame.getY() + selectFrame.getHeight());
		errorFrame.setSize(400	,100);
				
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new GridLayout(2,1));
		errorFrame.add(errorPanel);
		
		JLabel error = new JLabel("Der er fejl i indtastningen: " + errorTxt + " i kategorien: " + productCategory);
		JLabel errorFix = new JLabel("Det korrekte format i " + productCategory + " er " + correctExample);
		
		error.setForeground(Color.RED);
		errorFix.setForeground(Color.BLUE);
		
		errorPanel.add(error);
		errorPanel.add(errorFix);
		errorFrame.setVisible(true);
	}

	// Metode der tager imod en int, og loader et product ud fra det - S� l�nge SQL library ikke er indl�st vil den give fejl i #93 og # 96 :)
		public void loadProduct(int productID) {
			Connection conn = null;
			Statement stmt = null;
	    	ArrayList<String> sqlArray = new ArrayList<>();
		
			try {
				// Register JDBC driver
			//Class.forName (JDBC_DRIVER);		
				// Open Connection
				System.out.println("Connecting..");
			//conn = DriverManager.getConnection(DATABASE_URL,USER,PASS);
			
				//Execute query
				System.out.println("Selecting from ID");
				stmt = conn.createStatement();
				String sql;
				sql = "Select * FROM produkter"
						+ " WHERE ID=" + productID;
				ResultSet rs = stmt.executeQuery(sql);
			
			// 	Extract data from result set
				while (rs.next()) {
					//Retrieve by column name
					int id = rs.getInt("ID");
					String name = rs.getString("Navn");
					String price = rs.getString("Pris");
					String amount = rs.getString("Antal");
				
					// Display values
					productNameTxt.setText(name);
					productPriceTxt.setText(price);
					productAmountTxt.setText(amount);

				}				
		
			// 	Clean-up
				rs.close();
				stmt.close();
				conn.close();
			} 
			catch (SQLException se) {
				//Handle errors for JDBC
				se.printStackTrace();
			} 
			catch (Exception e) {
				//	Handle errors for Class.forName
				e.printStackTrace();
			} finally {
				// 	finally block used to close resources
				try {
					if (stmt != null)
						stmt.close();
				} 
				catch(SQLException se2) {
				}//nothing we can do				
				try {
					if(conn!=null)
					conn.close();
				} 
				catch (SQLException se){
					se.printStackTrace();
				}
			}
		}
		public void redigerLagerFrame() {
			JFrame frame = new JFrame();
				frame.setSize(500,500);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(4,2,100,100));
				
				JButton cancelChange = new JButton("Annuller �ndring");
				JButton editProduct = new JButton("Gem �ndring");
				
				
				// PART 3: Anvendelse
				cancelChange.addActionListener(new ActionListener() { 
					@Override 
					public void actionPerformed(ActionEvent event) { 				
							clearProductFields();
						}
					});
				editProduct.addActionListener(new ActionListener() { 
					@Override 
					public void actionPerformed(ActionEvent event) {								
							errorFrame.setVisible(!errorFrame.isVisible());
							String amount = productAmountTxt.getText();
							String price = productPriceTxt.getText();
							String name = productNameTxt.getText();
							
							if (isNameValid(name) == true) {
								if (isPriceValid(price) == true) {
									if (isAmountValid(amount) == true) {
										resetProductLabels();
									}
									else 
										runProductErrorCheck("Antal");
								}
								else 
									runProductErrorCheck("Pris");	
							}
							else 
								runProductErrorCheck("Navn");
						}
					});
				
			
			panel.add(productNameLbl);
			panel.add(productNameTxt);
			panel.add(productPriceLbl);
			panel.add(productPriceTxt);
			panel.add(productAmountLbl);
			panel.add(productAmountTxt);
			panel.add(editProduct);
			panel.add(cancelChange);
			
			frame.add(panel);
			frame.setVisible(true);
			
					selectFrame.setSize(400	,100);		
			selectFrame.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		
			JPanel selectPanel = new JPanel();		
			JLabel inputTxt = new JLabel("Produkt ID");
			JTextArea inputArea = new JTextArea(1,10);
					
			JButton select = new JButton("V�lg Produkt");		
			select.addActionListener(new ActionListener() { 
				@Override 
				public void actionPerformed(ActionEvent event) { 				
						loadProduct(Integer.parseInt(inputArea.getText()));
					}
				});
			selectPanel.add(inputTxt);
			selectPanel.add(inputArea);
			selectPanel.add(select);
			
			selectFrame.add(selectPanel);
			selectFrame.setVisible(true);
		}
		// Arbejder sammen med errorFramen - laver check p� indtastet info i TextField
		public void runProductErrorCheck(String category) {
			switch (category) {
				case "Navn":
								errorTxt = productNameTxt.getText();
								productCategory = "Navn";
								correctExample = "skal indeholder bokstaver eller tal";
								System.out.println("Produkt navn kunne ikke godkendes - Navnet skal indeholder bokstaver eller tal");
								productNameLbl.setForeground(Color.red);
								errorFrameUI();
					
							break;
				case "Pris":	errorTxt = productPriceTxt.getText();
								productCategory = "Pris";
								correctExample = "Angives med minimum 1 ciffer efter kommaet. eks: 2.0";
								System.out.println("Pris kunne ikke gemmes - Pris skal v�re angivet med minimum 1 ciffer efter kommaet. eks: 2.0");
								productPriceLbl.setForeground(Color.red);
								errorFrameUI();
							break;
				case "Antal":	errorTxt = productAmountTxt.getText();
								productCategory = "Antal";
								correctExample = "skal v�re et hel tal, eks: 1";
								System.out.println("Antal kunne ikke gemmes - Antal skal v�re et hel tal, eks: 1");
								productAmountLbl.setForeground(Color.red);
								errorFrameUI();
					
			}
	}
		
		// Metoder til at checke en String 
		public  boolean isDouble(String str) {
                    try {
                        Double.parseDouble(str);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
		

		
		public  boolean isInt(String str) {
                    try {
                        Integer.parseInt(str);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
		}
		
		
		
		public boolean isAmountValid(String amount) {
			if (isInt(amount) == true) {
				return true;
			}
			return false;
		}
		
		public boolean isPriceValid(String price) {
			if (isDouble(price) == true) {
				return true;
			}
			return false;
		}
                
                public boolean isWeightValid(String weight) {
			if (isDouble(weight) == true) {
				return true;
			}
			return false;
		}
		
		public boolean isNameValid(String name) {
			if (name != "" && !name.isEmpty()) {			
				return true;
			}
			return false;
		}
		
		public boolean isAddressValid(String address) {
			if (address != "" && !address.isEmpty()) {			
				return true;
			}
			return false;
		}               
		
		// Rediger kunde handlers
		
//		private String customerCategory = "";
//		private String customerName = "";
//		private String customerMail = "";
//		private String customerAddress = "";
//		private String activeCustomerID = "";
//		private JLabel customerNameLbl = new JLabel("Kunde navn:");
//		private JTextArea customerNameTxt = new JTextArea(1,20);
//		private JLabel customerMailLbl = new JLabel("Email:");
//		private JTextArea customerMailTxt = new JTextArea(1,1);
//		private JLabel customerAddressLbl = new JLabel("Antal:");
//		private JTextArea customerAddressTxt = new JTextArea(1,1);
//		
//		public void runCustomerErrorCheck(String category) {
//			switch (category) {
//				case "Navn":
//								errorTxt = customerNameTxt.getText();
//								customerCategory = "Navn";
//								correctExample = "skal indeholder bokstaver eller tal";
//								System.out.println("Produkt navn kunne ikke godkendes - Navnet skal indeholder bokstaver eller tal");
//								customerNameLbl.setForeground(Color.red);
//								errorFrameUI();
//					
//							break;
//				case "Mail":	errorTxt = customerMailTxt.getText();
//								customerCategory = "Pris";
//								correctExample = "Angives med minimum 1 ciffer efter kommaet. eks: 2.0";
//								System.out.println("Pris kunne ikke gemmes - Pris skal v�re angivet med minimum 1 ciffer efter kommaet. eks: 2.0");
//								customerMailLbl.setForeground(Color.red);
//								errorFrameUI();
//							break;
//				case "Adresse":	errorTxt = customerAddressTxt.getText();
//								customerCategory = "Antal";
//								correctExample = "skal v�re et hel tal, eks: 1";
//								System.out.println("Antal kunne ikke gemmes - Antal skal v�re et hel tal, eks: 1");
//								customerAddressLbl.setForeground(Color.red);
//								errorFrameUI();
//					
//			}
//	}
//			// Rediger kunde frame
//		public void redigerKunde() {
//			JFrame frame = new JFrame();
//				frame.setSize(500,500);
//				frame.setLocationRelativeTo(null);
//				frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//				JPanel panel = new JPanel();
//				panel.setLayout(new GridLayout(4,2,100,100));
//				
//				JButton cancelChange = new JButton("Annuller �ndring");
//				JButton editCustomer = new JButton("Gem �ndring");
//				
//				
//				// PART 3: Anvendelse
//				cancelChange.addActionListener(new ActionListener() { 
//					@Override 
//					public void actionPerformed(ActionEvent event) { 				
//							clearCustomerFields();
//						}
//					});
//				editCustomer.addActionListener(new ActionListener() { 
//					@Override 
//					public void actionPerformed(ActionEvent event) {								
//							errorFrame.setVisible(!errorFrame.isVisible());
//							String address = customerAddressTxt.getText();
//							String mail = customerMailTxt.getText();
//							String name = customerNameTxt.getText();
//							
//							if (isNameValid(name) == true) {
//								if (isEmailValid(mail) == true) {
//									if (isPhonenumberValid(address) == true) {									
//										resetCustomerLabels();
//									}
//									else 
//										runCustomerErrorCheck("Telefon");
//								}
//								else 
//									runCustomerErrorCheck("Email");	
//							}
//							else 
//								runCustomerErrorCheck("Navn");
//						}
//					});
//				
//			
//			panel.add(customerNameLbl);
//			panel.add(customerNameTxt);
//			panel.add(customerMailLbl);
//			panel.add(customerMailTxt);
//			panel.add(customerAddressLbl);
//			panel.add(customerAddressTxt);
//			panel.add(editCustomer);
//			panel.add(cancelChange);
//			
//			frame.add(panel);
//			frame.setVisible(true);
//			
//					selectFrame.setSize(400	,100);		
//			selectFrame.setLocation(frame.getX() + frame.getWidth(), frame.getY());
//		
//			JPanel selectPanel = new JPanel();		
//			JLabel inputTxt = new JLabel("Produkt ID");
//			JTextArea inputArea = new JTextArea(1,10);
//					
//			JButton select = new JButton("V�lg Produkt");		
//			select.addActionListener(new ActionListener() { 
//				@Override 
//				public void actionPerformed(ActionEvent event) { 				
//						loadProduct(Integer.parseInt(inputArea.getText()));
//					}
//				});
//			selectPanel.add(inputTxt);
//			selectPanel.add(inputArea);
//			selectPanel.add(select);
//			
//			selectFrame.add(selectPanel);
//			selectFrame.setVisible(true);
//		}
		
		 // Email format checker
		public boolean isEmailValid(String email) {

			if	(	email.contains(".de") ||
					email.contains(".se") ||
					email.contains(".com") || 
					email.contains(".dk")  &&
					email.contains("@")	) {
				return true;
			}
			return false;

}               //converters
                public String intToString(int number) {
                    String convNumber;
                    convNumber = "" + number;
                    return convNumber;
                }
                public String doubleToString(double number) {
                    String convNumber;
                    convNumber = "" + number;
                    return convNumber;
                }

                
                public int StringToInt(String number) {
                    int convNumber;
                    convNumber = Integer.parseInt(number);
                    return convNumber;
                }
                
                public double StringToDouble(String number) {
                    double convNumber;
                    convNumber = Double.parseDouble(number);
                    return convNumber;
                }
                
                
		// Phonenumber format checker
		public boolean isPhonenumberValid(String phoneNumber) {
                        return phoneNumber.length() == 8;
                }
		
//		public void clearCustomerFields() {
//			customerNameTxt.setText("");
//		    customerMailTxt.setText("");
//			customerAddressTxt.setText("");
//		}
//		
//		public void resetCustomerLabels() {
//			customerNameLbl.setForeground(Color.BLACK);
//			customerMailLbl.setForeground(Color.BLACK);
//			customerAddressLbl.setForeground(Color.BLACK);
//		}
//		
		


}
