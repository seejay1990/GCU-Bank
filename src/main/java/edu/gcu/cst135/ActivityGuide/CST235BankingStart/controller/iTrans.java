package edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller;

public interface iTrans {
	//Interface that runs per valid transaction
	void addTransaction(double amount, String description);
	void ListTransaction();

}
