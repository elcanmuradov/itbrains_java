package Services;

import DTOs.CustomerDTO;
import DTOs.TransactionDTO;
import Exceptions.InvalidAgeException;
import Exceptions.InvalidAmountException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TransferService {

    private FileService fileService;
    BufferedReader br = new BufferedReader(new FileReader("src/File/customers.txt"));
    Scanner sc = new Scanner(System.in);
    static Notification email = new Email();
    static Notification sms = new SMS();

    public TransferService(Notification email, Notification sms, FileService fileService) throws FileNotFoundException {
        this.email = email;
        this.sms = sms;
        this.fileService = fileService;
    }

    boolean isNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) < '0' || number.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    void cardToCard(CustomerDTO loggedInCustomer) throws InvalidAmountException, IOException {
        int birthYear = loggedInCustomer.getBirthDate();
        ArrayList<CustomerDTO> customers = fileService.getCustomers();
        if (2025 - birthYear < 18) {
            throw new InvalidAgeException("Acces denied. You must be 18 years old.");
        }

        String tempCardNumber, description;
        double amount;
        int choice;
        int count = 0;
        System.out.println("Your account balance: " + loggedInCustomer.getBalance());
        System.out.println();
        System.out.println("Enter card number to send money: ");
        tempCardNumber = sc.nextLine();
        while (count == 0) {
            while (tempCardNumber.length() != 16 || !isNumber(tempCardNumber)) {
                System.out.println("Card number must be 16 digits!!!");
                System.out.println("Enter card number again : ");
                tempCardNumber = sc.nextLine();
            }

            while (tempCardNumber.equals(loggedInCustomer.getCardNumber())) {
                System.out.println("You cannot send money to your own account. Please enter the correct card number.");
                tempCardNumber = sc.nextLine();
            }
            for (CustomerDTO customer : customers) {
                if (customer.toString().contains(tempCardNumber)) {
                    count = 1;
                    break;
                }
            }
            if (count == 0) {
                System.out.println("The bank account cannot be found. Please try again.");
                tempCardNumber = sc.nextLine();
            }
        }
        for (CustomerDTO customer : customers) {
            if (customer.toString().contains(tempCardNumber)) {
                System.out.println("Enter the amount you want to send: ");
                amount = sc.nextDouble();
                while (amount <= 0 || amount > loggedInCustomer.getBalance()) {
                    if (amount <= 0) {
                        throw new InvalidAmountException("Amount can't be negative");
                    } else if (amount > loggedInCustomer.getBalance()) {
                        throw new InvalidAmountException("Your balance is insufficient!");
                    }
                    System.out.println("Enter the amount you want to send: ");
                    amount = sc.nextDouble();
                }
                for (CustomerDTO isloggedcustomer : customers) {
                    if (isloggedcustomer.toString().equals(loggedInCustomer.toString())) {
                        isloggedcustomer.setBalance(isloggedcustomer.getBalance() - amount);
                        loggedInCustomer.setBalance(isloggedcustomer.getBalance());
                    }
                }
                customer.setBalance(customer.getBalance() + amount);
                fileService.loadCustomer(customers);
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
                //Logged
                TransactionDTO transaction = new TransactionDTO();
                transaction.setTransactionName("" + amount);
                transaction.setSenderBalance(loggedInCustomer.getBalance());
                transaction.setReceiverBalance(customer.getBalance());
                transaction.setUserDescription(description);
                transaction.setSenderCardNumber(loggedInCustomer.getCardNumber());
                transaction.setReceiverCardNumber(tempCardNumber);
                fileService.loadTransactions(transaction);

                if (loggedInCustomer.getNotificationType().equals("Email")) {
                    email.sendNotification(loggedInCustomer.getEmail(), amount);
                } else {
                    sms.sendNotification(loggedInCustomer.getPhoneNumber(), amount);
                }
                br.close();

            }
        }

    }
}
