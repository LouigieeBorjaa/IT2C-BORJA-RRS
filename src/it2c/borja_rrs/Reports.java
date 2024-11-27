package it2c.borja_rrs;

import java.util.Scanner;

class Reports {

    Config conf = new Config();

    public void generalReport() {
        Config conf = new Config();

        // Reservation Summary
        String reservationSummaryQuery = "SELECT COUNT(*) AS total_reservations, AVG(num_people) AS avg_people FROM Reservations";
        String[] reservationSummaryHeaders = {"Total Reservations", "Average Number of People"};
        String[] reservationSummaryColumns = {"total_reservations", "avg_people"};
        conf.viewRecords(reservationSummaryQuery, reservationSummaryHeaders, reservationSummaryColumns);

        // Table Usage Report
        String tableUsageQuery = "SELECT table_id, COUNT(*) AS times_reserved FROM Reservations GROUP BY table_id";
        String[] tableUsageHeaders = {"Table ID", "Times Reserved"};
        String[] tableUsageColumns = {"table_id", "times_reserved"};
        conf.viewRecords(tableUsageQuery, tableUsageHeaders, tableUsageColumns);

        // Customer Demographics
        String customerDemographicsQuery = "SELECT COUNT(*) AS total_customers FROM Customer";
        String[] customerDemographicsHeaders = {"Total Customers"};
        String[] customerDemographicsColumns = {"total_customers"};
        conf.viewRecords(customerDemographicsQuery, customerDemographicsHeaders, customerDemographicsColumns);

        // Reservation Trends
        String reservationTrendsQuery = "SELECT DATE(reserved_date) AS date, COUNT(*) AS total_reservations FROM Reservations GROUP BY DATE(reserved_date)";
        String[] reservationTrendsHeaders = {"Date", "Total Reservations"};
        String[] reservationTrendsColumns = {"date", "total_reservations"};
        conf.viewRecords(reservationTrendsQuery, reservationTrendsHeaders, reservationTrendsColumns);
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
