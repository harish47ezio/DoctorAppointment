package com.project.doctorappointment;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.project.dao.AppointmentDao;
import com.project.dao.CustomerDao;
import com.project.entity.Appointment;
import com.project.entity.Customer;
import com.project.impl.AppointmentDaoDbImpl;
import com.project.impl.CustomerDaoDbImpl;
import com.project.userException.ChoiceException;

public class AppointmentAPP {
	Scanner sc=new Scanner(System.in);
	private int chA;
	private char YNA;
	private static CustomerDao dao=new CustomerDaoDbImpl();
	private static AppointmentDao Appdao=new AppointmentDaoDbImpl();
	public AppointmentAPP() throws ChoiceException {
		
        do {
        	YNA='N';
        	System.out.println("\n1.Register Appointment   ");
            System.out.println("2.Modify Appointment     ");
            System.out.println("3.Delete Appointment     ");
            System.out.println("4.View Appointment");
            System.out.println("5.View All Appointment   ");
            System.out.println("5.GO back to Main Menu   ");
            
            System.out.println("\nChoose your service");
            setCh(Integer.parseInt(sc.nextLine()));
            
            switch(chA) {
            case 1:
            	System.out.println("\nWelcome to Registration");
            	System.out.println("-----------------------");
            	addAppointment();
            	break;
            case 2:
            	System.out.println("Welcome to Modification");
            	System.out.println("---------------------");
            	modappointment();
            	break;
            case 3:
            	System.out.println("Deletion Page");
            	System.out.println("-----------");
            	delAppointment();
            	break;
            case 4:
            	System.out.println("View Your Appointment");
            	System.out.println("-------------------");
            	viewsingleapp();
            	break;
            case 5:
            	System.out.println("View all Appointment");
            	System.out.println("------------------");
            	viewallapp();
            	break;
            case 6:
            	continue;
			default:
            	System.out.println("\nIncorrect value detected");
         
            }
            System.out.println("\nRepeat Appointment menu? Y/N:");
            setYN(sc.nextLine().charAt(0));
        }while(YNA=='Y'||YNA=='y');
	}
	
	private void modappointment() throws ChoiceException {
		// TODO Auto-generated method stub
		long num;
		int CID = 0;
		System.out.println("Login\n");
		System.out.println("Phone number");
		num=Long.parseLong(sc.nextLine());
		Appointment chng;
		try {
			CID=dao.ID(num);
			chng=updappoin(num);
			Appdao.moddapp(chng,CID);
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}

	private Appointment updappoin(long num) throws ChoiceException {
		// TODO Auto-generated method stub
		String Pass;
		int CID=0;
		int DI=0;
		boolean flag = false;
		int ch;
		Appointment VAP = null;
		System.out.println("Enter Password");
		Pass=sc.nextLine();
		try {
			flag=dao.passcheck(num,Pass);
			//System.out.println(flag);
		}catch(SQLException e) {
			System.out.println(e);
		}
		if(flag==true) {
			try{
				CID=dao.ID(num);
				VAP=Appdao.viewSingleapp(CID);
			}catch(SQLException e) {
				System.out.println(e);
			}
			System.out.println("\nChange Doctor");
			DoctorApp D=new DoctorApp();
			System.out.println("Enter Doctor ID you want to update");
			DI=Integer.parseInt(sc.nextLine());
			VAP.setDocID(DI);
			
			return VAP;
		}
		else {
			return null;
		}
	}

	private void viewsingleapp() {
		// TODO Auto-generated method stub
		long num;
		int CID;
		Appointment VAP = null;
		System.out.println("Login\n");
		System.out.println("Phone number");
		num=Long.parseLong(sc.nextLine());
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
			try {
				CID=dao.ID(num);
				VAP=Appdao.viewSingleapp(CID);
			}catch(SQLException e) {
				System.out.println(e);
			}
			System.out.println();
			System.out.println();
			System.out.println("Your Appointmnet");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.format("| %12s | %9s | %23s | %17s | %13s| %17s | %16s | %15s |\n","FirstName","Location","Email","CustPhoneNumber","DocName","DocPhoneNumber","Speciality","Status");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(VAP.outputapp());
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
			
		}
		else {
			System.out.println("Wrong Password");
		}
		
	}

	private void delAppointment() {
		// TODO Auto-generated method stub
		long num;
		System.out.println("Login\n");
		System.out.println("Phone number");
		int CID;
		num=Long.parseLong(sc.nextLine());
		String Pass;
		boolean flag = false;
		System.out.println("Enter Password");
		Pass=sc.nextLine();
		try {
			flag=dao.passcheck(num, Pass);
			if(flag) {
				CID=dao.ID(num);
				Appdao.del(CID);
			}
			else {
				System.out.println("wrong pass");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}	
	}

	private void viewallapp() {
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
				List<Appointment> VAP=Appdao.viewallapp();
				Iterator<Appointment> it=VAP.iterator();
				System.out.println();
				System.out.println();
				System.out.println("All Appointment");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.format("| %12s | %9s | %23s | %17s | %13s| %17s | %16s | %15s |\n","FirstName","Location","Email","CustPhoneNumber","DocName","DocPhoneNumber","Speciality","Status");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
				while(it.hasNext()) {
					System.out.println(it.next().outputapp());
				}
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
				
			}
			else {
				System.out.println("Wrong Password");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}

	private void addAppointment() throws ChoiceException {
		// TODO Auto-generated method stub
		Appointment newAPP;
		try {
			newAPP=createAPP();
			Appdao.add(newAPP);
		}catch(SQLException e) {
			System.out.println(e);
		}	
	}

	private Appointment createAPP() throws ChoiceException {
		// TODO Auto-generated method stub
		long num;
		System.out.println("Login\n");
		System.out.println("Phone number");
		num=Long.parseLong(sc.nextLine());
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
			int DI;
			
			Customer VO = null;
			try {
				VO=dao.modder(num);
			}catch(SQLException e) {
				System.out.println(e);
			}
			Appointment AP=new Appointment();
			AP.setCustId(VO.getId());
			DoctorApp D=new DoctorApp();
			System.out.println("Enter Doctor ID you want to book");
			DI=Integer.parseInt(sc.nextLine());
			AP.setDocID(DI);
			return AP;
			
		}
		else {
			System.out.println("Wrong Password");
		}
		return null;
	}

	/**
	 * @param ch the ch to set
	 */
	public void setCh(int ch) {
		this.chA = ch;
	}

	/**
	 * @param yN the yN to set
	 */
	public void setYN(char yN) {
		YNA = yN;
	}

}
