package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.Bank;



public class DatabaseBank {
	static Bank bank = new Bank("Grand Canyon Credit Union");
	static String url = "jdbc:mysql://cst235mysql.chgctfew4axe.us-east-2.rds.amazonaws.com:3306/BankingApp";
	static String userN = "admin";
	static String password = "password2019";

	//Database connection to be used with SQL statements
	public static Connection conn;

	static {
		System.out.println("Connecting to the GCU Bank....");

		try {
			conn = DriverManager.getConnection(url, userN, password);
			System.out.println("Connected to the GCU Bank \n");
			bank.start();

		} catch (SQLException e) {
			System.err.println("Login Failed");
			e.printStackTrace();
		}

	}

}
