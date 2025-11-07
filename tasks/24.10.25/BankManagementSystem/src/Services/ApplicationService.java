package Services;

import Exceptions.InvalidAmountException;

import java.io.IOException;
import java.util.Scanner;

import static Services.CustomerService.loggedInCustomer;

public class ApplicationService {
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final TransferService transferService;
    static Scanner sc = new Scanner(System.in);

    public ApplicationService(CustomerService customerService, TransactionService transactionService,  TransferService transferService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.transferService = transferService;
    }

    public void startApplication() throws InvalidAmountException, IOException {
        while (true) {

            int choice;
            if(loggedInCustomer == null) {
                System.out.println("Choose the action");
                System.out.println("\t 1. Create Account");
                System.out.println("\t 2. Login");
                System.out.println("\t 3. Show all customers");
                System.out.println("\t 4. Search customer");
                System.out.println("\t 0. Stop Application");
                choice = sc.nextInt();
                if (choice == 1) {
                    customerService.addCustomer();
                }else if (choice == 2) {
                    customerService.login();
                }else if (choice == 3) {
                    customerService.showAllCustomers();
                }else if (choice == 4) {
                    customerService.searchCustomer();
                }
                else if (choice == 0) {
                    customerService.setLoggedInCustomer(null);
                    return;
                }
            }else  {
                System.out.println("Choose the action");
                System.out.println("\t 1. Update Account");
                System.out.println("\t 2. Delete Account");
                System.out.println("\t 3. Send Money");
                System.out.println("\t 4. Show Details");
                System.out.println("\t 5. Show transactions");
                System.out.println("\t 6. Logout");
                choice = sc.nextInt();
                if (choice == 1) {
                    customerService.updateCustomer();
                } else if (choice == 2) {
                    customerService.deleteCustomer();
                } else if (choice == 3) {
                    transferService.cardToCard(loggedInCustomer);
                } else if (choice == 4) {
                    customerService.showDetails(loggedInCustomer);
                } else if (choice == 5) {
                    transactionService.showAllTransaction(loggedInCustomer);
                }
                if (choice == 6) {
                    loggedInCustomer = null;
                }

            }
        }
    }
}
