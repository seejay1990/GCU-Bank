package edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller;

import java.awt.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.DatabaseBank;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;

public class Bank {
	static Menu menu = new Menu();
	static Scanner scan = new Scanner(System.in);
	// Class attributes
	private String name; // Hold the bank name
	private static int currentCustomer; // Hold the current (picked) customer
	static List<Customer> customers = new ArrayList<>(); // Hold a list of ALL customers

	// Constructor
	public Bank(String name) {
		this.name = name;
	}

	// Starting point
	public void start() {
		int option;
		// Keep going until they select a valid option
		do {
			option = Menus.custMenu();
		} while (option < 0 || option > 3);
		processCustMenu(option);
	}

	// Process what menu item the user picked
	private void processCustMenu(int option) {
		switch (option) {
		case 1:
			createCustomer();
			break;
		case 2:
			pickCustomer();
			break;
		case 3:
			loginMenu();
			break;
		default:
			System.exit(0);
		}
	}

	// Create a customer and add to the customers list
	private void createCustomer() {

		String firstName = Menus.userStrInput("Enter the customer's first name: ").toUpperCase();
		String lastName = Menus.userStrInput("Enter the customer's last name: ").toUpperCase();
		String userName = Menus.userStrInput("Create a User Name: ");
		String passWord = Menus.userStrInput("Enter a Password: ");

		customers.add(new Customer(firstName, lastName, userName, passWord));
		currentCustomer = customers.size() - 1;

		try (PreparedStatement prepST = DatabaseBank.conn
				.prepareStatement("Insert Into BankingApp.customer_info (firstName, lastName, userName, passWord) "
						+ "Values (?, ?, ?, ?) ")) {

			prepST.setString(1, firstName);
			prepST.setString(2, lastName);
			prepST.setString(3, userName);
			prepST.setString(4, passWord);
			prepST.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to Update Table");
		}
		// Display the customer menu
		processCustMenu(Menus.custMenu());

	}

	// Process the selected customer to use for banking transactions
	private void pickCustomer() {
		do {
			// CST235 TASK: REMOVE THE LIST ARGUMENT
			currentCustomer = Menus.pickCustomerMenu(customers) - 1;
		} while (currentCustomer > (customers.size() - 1));

		// If the use picked -1 (e.g. 0-1) send them back to the beginning
		if (currentCustomer == -1)
//			start();
			
			Menus.custMenu();
		else {
			// send the current user to bank transaction menu
			loginMenu();
			processCustomerMenu(Menus.viewCustomerMenu(customers.get(currentCustomer)));
			
		}
	}

	private void loginMenu() {
		System.out.println("Login Screen");
		System.out.println("===============");
		System.out.println();

		System.out.println("Enter Username:");
		String userName = scan.nextLine();

		System.out.println("Enter Password:");
		String passWord = scan.nextLine();

		if (checkLogin(userName, passWord)) {
			System.out.println("Successfully Logged In");
			Menus.viewCustomerMenu(customers.get(currentCustomer));

		} else {
			System.out.println("You FAILED TRY AGAIN!");
			loginMenu();
		}

	}

	private static boolean checkLogin(String userName, String passWord) {
		String sql = "Select userName, passWord FROM customer_info Where userName = ? AND passWord = ?";
		try (PreparedStatement statement = DatabaseBank.conn.prepareStatement(sql)) {
			statement.setString(1, userName);
			statement.setString(2, passWord);
			ResultSet rSet = statement.executeQuery();
			if (rSet.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	// Process the bank transaction menu option
	private void processCustomerMenu(int parseInt) {

		switch (parseInt) {
		case 1:
			viewDepositChecking();
			viewBalances();
			break;
		case 2:
			viewDepositSavings();
			viewBalances();
			break;
		case 3:
			viewWithdrawalChecking();
			viewBalances();
			break;
		case 4:
			viewWithdrawalSavings();
			viewBalances();
			break;
		case 5:
			viewBalances();
			break;
		case 6:
			viewLoanPayment();
			viewBalances();
			break;
		case 7:
			viewEndOfMonth();
			viewBalances();
			break;
		default:
			// Back to the starting point and reset current customer
//			currentCustomer = 0;
			processCustMenu(Menus.custMenu());
		}
	}

	// Execute each end of month methods in each class
	private void viewEndOfMonth() {
		customers.get(currentCustomer).getSaving().doEndOfMonth();
		customers.get(currentCustomer).getChecking().doEndOfMonth();
		customers.get(currentCustomer).getLoan().doEndOfMonth();
	}

	// display and process loan payment
	private void viewLoanPayment() {
		customers.get(currentCustomer).getLoan().doCredit(Menus.userDblInput("How much to pay on your loan?"));
	}

	// display and process savings withdraw
	private void viewWithdrawalSavings() {
		customers.get(currentCustomer).getSaving().doCredit(Menus.userDblInput("How much to withdraw from savings?"));
	}

	// display and process checking withdraw
	private void viewWithdrawalChecking() {
		customers.get(currentCustomer).getChecking()
				.doCredit(Menus.userDblInput("What is you check amount to withdraw from checking?"));
	}

	// display and process savings deposit
	private void viewDepositSavings() {
		customers.get(currentCustomer).getSaving().doDebit(Menus.userDblInput("How much to deposit into savings?"));
	}

	// display and process checking deposit
	private void viewDepositChecking() {
		customers.get(currentCustomer).getChecking().doDebit(Menus.userDblInput("How much to deposit into checking?"));
	}

	// display balances
	private void viewBalances() {
		Menus.viewBalances(customers.get(currentCustomer));
		processCustomerMenu(Menus.viewCustomerMenu(customers.get(currentCustomer)));
	}

}
