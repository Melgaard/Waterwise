package waterwise;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author BlottoG
 */
public class Listener {

    Controller controller = new Controller();
    
    public Listener() {

    }

    //Inner classes
    public class ResetViewButton extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {

        }

    }

    public class EditOrderButton extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {

        }

    }

    public class ChangeStatusButton extends AbstractAction {

        JTable table;
        String cTTF;
        
        public ChangeStatusButton(JTable table, String classToTestFor){
            
            this.table = table;
            cTTF = classToTestFor;
            
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {

            Order c = (Order) controller.getElementFromTable(table, cTTF);
            controller.changeStatusMethod(c);
            //Repaint dur ikke QQ, men ellers virker det
//            table.repaint();
            
            
        }

    }

    public class PrintLabelButton extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {

        }

    }
    
       public class addProductButton extends AbstractAction {

        OrderFrame addProductAmount;
        String selectedItem;
        String selectedAmountString;
        Integer selectedAmount;

        public addProductButton(OrderFrame addProduct) {
            addProductAmount = addProduct;
        }
     
        @Override

        public void actionPerformed(ActionEvent ae) {

            try {

                selectedItem = addProductAmount.productbox.getSelectedItem().toString();
                selectedAmountString = addProductAmount.amountField.getText();
                selectedAmount = Integer.parseInt(selectedAmountString);

                Product temp = new Product("emil");

                for (Product p : ElementListCollection.getPList()) {
                    if (p.getProductName().equals(selectedItem)) {
                        temp = p;
                    }

                }
                addProductAmount.chosenProducts.add(temp);
                System.out.println("fra listener" + addProductAmount.chosenProducts.size());
                addProductAmount.listOfProducts.put(temp, selectedAmount);
                addProductAmount.productComboList.remove(temp.getProductName());
                addProductAmount.updateProductList();
                addProductAmount.updateProductComboBox();

            } catch (NullPointerException npe) {
                System.out.println("nullPointer");
                JOptionPane.showMessageDialog(null, "Ingen produkter valgt eller tilgængelige.");
            } catch (NumberFormatException nfe) {
                System.out.println("numberformat");
                JOptionPane.showMessageDialog(null, "Du skal vælge et antal vare.");
            }

        }
    }
       
       public class addProductFrameButton extends AbstractAction {

        AddProductFrame addProductFrameAmount;
        String selectedItem;
        String selectedAmountString;
        Integer selectedAmount;

        public addProductFrameButton(AddProductFrame addProduct) {
            addProductFrameAmount = addProduct;
        }
     

        @Override

        public void actionPerformed(ActionEvent ae) {

            try {

                selectedItem = addProductFrameAmount.productbox.getSelectedItem().toString();
                selectedAmountString = addProductFrameAmount.amountField.getText();
                selectedAmount = Integer.parseInt(selectedAmountString);

                Product temp = new Product("emil");

                for (Product p : ElementListCollection.getPList()) {
                    if (p.getProductName().equals(selectedItem)) {
                        temp = p;
                    }

                }
                addProductFrameAmount.chosenProducts.add(temp);
                System.out.println("fra listener" + addProductFrameAmount.chosenProducts.size());
                addProductFrameAmount.listOfProducts.put(temp, selectedAmount);
                addProductFrameAmount.productComboList.remove(temp.getProductName());
                addProductFrameAmount.updateProductList();
                addProductFrameAmount.updateProductComboBox();

            } catch (NullPointerException npe) {
                System.out.println("nullPointer");
                JOptionPane.showMessageDialog(null, "Ingen produkter valgt eller tilgængelige.");
            } catch (NumberFormatException nfe) {
                System.out.println("numberformat");
                JOptionPane.showMessageDialog(null, "Du skal vælge et antal vare.");
            }

        }
    }

    public class RemoveFromTableButton extends AbstractAction {

        OrderFrame removeProduct;
        int selectedRow;
        String selectedProduct;
        
        public RemoveFromTableButton(OrderFrame removeProductFromTable) {
            removeProduct = removeProductFromTable;

        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                selectedRow = removeProduct.productTable.getSelectedRow();
                if (removeProduct.productTable.getValueAt(selectedRow, 1) == null) {
                    JOptionPane.showMessageDialog(null, "Du har markeret et tomt felt.");

                }

                ArrayList<Product> tableProducts = new ArrayList<>(removeProduct.chosenProducts);
                for(Product p : tableProducts){
                    if(p.getProductName().equals(removeProduct.productTable.getValueAt(selectedRow, 1))){
                        removeProduct.chosenProducts.remove(p);
                        selectedProduct = p.getProductName();
                    }
                    
                }
                removeProduct.productComboList.add(selectedProduct);
                removeProduct.updateProductComboBox();
                removeProduct.updateProductList();
                
                
                System.out.println(removeProduct.productTable.getValueAt(selectedRow, 1));
                
                
                
            } catch (IndexOutOfBoundsException iob) {
                JOptionPane.showMessageDialog(null, "Du skal markere et produkt.");
            }

        }
    }

    public class SaveEditButton extends AbstractAction {

        OrderFrame ofSaveFrom;
        
        public SaveEditButton(OrderFrame ofSaveFrom) {
            this.ofSaveFrom = ofSaveFrom;
        }        
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            controller.saveEditMethod(ofSaveFrom);
        }

    }

    public class DisposeFrameButton extends AbstractAction {

        JFrame ftd;

        public DisposeFrameButton(JFrame frameToDispose) {
            ftd = frameToDispose;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {

            ftd.dispose();

        }

    }
    public class createNewProduct extends AbstractAction {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            NewProductFrame pF = new NewProductFrame();
        }
    }
    
    public class addProduct extends AbstractAction {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            AddProductFrame aPF = new AddProductFrame();
        }
    }
    
    public class createNewIncoming extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Incoming i = new Incoming();
        }

    }

    public class createNewOutgoing extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Outgoing o = new Outgoing();
        }

    }

}
