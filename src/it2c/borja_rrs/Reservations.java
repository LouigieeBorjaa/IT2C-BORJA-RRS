package it2c.borja_rrs;

import java.util.Scanner;
import java.time.LocalDate;

public class Reservations {

    
        
    public void manageReservations() {
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("--------------------------------");
            System.out.println("|      TABLE RESERVATIONS      |");
            System.out.println("--------------------------------");
            System.out.println("| 1. ADD RESERVATION           |");
            System.out.println("| 2. VIEW RESERVATIONS         |");
            System.out.println("| 3. UPDATE RESERVATION        |");
            System.out.println("| 4. DELETE RESERVATION        |");
            System.out.println("| 5. EXIT                      |");
            System.out.println("--------------------------------");

            int action = 0;

            // Ensure valid action input between 1 and 5
            while (action < 1 || action > 5) {
                System.out.print("Enter action: ");

                if (sc.hasNextInt()) {
                    action = sc.nextInt();

                    if (action < 1 || action > 5) {
                        System.out.println("Invalid option! Please enter a number between 1 and 5 only.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();  // Consume invalid input
                }
            }

            switch (action) {
                case 1:
                    addReservation();
                    break;

                case 2:
                    viewReservations();
                    break;

                case 3:
                    viewReservations();
                    updateReservation();
                    break;

                case 4:
                    viewReservations();
                    deleteReservation();
                    break;

                case 5:
                    System.out.println("\nReturning to Main System...\n");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            // Ask if the user wants to continue
            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();

        } while (response.equalsIgnoreCase("yes"));
    }

    // Method to add reservation
    private void addReservation() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
            Customer cstm = new Customer();
            cstm.viewCustomer();
            
            System.out.print("Enter Selected Customer ID: ");
            int id = sc.nextInt();
            
            String csql = "SELECT customer_id FROM Customer WHERE customer_id = ?";
            while(conf.getSingleValue(csql, id)== 0){
                System.out.print("Customer cannot found, Please Select ID Again: " );
                id = sc.nextInt();
            }
            
            Tables tbl = new Tables();
            tbl.viewTable();
              
            System.out.print("Enter Selected Table ID: ");
            int tid = sc.nextInt();
            
            String tsql = "SELECT table_id FROM Tables WHERE table_id = ?";
            while(conf.getSingleValue(tsql, tid)== 0){
                System.out.print("Table ID cannot found, Please Select ID Again: " );
                tid = sc.nextInt();
        }
            
        // Validate customer name
        String name;
        while (true) {
            System.out.print("Enter Customer Name: ");
            name = sc.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Invalid input! Customer name must only contain letters and spaces.");
            }
        }

        // Validate contact number
        String contact;
        while (true) {
            System.out.print("Enter Contact Number (11 digits only): ");
            contact = sc.nextLine();
            if (contact.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("Invalid input! Contact number must be exactly 11 digits.");
            }
        }

        // Validate number of people
        

        // Validate number of seats
        String seats;
        while (true) {
            System.out.print("Enter Number of Seaters: ");
            seats = sc.nextLine();
            if (seats.matches("\\d+") && Integer.parseInt(seats) > 0) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a positive integer for seats.");
            }
        }
        
        System.out.print("Enter Time Reservation: ");
        String reserved_time = sc.nextLine();

        LocalDate reserveDate = LocalDate.now();

        // SQL query to insert reservation into the table
        String sql = "INSERT INTO Reservations (reserved_time, reservation_seaters, reserved_date) "
                   + "VALUES (?, ?, ?)";
        conf.addRecords(sql,  reserved_time, seats, reserveDate);
        
        String sql2 = "UPDATE Tables SET status = 'Reserved' WHERE seats = ?";
        conf.addRecords(sql2, seats);
    }

    // Method to view all reservations
    public void viewReservations() {
        
        String query = "SELECT reservation_id, lname, status, bd_date, due_date, r_status, penalties FROM tbl_bdbooks "
                    + "LEFT JOIN tbl_books ON tbl_books.b_id = tbl_bdbooks.b_id "
                    + "LEFT JOIN tbl_borrowers ON tbl_borrowers.bw_id = tbl_bdbooks.bw_id";
        
        String query = "SELECT * FROM reservations";
        String[] headers = {"Reservation ID", "Customer Name", "Contact", "Number of People", "Reservation Seaters"};
        String[] columns = {"reservation_id", "customer_name", "contactnum", "num_people", "reservation_seaters"};

        Config conf = new Config();
        conf.viewRecords(query, headers, columns);
    }

    // Method to update a reservation
    private void updateReservation() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();

        // Validate Reservation ID
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
                sc.next();  // Consume invalid input
            }
        }
        sc.nextLine(); // Consume newline

        // Validate customer name
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

        // Validate contact number
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

        // Validate number of people
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
                sc.next();  // Consume invalid input
            }
        }
        sc.nextLine(); // Consume newline

        // Validate number of seats
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
        String updateQuery = "UPDATE reservations SET customer_name = ?, contactnum = ?, reservation_seaters = ?,  num_people = ? WHERE reservation_id = ?";
        conf.updateRecords(updateQuery, name, contact, reservationSeaters, numPeople, id);
    }

    // Method to delete a reservation
    private void deleteReservation() {
        Scanner sc = new Scanner(System.in);

        // Validate Reservation ID
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
                sc.next();  // Consume invalid input
            }
        }

        String deleteQuery = "DELETE FROM reservations WHERE reservation_id = ?";
        Config conf = new Config();
        conf.deleteRecords(deleteQuery, id);
    }
    }


