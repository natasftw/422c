package assignment4;

public class Input {
/**
 * All attributes are private with getters implemented below.
 */
	private boolean validInput;
	private String firstWord;
	private String lastWord;
	
/**
 * The constructor handles the input processing and outputs error
 * messages as appropriate.
 * @param input - the input string received
 * @param dict - our working dictionary to ensure words are real
 */
	public Input(String input, Dictionary dict)
	{
		input = input.trim();
		
		char space = ' ';
		int newWord = input.indexOf(space);
		
		if(newWord == -1)
		{
			firstWord = "none";
			lastWord = "none";
			validInput = false;
			System.out.println("You didn't input two words.  Please try again.");
		} else {
			firstWord = input.substring(0,newWord);
			lastWord = input.substring(newWord);
			firstWord = firstWord.trim();
			lastWord = lastWord.trim();
			validInput = true;
		}
		
		newWord = lastWord.indexOf(space);
		if(newWord != -1)
		{
			validInput = false;
			System.out.println("You input more than two words.  Please try again.");
		}
		
		if(validInput)
		{
			if(firstWord.contentEquals(lastWord))
			{
				validInput = false;
				System.out.println("Do we really want to calculate a ladder between the same word?");
			}
		
			boolean validFirstWord = dict.wordExists(firstWord);
			boolean validLastWord = dict.wordExists(lastWord);
			if(!validFirstWord && !validLastWord)
			{
				validInput = false;
				System.out.println("Neither " + firstWord + " nor " + lastWord + 
						" are valid words.  Please try again.");
			} else if(!validFirstWord) {
				validInput = false;
				System.out.println(firstWord + " is not a valid word.  Please try again.");
			} else if(!validLastWord) {
				validInput = false;
				System.out.println(lastWord + " is not a valid word.  Please try again.");
			}
		}
	}
	
/**
 * A group of getters for Input values.  We do not want anything
 * else changing the values so no setters have been included.
 */
	public String getFirstWord()
	{
		return firstWord;
	}
	
	public String getLastWord()
	{
		return lastWord;
	}
	
	public boolean getValidInput()
	{
		return validInput;
	}
}






