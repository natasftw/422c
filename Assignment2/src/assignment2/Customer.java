package assignment2;



public class Customer {
// instance variables
	private static final int MAX_ACCOUNTS = 4;
	
// A unique number that identifies the customer
	private int customerNumber;
		
// The name of the person that this account belongs to
    private String name;
	    
// the customer's address
    private String address;
   
// an array holding all of the customer's bank accounts
// Indexes are: 0 - checking, 1 - general savings, 2 - car savings, 3 - student savings
    private BankAccount[] accountsHeld = new BankAccount[MAX_ACCOUNTS];
    
    
// constructors
    public Customer()
    {
    	customerNumber = -1;
    	name = "Not found";
    	address = "Not Found";
    	accountsHeld[0] = null;
    	accountsHeld[1] = null;
    	accountsHeld[2] = null;
    	accountsHeld[3] = null;
    }
    
    public Customer(int custNumber, String initName, String initAddress)
    {
    	customerNumber = custNumber;
    	name = initName;
    	address = initAddress;
    	accountsHeld[0] = null;
    	accountsHeld[1] = null;
    	accountsHeld[2] = null;
    	accountsHeld[3] = null;
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
    
    public BankAccount getAccount(char type)
    {
    	if(type == 'C' || type == 'c')
    	{
    		return accountsHeld[0];
    	} else if(type == 'S' || type == 's') {
    		return accountsHeld[1];
    	} else if(type == 'A' || type == 'a') {
    		return accountsHeld[2];
    	} else if(type == 'L' || type == 'l') {
    		return accountsHeld[3];
    	} else {
    		return null;
    	}
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
    
    public void setAccount(char type, BankAccount newAccount)
    {
    	if(type == 'C' || type == 'c')
    	{
    		accountsHeld[0] = newAccount;
    	} else if(type == 'S' || type == 's') {
    		accountsHeld[1] = newAccount;
    	} else if(type == 'A' || type == 'a') {
    		accountsHeld[2] = newAccount;
    	} else if(type == 'L' || type == 'l') {
    		accountsHeld[3] = newAccount;
    	} else {
    		System.out.println("That account type doesn't exist. Please try again");
    	}
    }
}
