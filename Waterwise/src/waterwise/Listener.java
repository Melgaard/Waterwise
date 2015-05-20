package waterwise;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

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
