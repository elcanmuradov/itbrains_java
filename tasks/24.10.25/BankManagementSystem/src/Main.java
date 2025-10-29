import Services.*;

public class Main {
    public static void main(String[] args) {
        Notification email = new Email();
        Notification sms = new SMS();
        CustomerService customerService = new CustomerService();
        TransactionService transactionService = new TransactionService();
        TransferService transferService = new TransferService(email,sms);
        ApplicationService applicationService = new ApplicationService(customerService, transactionService, transferService);
        applicationService.startApplication();

    }
}
/*
Do sms/email notification

 */