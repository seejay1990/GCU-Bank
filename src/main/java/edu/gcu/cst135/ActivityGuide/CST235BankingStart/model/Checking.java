package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;

public class Checking extends Account {

	private double overDraft;
	
	List<Transaction> trans = new ArrayList<>();
	
	public Checking(String account, double amount) {
		super(account, amount);
		this.overDraft = 25.00;
	}
	
	
	
	
	public void doCredit(double amt) {
		if (this.getAccountBalance() < amt) {
			Menus.printOut("Insufficient funds! Overdraft fee of $" + overDraft + " applied");
			this.setAccountBalance(this.getAccountBalance() - overDraft);
			addTransaction(overDraft, "Overdraft fee applied");			
		}
		this.setAccountBalance(this.getAccountBalance() - amt);
		addTransaction(amt, "Checking Withdrawal");

	}
	
	public void doDebit(double amt) {
		this.setAccountBalance(this.getAccountBalance() + amt);
		addTransaction(amt, "Checking Deposit");

	}
	
	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}
	
	public String toString() {
		return super.getAccountNumber() + "  " + super.getAccountBalance();
	}

	@Override
	public void addTransaction(double amount, String description) {
		trans.add(new Transaction(this.getAccountNumber(), amount, description));
	}

	@Override
	public void ListTransaction() {
		Menus.printOut("########################");
		Menus.printOut(" Checking Transactions");
		Menus.printOut("########################");
		for(Transaction t : trans)
			System.out.println(t.toString());
		Menus.printOut("########################\n\n");
	}

	@Override
	public void doEndOfMonth() {
		// Nothing to do at this time
		Menus.printBalance(this);
		ListTransaction();	
	}

}
