package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.entity.Doctor;

public interface DoctorDao {

	List<Doctor> viewalldoc() throws SQLException;

}
