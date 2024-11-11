package it2c.borja_rrs;

import java.util.Scanner;

public class Customer {

    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        System.out.println("First Name: ");
        String fname = sc.next();
        System.out.println("Last Name: ");
        String lname = sc.next();
        System.out.println("address: ");
        String address = sc.next();
        System.out.println("contact-number: ");
        String contact_number = sc.next();

        String sql = "INSERT INTO Customer (fname, lname, address, contactnum) VALUES (?, ?, ?, ?)";
        conf.addRecords(sql, fname, lname, address, contact_number);
    }

    public void viewCustomer() {
        String cqry = "SELECT * FROM Customer";
        String[] votersHeaders = {"ID", "First Name", "Last Name", "Address", "Contact Number"};
        String[] votersColumns = {"customer_id", "fname", "lname", "address", "contactnum"};
        Config conf = new Config();
        conf.viewRecords(cqry, votersHeaders, votersColumns);
    }

    private void updateRecords() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer ID ");
        int id = sc.nextInt();

        System.out.println("Enter the new Address");
        String address = sc.next();

        System.out.println("Enter the new Contact Number");
        String contact = sc.next();

        String qry = "UPDATE Customer SET address = ?, contactnum = ? WHERE customer_id = ?";

        Config conf = new Config();
        conf.updateRecords(qry, address, contact, id);
    }

    public void deleteRecords() {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter ID to Delete: ");
        int id = input.nextInt();

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

                System.out.println("Enter actions: ");
                int action = input.nextInt();
                Customer burja = new Customer();
                switch (action) {
                    case 1:
                        burja.addCustomer();
                        break;
                    case 2:
                        burja.viewCustomer();
                        break;
                    case 3:
                        burja.updateRecords();
                        break;
                    case 4:
                        burja.viewCustomer();
                        burja.deleteRecords();
                        burja.viewCustomer();
                        break;

                }
                System.out.print("Continue? (yes/no)");
                response = input.next();
            } while (response.equalsIgnoreCase("yes"));
            System.out.println("Ty");

        }
    }

