// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 2

package assignment2;

public class Input {

	private int custNumber;
	private char transType;
	private double transAmount;
	private char acctType;
	private char transferAcct;
	
	
/**
 * Constructor sets all values to distinctly bad values.  Customer numbers start at 1.
 * 'z' is never used with an account or a transaction type.
 * 0 is a null transaction.
 * These values make it easy to see an input is invalid.
 */
	public Input()
	{
		custNumber = 0;
		transType = 'z';
		transAmount = 0;
		acctType = 'z';
		transferAcct = 'z';
	}

/**
 * A series of getters for each attribute
 */
	public int getCustNumber()
	{
		return custNumber;
	}
	
	public char getTransType()
	{
		return transType;
	}

	public char getAcctType()
	{
		return acctType;
	}
	
	public char getTransferAcct()
	{
		return transferAcct;
	}
	
	public double getTransAmount()
	{
		return transAmount;
	}
	
/**
 * A series of setters for each attribute
 */
	public void setCustNumber(int newNumber)
	{
		custNumber = newNumber;
	}
	
	public void setTransType(char newType)
	{
		transType = newType;
	}
	
	public void setAcctType(char newType)
	{
		acctType = newType;
	}
	
	public void setTransferAcct(char newType)
	{
		transferAcct = newType;
	}
	
	public void setTransAmount(double newAmount)
	{
		transAmount = newAmount;
	}
}
