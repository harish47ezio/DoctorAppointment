package com.project.doctorappointment;
import java.sql.SQLException;
import java.util.*;

import com.project.dao.DoctorDao;
import com.project.entity.Customer;
import com.project.entity.Doctor;
import com.project.impl.DoctorDaoDbImpl;
import com.project.userException.ChoiceException;
public class DoctorApp {
	Scanner sc=new Scanner(System.in);
	private static DoctorDao docdao=new DoctorDaoDbImpl();
	private char CHD='n';
	public DoctorApp() throws ChoiceException{
		
		do {
			try {
				List<Doctor> VO=docdao.viewalldoc();
				Iterator<Doctor> it=VO.iterator();
				System.out.println();
				System.out.println("---------------------------------------------------------");
				System.out.format("| %5s | %12s | %13s | %15s |\n","ID","Doctor Name","Phone Number","Specialisation");
				System.out.println("---------------------------------------------------------");
				while(it.hasNext()) {
					System.out.println(it.next().output());
				}
				System.out.println("---------------------------------------------------------");
				
				
			}catch(SQLException e) {
				System.out.println(e);
			}
			System.out.println("\nRepeat Doctor List Y/N");
			try{
				setCHD(sc.nextLine().charAt(0));
				if(CHD!='y') {
					if(CHD!='Y') {
						if(CHD!='N') {
							if(CHD!='n') {
								throw new ChoiceException("Y or N only accepted");
							}
						}
					}
				}
			}catch(ChoiceException e) {
				System.out.println(e);
			}
		}while(CHD=='y'||CHD=='Y');
		
		
		
	}
	/**
	 * @return the cHD
	 */
	public char getCHD() {
		return this.CHD;
	}
	/**
	 * @param cHD the cHD to set
	 */
	public void setCHD(char cHD) {
		this.CHD = cHD;
	}

}
