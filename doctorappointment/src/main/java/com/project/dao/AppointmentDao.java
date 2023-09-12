package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.entity.Appointment;

public interface AppointmentDao {

	boolean add(Appointment newAPP) throws SQLException;

	List<Appointment> viewallapp() throws SQLException;

	boolean del(int cID)throws SQLException;

	Appointment viewSingleapp(int cID)throws SQLException;

	boolean moddapp(Appointment chng, int cID) throws SQLException;

}
