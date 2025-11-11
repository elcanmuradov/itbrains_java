package Services;

import DTO.CustomerDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Functions {
    Scanner sc = new Scanner(System.in);
    boolean isTaken = false;
    FileService fs;

    public Functions(FileService fs) {
        this.fs = fs;
    }


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


    String isTakenEmail(String email) throws IOException {
        ArrayList<CustomerDTO> customers = fs.readCustomers();
        for (CustomerDTO c : customers) {


            if (c.getEmail().equals(email)) {
                isTaken = true;
                System.out.println("This Email is already taken!, Choose another one");
                email = sc.nextLine();
                break;
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
            for (CustomerDTO c : customers) {
                if (c.getEmail().equals(email)) {
                    isTaken = true;
                    System.out.println("This Email is already taken!, Choose another one");
                    email = sc.nextLine();
                    break;
                }
                isTaken = false;
            }
        }
            return email;

    }

    String isTakenPhone(String phone) throws IOException {
        ArrayList<CustomerDTO> customers = fs.readCustomers();
        for (CustomerDTO customer : customers) {
            if (customer.getPhone().equals(phone)) {
                System.out.println("This Phone is already taken!, Choose another one");
                phone = sc.nextLine();
                isTaken = true;
                break;
            }
        }
        while (isTaken) {
            while (!isNumber(phone) || phone.length() != 10) {
                System.out.println("Please enter a valid phone number");
                phone = sc.nextLine();
            }
            for (CustomerDTO customer : customers) {
                if (customer.getPhone().equals(phone)) {
                    isTaken = true;
                    System.out.println("This Phone is already taken!, Choose another one");
                    phone = sc.nextLine();
                    break;
                }
                isTaken = false;
            }
        }

        return phone;
    }

    boolean isCorretPrefix(String phone) {
        if ((phone.substring(0, 3).equals("050")) || (phone.substring(0, 3).equals("051")) || (phone.substring(0, 3).equals("055")) || (phone.substring(0, 3).equals("070")) || (phone.substring(0, 3).equals("077"))) {
            return true;
        } else {
            return false;
        }
    }


}
