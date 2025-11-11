
import Services.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileService fs = new FileService();
        Functions f = new Functions(fs);
        MovieService ms = new MovieService(fs);
        TicketService ts = new TicketService(fs);
        CustomerService cs = new CustomerService(fs,f);
        ApplicationService app = new ApplicationService(cs,ts,ms);

        app.startApplication();
    }
}
