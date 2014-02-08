package assignment2;

import javax.swing.JOptionPane; 

public class Client {

	public static void main (String [ ] args) 
	{ 
		//main program goes here		
		boolean moreInput = true;
		
		while(moreInput)
		{
			String input = JOptionPane.showInputDialog("Enter Desired Transaction: ");	
			System.out.println(input);
			
			//need to parse input and verify the input is valid


			//Poll customer for account number

			//Verify valid transaction

			//Pass transaction to account
			//Withdrawal and Deposit are already incorporated into class
			//Transfer will need to be handled here via a combination of W/D

			//Receive status
			//Output status to screen 
			//Currently handled inside classes.  Could be moved here if desired.
			
			
			//Repeats the process until user declares there is no more input.
			//While loop ensures user inputs either a 'Y' or 'N' before moving on
			boolean anotherTransaction = true;
			
			while(anotherTransaction)
			{
				String newInput = JOptionPane.showInputDialog("Do you have another transaction? (Y/N)");
				char checkInput = newInput.charAt(0);
				if(checkInput == 'N' || checkInput == 'n')
				{
					moreInput = false;
					anotherTransaction = false;
				} else if(checkInput == 'Y' || checkInput == 'y') {
					moreInput = true;
					anotherTransaction = false;
				} else {
					anotherTransaction = true;
				}
			}
		}
		
		//need to poll all accounts and post their information here
		//note: getters don't post values to screen
		
		System.exit(0);
	}
	
}
