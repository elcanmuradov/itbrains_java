package Services;

public class SMS  implements Notification {
    @Override
    public void sendNotification(String phoneNumber, double amount) {
        System.out.println(phoneNumber + ": "+ amount  +"AZN has been deducted from your balance.");
    }


}
