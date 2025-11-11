package Services;

import java.io.IOException;
import java.util.Scanner;

public class ApplicationService {
    Scanner scanner = new Scanner(System.in);
    CustomerService cs;
    TicketService ts;
    MovieService ms;

    public ApplicationService(CustomerService cs, TicketService ts, MovieService ms) {
        this.cs = cs;
        this.ts = ts;
        this.ms = ms;
    }

    public void startApplication() throws IOException {
        while (true) {
            System.out.println("1. Add customer");
            System.out.println("2. Add movie");
            System.out.println("3. Purchase ticket");
            System.out.println("4. Cancel ticket");
            System.out.println("5. View tickets");
            System.out.println("6. View movies");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    cs.addCustomer();
                    break;
                case 2:
                    ms.addMovie();
                    break;
                case 3:
                    ts.purchaseTicket();
                    break;
                case 4:
                    ts.cancelTicket();
                    break;
                case 5:
                    ts.viewTickets();
                    break;
                case 6:
                    ms.viewMovies();
                    break;
            }

        }
    }
}
