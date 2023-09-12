package com.project.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.DoctorDao;
import com.project.entity.Doctor;
import com.project.util.DBUtil;

public class DoctorDaoDbImpl implements DoctorDao {
	
	private Connection connection=DBUtil.getConnection();

	@Override
	public List<Doctor> viewalldoc() throws SQLException {
		List<Doctor> VAD=new ArrayList<>();
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery("select * from doctor");
		while(rs.next()) {
			Doctor upd=new Doctor();
			upd.setDoctoridd(rs.getInt(1));
			upd.setDocName(rs.getString(2));
			upd.setPhoneNumber(Long.parseLong(rs.getString(3)));
			upd.setSpec(rs.getString(4));
			VAD.add(upd);
		}
		return VAD;
	}

}
