
package it2c.borja_rrs;



import java.util.Scanner;

public class Reservations {

    // Method to add a table reservation
    public void addReservation() {
        Scanner input = new Scanner(System.in);
        Config conf = new Config();

        // Collecting user input for reservation details
        System.out.print("Enter Customer Name: ");
        String name = input.nextLine();

        System.out.print("Enter Contact Number: ");
        String contact = input.nextLine();

        System.out.print("Enter Number of People: ");
        int numPeople = input.nextInt();
        input.nextLine(); // Consume the leftover newline

        System.out.print("Enter Number of Seaters: ");
        String Seaters = input.nextLine();

        // SQL query to insert reservation into the table
        String sql = "INSERT INTO Reservations (customer_name, contactnum, num_people, reservation_seaters)"
                + " VALUES (?, ?, ?, ?)";
        conf.addRecords(sql, name, contact, numPeople, Seaters);
    }

    // Method to view all table reservations
    public void viewReservations() {
        String tqry = "SELECT * FROM reservations";
        String[] hrds = {"Reservation ID", "Customer Name", "Contact", "Number of People", "Reservation Seaters"};
        String[] clmns = {"res_id", "customer_name", "contact", "num_people", "reservation_seaters"};

        Config conf = new Config();

        conf.viewRecords(tqry, hrds, clmns);
    }

    // Method to update a table reservation
    private void updateReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Reservation ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter new Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter new Contact Number: ");
        String contact = sc.nextLine();

        System.out.print("Enter new Number of People: ");
        int numPeople = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter new Number of Seaters): ");
        String reservationTime = sc.nextLine();

        // SQL query to update reservation details
        String qry = "UPDATE reservations SET customer_name = ?, contact = ?, num_people = ?, reservation_time = ? WHERE res_id = ?";

        Config conf = new Config();
        conf.updateRecords(qry, name, contact, numPeople, reservationTime, id);
    }

    // Method to delete a reservation
    private void deleteReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Reservation ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM reservations WHERE res_id = ?";

        Config conf = new Config();
        conf.deleteRecords(qry, id);
    }

    // Main menu for table reservation operations
    public void ReservationsMenu() {
        Reservations res = new Reservations();
        Scanner input = new Scanner(System.in);
        String response;

        do {
            // Main Menu
            System.out.println("-------------------------------------");
            System.out.println("           Table Reservations        ");
            System.out.println("-------------------------------------");
            System.out.println("1. Add Reservation");
            System.out.println("2. Update Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Delete Reservation");
            System.out.println("5. Go back to Main Menu");

            System.out.print("Enter action: ");
            int action = input.nextInt();

            switch (action) {
                case 1:
                    res.addReservation();
                    break;

                case 2:
                    res.viewReservations();
                    res.updateReservation();
                    res.viewReservations();
                    break;

                case 3:
                    res.viewReservations();
                    break;

                case 4:
                    res.viewReservations();
                    res.deleteReservation();
                    res.viewReservations();
                    break;

                case 5:
                    System.out.println("\nGoing Back to Main Menu.");
                    return;

                default:
                    System.out.println("Invalid action. Try Again.");
                    break;
            }

            // Asking user if they want to continue
            System.out.print("Go back to Reservations Menu (yes/no): ");
            response = input.next();

        } while (response.equalsIgnoreCase("yes"));
    }

}

    



