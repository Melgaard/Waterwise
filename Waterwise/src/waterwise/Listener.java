package waterwise;

import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.AbstractAction;

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

            orderSaveTo.setOrderID(ofSaveFrom.orderID);
            orderSaveTo.setDeliveryType(ofSaveFrom.deliveryType);
            orderSaveTo.setPaymentType(ofSaveFrom.paymentType);
            orderSaveTo.setCustomerEmail(ofSaveFrom.customerEmail);
            

        }

    }

}
