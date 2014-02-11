// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 2

package assignment2;

import javax.swing.JOptionPane; 

public class Client {
		
	public static void main (String [] args) 
	{ 	
		boolean moreInput = true;
		Customer[] customers = new Customer[2];

		initializeData(customers);
				
		while(moreInput)
		{
			Input transaction = parseInput();
			boolean transferToSelf = false;

			if(transaction.getTransType() == 't')
			{
				char firstAccount = transaction.getAcctType();
				char secondAccount = transaction.getTransferAcct();
				if(firstAccount == secondAccount)
				{
					transferToSelf = true;
				}
			}
			
			if(!transferToSelf)
			{
				if(transaction.getAcctType() == 'c')
				{
					performCheckingTransaction(transaction, customers);
				} else {
					performSavingsTransaction(transaction, customers);
				}
			} else {
				System.out.println("Isn't It a Bit Odd to Transfer to and From a Single Account?");
			}
							
			moreInput = checkForMoreInput();
		}
		
		displayFinalResults(customers);	
		System.exit(0);
	}				

	
/**
 * A method to setup the initial state of the database for the Client
 * This creates two customers, one for each student on our team.
 * Each customer is filled with all account types, an address, a customer number, and a name.
 * Each account is assigned a number, owner, and all other attributes assigned	
 * @param customerArray - main's array used to hold both customers
 */
	
	private static void initializeData(Customer [] customerArray)
	{		
		customerArray[0] = new Customer();
		customerArray[1] = new Customer();
		
		customerArray[0].setAddress("120 Park Place");
		customerArray[0].setName("Jeff");
		customerArray[0].setCustomerNumber(1);
		customerArray[1].setAddress("321 Boardwalk");
		customerArray[1].setName("Jose");
		customerArray[1].setCustomerNumber(2);
		
		customerArray[0].setChecking(new CheckingAccount(1, "Jeff", 1000));
		customerArray[0].setSavings(new SavingsAccount(2, "Jeff", 1000));
		customerArray[0].setAutoLoan(new SavingsAccount(3, "Jeff", 1000));
		customerArray[0].setStudentLoan(new SavingsAccount(4, "Jeff", 1000));
		customerArray[1].setChecking(new CheckingAccount(5, "Jose", 1000));
		customerArray[1].setSavings(new SavingsAccount(6, "Jose", 1000));
		customerArray[1].setAutoLoan(new SavingsAccount(7, "Jose", 1000));
		customerArray[1].setStudentLoan(new SavingsAccount(8, "Jose", 1000));
		
		CheckingAccount tempChecking = customerArray[0].getChecking();
		tempChecking.setOverdraftAccount(customerArray[0].getSavings());
		tempChecking = customerArray[1].getChecking();
		tempChecking.setOverdraftAccount(customerArray[1].getSavings());
	}

/**
 * This method prompts the user to see if more transactions need to be input.
 * @return a boolean based on the user's decision
 */
	private static boolean checkForMoreInput()
	{
		boolean anotherTransaction = true;
		boolean returnValue = false;
		
		while(anotherTransaction)
		{
			String newInput = JOptionPane.showInputDialog("Do you have another transaction? (Y/N)");
			char checkInput = newInput.charAt(0);
			if(checkInput == 'N' || checkInput == 'n')
			{
				anotherTransaction = false;
			} else if(checkInput == 'Y' || checkInput == 'y') {
				returnValue = true;
				anotherTransaction = false;
			} else {
				anotherTransaction = true;
			}
		}
		return returnValue;
	}
	
/**
 * A method to perform any transaction required of a checking account
 * @param transaction - the input object
 * @param customers - the array of customers
 */
	private static void performCheckingTransaction(Input transaction, Customer[] customers)
	{
		boolean transfer = false;
		CheckingAccount workingAccount = customers[transaction.getCustNumber() - 1].getChecking();
		
		if(transaction.getTransType() == 't')
		{
			transfer = true;
		}
		
		if(!transfer)
		{			
			if(transaction.getTransType() == 'w')
			{
				workingAccount.withdraw(transaction.getTransAmount());
			} else if(transaction.getTransType() == 'd') {
				workingAccount.deposit(transaction.getTransAmount());
			} else if(transaction.getTransType() == 'g') {
				System.out.println(customers[transaction.getCustNumber() -1].getName() +"'s Checking Account Has a Balance of: $" + workingAccount.getBalance());
			} else {
				System.out.println("I'm Sorry.  Checking Accounts Do Not Get Interest.");
			}		
		} else {
			SavingsAccount transferHere = new SavingsAccount();
			if(transaction.getTransferAcct() == 's')
			{
				transferHere = customers[transaction.getCustNumber() - 1].getSavings();
			} else if(transaction.getTransferAcct() == 'l') {
				transferHere = customers[transaction.getCustNumber() - 1].getStudentLoan();
			} else {
				transferHere = customers[transaction.getCustNumber() - 1].getAutoLoan();
			}
			boolean processTransfer = workingAccount.withdraw(transaction.getTransAmount());
			if(processTransfer)
			{
				transferHere.deposit(transaction.getTransAmount());
			}
		}
	}
	
/**
 * A method to perform any transaction required of a savings account
 * @param transaction - the input object
 * @param customers - the array of customers
 */
	private static void performSavingsTransaction(Input transaction, Customer[] customers)
	{
		boolean transfer = false;
		SavingsAccount workingAccount = new SavingsAccount();

		
		if(transaction.getAcctType() == 's')
		{
			workingAccount = customers[transaction.getCustNumber() - 1].getSavings();
		} else if(transaction.getAcctType() == 'a') {
			workingAccount = customers[transaction.getCustNumber() - 1].getAutoLoan();
		} else {
			workingAccount = customers[transaction.getCustNumber() - 1].getStudentLoan();
		}
		
		if(transaction.getTransType() == 't')
		{
			transfer = true;
		}
		
		if(!transfer)
		{
			if(transaction.getTransType() == 'w')
			{
				workingAccount.withdraw(transaction.getTransAmount());
			} else if(transaction.getTransType() == 'd') {
				workingAccount.deposit(transaction.getTransAmount());
			} else if(transaction.getTransType() == 'g') {
				System.out.println(customers[transaction.getCustNumber() -1].getName() +"'s Savings Account Has a Balance of: $" + workingAccount.getBalance());
			} else {
				workingAccount.processInterest();
			}	
		} else {
			if(transaction.getTransferAcct() == 'c')
			{
				CheckingAccount transferToChecking = customers[transaction.getCustNumber() - 1].getChecking();
				
				boolean processTransfer = workingAccount.withdraw(transaction.getTransAmount());
				if(processTransfer)
				{
					transferToChecking.deposit(transaction.getTransAmount());
				}
			} else {
				SavingsAccount transferToSavings = new SavingsAccount();
				if(transaction.getTransferAcct() == 's')
				{
					transferToSavings = customers[transaction.getCustNumber() - 1].getSavings();
				} else if(transaction.getTransferAcct() == 'a') {
					transferToSavings = customers[transaction.getCustNumber() - 1].getAutoLoan();
				} else {
					transferToSavings = customers[transaction.getCustNumber() - 1].getStudentLoan();
				}
				
				boolean processTransfer = workingAccount.withdraw(transaction.getTransAmount());
				if(processTransfer)
				{
					transferToSavings.deposit(transaction.getTransAmount());
				}
			}
			


		}
		
		
	}
	
/**
 * Prints the final results to the console.
 * @param customers - the array holding all of our customers
 */
	private static void displayFinalResults(Customer[] customers)
	{
		int cusIndex = 0;
		while (cusIndex < customers.length)
		{
			if (customers[cusIndex].getChecking() != null)
			{
				System.out.println(customers[cusIndex].getName() + ", the final balance of your Checking account is $" + customers[cusIndex].getChecking().balance);	
			}
			if (customers[cusIndex].getSavings() != null)
			{
				System.out.println(customers[cusIndex].getName() + ", the final balance of your Savings account is $" + customers[cusIndex].getSavings().balance);
			}
			if (customers[cusIndex].getStudentLoan() != null)
			{
				System.out.println(customers[cusIndex].getName() + ", the final balance of your Student Loan account is $" + customers[cusIndex].getStudentLoan().balance);												
			}				
			if (customers[cusIndex].getAutoLoan() != null)
			{	
			System.out.println(customers[cusIndex].getName() + ", the final balance of your Auto Loan account is $" + customers[cusIndex].getAutoLoan().balance);							
			}
			cusIndex++;
		}
	}
	
/**
 * This method offers the user a series of prompts to accept an input.  
 * At each step, it verifies the input before accepting it.
 * When all attributes of the input have been accepted, it returns the input.
 * @return the Input as an object
 */
	private static Input parseInput()
	{
		Input transaction = new Input();
		char inputChar;
		int inputAccountNum;
		String inputAmount = new String();
		double inputAmountNum;
		boolean transfer = false;
		boolean invalidInput = true;
		boolean needAmount = true;
		
		while(invalidInput)
		{
			String input = JOptionPane.showInputDialog("Enter Desired Customer Number: ");	
			if(input.length() != 1)
			{
				System.out.println("Invalid Customer Number, Please Try Again: ");
			} else {
				inputChar = input.charAt(0);		//checks first, and only, character
				inputAccountNum = Character.getNumericValue(inputChar);
				if(inputAccountNum > 0 && inputAccountNum < 3)
				{
					transaction.setCustNumber(inputAccountNum);
					invalidInput = false;
				} else {
					System.out.println("Invalid Customer Number, Please Try Again: ");
				}
			}
		}
		
		invalidInput = true;
		while(invalidInput)
		{
			String input = JOptionPane.showInputDialog("Enter Transaction Type: ");	
			if(input.length() != 1)
			{
				System.out.println("Invalid Transaction Type, Please Try Again: ");
			} else {
				input.toLowerCase();
				inputChar = input.charAt(0);
				if(inputChar == 'w' || inputChar == 'd')
				{
					transaction.setTransType(inputChar);
					invalidInput = false;
				} else if(inputChar == 't')
				{
					transaction.setTransType(inputChar);
					invalidInput = false;
					transfer = true;
				} else if(inputChar == 'g' || inputChar == 'i') {
					transaction.setTransType(inputChar);
					invalidInput = false;
					needAmount = false;
				} else {
					System.out.println("Invalid Transaction Type, Please Try Again: ");
				}
			}
		}
	
/**
 * Don't need a transaction amount if user only cares to check the balance or if computing interest.
 */
		if(needAmount)
		{
			invalidInput = true;
		}	
		while(invalidInput)
		{
			String input = JOptionPane.showInputDialog("Enter Amount of Transaction: ");	
			int length = input.length();
			if(length < 1)
			{
				System.out.println("No Input Detected.  Please Enter Amount Again: ");
			} else {
				int numPeriods = 0;
				int index = 0;
				boolean numericalInput = true;
				
				for(index = 0; index < length; index++)
				{
					char testChar = input.charAt(index);
					if(testChar == '.')
					{
						numPeriods++;
					} else if(testChar == '0' || testChar == '1' || testChar == '2' || testChar == '3') {
						//placeholder.  only need to do anything if not a number.
					} else if(testChar == '4' || testChar == '5' || testChar == '6' || testChar == '7') {
						//placeholder.  only need to do anything if not a number.
					} else if(testChar == '8' || testChar == '9') {
						//placeholder.  only need to do anything if not a number.
					} else {
						numericalInput = false;
					}
				}
				if(numPeriods > 1)
				{
					numericalInput = false;
				}
				
				if(numericalInput)
				{
					double amount = Double.parseDouble(input);
					if(amount == 0)
					{
						System.out.println("Isn't a $0.00 Transaction Rather Pointless?  Try a New Value: ");
					} else {
					transaction.setTransAmount(amount);
					invalidInput = false;
					}
				} else {
					System.out.println("Not a Valid Amount.  Please Try Again");
				}
			}
		}
		
		invalidInput = true;
		while(invalidInput)
		{
			String input = new String();
			
			if(transfer)
			{
				input = JOptionPane.showInputDialog("Please Select Account To Receive Funds: ");
			} else {
				input = JOptionPane.showInputDialog("Please Select Account: ");
			}
			
			if(input.length() != 1)
			{
				System.out.println("Invalid Account.  Please Try Again.");
			} else {
				input.toLowerCase();
				inputChar = input.charAt(0);
				
				if(inputChar == 'c' || inputChar == 's' || inputChar == 'l' || inputChar == 'a')
				{
					invalidInput = false;
					transaction.setAcctType(inputChar);
				} else {
					System.out.println("Invalid Account.  Please Try Again.");
				}
			}
		}

		if(transfer)
		{
			invalidInput = true;
		}
		while(invalidInput)
		{
			String input = JOptionPane.showInputDialog("Please Select Account To Send Funds: ");
			
			if(input.length() != 1)
			{
				System.out.println("Invalid Account.  Please Try Again.");
			} else {
				input.toLowerCase();
				inputChar = input.charAt(0);
				
				if(inputChar == 'c' || inputChar == 's' || inputChar == 'l' || inputChar == 'a')
				{
					invalidInput = false;
					transaction.setTransferAcct(inputChar);
				} else {
					System.out.println("Invalid Account.  Please Try Again.");
				}
			}
			
		}
		
		return transaction;
	}
}	
	