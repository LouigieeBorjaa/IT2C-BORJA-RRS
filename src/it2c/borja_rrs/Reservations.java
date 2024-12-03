package it2c.borja_rrs;

import java.util.Scanner;
import java.time.LocalDate;

public class Reservations {

    // Method to add a table reservation
    public void addReservation() {
        Scanner input = new Scanner(System.in);
        Config conf = new Config();

        // Validate customer name (alphabetic characters and spaces only)
        String name;
        while (true) {
            System.out.print("Enter Customer Name: ");
            name = input.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Invalid input! Customer name must only contain letters and spaces.");
            }
        }

        // Validate contact number (digits only and 11 digits length)
        String contact;
        while (true) {
            System.out.print("Enter Contact Number (11 digits only): ");
            contact = input.nextLine();
            if (contact.matches("\\d{11}")) { // Exactly 11 digits
                break;
            } else {
                System.out.println("Invalid input! Contact number must be exactly 11 digits.");
            }
        }

        // Validate number of people (positive integer)
        int numPeople;
        while (true) {
            System.out.print("Enter Number of People: ");
            if (input.hasNextInt()) {
                numPeople = input.nextInt();
                if (numPeople > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Number of people must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer for number of people.");
                input.next(); // Consume invalid input
            }
        }
        input.nextLine(); // Consume newline left by nextInt()

        // Validate number of seats (positive integer)
        String seats;
        while (true) {
            System.out.print("Enter Number of Seaters: ");
            seats = input.nextLine();
            if (seats.matches("\\d+") && Integer.parseInt(seats) > 0) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a positive integer for seats.");
            }
        }

        LocalDate reserveDate = LocalDate.now();

        // SQL query to insert reservation into the table
        String sql = "INSERT INTO Reservations (customer_name, contactnum, num_people, reservation_seaters, reserved_date) "
                + "VALUES (?, ?, ?, ?, ?)";
        conf.addRecords(sql, name, contact, numPeople, seats, reserveDate);
        
        String sql2 = "UPDATE Tables SET status = 'Reserved'";
        

    }

    // Method to view all table reservations
    public void viewReservations() {
        String tqry = "SELECT * FROM reservations";
        String[] hrds = {"Reservation ID", "Customer Name", "Contact", "Number of People", "Reservation Seaters"};
        String[] clmns = {"reservation_id", "customer_name", "contactnum", "num_people", "reservation_seaters"};

        Config conf = new Config();
        conf.viewRecords(tqry, hrds, clmns);
    }

    // Method to update a table reservation
    private void updateReservation() {
        Scanner sc = new Scanner(System.in);

        // Validate Reservation ID (positive integer)
        int id;
        while (true) {
            System.out.print("Enter Reservation ID to Update: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Reservation ID must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer for Reservation ID.");
                sc.next(); // Consume invalid input
            }
        }
        sc.nextLine(); // Consume newline

        // Validate customer name (alphabetic characters and spaces only)
        String name;
        while (true) {
            System.out.print("Enter new Customer Name: ");
            name = sc.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Invalid input! Customer name must only contain letters and spaces.");
            }
        }

        // Validate contact number (11 digits only)
        String contact;
        while (true) {
            System.out.print("Enter new Contact Number (11 digits only): ");
            contact = sc.nextLine();
            if (contact.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("Invalid input! Contact number must be exactly 11 digits.");
            }
        }

        // Validate number of people (positive integer)
        int numPeople;
        while (true) {
            System.out.print("Enter new Number of People: ");
            if (sc.hasNextInt()) {
                numPeople = sc.nextInt();
                if (numPeople > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Number of people must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer for number of people.");
                sc.next(); // Consume invalid input
            }
        }
        sc.nextLine(); // Consume newline

        // Validate number of seats (positive integer)
        String reservationSeaters;
        while (true) {
            System.out.print("Enter new Number of Seaters: ");
            reservationSeaters = sc.nextLine();
            if (reservationSeaters.matches("\\d+") && Integer.parseInt(reservationSeaters) > 0) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a positive integer for seats.");
            }
        }

        // SQL query to update reservation details
        String qry = "UPDATE reservations SET customer_name = ?, contact = ?, num_people = ?, reservation_seaters = ? WHERE reservaion_id = ?";
        Config conf = new Config();
        conf.updateRecords(qry, name, contact, numPeople, reservationSeaters, id);
    }

    // Method to delete a reservation
    private void deleteReservation() {
        Scanner sc = new Scanner(System.in);

        // Validate Reservation ID (positive integer)
        int id;
        while (true) {
            System.out.print("Enter Reservation ID to Delete: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Reservation ID must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer for Reservation ID.");
                sc.next(); // Consume invalid input
            }
        }

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
