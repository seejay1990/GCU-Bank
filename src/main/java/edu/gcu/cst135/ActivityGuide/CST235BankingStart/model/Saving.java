package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;

public class Saving extends Account{
	
	private double minBalance;
	private double intRate;
	
	List<Transaction> trans = new ArrayList<>();
	
	public Saving(String account, double amount) {
		super(account, amount);
		this.setMinBalance(250.00);
		this.intRate = .01;
	}
	
	public void doCredit(double amt) {
		this.setAccountBalance(this.getAccountBalance() - amt);
		addTransaction(amt, "Savings Withdrawal");
	}
	
	public void doDebit(double amt) {
		this.setAccountBalance(this.getAccountBalance() + amt);
		addTransaction(amt, "Savings Deposit");
	}
	
	public String toString() {
		return super.getAccountNumber() + "  " + super.getAccountBalance();
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	@Override
	public void addTransaction(double amount, String description) {
		trans.add(new Transaction(this.getAccountNumber(), amount, description));
	}

	@Override
	public void ListTransaction() {
		Menus.printOut("########################");
		Menus.printOut(" Savings Transactions");
		Menus.printOut("########################");
		for(Transaction t : trans)
			System.out.println(t.toString());
		Menus.printOut("########################\n\n");
	}

	@Override
	public void doEndOfMonth() {
		if (this.getAccountBalance() < this.minBalance) {
				Menus.printOut("Account below minumum balance - No interest accrued!");
		}
		else {
			Menus.printOut("Account above minumum balance - Interest accrued!");
			double interest = this.getAccountBalance() * intRate;
			Menus.printOut("Interest Earned : $" + interest);
			this.setAccountBalance(this.getAccountBalance() + interest);
			addTransaction(interest, "Interested Added");	
		}
		Menus.printBalance(this);
		ListTransaction();
	}
}
