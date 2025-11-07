package Services;

import DTOs.CustomerDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Functions {
    Scanner sc = new Scanner(System.in);
    boolean isTaken = false;
    BufferedReader br = new BufferedReader(new FileReader("src/File/customers.txt"));

    public Functions() throws FileNotFoundException {
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

    String isTakenCardNumber(String str) throws IOException {
        String fileName = "src/File/customers.txt";
        String line;

        while ((line = br.readLine()) != null) {
            if (line.contains(str)) {
                System.out.println("This card number is already taken!, Choose another one");
                str = sc.nextLine();
                break;
            }
        }

        while (isTaken) {
            while (str.length() != 16 || !isNumber(str)) {
                System.out.println("Card number must be 16 digits!!!");
                System.out.println("Enter card number again : ");
                str = sc.nextLine();
            }

            try (BufferedReader tempReader = new BufferedReader(new FileReader(fileName))) {
                String tempLine;
                isTaken = false;
                while ((tempLine = tempReader.readLine()) != null) {
                    if (tempLine.contains(str)) {
                        isTaken = true;
                        System.out.println("This card number is already taken!, Choose another one");
                        str = sc.nextLine();
                        break;
                    }
                }
            }
        }
        return str;
    }

    String isTakenEmail(String email) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {

            if (line.contains(email)) {
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
            while ((line = br.readLine()) != null) {

                if (line.contains(email)) {
                    isTaken = true;
                    System.out.println("This Email is already taken!, Choose another one");
                    email = sc.nextLine();
                    break;
                } else {
                    isTaken = false;

                }
            }
        }
        return email;
    }

    String isTakenPhone(String phone) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {

            if (line.contains(phone)) {
                isTaken = true;
                System.out.println("This Phone number is already taken!, Choose another one");
                phone = sc.nextLine();
                break;

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
            while ((line = br.readLine()) != null) {
                if (line.contains(phone)) {
                    isTaken = true;
                    System.out.println("This phone number is already taken!, Choose another one");
                    phone = sc.nextLine();
                    break;
                } else {
                    isTaken = false;
                }
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
