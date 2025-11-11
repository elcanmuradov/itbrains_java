package Services;

import DTO.CustomerDTO;
import DTO.NotificationChannel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    FileService fs;
    Functions functions;
    ArrayList<CustomerDTO> customers;

    public CustomerService(FileService fs, Functions f) {
        this.fs = fs;
        this.functions = f;
    }

    Scanner sc = new Scanner(System.in);


    void addCustomer() throws IOException {
        CustomerDTO customer = new CustomerDTO();
        int id = fs.readCustomers().size() + 1;
        customer.setCustomerId(id);
        System.out.println("Enter Customer Name");
        String name = sc.nextLine();
        while (!functions.isString(name)) {
            System.out.println("Invalid Customer Name");
            name = sc.nextLine();
        }
        customer.setName(name);

        System.out.println("Enter Customer Email");
        String email = sc.nextLine();
        customer.setEmail(functions.isTakenEmail(email));

        System.out.println("Enter Customer Phone");
        String phone = sc.nextLine();
        customer.setPhone(functions.isTakenPhone(phone));

        System.out.println("Select notification channel");
        System.out.println("\t1- SMS\n\t2- EMAIL");
        int channel = sc.nextInt();
        while (channel < 1 || channel > 2) {

            System.out.println("Invalid channel");
            System.out.println("Please select a valid channel");
            channel = sc.nextInt();

        }
        if (channel == 1) {
            customer.setNotificationChannel(NotificationChannel.SMS);
        } else {
            customer.setNotificationChannel(NotificationChannel.EMAIL);
        }
        customers = fs.readCustomers();
        customers.add(customer);
        fs.writeCustomer(customers);
    }
}
