package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.Bank;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;

/**
 * @author Charles R. Edwards Jr.
 *
 */
/**
 * @author Charles R. Edwards Jr.
 *
 */
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
		Bank wtdAgain = new Bank("");
		
		
		if(amt > this.getAccountBalance()) {
			System.out.println("Unable to withdraw more than current Savings Account balance.\nPlease Try Again");
			System.out.println("Current Savings Balance ==> " + this.getAccountBalance() +"\n");
			wtdAgain.viewWithdrawalSavings();
		}else if(amt <= -1) {
			System.out.println("Unable to withdraw a negative entry!\nPlease Try Again\n");
			wtdAgain.viewWithdrawalSavings();
		} else {
			this.setAccountBalance(this.getAccountBalance() - amt);
			addTransaction(amt, "Savings Withdrawal");
		}
		
	}
	
	public void doDebit(double amt) {
		Bank deptAgain = new Bank("");
		
		if(amt <= -1 ) {
			System.out.println("Unable to process negative deposit!\nPlease Try Again\n");
			deptAgain.viewDepositSavings();
		}else {
			this.setAccountBalance(this.getAccountBalance() + amt);
			addTransaction(amt, "Savings Deposit");
		}
		
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
