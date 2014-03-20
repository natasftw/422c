package assignment4;

public class Input {

	private String firstWord;
	private String lastWord;
	private boolean validInput;
	
	public Input(String input) {
		input = input.trim();
		
		char space = ' ';
		int newWord = input.indexOf(space);
		
		if(newWord == -1)
		{
			firstWord = "none";
			lastWord = "none";
			validInput = false;
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
		}
	}

/**
 * A collection of getters for the Input values.
 * Setters are not implemented as the constructor
 * should be the only one changing the attribute
 * values.
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
