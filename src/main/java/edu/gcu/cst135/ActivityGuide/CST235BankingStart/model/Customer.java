package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

public class Customer {

	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	private Checking checking;
	private Saving saving;
	private Loan loan;

	// Getters/Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Checking getChecking() {
		return checking;
	}

	public void setChecking(Checking checking) {
		this.checking = checking;
	}

	public Saving getSaving() {
		return saving;
	}

	public void setSaving(Saving saving) {
		this.saving = saving;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * Customer method. Creates initial values of $2,000
	 * 
	 * @param firstName Customer First Name
	 * @param lastName Customer Last Name
	 * @param userName User created user name
	 * @param passWord User created password
	 */
	public Customer(String firstName, String lastName, String userName, String passWord) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
		checking = new Checking(generateAcct('C'), 2000);
		saving = new Saving(generateAcct('S'), 2000);
		loan = new Loan(generateAcct('L'), 2000);
	}

	private String generateAcct(char type) {
		String acct = "0";
		for (int x = 0; x < 9; x++) {
			acct += (int) (Math.random() * 9) + 1;
		}
		return acct + type;
	}

	public String toString() {
		return firstName + " " + lastName;
	}

}
