package assignment2;



public class Customer {
// instance variables
	
// A unique number that identifies the customer
	private int customerNumber;
		
// The name of the person that this account belongs to
    private String name;
	    
// the customer's address
    private String address;
   
// all of the customer's various bank accounts
    private CheckingAccount checking;
    private SavingsAccount savings;
    private SavingsAccount autoLoan;
    private SavingsAccount studentLoan;
    
    
// constructors
    public Customer()
    {
    	customerNumber = -1;
    	name = "Not found";
    	address = "Not Found";
    	checking = null;
    	savings = null;
    	autoLoan = null;
    	studentLoan = null;
    }
    
    public Customer(int custNumber, String initName, String initAddress)
    {
    	customerNumber = custNumber;
    	name = initName;
    	address = initAddress;
    	checking = null;
    	savings = null;
    	autoLoan = null;
    	studentLoan = null;
    }
    
// getter methods
    public int getCustomerNumber()
    {
    	return customerNumber;
    }
	
    public String getName()
    {
    	return name;
    }
	
    public String getAddress()
    {
    	return address;
    }
    
    public CheckingAccount getChecking()
    {
    	return checking;
    }
    
    public SavingsAccount getSavings()
    {
    	return savings;
    }
    
    public SavingsAccount getAutoLoan()
    {
    	return autoLoan;
    }
    
    public SavingsAccount getStudentLoan()
    {
    	return studentLoan;
    }
    
// setter methods
    public void setCustomerNumber(int number)
    {
    	customerNumber = number;
    }
    
    public void setName(String newName)
    {
    	name = newName;
    }
    
    public void setAddress(String newAddress)
    {
    	address = newAddress;
    }
    
    public void setChecking(CheckingAccount newAccount)
    {
    	checking = newAccount;
    }
    
    public void setSavings(SavingsAccount newAccount)
    {
    	savings = newAccount;
    }
    
    public void setAutoLoan(SavingsAccount newAccount)
    {
    	autoLoan = newAccount;
    }
    
    public void setStudentLoan(SavingsAccount newAccount)
    {
    	studentLoan = newAccount;
    }
}
