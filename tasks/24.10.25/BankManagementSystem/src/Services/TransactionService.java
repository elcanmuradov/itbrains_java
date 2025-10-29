package Services;

import DTOs.CustomerDTO;
import DTOs.TransactionDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class TransactionService {
    Scanner sc = new Scanner(System.in);
    void showAllTransaction(CustomerDTO customer) {
        int choice;
        ArrayList<TransactionDTO> transactions = customer.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions in this customer");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + ". Transaction " + transactions.get(i).getTransactionDate() + "  &  " + transactions.get(i).getTransactionName());
            }
        }

        System.out.println("Would you like to see your account transactions in detail?");
        System.out.println("\t1. Yes \n\t2. No");
        choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1){
            System.out.println("Enter the number of the transaction to view.");
            int transactionNumber = sc.nextInt();
            while(transactionNumber < 1 || transactionNumber > transactions.size()){
                System.out.println("Please enter a number between 1 and " + (transactions.size()-1));
                transactionNumber = sc.nextInt();
            }
            showDetailed(transactionNumber);
        }else{
            return;
        }
    }
    void showDetailed(int transactionNumber) {
        ArrayList<TransactionDTO> transactions = CustomerService.loggedInCustomer.getTransactions();
        TransactionDTO transaction = transactions.get(transactionNumber - 1);
        System.out.println("Transaction number " + transactionNumber);
        System.out.println("Transaction date: " + transaction.getTransactionDate());
        System.out.println("From: ****" + transaction.getCardNumber().substring(transaction.getCardNumber().length() - 4));
        System.out.println("Amount: " + transaction.getTransactionName());
        System.out.println("Your account balance: " + transaction.getUserBalance());
        System.out.println("Description: " + transaction.getUserDescription());
    }
}
