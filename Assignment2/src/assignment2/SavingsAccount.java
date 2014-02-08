package assignment2;

public class SavingsAccount extends BankAccount {

	private double interestRate;
	private double minimumBalance;

/* Constructors inherit defaults from BankAccount and default to
 * a 5% interest rate and 1000 minimum balance.  To change the default
 * values, user needs to use setter methods to avoid overload confusion
*/
	public SavingsAccount() {
		super();
		interestRate = 1.05;
		minimumBalance = 1000;
	}

	public SavingsAccount(double initialBalance) {
		super(initialBalance);
		interestRate = 1.05;
		minimumBalance = 1000;
	}

	public SavingsAccount(int acct, String owner, double initBalance) {
		super(acct, owner, initBalance);
		interestRate = 1.05;
		minimumBalance = 1000;
	}

/**
 * Update the current balance by subtracting the given amount.
 * Precondition: the current balance must be above the minimal balance.
 * Postcondition: the new balance is increased by the interest rate.
 */	
	
	public void ProcessInterest()
	{
		if(balance < minimumBalance)
		{
			System.out.println("Insufficient funds to earn interest.");
		} else {
			balance = balance * interestRate;
			System.out.println(ownersName + "'s accounted updated to: $" + balance);
		}
	}
	
	
// Getter methods
	public double getInterestRate()
	{
		return interestRate;
	}
	
	public double getMinimumBalance()
	{
		return minimumBalance;
	}
	
// Setter methods
	public void setInterestRate(double rate)
	{
		interestRate = rate;
	}
	
	public void setMinimumBalance(double minimum)
	{
		minimumBalance = minimum;
	}
	
}

