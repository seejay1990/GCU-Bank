package edu.gcu.cst135.ActivityGuide.CST235BankingStart.view;

import java.util.Scanner;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Account;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;



public class Menus {

	
	public static Scanner scan = new Scanner(System.in);



	/** Banking launch menu
	 * @return option - Users choice
	 */
	public static int custMenu() {
		int option = 0;
		try {
			System.out.println("***************************");
			System.out.println("  BANK CUSTOMER MENU  ");
			System.out.println("***************************");
			System.out.println(" 1. Create a customer");
			System.out.println(" 2. Login Menu");
			System.out.println("---------------------------");
			System.out.println(" 0. Exit");
			System.out.println("***************************");
			System.out.println("What is your choice?");
			String opt = scan.nextLine();
			option = Integer.parseInt(opt);
		} catch (Exception e) {
			System.err.println("Invalid Input. Try again!");
			option = -1;
		}
		return option;
	}

	
	
	/** Getting user verbal inputs
	 * @param message 
	 * @return Users input as a String
	 */
	public static String userStrInput(String message) {
		System.out.println(message);
		return scan.nextLine();
	}

	
	
	/** Getting user values for transactions
	 * @param message
	 * @return User double input (money)
	 */
	public static double userDblInput(String message) {
		double amount = 0.0;
		try {
			System.out.println(message);
			String input = scan.nextLine();
			amount = Double.parseDouble(input);
		} catch (Exception e) { 
			System.err.println("Wrong value input\n");
			amount = -1.0;
		}
		return amount;
	}

	
	/**Display of the banking menu
	 * @param c
	 * @return Customer id info
	 */
	public static int viewCustomerMenu(Customer c) {

		try {
			String option;
			do {
				System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("                MAIN MENU");
				System.out.println("Welcome Back! " + c.getFirstName().toUpperCase() + " " + c.getLastName().toUpperCase());
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("Pick an option: ");
				System.out.println("-----------------------");
				System.out.println(" 1: Deposit to Checking");
				System.out.println(" 2: Deposit to Savings");
				System.out.println(" 3: Withdraw from Checking");
				System.out.println(" 4: Withdraw from Savings");
				System.out.println(" 5: Get balance");
				System.out.println(" 6: Make Loan Payment");
				System.out.println(" 7: Get monthly statement");
				System.out.println("------------------------");
				System.out.println(" 9: : Logout");
				option = scan.nextLine();
				return Integer.parseInt(option);
			} while (Integer.parseInt(option) != 9);
		} catch (Exception e) { 
			System.out.println("Wrong transaction menu input\n");
			viewCustomerMenu(c);
		}
		return 0;
	}

	
	
	/** View customer balances
	 * @param cust - customer balance pulled from the customer account getters.
	 */
	public static void viewBalances(Customer cust) {
		System.out.println("------------------------");
		System.out.println("CUSTOMER BALANCES");
		System.out.println("------------------------");
		System.out.println("CHECKING : \t" + cust.getChecking().getAccountNumber() + " \t $"
				+ cust.getChecking().getAccountBalance());
		System.out.println(
				"SAVING :   \t" + cust.getSaving().getAccountNumber() + " \t $" + cust.getSaving().getAccountBalance());
		System.out.println(
				"LOAN :     \t" + cust.getLoan().getAccountNumber() + " \t $" + cust.getLoan().getAccountBalance());
		System.out.println("------------------------");
	}

	// Formatted syso method
	public static void printOut(String message) {
		System.out.println(" > " + message);
	}

	// Formatted balance printing method
	public static <T> void printBalance(T obj) {
		System.out.println(((Account) obj).getAccountNumber() + " : $" + ((Account) obj).getAccountBalance());
	}
}
