
import Exceptions.InvalidAmountException;
import Services.FileService;
import Services.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InvalidAmountException, IOException {
        Notification email = new Email();
        Notification sms = new SMS();
        FileService fileService = new FileService();
        CustomerService customerService = new CustomerService(fileService);
        TransactionService transactionService = new TransactionService(fileService);
        TransferService transferService = new TransferService(email, sms, fileService);
        ApplicationService applicationService = new ApplicationService(customerService, transactionService, transferService);
        applicationService.startApplication();

    }
}
