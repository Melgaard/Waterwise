package waterwise;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 *
 * @author BlottoG
 */
public class Listener {

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

        @Override
        public void actionPerformed(ActionEvent ae) {

        }

    }

    public class PrintLabelButton extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {

        }

    }

    public class SaveEditButton extends AbstractAction {

        Order orderSaveTo;
        OrderFrame ofSaveFrom;

        public SaveEditButton(OrderFrame orderframeToSave) {

            ofSaveFrom = orderframeToSave;
            orderSaveTo = ofSaveFrom.orderShown;

        }

        @Override
        public void actionPerformed(ActionEvent ae) {

            //Check for error in typed data
            System.out.println("something needs doing! - SaveEditButton");
            
            //Set order data to the orderframe data
            orderSaveTo.setOrderID(ofSaveFrom.orderIDField.getText());
            orderSaveTo.setListOfProducts(ofSaveFrom.listOfProducts);
            orderSaveTo.setDeliveryType(ofSaveFrom.deliveryTypeField.getText());
            orderSaveTo.setPaymentType(ofSaveFrom.paymentTypeField.getText());
            
            
            
            
            if (orderSaveTo instanceof Outgoing){
//                orderSaveTo.setSupplier(supplierField.getText());
            }
            else if (orderSaveTo instanceof Incoming){
//                orderSaveTo.setCustomerPhonenumber(customerPhonenumberField.getText());
            }
            
            orderSaveTo.Update();
            ofSaveFrom.dispose();
        }

    }
    public class DisposeFrameButton extends AbstractAction {

        JFrame ftd;
        
        public DisposeFrameButton(JFrame frameToDispose){
            ftd = frameToDispose;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            ftd.dispose();
            
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
