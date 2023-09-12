package com.project.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.AppointmentDao;
import com.project.entity.Appointment;
import com.project.entity.Customer;
import com.project.util.DBUtil;

public class AppointmentDaoDbImpl implements AppointmentDao {

	private Connection connection=DBUtil.getConnection();
	@Override
	public boolean add(Appointment newAPP) throws SQLException {
		PreparedStatement ps=connection.prepareStatement("insert into appointment(customerId, doctorId, Status) values(?,?,?)");
		ps.setInt(1, newAPP.getCustId());
		ps.setInt(2, newAPP.getDocID());
		ps.setString(3, "Confirmed");
		int execup=ps.executeUpdate();
		if(execup>0) {
			System.out.println("\nAppointment Booked Successfully");
			return true;
		}
		else {
			return false;
		}
		
	}
	@Override
	public List<Appointment> viewallapp() throws SQLException {
		// TODO Auto-generated method stub
		List <Appointment> VOO=new ArrayList<>();
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select C.FirstName, C.Location, C.Email, C.phoneNumber, D.DoctorName, D.phoneNumber, D.Specalist, A.status from appointment A, customer C, doctor D where C.customerId=A.customerId and D.doctorId=A.doctorId;");
		int i=0;
		while(rs.next()) {
			Appointment upd=new Appointment();
			upd.setFirstName(rs.getString(1));
			upd.setLocation(rs.getString(2));
			upd.setEmail(rs.getString(3));
			upd.setphoneNumber(Long.parseLong(rs.getString(4)));
			upd.setDocName(rs.getString(5));
			upd.setPhoneNumber(Long.parseLong(rs.getString(6)));
			upd.setSpec(rs.getString(7));
			upd.setStatus(rs.getString(8));
			VOO.add(upd);
		}
		return VOO;
	}
	@Override
	public boolean del(int cID) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps=connection.prepareStatement("delete from appointment where customerId="+cID);
		int execup=ps.executeUpdate();
		if(execup>0) {
			System.out.println("\nAppointment Deleted");
			return true;
		}
		else {
			return false;
		}
		
	}
	@Override
	public Appointment viewSingleapp(int cID) throws SQLException {
		// TODO Auto-generated method stub
		Appointment upd=new Appointment();
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select C.FirstName, C.Location, C.Email, C.phoneNumber, D.DoctorName, D.phoneNumber, D.Specalist, A.status from appointment A, customer C, doctor D where C.customerId=A.customerId and D.doctorId=A.doctorId and A.customerId="+cID+";");
		int i=0;
		while(rs.next()) {
			
			upd.setFirstName(rs.getString(1));
			upd.setLocation(rs.getString(2));
			upd.setEmail(rs.getString(3));
			upd.setphoneNumber(Long.parseLong(rs.getString(4)));
			upd.setDocName(rs.getString(5));
			upd.setPhoneNumber(Long.parseLong(rs.getString(6)));
			upd.setSpec(rs.getString(7));
			upd.setStatus(rs.getString(8));
		}
		return upd;
	}
	@Override
	public boolean moddapp(Appointment chng, int cID) throws SQLException {
		PreparedStatement ps=connection.prepareStatement("update appointment set doctorId=? where customerId="+cID+";");
		//Setting Value
		ps.setInt(1, chng.getDocID());
		System.out.println(chng.getDocID());
		//code gets executed
		int execup=ps.executeUpdate();
		if(execup>0) {
			System.out.println("\nAppointment Modified");
			return true;
		}
		else {
			return false;
		}
		
	}
	

}
