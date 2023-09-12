package com.project.entity;

import java.util.Formatter;

public class Appointment extends Customer {
	private String status;
	private int appId;
	private int custId;
	private int docID;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the appId
	 */
	public int getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(int appId) {
		this.appId = appId;
	}

	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}

	/**
	 * @return the docID
	 */
	public int getDocID() {
		return docID;
	}

	/**
	 * @param docID the docID to set
	 */
	public void setDocID(int docID) {
		this.docID = docID;
	}

	public Formatter outputapp() {
		// TODO Auto-generated method stub
		fmt.format("| %12s | %9s | %23s | %17s | %13s| %17s | %16s | %15s |",getFirstName(),getLocation(),getEmail(),getPhoneNumber(),getDocName(),getPhoneNumber(),getSpec(),getStatus() );
		return fmt;
	}
	

}
