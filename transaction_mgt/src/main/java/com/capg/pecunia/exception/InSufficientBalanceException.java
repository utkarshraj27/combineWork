package com.capg.pecunia.exception;

public class InSufficientBalanceException extends RuntimeException {
	
	public InSufficientBalanceException(String msg)
	{
		super(msg);
	}

}
