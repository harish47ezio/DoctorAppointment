package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.entity.Customer;

public interface CustomerDao {
	
	boolean add(Customer Cust)throws SQLException;

	boolean passcheck(long num, String pass) throws SQLException;

	Customer modder(long num) throws SQLException;

	boolean modupd(Customer modcustomer, long num) throws SQLException;

	boolean del(long num) throws SQLException;

	List<Customer> viewall() throws SQLException;

	int ID(long num)throws SQLException;
}
