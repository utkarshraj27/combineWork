package com.capg.pecunia.service;

import com.capg.pecunia.entities.Account;
import com.capg.pecunia.entities.Cheque;
import com.capg.pecunia.entities.Transaction;

public interface ITransaction {
	
	int generateTransactionId(Transaction transaction);
	
	String generateChequeId(Cheque cheque);
	
	int debitUsingSlip(Transaction transaction);
	
	int creditUsingSlip(Transaction transaction);
	
	int creditUsingCheque(Transaction transaction,Cheque cheque);
	
	int debitUsingCheque(Transaction transaction,Cheque cheque);
	
	Account getAccountById(String AccountId);
	
	double getBalance(Account account);
	
	boolean updateBalance(Account account,double balance);

}
