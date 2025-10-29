package Services;

public class Email implements Notification {
    public void sendNotification(String email, double amount) {
        System.out.println(email + ": "+ amount  +"AZN has been deducted from your balance.");
    }
}
