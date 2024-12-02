package it2c.borja_rrs;

import java.util.Scanner;

public class Customer {

    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();

        // Validate first name (alphabetic characters only)
        String fname;
        while (true) {
            System.out.println("First Name: ");
            fname = sc.next();
            if (fname.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid input! First name must only contain letters.");
            }
        }

        // Validate last name (alphabetic characters only)
        String lname;
        while (true) {
            System.out.println("Last Name: ");
            lname = sc.next();
            if (lname.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid input! Last name must only contain letters.");
            }
        }

        // Validate address (not empty)
        sc.nextLine(); // Consume newline left by next()
        String address;
        while (true) {
            System.out.println("Address: ");
            address = sc.nextLine();
            if (!address.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Address cannot be empty.");
            }
        }

        // Validate contact number (digits only and length check)
        String contact_number;
        while (true) {
            System.out.print("Customer's Contact Number (digits only, 11 digits expected): ");
            contact_number = sc.next();
            if (contact_number.matches("\\d{11}")) { // Assuming a 11-digit phone number format
                break;
            } else {
                System.out.println("Invalid input! Please enter exactly 11 digits.");
            }
        }

        // SQL Insert
        String sql = "INSERT INTO Customer (fname, lname, address, contactnum) VALUES (?, ?, ?, ?)";
        conf.addRecords(sql, fname, lname, address, contact_number);
    }

    public void viewCustomer() {
        String cqry = "SELECT * FROM Customer";
        String[] customerHeaders = {"ID", "First Name", "Last Name", "Address", "Contact Number"};
        String[] customerColumns = {"customer_id", "fname", "lname", "address", "contactnum"};
        Config conf = new Config();
        conf.viewRecords(cqry, customerHeaders, customerColumns);
    }

    private void updateRecords() {
        Scanner sc = new Scanner(System.in);

        // Validate customer ID (ensure it's a positive integer)
        int id;
        while (true) {
            System.out.println("Enter Customer ID: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Customer ID must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer for Customer ID.");
                sc.next(); // Consume invalid input
            }
        }

        // Validate address (not empty)
        sc.nextLine(); // Consume newline
        String address;
        while (true) {
            System.out.println("Enter the new Address: ");
            address = sc.nextLine();
            if (!address.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Address cannot be empty.");
            }
        }

        // Validate contact number (digits only and length check)
        String contact;
        while (true) {
            System.out.print("Enter the new Contact Number (11 digits only): ");
            contact = sc.next();
            if (contact.matches("\\d{11}")) { 
                break;
            } else {
                System.out.println("Invalid input! Please enter exactly 11 digits.");
            }
        }

        // SQL Update
        String qry = "UPDATE Customer SET address = ?, contactnum = ? WHERE customer_id = ?";
        Config conf = new Config();
        conf.updateRecords(qry, address, contact, id);
    }

    public void deleteRecords() {
        Scanner input = new Scanner(System.in);

        // Validate customer ID (ensure it's a positive integer)
        int id;
        while (true) {
            System.out.print("Enter ID to Delete: ");
            if (input.hasNextInt()) {
                id = input.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Customer ID must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                input.next(); // Consume invalid input
            }
        }

        Config conf = new Config();
        String sqlDelete = "DELETE FROM Customer WHERE customer_id = ?";
        conf.deleteRecords(sqlDelete, id);
    }

    public void customerOp() {
        Scanner input = new Scanner(System.in);
        String response;

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. REMOVE");

            System.out.println("Enter action: ");
            int action = input.nextInt();
            Customer customer = new Customer();
            switch (action) {
                case 1:
                    customer.addCustomer();
                    break;
                case 2:
                    customer.viewCustomer();
                    break;
                case 3:
                    customer.updateRecords();
                    break;
                case 4:
                    customer.viewCustomer();
                    customer.deleteRecords();
                    customer.viewCustomer();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }

            System.out.print("Continue? (yes/no): ");
            response = input.next();
        } while (response.equalsIgnoreCase("yes"));
        System.out.println("Thank you! See you again.");
    }
}
