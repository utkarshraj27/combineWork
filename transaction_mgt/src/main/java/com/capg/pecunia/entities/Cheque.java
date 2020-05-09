package com.capg.pecunia.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="cheque")
public class Cheque {
	@Id
	private String ChequeId;
	private int chequeNum;    /* should be of 6 digit only */
	private String chequeAccountNo;  /*should be of 12 digit */
	private String chequeHolderName;
	private String chequeBankName;  
	private String chequeIFSC;   /* should be 10 character alphanumeric */
	private Date chequeIssueDate;
	private String chequeStatus;    
	public String getChequeId() {
		return ChequeId;
	}
	public void setChequeId(String chequeId) {
		ChequeId = chequeId;
	}
	public int getChequeNum() {
		return chequeNum;
	}
	public void setChequeNum(int chequeNum) {
		this.chequeNum = chequeNum;
	}
	public String getChequeAccountNo() {
		return chequeAccountNo;
	}
	public void setChequeAccountNo(String chequeAccountNo) {
		this.chequeAccountNo = chequeAccountNo;
	}
	public String getChequeHolderName() {
		return chequeHolderName;
	}
	public void setChequeHolderName(String chequeHolderName) {
		this.chequeHolderName = chequeHolderName;
	}
	public String getChequeBankName() {
		return chequeBankName;
	}
	public void setChequeBankName(String chequeBankName) {
		this.chequeBankName = chequeBankName;
	}
	public String getChequeIFSC() {
		return chequeIFSC;
	}
	public void setChequeIFSC(String chequeIFSC) {
		this.chequeIFSC = chequeIFSC;
	}
	public Date getChequeIssueDate() {
		return chequeIssueDate;
	}
	public void setChequeIssueDate(Date chequeIssueDate) {
		this.chequeIssueDate = chequeIssueDate;
	}
	public String getChequeStatus() {
		return chequeStatus;
	}
	public void setChequeStatus(String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}
	
	
	
	
}
