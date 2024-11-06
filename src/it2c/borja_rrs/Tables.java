 
package it2c.borja_rrs;
import java.util.Scanner;

public class Tables {
    
  
    

    public void addTable() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        System.out.println("Customer Name: ");
        String fname = sc.next();
        System.out.println("Customer Address: ");
        String lname = sc.next();
        System.out.println("Contact-Number: ");
        String address = sc.next();
        System.out.println("Seaters: ");
        String contact_number = sc.next();
       
        String sql = "INSERT INTO Tables (c_name, c_address, contactnum, seaters) VALUES (?, ?, ?, ?)";
        conf.addRecords(sql, fname, lname, address, contact_number);
    }
    
    private void viewTable() {
        String cqry = "SELECT * FROM Tables";
        String[] votersHeaders = {"customer_id", "c_Name", "c_address", "contactnum", "seaters"};
        String[] votersColumns = {"customer_id", "c_name", "c_address", "contactnum", "seaters"};
        Config conf = new Config();
        conf.viewRecords(cqry, votersHeaders, votersColumns);
    }
    
    private void updateTable(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Customer Name ");
        int id = sc.nextInt();
        
        System.out.println("Enter the new Customer Address");
        String address = sc.next();
        
        System.out.println("Enter the new Contact Number");
        String contact = sc.next();
        
        
        String qry = "UPDATE Tables SET c_address = ?, contactnum = ? WHERE customer_id = ?";
        
        Config conf = new Config();
        conf.updateRecords(qry, address, contact, id);
    }
    
    public void deleteTable() {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter ID to Delete: ");
        int id = input.nextInt();

        Config conf = new Config();

        String sqlDelete = "DELETE FROM Tables WHERE customer_id = ?";

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



