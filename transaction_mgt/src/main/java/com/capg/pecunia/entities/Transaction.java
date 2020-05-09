package com.capg.pecunia.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	private int transId;
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	private String transAccountId;
	private String transType;
	private double transAmount;
	private String transOption;
	private Date transDate;
	private String chequeId;
	private String transFrom;
	private String transTo;
	private double transClosingBalance;
	public String getTransAccountId() {
		return transAccountId;
	}
	public void setTransAccountId(String transAccountId) {
		this.transAccountId = transAccountId;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public double getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransOption() {
		return transOption;
	}
	public void setTransOption(String transOption) {
		this.transOption = transOption;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getChequeId() {
		return chequeId;
	}
	public void setChequeId(String chequeId) {
		this.chequeId = chequeId;
	}
	public String getTransFrom() {
		return transFrom;
	}
	public void setTransFrom(String transFrom) {
		this.transFrom = transFrom;
	}
	public String getTransTo() {
		return transTo;
	}
	public void setTransTo(String transTo) {
		this.transTo = transTo;
	}
	public double getTransClosingBalance() {
		return transClosingBalance;
	}
	public void setTransClosingBalance(double transClosingBalance) {
		this.transClosingBalance = transClosingBalance;
	}
	
	
	
	
	

}
