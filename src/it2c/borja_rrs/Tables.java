 
package it2c.borja_rrs;
import java.util.Scanner;

public class Tables {
    
    // Method to add a new table with validation
    public void addTable() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        
        // Validate number of seats
        int seaters = 0;
        while (true) {
            System.out.println("Enter number of Table Seaters (integer > 0): ");
            if (sc.hasNextInt()) {
                seaters = sc.nextInt();
                if (seaters > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid number greater than 0.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                sc.next(); // Consume invalid input
            }
        }
        
        // Validate table location (not empty)
        sc.nextLine(); // Consume newline left by nextInt()
        String location;
        while (true) {
            System.out.println("Enter Table's Location: ");
            location = sc.nextLine();
            if (!location.isEmpty()) {
                break;
            } else {
                System.out.println("Location cannot be empty.");
            }
        }
        
        // Validate table status (allowed values)
        String status;
        while (true) {
            System.out.println("Enter Table Status (available/reserved/unavailable): ");
            status = sc.nextLine().toLowerCase();
            if (status.equals("available") || status.equals("reserved") || status.equals("unavailable")) {
                break;
            } else {
                System.out.println("Please enter a valid status: available, reserved, or unavailable.");
            }
        }
        
        // Validate reserved date (format check)
        String reserveD;
        while (true) {
            System.out.println("Enter reserved date (YYYY-MM-DD): ");
            reserveD = sc.nextLine();
            if (reserveD.matches("\\d{4}-\\d{2}-\\d{2}")) { // Simple date format validation
                break;
            } else {
                System.out.println("Please enter a valid date in the format YYYY-MM-DD.");
            }
        }
        
        // Validate customer contact number (numbers only)
        String contactNumber;
        while (true) {
            System.out.print("Customer's Contact Number (numbers only): ");
            contactNumber = sc.nextLine();
            if (contactNumber.matches("\\d+")) { 
                break;
            } else {
                System.out.println("Please enter numbers only.");
            }
        }

        // SQL Insert
        String sql = "INSERT INTO Tables (c_seats, c_location, status, reserved_date, contact_number) VALUES (?, ?, ?, ?, ?)";
        conf.addRecords(sql, seaters, location, status, reserveD, contactNumber);
    }
    
    // Method to view all tables
    public void viewTable() {
        String cqry = "SELECT * FROM Tables";
        String[] tableHeaders = {"Table Number", "Seats", "Location", "Status", "Reserved Date"};
        String[] tableColumns = {"Table_id", "c_seats", "c_location", "status", "reserved_date"};
        Config conf = new Config();
        conf.viewRecords(cqry, tableHeaders, tableColumns);
    }
    
    // Method to update an existing table with validation
    private void updateTable() {
        Scanner sc = new Scanner(System.in);
        
        // Validate table ID (ensure it's an existing ID)
        int id;
        while (true) {
            System.out.println("Enter Table ID to update: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid table ID.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                sc.next(); // Consume invalid input
            }
        }
        
        // Validate new number of seats (positive integer)
        int seaters;
        while (true) {
            System.out.println("Enter new number of Table Seaters (integer > 0): ");
            if (sc.hasNextInt()) {
                seaters = sc.nextInt();
                if (seaters > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid number greater than 0.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                sc.next(); // Consume invalid input
            }
        }

        // Validate new table location (not empty)
        sc.nextLine(); // Consume newline
        String location;
        while (true) {
            System.out.println("Enter new Table's Location: ");
            location = sc.nextLine();
            if (!location.isEmpty()) {
                break;
            } else {
                System.out.println("Location cannot be empty.");
            }
        }

        // Validate new table status
        String status;
        while (true) {
            System.out.println("Enter new Table Status (available/reserved/unavailable): ");
            status = sc.nextLine().toLowerCase();
            if (status.equals("available") || status.equals("reserved") || status.equals("unavailable")) {
                break;
            } else {
                System.out.println("Please enter a valid status: available, reserved, or unavailable.");
            }
        }

        // Validate new reserved date
        String reserveDate;
        while (true) {
            System.out.println("Enter new reserved date (YYYY-MM-DD): ");
            reserveDate = sc.nextLine();
            if (reserveDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break;
            } else {
                System.out.println("Please enter a valid date in the format YYYY-MM-DD.");
            }
        }

        // SQL Update
        String qry = "UPDATE Tables SET c_seats = ?, c_location = ?, status = ?, reserved_date = ? WHERE table_id = ?";
        Config conf = new Config();
        conf.updateRecords(qry, seaters, location, status, reserveDate, id);
    }
    
    // Method to delete a table
    public void deleteTable() {
        Scanner sc = new Scanner(System.in);
        
        // Validate table ID
        int id;
        while (true) {
            System.out.print("Enter Table Number to Delete: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid table ID.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                sc.next(); // Consume invalid input
            }
        }

        Config conf = new Config();
        String sqlDelete = "DELETE FROM Tables WHERE table_id = ?";
        conf.deleteRecords(sqlDelete, id);
    }

    // Main operation
    public void tablesOp() {
        Scanner input = new Scanner(System.in);
        String response;

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. REMOVE");

            System.out.print("Enter action: ");
            int action = input.nextInt();
            Tables tables = new Tables();
            switch (action) {
                case 1:
                    tables.addTable();
                    break;
                case 2:
                    tables.viewTable();
                    break;
                case 3:
                    tables.updateTable();
                    break;
                case 4:
                    tables.deleteTable();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }

            System.out.print("Continue? (yes/no): ");
            response = input.next();
        } while (response.equalsIgnoreCase("yes"));
        System.out.println("Thank you! See you again.");
    }

    void sablesop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



