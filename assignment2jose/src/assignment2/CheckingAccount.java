// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 2

package assignment2;

public class CheckingAccount extends BankAccount {

	private static final int OVERDRAFT_CHARGE = 20;
	
	private SavingsAccount overdraftAccount;
	
	public CheckingAccount() {
		super();
		overdraftAccount = null;
	}

	public CheckingAccount(double initialBalance) {
		super(initialBalance);
		overdraftAccount = null;
	}

	public CheckingAccount(int acct, String owner, double initBalance) {
		super(acct, owner, initBalance);
		overdraftAccount = null;
	}
	
    /**
     * Update the current balance by subtracting the given amount.
     * Precondition: the current balance must have at least the amount in it.
     * Postcondition: the new balance is decreased by the given amount.
     * Special case: If an overdraft account exists, that account can finish transaction
     * @param amount  The amount to subtract
     */
    public boolean withdraw(double amount) 
    {  
        if (balance >=  amount)
        {
            balance = balance - amount;
            System.out.println(ownersName + "'s new checking account balance is: $" + balance);
            return true;
        } else {
        	boolean overdraftProtection = false;
        	double protection = 0;
        	
        	if(overdraftAccount != null)
        	{
        		overdraftProtection = true;
        	}
        	
        	if(overdraftProtection)
        	{
        		protection = overdraftAccount.getBalance();
        	}
        	
        	double totalBalance = balance + protection;
        	
        	boolean haveEnoughMoney = true;
        	
        	if(totalBalance < (amount+20))
        	{
        		haveEnoughMoney = false;
        	}
        	
           	if(haveEnoughMoney)
        	{
        		double difference = amount - balance;
        		balance = 0;
        		overdraftAccount.withdraw(difference + OVERDRAFT_CHARGE);
        		System.out.println(ownersName + "'s new checking account balance is: $" + balance);
        		System.out.println("Due to overdraft, $" + (difference + OVERDRAFT_CHARGE) + " was deducted from savings");
        		System.out.println("$" + OVERDRAFT_CHARGE + " of that was charged as a transaction fee.");
        		return true;
        	} else {
        		System.out.println("Insufficient funds.  Withdrawal not processed.");
        		return false;
        	}
 
        }
    }
	

// getter method
	public SavingsAccount getOverdraftAccount()
	{
		return overdraftAccount;
	}
	
// setter method
	public void setOverdraftAccount(SavingsAccount overdraft)
	{
		overdraftAccount = overdraft;
	}

}
