package Services;

import DTOs.CustomerDTO;
import DTOs.TransactionDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionService {
    Scanner sc = new Scanner(System.in);
    FileService fileService;

    public TransactionService(FileService fileService) {
        this.fileService = fileService;
    }

    void showAllTransaction(CustomerDTO customer) {
        int choice;
        int counter = 0;
        ArrayList<TransactionDTO> transactions = fileService.getTransactions();
        for (TransactionDTO transaction : transactions) {
            if (customer.toString().contains(transaction.getSenderCardNumber()) || customer.toString().contains(transaction.getReceiverCardNumber())) {
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("There are no transactions in this customer");
        } else {
            for (TransactionDTO transaction : transactions) {
                if (customer.toString().contains(transaction.getSenderCardNumber())) {
                    System.out.println("\n\n=============----=============");
                    System.out.println("Transaction date: " + transaction.getTransactionDate());
                    System.out.println("From: ****" + transaction.getSenderCardNumber().substring(transaction.getSenderCardNumber().length() - 4));
                    System.out.println("To: ****" + transaction.getReceiverCardNumber().substring(transaction.getReceiverCardNumber().length() - 4));
                    System.out.println("Amount: -" + transaction.getTransactionName());
                    System.out.println("Your account balance: " + transaction.getSenderBalance());
                    if (transaction.getUserDescription() != null) {
                        System.out.println("Description: " + transaction.getUserDescription());
                    }
                    System.out.println("=============----=============");
                } else if (customer.toString().contains(transaction.getReceiverCardNumber())) {
                    System.out.println("\n\n=============----=============");
                    System.out.println("Transaction date: " + transaction.getTransactionDate());
                    System.out.println("From: ****" + transaction.getSenderCardNumber().substring(transaction.getSenderCardNumber().length() - 4));
                    System.out.println("To: ****" + transaction.getReceiverCardNumber().substring(transaction.getReceiverCardNumber().length() - 4));
                    System.out.println("Amount: +" + transaction.getTransactionName());
                    System.out.println("Your account balance: " + transaction.getReceiverBalance());
                    if (transaction.getUserDescription() != null) {
                        System.out.println("Description: " + transaction.getUserDescription());
                    }
                    System.out.println("=============----=============");
                }
            }

        }
    }
}


