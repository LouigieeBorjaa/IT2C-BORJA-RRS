package it2c.borja_rrs;

import java.util.Scanner;

class Reports {

    Config conf = new Config();

    public void generalReport() {

        Reservations reserve = new Reservations();

        reserve.viewReservations();

    }

    public void individualReport() {

        Scanner sc = new Scanner(System.in);
        Config conf = new Config();

        System.out.print("Enter Customer ID for Individual Report: ");
        int customerId = sc.nextInt();

        // Customer Details
        String customerQuery = "SELECT * FROM Customer WHERE customer_id = " + customerId;
        String[] customerHeaders = {"Customer ID", "First Name", "Last Name", "Address", "Contact Number"};
        String[] customerColumns = {"customer_id", "fname", "lname", "address", "contactnum"};
        conf.viewRecords(customerQuery, customerHeaders, customerColumns);

        // Reservation History
        String reservationHistoryQuery = "SELECT * FROM Reservations WHERE customer_id = " + customerId;
        String[] reservationHistoryHeaders = {"Reservation ID", "Customer Name", "Contact", "Number of People", "Reservation Seaters"};
        String[] reservationHistoryColumns = {"reservation_id", "customer_name", "contactnum", "num_people", "reservation_seaters"};
        conf.viewRecords(reservationHistoryQuery, reservationHistoryHeaders, reservationHistoryColumns);

    }

    public void Report() {

        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("-----------------------");
            System.out.println("   ORDER REPORTS    ");
            System.out.println("-----------------------");
            System.out.println("1.GENERAL REPORTS");
            System.out.println("2.INDIVIDUAL REPORTS");
            System.out.println("3.EXIT REPORTS PANEL");

            System.out.print("Enter action:");
            int action = sc.nextInt();

            Reports rep = new Reports();

            switch (action) {

                case 1:

                    rep.generalReport();

                    break;

                case 2:

                    rep.individualReport();

                    break;

                case 3:

                    System.out.println("\nReturning to Main System...\n");
                    return;

                default:
                    System.out.print("Invalid option. Please try again.");
                    break;
            }

            System.out.println("Do you want to continue? (yes/no): ");
            response = sc.next();

            while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
                System.out.println("Invalid input! Please answer only 'yes' or 'no'.");
                System.out.print("Do you want to continue? (yes/no): ");
                response = sc.next();
            }

        } while (response.equalsIgnoreCase("yes"));

    }

}
