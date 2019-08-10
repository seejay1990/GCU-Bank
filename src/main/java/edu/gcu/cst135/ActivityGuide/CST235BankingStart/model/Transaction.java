package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.Date;

public class Transaction {
	
	private String acctNumber;
	private double amount;
	private Date date;
	private String description;
	
	Transaction (String acctNumber, double amount, String description){
		this.acctNumber = acctNumber;
		this.amount = amount;
		this.description = description;
		this.date = new Date();
	}
	
	public String toString() {
		return acctNumber + " " + "\t$" + amount + " \t" + description + " \t" + date;
	}

}
