package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.Bank;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;


public class Loan extends Account {

	private double lateFee;
	private boolean isPaid = false;
	private double intRate;
	
	List<Transaction> trans = new ArrayList<>();
	
	public Loan(String account, double amount) {
		super(account, amount);
		this.lateFee =25.00;
		this.intRate = .02;
	}

	public void doCredit(double amt) {
		this.setAccountBalance(this.getAccountBalance() - amt);
		isPaid = true;
		addTransaction(amt, "Loan Payment");
	}
	
	public void doDebit(double amt) {
		Bank loan = new Bank("");
		
		if(amt <= -1 ) {
			System.out.println("Unable to process negative deposit!\nPlease Try Again\n");
			loan.viewLoanPayment();
		}else {
			this.setAccountBalance(this.getAccountBalance() + amt);
			addTransaction(amt, "Loan Withdrawal");
		}
		
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
		Menus.printOut(" Loan Transactions");
		Menus.printOut("########################");
		for(Transaction t : trans)
			System.out.println(t.toString());
		Menus.printOut("########################\n\n");
	}

	@Override
	public void doEndOfMonth() {
		double interest = this.getAccountBalance() * intRate;
		Menus.printOut("Interest charged : $" + interest);
		this.setAccountBalance(this.getAccountBalance() + interest);
		addTransaction(interest, "Monthly Interest Added");
		if (!isPaid) {
			Menus.printOut("No Payment this month - Late fee added!");
			this.setAccountBalance(this.getAccountBalance() + lateFee);
			addTransaction(lateFee, "Late Fee Added");
		}
		Menus.printBalance(this);
		ListTransaction();
	}
}
