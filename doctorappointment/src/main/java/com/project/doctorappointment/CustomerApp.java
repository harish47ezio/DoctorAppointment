package com.project.doctorappointment;

import java.nio.file.FileSystemException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import com.project.dao.CustomerDao;
import com.project.entity.Customer;
import com.project.impl.CustomerDaoDbImpl;
import com.project.userException.ChoiceException;
import com.project.userException.numberLengthException;

public class CustomerApp {
	static Scanner sc=new Scanner(System.in);
	private int chC;
	private char YNC;
	private static CustomerDao dao=new CustomerDaoDbImpl();
	
	public CustomerApp() {
		
        do {
        	YNC='N';
        	System.out.println("\n1.Register Customer   ");
            System.out.println("2.Modify Customer     ");
            System.out.println("3.Delete Customer     ");
            System.out.println("4.View Customer Detail");
            System.out.println("5.View All Customer   ");
            System.out.println("6.GO back to Main Menu   ");
            
            System.out.println("\nChoose your service");
            setCh(Integer.parseInt(sc.nextLine()));
            
            switch(chC) {
            case 1:
            	System.out.println("\nWelcome to Registration");
            	System.out.println("-----------------------");
            	addCustomer();
            	break;
            case 2:
            	System.out.println("\nWelcome to Modification");
            	System.out.println("-----------------------");
            	modCustomer();
            	break;
            case 3:
            	System.out.println("\nDeletion Page");
            	System.out.println("-------------");
            	delCustomer();
            	break;
            case 4:
            	System.out.println("\nView Your Detail");
            	System.out.println("----------------");
            	viewone();
            	break;
            case 5:
            	System.out.println("\nView all Detail");
            	System.out.println("---------------");
            	viewall();
            	break;
            case 6:
            	continue;
			default:
            	System.out.println("\nIncorrect value detected");
         
            }
            System.out.println("\nRepeat Customer menu? Y/N:");
            try{
            	setYN(sc.nextLine().charAt(0));
            	if(YNC!='y') {
					if(YNC!='Y') {
						if(YNC!='N') {
							if(YNC!='n') {
								throw new ChoiceException("Y or N only accepted");
							}
						}
					}
				}
            
            }catch(ChoiceException e) {
            	System.out.println(e);
            }
            
        }while(YNC=='Y'||YNC=='y');
	}
	
	private void viewall() {
		// TODO Auto-generated method stub
		long num;
		System.out.println("Login\n");
		System.out.println("Phone number");
		num=Long.parseLong(sc.nextLine());
		try {
			String Pass;
			boolean flag = false;
			System.out.println("Enter Password");
			Pass=sc.nextLine();
			flag=dao.passcheck(num, Pass);
			if(flag) {
				List<Customer> VO=dao.viewall();
				Iterator<Customer> it=VO.iterator();
				System.out.println();
				System.out.println("All Details");
				System.out.println("------------------------------------------------------------------------------------");
				System.out.format("| %12s | %12s | %23s | %9s | %13s|\n","First Name","Last Name","Email","Location","Phone Number");
				System.out.println("------------------------------------------------------------------------------------");
				while(it.hasNext()) {
					System.out.println(it.next().output());
				}
				System.out.println("------------------------------------------------------------------------------------");
				
			}
			else {
				System.out.println("Wrong Password");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		
		
	}

	private void viewone() {
		// TODO Auto-generated method stub
		long num;
		System.out.println("Login\n");
		System.out.println("Phone number");
		num=Long.parseLong(sc.nextLine());
		Customer VO;
		try {
			String Pass;
			boolean flag = false;
			System.out.println("Enter Password");
			Pass=sc.nextLine();
			flag=dao.passcheck(num, Pass);
			if(flag) {
				VO=dao.modder(num);
				System.out.println();
				System.out.println("Your Details");
				System.out.println("------------------------------------------------------------------------------------");
				System.out.format("| %12s | %12s | %23s | %9s | %13s|\n","First Name","Last Name","Email","Location","Phone Number");
				System.out.println("------------------------------------------------------------------------------------");
				System.out.println(VO.output());
				System.out.println("------------------------------------------------------------------------------------");
			}
			else {
				System.out.println("Wrong Password");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

	private void delCustomer() {
		// TODO Auto-generated method stub
		long num;
		System.out.println("Login\n");
		System.out.println("Phone number");
		num=Long.parseLong(sc.nextLine());
		boolean del;
		try {
			del=remCustomer(num);
			if(del) {
				dao.del(num);
			}
			else {
				System.out.println("wrong password");
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}

	Boolean remCustomer(long num) {
		// TODO Auto-generated method stub
		String Pass;
		boolean flag = false;
		System.out.println("Enter Password");
		Pass=sc.nextLine();
		try {
			flag=dao.passcheck(num, Pass);
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		if(flag) {
			return true;
		}
		else {
			return false;
		}
	}

	private void modCustomer() {
		// TODO Auto-generated method stub
		long num;
		System.out.println("Login\n");
		System.out.println("Phone number");
		num=Long.parseLong(sc.nextLine());
		Customer modcustomer;
		try {
			modcustomer=updCustomer(num);
			dao.modupd(modcustomer,num);	
		}catch(SQLException e) {
			System.out.println(e);
		}
			
		
	}

	private static Customer updCustomer(long num) {
		String Pass;
		
		boolean flag = false;
		int ch;
		
		
		System.out.println("Enter Password");
		Pass=sc.nextLine();
		try {
			flag=dao.passcheck(num,Pass);
		}catch(SQLException e) {
			System.out.println(e);
		}
		if(flag==true) {
			try{
				Customer obj=dao.modder(num); 
				do {
					System.out.println("\nWhat you want to modify?");
					System.out.println("\n1.First Name");
					System.out.println("\n2.Last Name");
					System.out.println("\n3.Location");
					System.out.println("\n4.Email");
					System.out.println("\n5.Phone Number");
					System.out.println("\n6.Password");
					System.out.println("\nEnter your Choice");
					ch=Integer.parseInt(sc.nextLine());
					switch(ch) {
					case 1:
						System.out.println("\nChange First name");
						obj.setFirstName(sc.nextLine());
						break;
					case 2:
						System.out.println("\nChange Last Name");
						obj.setLastName(sc.nextLine());
						break;
					case 3:
						System.out.println("\nChange location");
						obj.setLocation(sc.nextLine());
						break;
					case 4:
						System.out.println("\nChange Email");
						obj.setEmail(sc.nextLine());
						break;
					case 5:
						System.out.println("\nChange Phone Number");
						obj.setphoneNumber(Long.parseLong(sc.nextLine()));
						break;
					case 6:
						System.out.println("\nChange password");
						obj.setPassword(sc.nextLine());
						break;
					case 7:
						continue;
					default:
						System.out.println("Incorrect Value Detected");
					}
					System.out.println("\nDo you want to update more?");
					try{
						ch=sc.nextLine().charAt(0);
						if(ch!='y') {
							if(ch!='Y') {
								if(ch!='N') {
									if(ch!='n') {
										throw new ChoiceException("Y or N only accepted");
									}
								}
							}
						}
					}catch(ChoiceException e) {
						System.out.println(e);
					}
				}while(ch=='y'||ch=='Y');
				return obj;
			}catch(SQLException e) {
				System.out.println(e);
			}
			/*
			
			switch(ch) {
			case 1:
				
				
			}*/
		}
		else {
			System.out.println("not working");
		}
		
		
		
		return null;
	}

	
	private void addCustomer() {
		Customer newCustomer;
		try {
			newCustomer=createCustomer();
			dao.add(newCustomer);
		}catch(SQLException e) {
			System.out.println(e);
		}	
	}

	private static Customer createCustomer() {
		Customer obj=new Customer();
		try{
			//Customer obj=new Customer();
			System.out.println("Enter the First name:");
			obj.setFirstName(sc.nextLine());
			System.out.println("Enter the Last Name");
			obj.setLastName(sc.nextLine());
			System.out.println("Enter Location");
			obj.setLocation(sc.nextLine());
			System.out.println("Enter Email");
			obj.setEmail(sc.nextLine());
			System.out.println("Enter Phone Number");
			obj.setphoneNumber(Long.parseLong(sc.nextLine()));
			long l=Long.parseLong("1000000000");
			long m=Long.parseLong("9999999999");
			if(obj.getphoneNumber()<l &&obj.getphoneNumber()<m){
				throw new numberLengthException("phone number should have 10 digits");
			}
			System.out.println("Enter Password");
			obj.setPassword(sc.nextLine());
			
		}catch(numberLengthException e) {
			System.out.println(e);
		}
		return obj;
		
	}

	/**
	 * @param ch the ch to set
	 */
	public void setCh(int ch) {
		this.chC = ch;
	}

	/**
	 * @param yN the yN to set
	 */
	public void setYN(char yN) {
		YNC = yN;
	}
	

	

}
