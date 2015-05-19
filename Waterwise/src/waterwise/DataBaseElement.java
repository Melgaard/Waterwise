package waterwise;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataBaseElement {

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public void Update() {
        System.out.println("Update method has not been Overridden correctly");
    }

    public void Delete() {
        System.out.println("Delete method has not been Overridden correctly");
    }
}
