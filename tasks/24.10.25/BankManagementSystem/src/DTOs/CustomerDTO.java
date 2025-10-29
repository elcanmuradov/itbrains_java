package DTOs;

import java.util.ArrayList;

public class CustomerDTO {
    private int attempts = 5;

    static private int id = 1;

    private int customerId;

    private String name;

    private String surname;

    private String notificationType;

    private String email = null;

    private String phoneNumber = null;

    private String cardNumber;

    private boolean isBlocked = false;
    // private String cardExpiryDate;
    private String pinCode;

    private ArrayList<TransactionDTO> transactions = new ArrayList<>();

    private double balance = 1000;

    public CustomerDTO() {
        customerId = id++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public int getAttempts() {
        return attempts;
    }
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public ArrayList<TransactionDTO> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void addTransaction(TransactionDTO transaction) {
        transactions.add(transaction);
    }
}
