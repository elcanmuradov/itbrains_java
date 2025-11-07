package DTOs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDTO {
    private static int id = 1;
    private String transactionID;
    private String transactionName;



    private String userDescription;
    private double senderBalance;
    private double receiverBalance;
    private String senderCardNumber;
    private String receiverCardNumber;

    public String getReceiverCardNumber() {
        return receiverCardNumber;
    }

    public void setReceiverCardNumber(String receiverCardNumber) {
        this.receiverCardNumber = receiverCardNumber;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    private String transactionDate;
    public TransactionDTO() {
        transactionID = String.valueOf(id++);
        transactionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getReceiverBalance() {
        return receiverBalance;
    }

    public void setReceiverBalance(double receiverBalance) {
        this.receiverBalance = receiverBalance;
    }

    public double getSenderBalance() {
        return senderBalance;
    }

    public void setSenderBalance(double senderBalance) {
        this.senderBalance = senderBalance;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
}
