package it2c.borja_rrs;

import static java.lang.System.exit;
import java.util.Scanner;

 

public class IT2C_BORJA_RRS {
    
  
    public static void main(String[] args) {
        
        
       Scanner sc = new Scanner(System.in);
        String response;
        boolean exit = true; 
        do {

            System.out.println("-------------------------------------");
            System.out.println("|  WELCOME TO DELIVERY APP SYSTEM |");
            System.out.println("-------------------------------------");
            System.out.println("1. CUSTOMER");
            System.out.println("2. TABLES");
            
            
            System.out.println("5. EXIT");

            System.out.print("Enter action: ");
            int act = sc.nextInt();
            


            switch (act) {

                case 1:
                    
                    Customer cstm = new Customer();
                    
                    cstm.customerOp();

                    

                    break;

                case 2:
                    
                    Tables tbl  = new Tables();
                   
                  tbl.tablesop();
                   break;   
                  
                case 3:
                    
//                    Order ord = new Order();
//                    ord.Ordering();
                   
                    break;

                case 4:
                    
  
                    
                    
                    
                    break;

                case 5:
                    System.out.println("Exiting the program. Type 'yes to coninue: !");
                    response = sc.next();
                    if(response.equalsIgnoreCase("yes")){
                        exit = false;
                    }
                    break;
                
            }

        }while(exit);
       
    }

}
        
