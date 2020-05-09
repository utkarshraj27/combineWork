package com.capg.pecunia.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.pecunia.dao.AccountDao;
import com.capg.pecunia.dao.TransactionDao;
import com.capg.pecunia.entities.Account;
import com.capg.pecunia.entities.Cheque;
import com.capg.pecunia.entities.Transaction;
import com.capg.pecunia.exception.InSufficientBalanceException;
import com.capg.pecunia.exception.InValidAccountException;
import com.capg.pecunia.exception.ChequeBounceException;

@Service
@Transactional
public class ITransactionImpl implements ITransaction{
	@Autowired
	private TransactionDao transdao;
	
	
	@Autowired
	private AccountDao accountdao;

	@Override
	public int generateTransactionId(Transaction transaction) {
		// TODO Auto-generated method stub
		
		/*  RANDOM  DIGIT NUMBER TO GENERATE */
		
		return 0;
	}

	@Override
	public String generateChequeId(Cheque cheque) {
		// TODO Auto-generated method stub
		
		/* RANDOM DIGIT NUMBER IN STRING TO GENERATE  */
		
		return null;
	}

	@Override
	public int debitUsingSlip(Transaction transaction) {
		String accountId = transaction.getTransAccountId();
		Account  userAccount= getAccountById(accountId);
		transaction.setTransFrom(userAccount.getAccountHolderName());
		double balance = getBalance(userAccount);
		double transAmt = transaction.getTransAmount();
		if(transAmt>balance)
		{
			throw new InSufficientBalanceException("Not Enough Balance In The Account");
		}
		
		double updateBalance = balance-transAmt;
		boolean b = updateBalance(userAccount,updateBalance);
		int transactionId = generateTransactionId(transaction);
		transaction.setTransId(transactionId);
		transaction.setTransType("Debit");                      /*setting values for transaction entity */
		transaction.setTransOption("Using Slip");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		transaction.setTransDate(sdf.format(date));            /*  dont know how to set date in java.util.date*/
		
		transaction.setTransClosingBalance(updateBalance);
		transdao.save(transaction);
		return transactionId;

	}

	@Override
	public Account getAccountById(String AccountId) {
		 Optional<Account> optional = accountdao.findById(AccountId);
		 if (optional.isPresent()) {
	            Account myAccount = optional.get();
	            return myAccount;
	        }
	        throw new InValidAccountException("Account Not Found For Id=" + AccountId);
	    }
	@Override
	public double getBalance(Account account) {
		double myBalance = account.getBalance();
		return myBalance;
	}

	@Override
	public boolean updateBalance(Account account,double balance) {
		 Account existingAccount = getAccountById(account.getAccountId());
		existingAccount.setAccountHolderName(account.getAccountHolderName());
		existingAccount.setAccountId(account.getAccountId());
		existingAccount.setBalance(balance);
		accountdao.save(existingAccount);
		return true;
	}

	@Override
	public int creditUsingSlip(Transaction transaction) {			
		String accountId = transaction.getTransAccountId();			/*creditting the money manually  using slip */
		Account userAccount = getAccountById(accountId);
		transaction.setTransTo(userAccount.getAccountHolderName());	/*customer will pay the money to the bank to credit that amount
																			in a particular account */
		double balance = getBalance(userAccount);
		double transAmt = transaction.getTransAmount();
		double updatedBalance = balance+transAmt;
		boolean a = updateBalance(userAccount, updatedBalance);
		int transactionid = generateTransactionId(transaction);
		transaction.setTransId(transactionid);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		transaction.setTransDate(sdf.format(date));   /*  dont know how to set date in java.util.date*/
		transaction.setTransType("Credit");
		transaction.setTransOption("Using Slip");             /*setting values for transaction entity */
		transaction.setTransClosingBalance(updatedBalance);
		transdao.save(transaction);
		return transactionid;
		
		
	}

	@Override
	public int creditUsingCheque(Transaction transaction, Cheque cheque) {
		String chequeAccountNumber = cheque.getChequeAccountNo();  /* account of payee where amount will be deducted*/
		Account chequeAcc = getAccountById(chequeAccountNumber);
		transaction.setTransFrom(cheque.getChequeHolderName()); /*transaction information is set with chequeholderName */ 
		String transAccountNumber  = transaction.getTransAccountId();  /* account of beneficiary where amount will be credited*/
		Account transAcc = getAccountById(transAccountNumber);   
		transaction.setTransTo(transAcc.getAccountHolderName()); /*transaction info is set with name where amount will be credited */
		double chequeAccountBalance = getBalance(chequeAcc);
		double transAccountBalance = getBalance(transAcc);
		double  transAmt = transaction.getTransAmount();
		if(transAmt>chequeAccountBalance)
		{
			cheque.setChequeStatus("Cheque bounce");
			throw new ChequeBounceException("Cheque is Bounced");

		}
		double updatedChequeAccountBalance = chequeAccountBalance-transAmt;
		double updatedTransAccountBalance = transAccountBalance+transAmt;
		boolean a = updateBalance(transAcc, updatedTransAccountBalance);
		boolean b = updateBalance(chequeAcc, updatedChequeAccountBalance);
		cheque.setChequeStatus("Cheque is cleared");               /*setting cheque status */
		int transId = generateTransactionId(transaction);
		String chequeId  = generateChequeId(cheque);
		transaction.setTransId(transId);                /*setting values for transaction entity */
		transaction.setChequeId(chequeId);
		transaction.setTransDate(new Date());
		transaction.setTransOption("Cheque");
		transaction.setTransType("Credit");
		transaction.setTransClosingBalance(updatedTransAccountBalance);
		transdao.save(transaction);
		return transId;
	}

	@Override
	public int debitUsingCheque(Transaction transaction, Cheque cheque) {
		String accountId = cheque.getChequeAccountNo();
		transaction.setTransAccountId(accountId);
		Account myAccount = getAccountById(accountId);				/*cheque amount will be clear from the account no.
																		mentioned in the cheque*/
		transaction.setTransFrom(myAccount.getAccountHolderName());
		double myBalance = getBalance(myAccount);
		double transAmt = transaction.getTransAmount();
		if(transAmt>myBalance)
		{
			throw new InSufficientBalanceException("Not Enough Balance In The Account");
		}
		double updateBalance = myBalance-transAmt;
		boolean b = updateBalance(myAccount,updateBalance);
		cheque.setChequeStatus("Cheque is clear" );    /*setting cheque status */
		int transId = generateTransactionId(transaction);
		String chequeId = generateChequeId(cheque);
		transaction.setChequeId(chequeId);
		transaction.setTransId(transId);                  /*setting values for transaction entity */
		transaction.setTransOption("Cheque");
		transaction.setTransType("debit");
		transaction.setTransClosingBalance(updateBalance);
		transaction.setTransDate(new Date());   /*dont know how to set current date in java.util.Date */
		transdao.save(transaction);
		return transId;
	}

}
