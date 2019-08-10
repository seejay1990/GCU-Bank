package edu.gcu.cst135.ActivityGuide.CST235BankingStart;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.Bank;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.DatabaseBank;

public class App {
	// Starting point for banking application
	public static void main(String[] args) {
		// Create an object and set the name
		Bank bank = new Bank("Grand Canyon Credit Union");
		DatabaseBank db = new DatabaseBank();


	}
}
