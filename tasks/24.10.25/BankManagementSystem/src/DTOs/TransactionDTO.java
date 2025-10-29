package DTOs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDTO {
    private static int id = 1;
    private String transactionID;
    private String transactionName;



    private String userDescription;
    private double userBalance;
    private String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private String transactionDate;
    public TransactionDTO() {
        transactionID = String.valueOf(id++);
        transactionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }



    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
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
