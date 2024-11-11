 
package it2c.borja_rrs;
import java.util.Scanner;

public class Tables {
    
  
    

    public void addTable() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        System.out.println("Enter number Table Seaters: ");
        String fname = sc.next();
        System.out.println("Enter Table's Location: ");
        String lname = sc.next();
        System.out.println("Enter Table Status: ");
        String address = sc.next();
        System.out.println("Enter reserved date: ");
        String contact_number = sc.next();
       
        String sql = "INSERT INTO Tables (c_seats, c_location, status, reserved_date) VALUES (?, ?, ?, ?)";
        conf.addRecords(sql, fname, lname, address, contact_number);
    }
    
    public void viewTable() {
        String cqry = "SELECT * FROM Tables";
        String[] votersHeaders = {"Table Number", "Seats", "Location", "Status", "Teserved Date"};
        String[] votersColumns = {"Table_id", "c_seats", "c_Location", "Status", "Reserved_date"};
        Config conf = new Config();
        conf.viewRecords(cqry, votersHeaders, votersColumns);
    }
    
    private void updateTable(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new Table Seaters ");
        int id = sc.nextInt();
        
        System.out.println("Enter the new Table's Location");
        String tLocation = sc.next();
        
        System.out.println("Enter the new Table Status");
        String tStatus = sc.next();
        
        System.out.println("Enter the new reserved date");
        String rDate = sc.next();
        
        
        String qry = "UPDATE Tables SET c_seats = ?, reserved_date = ?,c_location = ?, status = ? WHERE table_id = ?";
        
        Config conf = new Config();
        conf.updateRecords(qry, tLocation, rDate, tStatus, id);
    }
    
    public void deleteTable() {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Table Number to Delete: ");
        int id = input.nextInt();

        Config conf = new Config();

        String sqlDelete = "DELETE FROM Tables WHERE table_id = ?";

        conf.deleteRecords(sqlDelete, id);

    }
    
    public void tablesop() {
  

        Scanner input = new Scanner(System.in);
        String response; 

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. REMOVE");

            System.out.println("Enter actions: ");
            int action = input.nextInt();
            Tables burja = new Tables();
            switch (action) {
                case 1:    
                    burja.addTable();
                    break;
                case 2:
                    burja.viewTable();
                    break;
                case 3:
                    burja.updateTable();
                    break;
                case 4:
                    burja.viewTable();
                    burja.deleteTable();
                    burja.viewTable();
                    break;
                    
            }
            System.out.print("Continue? (yes/no)");
            response = input.next();
        } while (response.equals("yes"));
        System.out.println("Thank You. See you again.");

    }

   
        
    }



