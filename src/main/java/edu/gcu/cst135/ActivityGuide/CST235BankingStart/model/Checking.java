package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.Bank;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;

/**
 * @author Charles R. Edwards Jr.
 *
 */
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
		}else if(amt <= -1){
			System.out.println("Unable to process negative value.\nPlease Try Again\n");
			
		} else{
			this.setAccountBalance(this.getAccountBalance() - amt);
			addTransaction(amt, "Checking Withdrawal");
		}
		

	}
	
	public void doDebit(double amt) {
		Bank deptAgain = new Bank("");
		if(amt <= -1 ) {
			System.out.println("Unable to process negative deposit!\nPlease Try Again\n");
			deptAgain.viewDepositChecking();
		} else {
			this.setAccountBalance(this.getAccountBalance() + amt);
			addTransaction(amt, "Checking Deposit");
		}

	}
	//Getters and Setters
	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}
	
	public String toString() {
		return super.getAccountNumber() + "  " + super.getAccountBalance();
	}

	//Captures and records users transactions to List
	@Override
	public void addTransaction(double amount, String description) {
		trans.add(new Transaction(this.getAccountNumber(), amount, description));
	}
	//Displays the users transactions
	@Override
	public void ListTransaction() {
		Menus.printOut("########################");
		Menus.printOut(" Checking Transactions");
		Menus.printOut("########################");
		for(Transaction t : trans)
			System.out.println(t.toString());
		Menus.printOut("########################\n\n");
	}

	// Display a customers end of month statements
	@Override
	public void doEndOfMonth() {
		Menus.printBalance(this);
		ListTransaction();	
	}

}
