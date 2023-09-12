package com.project.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.project.dao.CustomerDao;
import com.project.entity.Customer;
import com.project.util.DBUtil;

public class CustomerDaoDbImpl implements CustomerDao {
	private static final String INSERT_CUSTOMER="insert into customer(FirstName, LastName, Location, Email, phoneNumber, password) values(?,?,?,?,?,?)";
	private Connection connection=DBUtil.getConnection();
	
	@Override
	public boolean add(Customer CUSTO) throws SQLException{
		PreparedStatement ps=connection.prepareStatement(INSERT_CUSTOMER);
		//Setting Value
		ps.setString(1, CUSTO.getFirstName());
		ps.setString(2, CUSTO.getLastName());
		ps.setString(3, CUSTO.getLocation());
		ps.setString(4, CUSTO.getEmail());
		ps.setString(5, Long.toString(CUSTO.getphoneNumber()));
		ps.setString(6, CUSTO.getPassword());
		//code gets executed
		int execup=ps.executeUpdate();
		if(execup>0) {
			System.out.println("\nHI "+CUSTO.getFirstName()+", your Registration was Successful");
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean passcheck(long num, String pass) throws SQLException {
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select password from customer where phoneNumber="+Long.toString(num));
		String SPass = null;
		while(rs.next())
		SPass=rs.getString(1);
		if(SPass.equals(pass)) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	@Override
	public Customer modder(long num)throws SQLException {
		Customer upd=new Customer();
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from customer where phoneNumber="+Long.toString(num));
		while(rs.next()) {
			upd.setId(rs.getInt(1));
			upd.setFirstName(rs.getString(2));
			upd.setLastName(rs.getString(3));
			upd.setLocation(rs.getString(4));
			upd.setEmail(rs.getString(5));
			upd.setphoneNumber(Long.parseLong(rs.getString(6)));
			upd.setPassword(rs.getString(7));
		}
		return upd;
	}
	
	@Override
	public boolean modupd(Customer modcustomer, long num)throws SQLException{
		PreparedStatement ps=connection.prepareStatement("update customer set FirstName=?, LastName=?, Location=?, Email=?, phoneNumber=?, password=? where phoneNumber="+num);
		//Setting Value
		ps.setString(1, modcustomer.getFirstName());
		ps.setString(2, modcustomer.getLastName());
		ps.setString(3, modcustomer.getLocation());
		ps.setString(4, modcustomer.getEmail());
		ps.setString(5, Long.toString(modcustomer.getphoneNumber()));
		ps.setString(6, modcustomer.getPassword());
		//code gets executed
		int execup=ps.executeUpdate();
		if(execup>0) {
			System.out.println("\nHI "+modcustomer.getFirstName()+", your updation was Successful");
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean del(long num) throws SQLException{
		PreparedStatement ps=connection.prepareStatement("delete from customer where phoneNumber="+num);
		int execup=ps.executeUpdate();
		if(execup>0) {
			System.out.println("\nDeleted Successfully");
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public List<Customer> viewall() throws SQLException{
		
		List <Customer> VOO=new ArrayList<>();
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from customer");
		int i=0;
		while(rs.next()) {
			Customer upd=new Customer();
			upd.setFirstName(rs.getString(2));
			upd.setLastName(rs.getString(3));
			upd.setLocation(rs.getString(4));
			upd.setEmail(rs.getString(5));
			upd.setphoneNumber(Long.parseLong(rs.getString(6)));
			upd.setPassword(rs.getString(7));
			VOO.add(upd);
		}
		return VOO;
	}

	@Override
	public int ID(long num) throws SQLException {
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select customerId from customer where phoneNumber="+Long.toString(num));
		int CID=0;
		while(rs.next())
		CID=rs.getInt(1);
		return CID;
	}
}
