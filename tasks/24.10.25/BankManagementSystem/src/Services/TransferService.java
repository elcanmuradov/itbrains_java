package Services;

import DTOs.CustomerDTO;
import DTOs.TransactionDTO;

import java.util.Scanner;

public class TransferService {
    Scanner sc = new Scanner(System.in);
    static Notification email = new Email();
    static Notification sms = new SMS();
    public TransferService(Notification email, Notification sms) {
        this.email = email;
        this.sms = sms;
    }
    boolean isNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) < '0' || number.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    void cardToCard(CustomerDTO loggedInCustomer) {

        String tempCardNumber, description;
        double amount;
        int choice;
        int count = 0;
        loggedInCustomer.setAttempts(5);
        System.out.println("Your account balance: " + loggedInCustomer.getBalance());
        System.out.println();
        System.out.println("Enter your own card number to send money: ");
        tempCardNumber = sc.nextLine();

        while (tempCardNumber.length() != 16 || !isNumber(tempCardNumber)) {
            System.out.println("Card number must be 16 digits!!!");
            System.out.println("Enter card number again : ");
            tempCardNumber = sc.nextLine();
        }

        while (tempCardNumber.equals(loggedInCustomer.getCardNumber())) {
            System.out.println("You cannot send money to your own account. Please enter the correct card number.");
            tempCardNumber = sc.nextLine();
        }
        while (count == 0) {
            for (CustomerDTO customer : CustomerService.customers){
                if (customer.getCardNumber().equals(tempCardNumber)) {
                    count = 1;
                    break;
                }
            }
            System.out.println("The bank account cannot be found. Please try again.");
            tempCardNumber = sc.nextLine();
        }
        for (CustomerDTO tempcustomer : CustomerService.customers) {
            if (tempcustomer.getCardNumber().equals(tempCardNumber)) {
                System.out.println("Enter the amount you want to send: ");
                amount = sc.nextDouble();
                while (amount <= 0 || amount > loggedInCustomer.getBalance()) {
                    if (amount <= 0) {
                        System.out.println("Invalid amount !");
                    } else if (amount > loggedInCustomer.getBalance()) {
                        System.out.println("Insufficient balance !");
                    }
                    System.out.println("Enter the amount you want to send: ");
                    amount = sc.nextDouble();
                }
                loggedInCustomer.setBalance(loggedInCustomer.getBalance() - amount);
                tempcustomer.setBalance(tempcustomer.getBalance() + amount);
                System.out.println("Do you want add description? ");
                System.out.println("\t1. Yes \n\t2. No");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    System.out.println("Enter your description: ");
                    description = sc.nextLine();
                } else {
                    description = null;
                }
                // Logged
                TransactionDTO transaction1 = new TransactionDTO();
                transaction1.setTransactionName("-" + amount);
                transaction1.setUserBalance(loggedInCustomer.getBalance());
                transaction1.setUserDescription(description);
                transaction1.setCardNumber(tempCardNumber);
                loggedInCustomer.addTransaction(transaction1);
                // Card
                TransactionDTO transaction2 = new TransactionDTO();
                transaction2.setTransactionName("+" + amount);
                transaction2.setUserBalance(tempcustomer.getBalance());
                transaction2.setUserDescription(description);
                transaction2.setCardNumber(tempCardNumber);
                tempcustomer.addTransaction(transaction2);


                if (loggedInCustomer.getNotificationType().equals("Email")) {
                    email.sendNotification(loggedInCustomer.getEmail(), amount);
                }else{
                    sms.sendNotification(loggedInCustomer.getPhoneNumber(), amount);
                }

            }
        }

    }
}
