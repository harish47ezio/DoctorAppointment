package com.project.entity;

import java.util.Formatter;

public class Doctor {
	private String docName;
	private int doctoridd;
	private long PhoneNumber;
	private String Spec;
	Formatter fmt=new Formatter();
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}
	/**
	 * @return the phoneNumber
	 */
	public long getPhoneNumber() {
		return PhoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(long phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	/**
	 * @return the spec
	 */
	public String getSpec() {
		return Spec;
	}
	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		Spec = spec;
	}
	public Formatter output() {
		fmt.format("| %5s | %12s | %13s | %15s |",doctoridd,docName,PhoneNumber,Spec);
		return fmt;
	}
	/**
	 * @return the doctoridd
	 */
	public int getDoctoridd() {
		return doctoridd;
	}
	/**
	 * @param doctoridd the doctoridd to set
	 */
	public void setDoctoridd(int doctoridd) {
		this.doctoridd = doctoridd;
	}

}
