package Services;

import DTOs.CustomerDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    ArrayList<CustomerDTO> customers;
    static CustomerDTO loggedInCustomer = null;
    Scanner sc = new Scanner(System.in);
    Functions functions = new Functions();
    BufferedReader br = new BufferedReader(new FileReader("src/File/customers.txt"));
    FileService fileService;

    public CustomerService(FileService fileService) throws FileNotFoundException {

        this.fileService = fileService;
    }

    void getCustomers() throws IOException {
        customers = fileService.getCustomers();
    }

    void showAllCustomers() throws IOException {
        this.customers = fileService.getCustomers();
        int counter = 0;
        String line;
        for (CustomerDTO customer : customers) {
            showDetails(customer);

            System.out.println();
            counter++;
        }
        if (counter == 0) {
            System.out.println("No customers exist");
        }
    }

    void login() throws IOException {
        this.customers = fileService.getCustomers();
        String loginsection;
        String customerPinCode;
        int section;
        String line;
        System.out.println("How would you like to enter? : ");
        System.out.println("\t1. SMS");
        System.out.println("\t2. Email");
        section = sc.nextInt();
        while (section != 1 && section != 2) {
            System.out.println("Please enter a valid option");
            section = sc.nextInt();
        }
        if (section == 1) {
            sc.nextLine();
            System.out.println("Please enter your phone");
            loginsection = sc.nextLine();
            while (!functions.isNumber(loginsection) && !functions.isCorretPrefix(loginsection)) {
                System.out.println("Please enter a valid phone number");
                loginsection = sc.nextLine();
            }
            for (CustomerDTO customer : customers) {
                if (customer.getNotificationType().equals("SMS")) {
                    if (loginsection.equals(customer.getPhoneNumber())) {
                        System.out.println("For security purposes, you need to enter the pin code of your account: ");
                        customerPinCode = sc.nextLine();
                        while (!customerPinCode.equals(customer.getPinCode())) {
                            if (customerPinCode.equals("0000")) {
                                break;
                            }
                            while (customerPinCode.length() != 4) {
                                System.out.println("Invalid PIN CODE, Pincode must be 4 digits!!!");
                                customerPinCode = sc.nextLine();
                            }
                            System.out.println("If you want to exit enter 0000");
                            System.out.println("Incorrect PIN CODE, Try again");
                            customerPinCode = sc.nextLine();

                        }
                        loggedInCustomer = customer;
                        System.out.println("You have successfully logged in.!");
                        break;
                    }
                }

            }
        } else {
            sc.nextLine();
            System.out.println("Please enter your email");
            loginsection = sc.nextLine();
            while (true) {
                if (loginsection.length() < 12) {
                    System.out.println("Please enter the correct email format: example@gmail.com");
                    loginsection = sc.nextLine();
                } else if ((!loginsection.substring(loginsection.length() - 10).equals("@gmail.com")) || !functions.isString(loginsection.substring(0, loginsection.length() - 10))) {
                    System.out.println("Supported email types: Gmail");
                    System.out.println("Please enter the correct email format: example@gmail.com");
                    loginsection = sc.nextLine();
                } else {
                    break;
                }
            }
            for (CustomerDTO customer : customers) {
                if (customer.getNotificationType().equals("Email")) {
                    if (loginsection.equals(customer.getEmail())) {
                        System.out.println("For security purposes, you need to enter the pin code of your account: ");
                        customerPinCode = sc.nextLine();
                        while (!customerPinCode.equals(customer.getPinCode())) {
                            if (customerPinCode.equals("0000")) {
                                break;
                            }
                            while (customerPinCode.length() != 4) {
                                System.out.println("Invalid PIN CODE, Pincode must be 4 digits!!!");
                                customerPinCode = sc.nextLine();
                            }
                            System.out.println("If you want to exit enter 0000");
                            System.out.println("Incorrect PIN CODE, Try again");
                            customerPinCode = sc.nextLine();

                        }
                        loggedInCustomer = customer;
                        System.out.println("You have successfully logged in.!");
                        break;
                    }

                }
            }
        }
    }

    void addCustomer() throws IOException {
        this.customers = fileService.getCustomers();
        // Set Name
        CustomerDTO customer = new CustomerDTO();
        String tempName;

        System.out.println("Enter Customer Name : ");
        tempName = sc.nextLine();
        while ((tempName.length() < 3 || tempName.length() > 10) || !functions.isString(tempName)) {

            System.out.println("Customer name must be between 3-16 characters!!!");
            System.out.println("Enter customer name again : ");
            tempName = sc.nextLine();
        }
        customer.setName(tempName);

        // Set surname

        System.out.println("Enter Customer Surname : ");
        String tempSurname = sc.nextLine();
        while ((tempSurname.length() < 6 || tempSurname.length() > 10) || !functions.isString(tempSurname)) {

            System.out.println("Customer name must be between 6-16 characters!!!");
            System.out.println("Enter customer surname again : ");
            tempSurname = sc.nextLine();
        }
        customer.setSurname(tempSurname);
        // Set birthday

        System.out.println("Enter Customer Birth year : ");
        int tempBirthDate = sc.nextInt();
        while (tempBirthDate < 1000 || tempBirthDate > 9999) {
            System.out.println("Invalid Birth year !");
            tempBirthDate = sc.nextInt();
        }
        customer.setBirthDate(tempBirthDate);

        //  Set CardNumber
        boolean isTaken = false;
        String tempCardNumber;
        sc.nextLine();
        System.out.println("Enter your card number : ");
        tempCardNumber = sc.nextLine();

        while (tempCardNumber.length() != 16 || !functions.isNumber(tempCardNumber)) {
            System.out.println("Card number must be 16 digits!!!");
            System.out.println("Enter card number again : ");
            tempCardNumber = sc.nextLine();
        }
        customer.setCardNumber(functions.isTakenCardNumber(tempCardNumber));

        // set pin code

        String tempPinCode;
        System.out.println("Enter your pincode : ");
        tempPinCode = sc.nextLine();
        while (tempPinCode.length() != 4 || !functions.isNumber(tempPinCode)) {
            System.out.println("Pincode must be 4 digits!!!");
            System.out.println("Enter your pincode again : ");
            tempPinCode = sc.nextLine();
        }
        customer.setPinCode(tempPinCode);

        // choose Notification type         @gmail.com

        int chooseNotification;
        System.out.println("Which way should we send you the notification?\n\t 1- SMS\n\t 2- Email");
        chooseNotification = sc.nextInt();
        while (chooseNotification < 1 || chooseNotification > 2) {
            System.out.println("Please enter a number between 1 and 2!!!");
            chooseNotification = sc.nextInt();
        }
        sc.nextLine();
        if (chooseNotification == 1) {
            System.out.println("Please enter your phone number : ");
            customer.setNotificationType("SMS");
            String phone = sc.nextLine();
            while (!functions.isCorretPrefix(phone) || phone.length() != 10 || !functions.isNumber(phone)) {
                if (phone.length() != 10 || !functions.isNumber(phone)) {
                    System.out.println("Phone number must be 10 digits!");
                } else if (!functions.isCorretPrefix(phone)) {
                    System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t075\t077");
                }
                System.out.println("Please enter your phone number again : ");
                phone = sc.nextLine();
            }

            customer.setPhoneNumber(functions.isTakenPhone(phone));
            customer.setEmail("null");
        } else {
            System.out.println("Please enter your email address : ");
            customer.setNotificationType("Email");
            String email = sc.nextLine();
            while (true) {
                if (email.length() < 12) {
                    System.out.println("Please enter the correct email format: example@gmail.com");
                    email = sc.nextLine();
                } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !functions.isString(email.substring(0, email.length() - 10))) {
                    System.out.println("Supported email types: Gmail");
                    System.out.println("Please enter the correct email format: example@gmail.com");
                    email = sc.nextLine();
                } else {
                    break;
                }
            }
            customer.setPhoneNumber("null");
            customer.setEmail((functions.isTakenEmail(email)).toLowerCase());

        }
        customers.add(customer);
        fileService.loadCustomer(customers);

    }

    void searchCustomer() throws IOException {
        this.customers = fileService.getCustomers();
        int count = 0;
        int choice;
        System.out.println("Please enter keyword: ");
        String keyword = sc.nextLine().toLowerCase();
        for (CustomerDTO customer : customers) {
            if (customer.toString().toLowerCase().contains(keyword)) {
                showDetails(customer);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No customer was found.");
            System.out.println("Are you sure you want to continue searching?");
            System.out.println("\t1 - Yes\n\t2 - No");
            choice = sc.nextInt();
            while (choice < 1 || choice > 2) {
                System.out.println("Please enter a number between 1 and 2");
                choice = sc.nextInt();
            }
            if (choice == 1) {
                sc.nextLine();
                searchCustomer();
            } else {
                return;
            }
        }
    }

    void updateCustomer() throws IOException {

        int changeChoice;
        System.out.println("What kind of changes do you want to make?");
        System.out.println("\t 1. Change Name");
        System.out.println("\t 2. Change Pin Code");
        System.out.println("\t 3. Change Phone/Email");
        System.out.println("\t 4. Change Notification type");
        System.out.println("\t 5. Exit");
        changeChoice = sc.nextInt();
        while (changeChoice < 1 || changeChoice > 5) {
            System.out.println("Please enter a number between 1 and 5:");
            changeChoice = sc.nextInt();
        }
        int areYouSure;
        boolean isTaken = false;
        //  Change Name
        if (changeChoice == 1) {
            System.out.println("Your current name is " + loggedInCustomer.getName());
            System.out.println("Are you sure to change name?\n\t 1 - Yes\n\t 2 - No");
            areYouSure = sc.nextInt();
            while (areYouSure < 1 || areYouSure > 2) {
                System.out.println("Please enter a number between 1 and 2:");
                areYouSure = sc.nextInt();
            }
            if (areYouSure == 1) {
                String tempName = sc.nextLine();
                while ((tempName.length() < 4 || tempName.length() > 10) && functions.isValid(tempName)) {
                    System.out.println("Customer name must be between 4-16 characters!!!");
                    System.out.println("Enter customer name again : ");
                    tempName = sc.nextLine();
                }
                for (CustomerDTO customer : customers) {
                    if (customer.toString().equals(loggedInCustomer.toString())) {
                        customer.setName(tempName);
                    }
                }
                fileService.loadCustomer(customers);
            }

        }


        // Change pin code
        else if (changeChoice == 2) {
            sc.nextLine();
            System.out.println("Your pincode is " + loggedInCustomer.getPinCode());
            System.out.println("Are you sure to change pincode?\n\t 1 - Yes\n\t 2 - No");
            areYouSure = sc.nextInt();
            while (areYouSure < 1 || areYouSure > 2) {
                System.out.println("Please enter a number between 1 and 2:");
                areYouSure = sc.nextInt();
            }
            if (areYouSure == 1) {
                String tempPinCode;
                sc.nextLine();
                System.out.println("Enter your pincode : ");
                tempPinCode = sc.nextLine();
                while (tempPinCode.length() != 4 || !functions.isNumber(tempPinCode) || tempPinCode.equals("0000")) {
                    System.out.println("Pincode must be 4 digits!!!");
                    System.out.println("Enter your pincode again : ");
                    tempPinCode = sc.nextLine();
                }
                for (CustomerDTO customer : customers) {
                    if (customer.toString().equals(loggedInCustomer.toString())) {
                        customer.setPinCode(tempPinCode);
                    }
                }
                fileService.loadCustomer(customers);
            }
        }

        // change email/phone
        else if (changeChoice == 3) {
            String customerNotificationType = loggedInCustomer.getNotificationType();
            System.out.println("ID : " + loggedInCustomer.getCustomerId() + "\nYour notification type is " + customerNotificationType + " and your contact is " + (customerNotificationType.equals("Email") ? loggedInCustomer.getEmail() : loggedInCustomer.getPhoneNumber()));
            System.out.println("Are you sure to change name?\n\t 1 - Yes\n\t 2 - No");
            areYouSure = sc.nextInt();
            while (areYouSure < 1 || areYouSure > 2) {
                System.out.println("Please enter a number between 1 and 2:");
                areYouSure = sc.nextInt();
            }
            sc.nextLine();
            if (areYouSure == 1) {
                System.out.println("Enter your new" + (customerNotificationType.equals("SMS") ? " phone number: " : " email adress: "));
                if (customerNotificationType.equals("SMS")) {
                    System.out.println("Please enter your phone number : ");
                    loggedInCustomer.setNotificationType("SMS");
                    String phone = sc.nextLine();
                    while (!functions.isCorretPrefix(phone) || phone.length() != 10 || !functions.isNumber(phone)) {
                        if (phone.length() != 10 || !functions.isNumber(phone)) {
                            System.out.println("Phone number must be 10 digits!");
                        } else if (!functions.isCorretPrefix(phone)) {
                            System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t075\t077");
                        }
                        System.out.println("Please enter your phone number again : ");
                        phone = sc.nextLine();
                    }
                    for (CustomerDTO customer : customers) {
                        if (customer.toString().equals(loggedInCustomer.toString())) {
                            customer.setPhoneNumber(phone);
                        }
                    }
                    fileService.loadCustomer(customers);

                } else {
                    System.out.println("Please enter your new email address : ");
                    loggedInCustomer.setNotificationType("Email");
                    String email = sc.nextLine();
                    while (true) {
                        if (email.length() < 12) {
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !functions.isString(email.substring(0, email.length() - 10))) {
                            System.out.println("Supported email types: Gmail");
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else {
                            break;
                        }
                    }
                    sc.nextLine();


                    for (CustomerDTO customer : customers) {
                        if (customer.toString().equals(loggedInCustomer.toString())) {
                            customer.setEmail(email);
                        }
                    }
                    fileService.loadCustomer(customers);
                }
            }
        }

        // change notification type
        else if (changeChoice == 4) {
            System.out.println("ID : " + loggedInCustomer.getCustomerId() + "\nYour notification type is " + loggedInCustomer.getNotificationType());
            System.out.println("Are you sure to change notification type?\n\t 1 - Yes\n\t 2 - No");
            areYouSure = sc.nextInt();
            while (areYouSure < 1 || areYouSure > 2) {
                System.out.println("Please enter a number between 1 and 2:");
                areYouSure = sc.nextInt();
            }
            if (areYouSure == 1) {
                loggedInCustomer.setNotificationType(loggedInCustomer.getNotificationType().equals("Email") ? "SMS" : "Email");
                sc.nextLine();
                if (loggedInCustomer.getNotificationType().equals("SMS")) {
                    System.out.println("Enter your new phone number: ");
                    loggedInCustomer.setNotificationType("SMS");
                    String phone = sc.nextLine();
                    while (!functions.isCorretPrefix(phone) || phone.length() != 10 || !functions.isNumber(phone)) {
                        if (phone.length() != 10 || !functions.isNumber(phone)) {
                            System.out.println("Phone number must be 10 digits!");
                        } else if (!functions.isCorretPrefix(phone)) {
                            System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t077");
                        }
                        System.out.println("Please enter your phone number again : ");
                        phone = sc.nextLine();
                    }


                    for (CustomerDTO customer : customers) {
                        if (customer.toString().equals(loggedInCustomer.toString())) {
                            customer.setPhoneNumber(functions.isTakenPhone(phone));
                            customer.setEmail("null");
                        }
                    }
                    fileService.loadCustomer(customers);
                } else {
                    System.out.println("Please enter your new email address : ");
                    loggedInCustomer.setNotificationType("Email");
                    String email = sc.nextLine();
                    while (true) {
                        if (email.length() < 12) {
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !functions.isString(email.substring(0, email.length() - 10))) {
                            System.out.println("Supported email types: Gmail");
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else {
                            break;
                        }
                    }
                    for (CustomerDTO customer : customers) {
                        if (customer.toString().equals(loggedInCustomer.toString())) {
                            customer.setEmail(functions.isTakenEmail(email));
                            customer.setPhoneNumber("null");
                        }
                    }
                    fileService.loadCustomer(customers);
                }

            }
        }

    }

    void deleteCustomer() throws IOException {
        for (CustomerDTO customer : customers) {
            if (customer.toString().equals(loggedInCustomer.toString())) {
                customers.remove(customer);
                break;
            }
        }
        System.out.println("Your account was successfully deleted.");
        fileService.loadCustomer(customers);
        loggedInCustomer = null;
    }

    void showDetails(CustomerDTO customer) {
        this.customers = fileService.getCustomers();
        System.out.println("\tYour account name: " + customer.getName());
        System.out.println("\tYour account surname: " + customer.getSurname());
        System.out.println("\tType of notification in your account: " + customer.getNotificationType());
        System.out.println("\t" + (customer.getNotificationType().equals("SMS") ? "Your phone number: " + customer.getPhoneNumber() : "Your email adress: " + customer.getEmail()));
        System.out.println("\tYour card number: " + customer.getCardNumber());
        System.out.println("\tYour card birth year: " + customer.getBirthDate());
        System.out.println("\tYour account balance: " + customer.getBalance());
        System.out.println();
        System.out.println();
    }
    public void setLoggedInCustomer(CustomerDTO customer) {
        loggedInCustomer = customer;
    }
}

