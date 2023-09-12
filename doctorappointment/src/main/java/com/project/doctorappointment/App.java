package com.project.doctorappointment;
import java.io.IOException;
import java.util.*;

import com.project.entity.Customer;
import com.project.userException.ChoiceException;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ChoiceException
    {
    	int ch; //Choice for menu
    	char YN = 'Y'; //Choice for loop
    	Scanner sc=new Scanner(System.in); //scanner object
    	
    	//do while loop for iterating the main menu over and over
    	do {
    		//Interface
            System.out.println(" -------------");
            System.out.println("|1.Customer   |");
            System.out.println("|2.Doctor     |");
            System.out.println("|3.Appointment|");
            System.out.println("|4.Exit       |");
            System.out.println(" -------------\n");
            System.out.println("Enter Any Option");
            
            //getting user input
            try {
            	ch=Integer.parseInt(sc.nextLine());
            	switch(ch) {
                case 1:
                	System.out.println("\nHello Customer, What you want to do?");//customer block
                	CustomerApp C=new CustomerApp();
                	/*Customer cust=new Customer();
                	cust.setId(Integer.parseInt(sc.nextLine()));
                	System.out.println(cust);
                	*/break;
                	
                case 2:
                	System.out.println("\nDoctors List");           //doctor block
                	DoctorApp D=new DoctorApp();
                	break;
                case 3:
                	System.out.println("\nAppointment");     //appointment
                	AppointmentAPP A=new AppointmentAPP();
                	break;
                case 4:
                	System.out.println("\nThank you, visit again"); //exit block
                	System.exit(0);
                default:
                	System.out.println("\nIncorrect value detected");
                	
                
                
                
                System.out.println("\nRepeat Main menu? Y/N:"); //choice for iterating the loop
                YN=sc.nextLine().charAt(0);                       //getting input to repeat the menu
                }
            }catch(NumberFormatException e) {
            	System.out.println("Numbers only accepted");
            }
            
            
            //switch case for each option
              
    	}while(YN=='y'||YN=='Y');
         
    	System.out.println("\nThank you, visit again"); //exit block
    	System.exit(0);
                
        
    
    	 		
        
    }
  
//    	final static String ESC = "\033 [";
//    	System.out.print (ESC + "2J");
//		System.out.flush();
}
