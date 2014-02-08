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
    public void withdraw(double amount) 
    {  
        if (balance >=  amount)
        {
            balance = balance - amount;
            System.out.println(ownersName + "'s new balance is: $" + balance);
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
        	
        	if((balance+protection+OVERDRAFT_CHARGE) >= amount)
        	{
        		double difference = amount - balance;
        		balance = 0;
        		overdraftAccount.withdraw(difference + OVERDRAFT_CHARGE);
        		System.out.println(ownersName + "'s new balance is: $" + balance);
        		System.out.println("Due to overdraft, $" + difference + " was deducted from savings");
        		System.out.println("$" + OVERDRAFT_CHARGE + " was charged as a transaction fee.");
        	} else {
        		System.out.println("Insufficient funds.  Withdrawal not processed.");
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
