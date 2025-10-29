package Services;

import DTOs.CustomerDTO;
import DTOs.TransactionDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    static ArrayList<CustomerDTO> customers = new ArrayList<>();
    static CustomerDTO loggedInCustomer = null;
    Scanner sc = new Scanner(System.in);


    boolean isNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) < '0' || number.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    boolean isValid(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                return false;
            }
        }
        return true;
    }

    boolean isString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!isChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    boolean isChar(char ch) {
        if (!Character.isLetter(ch)) {
            return false;
        }
        return true;
    }

    boolean isCorretPrefix(String phone) {
        if ((phone.substring(0, 3).equals("050")) || (phone.substring(0, 3).equals("051")) || (phone.substring(0, 3).equals("055")) || (phone.substring(0, 3).equals("070")) || (phone.substring(0, 3).equals("077"))) {
            return true;
        } else {
            return false;
        }
    }

    void showAllCustomers() {
        int counter = 0;
        for (CustomerDTO c : customers) {
            System.out.println("=======\tID: " + c.getCustomerId() + "\t=======");
            System.out.println("\tName: " + c.getName());
            System.out.println("\tSurname: " + c.getSurname());
            System.out.println("\tCard Number: " + c.getCardNumber());
            System.out.println("\tNotification type: " + c.getNotificationType());
            System.out.println("\t" + (c.getNotificationType().equals("SMS") ? "Phone Number: " + c.getPhoneNumber() : "Email: " + c.getEmail()));
            System.out.println("\tBalance: " + c.getBalance());
            System.out.println();
            System.out.println();
            counter++;
        }
        if (counter == 0) {
            System.out.println("No customers exist");
        }
    }

    void login() {
        int customerId;
        String customerPinCode;

        System.out.println("Enter Customer ID : ");
        customerId = sc.nextInt() - 1;
        if (customerId == -1) {
            return;
        }
        while (customerId > customers.size() || customerId < 0) {
            System.out.println("Invalid ID, Please enter your customer ID:");
            customerId = sc.nextInt() - 1;
        }

        CustomerDTO customer = customers.get(customerId);
        if (customer.getIsBlocked()) {
            System.out.println("Customer is blocked!");
            return;
        }
        sc.nextLine();
        System.out.println("For security purposes, you need to enter the pin code of your account: ");
        customerPinCode = sc.nextLine();
        while (!customerPinCode.equals(customer.getPinCode())) {
            if (customer.getIsBlocked()) {
                System.out.println("Customer is blocked!");
                return;
            }
            while (customerPinCode.length() != 4) {
                System.out.println("Invalid PIN CODE, Pincode must be 4 digits!!!");
                customerPinCode = sc.nextLine();
            }
            if (!customerPinCode.equals(customer.getPinCode())) {
                System.out.println("Your pin code is incorrect, you have " + customer.getAttempts() + " more attempts left. Otherwise your account will be blocked");
                customer.setAttempts(customer.getAttempts() - 1);
                customerPinCode = sc.nextLine();
                while (customerPinCode.length() != 4 || !isNumber(customerPinCode)) {
                    System.out.println("Invalid PIN CODE, Pincode must be 4 digits!!!");
                    customerPinCode = sc.nextLine();
                }
            }
            if (customer.getAttempts() == 0) {
                System.out.println("Your account has been blocked! Please contact the bank staff");
                customer.setIsBlocked(true);
            }
        }
        customer.setAttempts(5);
        loggedInCustomer = customer;
        System.out.println("You have successfully logged in.!");
    }

    void addCustomer() {
        // Set Name
        CustomerDTO customer = new CustomerDTO();
        String tempName;

        System.out.println("Enter Customer Name : ");
        tempName = sc.nextLine();
        while ((tempName.length() < 3 || tempName.length() > 10) || !isString(tempName)) {

            System.out.println("Customer name must be between 3-16 characters!!!");
            System.out.println("Enter customer name again : ");
            tempName = sc.nextLine();
        }
        customer.setName(tempName);

        // Set surname

        System.out.println("Enter Customer Surname : ");
        String tempSurname = sc.nextLine();
        while ((tempSurname.length() < 6 || tempSurname.length() > 10) || !isString(tempSurname)) {

            System.out.println("Customer name must be between 6-16 characters!!!");
            System.out.println("Enter customer surname again : ");
            tempSurname = sc.nextLine();
        }
        customer.setSurname(tempSurname);


        //  Set CardNumber
        boolean isTaken = false;
        String tempCardNumber;
        System.out.println("Enter your card number : ");
        tempCardNumber = sc.nextLine();

        while (tempCardNumber.length() != 16 || !isNumber(tempCardNumber)) {
            System.out.println("Card number must be 16 digits!!!");
            System.out.println("Enter card number again : ");
            tempCardNumber = sc.nextLine();
        }
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCardNumber().equals(tempCardNumber)) {
                isTaken = true;
                System.out.println("This card number is already taken!, Choose another one");
                tempCardNumber = sc.nextLine();
                break;
            }
        }
        while (isTaken) {
            while (tempCardNumber.length() != 16 || !isNumber(tempCardNumber)) {
                System.out.println("Card number must be 16 digits!!!");
                System.out.println("Enter card number again : ");
                tempCardNumber = sc.nextLine();
            }
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getCardNumber().equals(tempCardNumber)) {
                    isTaken = true;
                    System.out.println("This card number is already taken!, Choose another one");
                    break;
                } else {
                    isTaken = false;
                }
            }
        }
        customer.setCardNumber(tempCardNumber);

        // set pin code

        String tempPinCode;
        System.out.println("Enter your pincode : ");
        tempPinCode = sc.nextLine();
        while (tempPinCode.length() != 4 || !isNumber(tempPinCode)) {
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
            while (!isCorretPrefix(phone) || phone.length() != 10 || !isNumber(phone)) {
                if (phone.length() != 10 || !isNumber(phone)) {
                    System.out.println("Phone number must be 10 digits!");
                } else if (!isCorretPrefix(phone)) {
                    System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t075\t077");
                }
                System.out.println("Please enter your phone number again : ");
                phone = sc.nextLine();
            }
            for (int i = 0; i < customers.size(); i++) {
                if (!customers.get(i).getPhoneNumber().equals("null")) {
                    if (customers.get(i).getPhoneNumber().equals(phone)) {
                        isTaken = true;
                        System.out.println("This Phone number is already taken!, Choose another one");
                        phone = sc.nextLine();
                        break;
                    }
                }
            }
            while (isTaken) {
                while (!isCorretPrefix(phone) || phone.length() != 10 || !isNumber(phone)) {
                    if (phone.length() != 10 || !isNumber(phone)) {
                        System.out.println("Phone number must be 10 digits!");
                    } else if (!isCorretPrefix(phone)) {
                        System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t077");
                    }
                    System.out.println("Please enter your phone number again : ");
                    phone = sc.nextLine();
                }
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getPhoneNumber().equals(phone)) {
                        isTaken = true;
                        System.out.println("This phone number is already taken!, Choose another one");
                        phone = sc.nextLine();
                        break;
                    } else {
                        isTaken = false;
                    }
                }
            }
            customer.setPhoneNumber(phone);
            customer.setEmail("null");
        } else {
            System.out.println("Please enter your email address : ");
            customer.setNotificationType("Email");
            String email = sc.nextLine();
            while (true) {
                if (email.length() < 12) {
                    System.out.println("Please enter the correct email format: example@gmail.com");
                    email = sc.nextLine();
                } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !isString(email.substring(0, email.length() - 10))) {
                    System.out.println("Supported email types: Gmail");
                    System.out.println("Please enter the correct email format: example@gmail.com");
                    email = sc.nextLine();
                } else {
                    break;
                }
            }
            for (int i = 0; i < customers.size(); i++) {
                if (!customers.get(i).getEmail().equals("null")) {
                    if (customers.get(i).getEmail().equals(email)) {
                        isTaken = true;
                        System.out.println("This Email is already taken!, Choose another one");
                        email = sc.nextLine();
                        break;
                    }
                }
            }
            while (isTaken) {
                while (true) {
                    if (email.length() < 12) {
                        System.out.println("Please enter the correct email format: example@gmail.com");
                        email = sc.nextLine();
                    } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !isString(email.substring(0, email.length() - 10))) {
                        System.out.println("Supported email types: Gmail");
                        System.out.println("Please enter the correct email format: example@gmail.com");
                        email = sc.nextLine();
                    } else {
                        break;
                    }
                }
                for (int i = 0; i < customers.size(); i++) {
                    if (!customers.get(i).getEmail().equals("null")) {
                        if (customers.get(i).getEmail().equals(email)) {
                            isTaken = true;
                            System.out.println("This Email is already taken!, Choose another one");
                            email = sc.nextLine();
                            break;
                        } else {
                            isTaken = false;
                        }
                    }
                }
            }
            customer.setPhoneNumber("null");
            customer.setEmail(email.toLowerCase());
        }
        System.out.println("ID: " + customer.getCustomerId() + "\tCustomer has been successfully created. ! ");
        customers.add(customer);

    }

    void searchCustomer() {
        int count = 0;
        int choice;
        System.out.println("Please enter keyword: ");
        String keyword = sc.nextLine().toLowerCase();
        for (CustomerDTO customer : customers) {
            if ((customer.getName().toLowerCase().contains(keyword)) || (customer.getSurname().toLowerCase().contains(keyword))) {
                showDetails(customer);
                count++;
            }
            if (customer.getEmail().equals("null")) {
                if (customer.getEmail().equals(keyword)) {
                    showDetails(customer);
                    count++;
                }
            }

        }
        sc.nextLine();
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

    void updateCustomer() {

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
            System.out.println("ID : " + loggedInCustomer.getCustomerId() + "\nYour current name is " + loggedInCustomer.getName());
            System.out.println("Are you sure to change name?\n\t 1 - Yes\n\t 2 - No");
            areYouSure = sc.nextInt();
            while (areYouSure < 1 || areYouSure > 2) {
                System.out.println("Please enter a number between 1 and 2:");
                areYouSure = sc.nextInt();
            }
            if (areYouSure == 1) {
                String tempName = sc.nextLine();
                while ((tempName.length() < 4 || tempName.length() > 10) && isValid(tempName)) {
                    System.out.println("Customer name must be between 4-16 characters!!!");
                    System.out.println("Enter customer name again : ");
                    tempName = sc.nextLine();
                }
                loggedInCustomer.setName(tempName);


            }

        }


        // Change pin code
        else if (changeChoice == 2) {
            sc.nextLine();
            System.out.println("ID : " + loggedInCustomer.getCustomerId() + "\nYour pincode is " + loggedInCustomer.getPinCode());
            System.out.println("Are you sure to change name?\n\t 1 - Yes\n\t 2 - No");
            areYouSure = sc.nextInt();
            while (areYouSure < 1 || areYouSure > 2) {
                System.out.println("Please enter a number between 1 and 2:");
                areYouSure = sc.nextInt();
            }
            if (areYouSure == 1) {
                String tempPinCode;
                System.out.println("Enter your pincode : ");
                tempPinCode = sc.nextLine();
                while (tempPinCode.length() != 4 || !isNumber(tempPinCode)) {
                    System.out.println("Pincode must be 4 digits!!!");
                    System.out.println("Enter your pincode again : ");
                    tempPinCode = sc.nextLine();
                }
                loggedInCustomer.setPinCode(tempPinCode);
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
                    while (!isCorretPrefix(phone) || phone.length() != 10 || !isNumber(phone)) {
                        if (phone.length() != 10 || !isNumber(phone)) {
                            System.out.println("Phone number must be 10 digits!");
                        } else if (!isCorretPrefix(phone)) {
                            System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t075\t077");
                        }
                        System.out.println("Please enter your phone number again : ");
                        phone = sc.nextLine();
                    }
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getPhoneNumber().equals("null")) {
                            if (customers.get(i).getPhoneNumber().equals(phone)) {
                                isTaken = true;
                                System.out.println("This Phone number is already taken!, Choose another one");
                                phone = sc.nextLine();
                                break;
                            }
                        }
                    }
                    sc.nextLine();
                    while (isTaken) {
                        while (!isCorretPrefix(phone) || phone.length() != 10 || !isNumber(phone)) {
                            if (phone.length() != 10 || !isNumber(phone)) {
                                System.out.println("Phone number must be 10 digits!");
                            } else if (!isCorretPrefix(phone)) {
                                System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t075\t077");
                            }
                            System.out.println("Please enter your phone number again : ");
                            phone = sc.nextLine();
                        }
                        for (int i = 0; i < customers.size(); i++) {
                            if (customers.get(i).getPhoneNumber().equals(phone)) {
                                isTaken = true;
                                System.out.println("This phone number is already taken!, Choose another one");
                                phone = sc.nextLine();
                                break;
                            } else {
                                isTaken = false;
                            }
                        }
                    }
                    loggedInCustomer.setPhoneNumber(phone);
                } else {
                    System.out.println("Please enter your new email address : ");
                    loggedInCustomer.setNotificationType("Email");
                    String email = sc.nextLine();
                    while (true) {
                        if (email.length() < 12) {
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !isString(email.substring(0, email.length() - 10))) {
                            System.out.println("Supported email types: Gmail");
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else {
                            break;
                        }
                    }sc.nextLine();

                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getEmail().equals("null")) {
                            if (customers.get(i).getEmail().equals(email)) {
                                isTaken = true;
                                System.out.println("This Email is already taken!, Choose another one");
                                email = sc.nextLine();
                                break;
                            }
                        }
                    }
                    while (isTaken) {
                        while (true) {
                            if (email.length() < 12) {
                                System.out.println("Please enter the correct email format: example@gmail.com");
                                email = sc.nextLine();
                            } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !isString(email.substring(0, email.length() - 10))) {
                                System.out.println("Supported email types: Gmail");
                                System.out.println("Please enter the correct email format: example@gmail.com");
                                email = sc.nextLine();
                            } else {
                                break;
                            }
                        }
                        for (int i = 0; i < customers.size(); i++) {
                            if (customers.get(i).getEmail().equals(email)) {
                                isTaken = true;
                                System.out.println("This Email is already taken!, Choose another one");
                                email = sc.nextLine();
                                break;
                            } else {
                                isTaken = false;
                            }

                        }
                    }
                    loggedInCustomer.setEmail(email);
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
                    while (!isCorretPrefix(phone) || phone.length() != 10 || !isNumber(phone)) {
                        if (phone.length() != 10 || !isNumber(phone)) {
                            System.out.println("Phone number must be 10 digits!");
                        } else if (!isCorretPrefix(phone)) {
                            System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t077");
                        }
                        System.out.println("Please enter your phone number again : ");
                        phone = sc.nextLine();
                    }
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getPhoneNumber().equals("null")) {
                            if (customers.get(i).getPhoneNumber().equals(phone)) {
                                isTaken = true;
                                System.out.println("This Phone number is already taken!, Choose another one");
                                phone = sc.nextLine();
                                break;
                            }
                        }
                    }
                    while (isTaken) {
                        while (!isCorretPrefix(phone) || phone.length() != 10 || !isNumber(phone)) {
                            if (phone.length() != 10 || !isNumber(phone)) {
                                System.out.println("Phone number must be 10 digits!");
                            } else if (!isCorretPrefix(phone)) {
                                System.out.println("Unsupported prefix! \n Use:\t050\t051\t055\t070\t075\t077");
                            }
                            System.out.println("Please enter your phone number again : ");
                            phone = sc.nextLine();
                        }
                        for (int i = 0; i < customers.size(); i++) {
                            if (customers.get(i).getPhoneNumber().equals(phone)) {
                                isTaken = true;
                                System.out.println("This phone number is already taken!, Choose another one");
                                phone = sc.nextLine();
                                break;
                            } else {
                                isTaken = false;
                            }
                        }
                    }
                    loggedInCustomer.setPhoneNumber(phone);
                    loggedInCustomer.setEmail("null");
                } else {
                    System.out.println("Please enter your new email address : ");
                    loggedInCustomer.setNotificationType("Email");
                    String email = sc.nextLine();
                    while (true) {
                        if (email.length() < 12) {
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !isString(email.substring(0, email.length() - 10))) {
                            System.out.println("Supported email types: Gmail");
                            System.out.println("Please enter the correct email format: example@gmail.com");
                            email = sc.nextLine();
                        } else {
                            break;
                        }
                    }
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getEmail().equals("null")) {
                            if (customers.get(i).getEmail().equals(email)) {
                                isTaken = true;
                                System.out.println("This Email is already taken!, Choose another one");
                                email = sc.nextLine();
                                break;
                            }
                        }
                    }
                    while (isTaken) {
                        while (true) {
                            if (email.length() < 12) {
                                System.out.println("Please enter the correct email format: example@gmail.com");
                                email = sc.nextLine();
                            } else if ((!email.substring(email.length() - 10).equals("@gmail.com")) || !isString(email.substring(0, email.length() - 10))) {
                                System.out.println("Supported email types: Gmail");
                                System.out.println("Please enter the correct email format: example@gmail.com");
                                email = sc.nextLine();
                            } else {
                                break;
                            }
                        }
                        for (int i = 0; i < customers.size(); i++) {
                            if (customers.get(i).getEmail().equals(email)) {
                                isTaken = true;
                                System.out.println("This Email is already taken!, Choose another one");
                                email = sc.nextLine();
                                break;
                            } else {
                                isTaken = false;
                            }

                        }
                    }
                    loggedInCustomer.setEmail(email);
                    loggedInCustomer.setPhoneNumber("null");
                }

            }
        }

    }

    void deleteCustomer() {

        customers.remove(loggedInCustomer.getCustomerId() - 1);
        System.out.println("Your account has been succesfully deleted!");
// 1 3
        for (int i = loggedInCustomer.getCustomerId(); i <= customers.size(); i++) {
            CustomerDTO tempcustomer = customers.get(i - 1);
            tempcustomer.setCustomerId(i);
        }
        loggedInCustomer = null;
    }

    void showDetails(CustomerDTO customer) {

        System.out.println("=======\tID: " + customer.getCustomerId() + "=======");
        System.out.println("\tYour account name: " + customer.getName());
        System.out.println("\tYour account surname: " + customer.getSurname());
        System.out.println("\tType of notification in your account: " + customer.getNotificationType());
        System.out.println("\t" + (customer.getNotificationType().equals("SMS") ? "Your phone number: " + customer.getPhoneNumber() : "Your email adress: " + customer.getEmail()));
        System.out.println("\tYour card number: " + customer.getCardNumber());
        System.out.println("\tYour account balance: " + customer.getBalance());
        System.out.println();
        System.out.println();
    }

    public void setLoggedInCustomer(CustomerDTO loggedInCustomer) {
        CustomerService.loggedInCustomer = loggedInCustomer;
    }
}

