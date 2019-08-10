package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.iTrans;

public abstract class Account implements iTrans {
	private String accountNumber;
	private double accountBalance;
	
	public abstract void doEndOfMonth();
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public void doCredit(double amt) {			
		this.setAccountBalance(this.getAccountBalance() - amt);
	}
	
	public void doDebit(double amt) {
		this.setAccountBalance(this.getAccountBalance() + amt);
	}

	public String toString() {
		return accountNumber + " $" + accountBalance;
	}

	public Account(String accountNumber, double accountBalance) {
		
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
}
