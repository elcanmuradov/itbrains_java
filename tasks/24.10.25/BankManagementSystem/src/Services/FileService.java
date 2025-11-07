package Services;

import DTOs.CustomerDTO;
import DTOs.TransactionDTO;

import java.io.*;
import java.util.ArrayList;

public class FileService {
    private CustomerDTO customer;
    private String customerFilePath;
    private String transactionFilePath;

    public FileService() {
        this.customerFilePath = "src/File/customers.txt";
        this.transactionFilePath = "src/File/transactions.txt";
    }


    public void loadCustomer(ArrayList<CustomerDTO> customers) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(customerFilePath))) {
            for (CustomerDTO customer : customers) {
                bw.write(customer.getName() + "," + customer.getSurname() + "," + customer.getCardNumber() + "," + customer.getPinCode() + "," + customer.getNotificationType() + "," + customer.getEmail() + "," + customer.getPhoneNumber() + "," + customer.getBirthDate() + "," + customer.getBalance());
                bw.newLine();
            }
        }
    }

    public ArrayList<CustomerDTO> getCustomers() {
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(customerFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                CustomerDTO customer = new CustomerDTO();
                customer.setName(arr[0]);
                customer.setSurname(arr[1]);
                customer.setCardNumber(arr[2]);
                customer.setPinCode(arr[3]);
                customer.setNotificationType(arr[4]);
                customer.setEmail(arr[5]);
                customer.setPhoneNumber(arr[6]);
                customer.setBirthDate(Integer.parseInt(arr[7]));
                customer.setBalance(Double.parseDouble(arr[8]));
                customers.add(customer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public void loadTransactions(TransactionDTO transaction) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(transactionFilePath,true))) {
                bw.write(transaction.getSenderCardNumber() + ',' + transaction.getReceiverCardNumber() + ',' + transaction.getTransactionName() + ',' + transaction.getUserDescription() + ',' + transaction.getSenderBalance() + ',' + transaction.getReceiverBalance() + ',' + transaction.getTransactionDate());

        }
    }

    public ArrayList<TransactionDTO> getTransactions() {
        ArrayList<TransactionDTO> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(transactionFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                TransactionDTO transaction = new TransactionDTO();
                transaction.setSenderCardNumber(arr[0]);
                transaction.setReceiverCardNumber(arr[1]);
                transaction.setTransactionName(arr[2]);
                transaction.setUserDescription(arr[3]);
                transaction.setSenderBalance(Double.parseDouble(arr[4]));
                transaction.setReceiverBalance(Double.parseDouble(arr[5]));
                transaction.setTransactionDate(arr[6]);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }


}