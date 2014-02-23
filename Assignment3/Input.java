// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 3

package Assignment3;

public class Input {

/**
 * The list of attributes possible for every input.
 */
	private String operation;
	private String category;
	private String name;
	private double price;
	private int quantity;
	private double weight;
	private boolean validInput;
	private boolean isFragile;
	private boolean isPerishable;
	private String deliveryState;
	
/**
 * 	Getters are defined below.  Setters are not implemented
 * 	as only the constructor should make an input object.
 */
	
/**
 * Constructor accepts a String and creates an input object.
 * @param newLine - the input string.
 */
	public Input(String newLine)
	{
		isFragile = false;
		isPerishable = false;
		deliveryState = "NA";
		
		boolean needData = false;
		String currentWord = new String();
		newLine = newLine.toLowerCase();
		
		//First, we need to check to make sure the input text
		//actually includes text.  If not, we need to exit.
		
		int length = newLine.length();
		
		if(length == 0)
		{
			validInput = false;
			emptyInput();
		} else {
			validInput = true;
		}
		
		//The input will be in one of several forms.  We will pull
		//a "word" at a time from the input.  Each word is an input
		//field that may, or may not, need to be processed.  Once an
		//input is found to be bad, the rest of the checks are
		//unimportant.  As such, each step will check to see if we have
		//a valid input.  If not, it will skip any processing.
		
		if(validInput)
		{
			//Get a new word and trip the input to only include
			//new information for the next step.
			currentWord = getWord(newLine);
			newLine = trimInput(currentWord, newLine);
			
			//Check to see if the word is one of our operations
			//If so, we set operation to the word.  If not, we need
			//to treat the input as bad.
			boolean statusCheck = verifyOperation(currentWord);
			if(statusCheck)
			{
				operation = currentWord;
			} else {
				emptyInput();
			}
		}
		
		//The print operation doesn't require any more input.  We
		//only need to continue looking for data if the input passed
		//the first step and isn't print.
		needData = validInput && !operation.contentEquals("print");
		
		if(needData)
		{
			currentWord = getWord(newLine);
			newLine = trimInput(currentWord, newLine);
			
			//We need to handle insert different than other operations.
			//This is because insert has a category field.
			if(operation.contentEquals("insert"))
			{
				//This ensures the category is one that exists.
				//If so, we fill in the category and grab another
				//word to fill in the name field.  Otherwise, 
				//we process it as a bad input.
				boolean statusCheck = verifyCategory(currentWord);
				if(statusCheck)
				{
					category = currentWord;
					currentWord = getWord(newLine);
					newLine = trimInput(currentWord, newLine);
					name = currentWord;
				} else {
					emptyInput();
				}
			} else if(validInput) {
				//All other operations accept name as the next input
				//item.  This is set.
				category = "NA";
				name = currentWord;
			}
		}
		
		//Again, we check to make sure our input is still valid
		//before processing the next word.  If the operation is an
		//insert, there will be an additional price field here.  
		//We only handle this here.  If the operation isn't insert,
		//the if-statement will fail and nothing will be processed.
		if(validInput)
		{
			if(operation.contentEquals("insert"))
			{
				currentWord = getWord(newLine);
				newLine = trimInput(currentWord, newLine);
				
				//Call a method to verify the price is valid.
				boolean statusCheck = verifyPrice(currentWord);

				//If it's valid, we set price.  Otherwise, we post an error.
				if(statusCheck)
				{
					price = Double.parseDouble(currentWord);
				} else {
					emptyInput();
					System.out.println("Invalid Price. Please try again.");
				}
			}
		}
		
		//The only operations that still need input are insert and update.
		//This checks to see if we have one of those operations.  If so,
		//we check to see if the input is still valid.  
		needData = operation.contentEquals("insert") || operation.contentEquals("update");
		needData = needData && validInput;
		
		if(needData)
		{
			currentWord = getWord(newLine);
			newLine = trimInput(currentWord, newLine);
			
			//This field is a quantity field.  We need to verify
			//the input is a positive whole number.  It's rather difficult
			//to have negative bananas or a fraction of a banana in our cart.
			boolean statusCheck = verifyQuantity(currentWord);
			
			if(statusCheck)
			{
				quantity = Integer.parseInt(currentWord);
				if(quantity == 0)
				{
					System.out.println("Insert 0 items? No thank you");
					emptyInput();
				}
			} else {
				System.out.println("Invalid quantity. Please try again");
				emptyInput();
			}
		}
			
		//At this point, only the insert operation can have any further
		//input fields.  
		needData = validInput && operation.contentEquals("insert");
		if(needData)
		{
			currentWord = getWord(newLine);
			newLine = trimInput(currentWord, newLine);
			
			//First, we fill in the weight field if the input contains
			//a valid weight.
			boolean statusCheck = verifyWeight(currentWord);
			if(statusCheck)
			{
				weight = Double.parseDouble(currentWord);
				if(weight == 0)
				{
					System.out.println("It's weightless?  Try again.");
					emptyInput();
				}
			} else {
				System.out.println("Invalid weight. Please try again.");
				emptyInput();
			}
		}
		
		//This just makes sure weight was valid and doesn't create a mess
		//of nested if-statements.
		needData = validInput && operation.contentEquals("insert");
		if(needData)
		{
			//The clothing category is complete.  We don't need any
			//more input if this is the case.
			if(category.contentEquals("clothing"))
			{
				needData = false;
			}
		}

		if(needData)
		{
			currentWord = getWord(newLine);
			newLine = trimInput(currentWord, newLine);
			
			//We quickly check if the next input is a valid
			//code for the category type.  Then, we set the 
			//appropriate attribute to match the code.
			//Initially, we set these booleans to be false so
			//our code here only needs to update the true cases.
			boolean statusCheck = verifyCode(currentWord);
			if(statusCheck)
			{
				if(category.contentEquals("grocery"))
				{
					if(currentWord.contentEquals("p"))
					{
						isPerishable = true;
					}
				} else {
					if(currentWord.contentEquals("f"))
					{
						isFragile = true;
					}
				}
			} else {
				System.out.println("Invalid code. Please try again.");
				emptyInput();
			}
		}

		//The only potential input at this point is the state to deliver
		//an electroncis item to within the insert command.  We check
		//for this case.
		needData = validInput && operation.contentEquals("insert");
		if(needData)
		{
			if(!category.contentEquals("electronics"))
			{
				needData = false;
			}
		}

		if(needData)
		{
			currentWord = getWord(newLine);
			newLine = trimInput(currentWord, newLine);
			
			//This method verifies the code matches one of the
			//50 possible state codes.
			boolean statusCheck = verifyState(currentWord);
			if(statusCheck)
			{
				//State codes look better in uppercase.
				deliveryState = currentWord.toUpperCase();
			} else {
				System.out.println("Invalid State. Please try again.");
				emptyInput();
			}
		}
		
		//If we get here with a valid input, there aren't any more fields
		//to process.  But, we need to make sure there isn't additional
		//information in the input line.  If so, the line is erroneous
		//and we need to alert the user.
		if(validInput)
		{
			//Trimming removes all white space at the front and back
			//of a string.  If the string is purely white space, it is 
			//all deleted.  Using this command allows us to ignore extra
			//spaces at the end of the input and process the input.
			//We don't care if the user hit the space bar one time too many.
			newLine = newLine.trim();
			length = newLine.length();
			
			//If we enter this if-statement, there is more information in the 
			//input so we need to alert the user to the error.
			if(length != 0)
			{
				System.out.println("Extra characters detected.  Input discarded");
				emptyInput();
			}
		}
	}
	
	
/**
 * 	A collection of getter methods for all attributes
 */
	public String getOperation()
	{
		return operation;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public double getWeight()
	{
		return weight;
	}

	public boolean getValidInput()
	{
		return validInput;
	}
	
	public boolean getIsFragile()
	{
		return isFragile;
	}

	public boolean getIsPerishable()
	{
		return isPerishable;
	}
	
	public String getDeliveryState()
	{
		return deliveryState;
	}

	
/**
 * 	Takes the string and splits off the characters before a space is found.
 * 	Returns the word prior to the space.
 */
	private String getWord(String inputString)
	{
		int nextSpace;
		char space = ' ';
		String newWord = new String();
		
		//This ensures we don't try to operate on a space if any
		//input fields are split by 2 or more spaces.
		nextSpace = inputString.indexOf(space);
		while(nextSpace == 0)
		{
			inputString = inputString.substring(1); //eliminates the space
			nextSpace = inputString.indexOf(space);
		}
		
		//nextSpace will be -1 if a space doesn't exist in the string.
		//If a space exists, we only need the word up until the space
		if(nextSpace > 0)
		{
			newWord = inputString.substring(0, nextSpace);
			} else {
				newWord = inputString;
			}
		return newWord;
	}
	
/**
 * 	Checks a string to see if it is a valid state code and returns
 * 	an appropriate boolean.
 */
	private boolean verifyState(String state)
	{
		if(state.contentEquals("al") || state.contentEquals("ak") ||
				state.contentEquals("az") || state.contentEquals("ar") || 
				state.contentEquals("ca") || state.contentEquals("co") || 
				state.contentEquals("ct") || state.contentEquals("de") || 
				state.contentEquals("fl") || state.contentEquals("ga") || 
				state.contentEquals("hi") || state.contentEquals("id") || 
				state.contentEquals("il") || state.contentEquals("in") || 
				state.contentEquals("ia") || state.contentEquals("ks") || 
				state.contentEquals("ky") || state.contentEquals("la") || 
				state.contentEquals("md") || state.contentEquals("me") || 
				state.contentEquals("ma") || state.contentEquals("mi") || 
				state.contentEquals("ms") || state.contentEquals("mn") || 
				state.contentEquals("mo") || state.contentEquals("mt") || 
				state.contentEquals("nv") || state.contentEquals("ne") || 
				state.contentEquals("nh") || state.contentEquals("nj") || 
				state.contentEquals("ny") || state.contentEquals("nm") || 
				state.contentEquals("nc") || state.contentEquals("nd") || 
				state.contentEquals("ok") || state.contentEquals("oh") || 
				state.contentEquals("or") || state.contentEquals("pa") || 
				state.contentEquals("sc") || state.contentEquals("ri") || 
				state.contentEquals("sd") || state.contentEquals("tn") || 
				state.contentEquals("ut") || state.contentEquals("tx") || 
				state.contentEquals("vt") || state.contentEquals("va") || 
				state.contentEquals("wv") || state.contentEquals("wa") || 
				state.contentEquals("wi") || state.contentEquals("wy"))
		{
			return true;
		} else {
			return false;
		}
	}
	
/**
 * 	Checks a string to see if it matches one of our available
 * 	operations and returns an appropriate boolean.
 */
	private boolean verifyOperation(String operation)
	{		
		if(operation.contentEquals("insert"))
		{
			return true;
		} else if(operation.contentEquals("search")) {
			return true;
		} else if(operation.contentEquals("update")) {
			return true;
		} else if(operation.contentEquals("delete")) {
			return true;
		} else if(operation.contentEquals("print")) {
			return true;
		} else {
			System.out.println("Invalid operation. Please try again");
			return false;
		}
	}

/**
 * 	Checks a string and see if the perishable or fragile code
 * 	exists and matches the correct category then sends the 
 * 	appropriate boolean.
 */
	private boolean verifyCode(String code)
	{
		if(code.contentEquals("np") || code.contentEquals("p"))
		{
			if(category.contentEquals("grocery"))
			{
				return true;
			} else {
				return false;
			}
		} else if(code.contentEquals("f") || code.contentEquals("nf"))
		{
			if(category.contentEquals("electronics"))
			{
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
/**
 * 	Checks a string to ensure only numbers exist and returns
 * 	an appropriate boolean.
 */
	private boolean verifyQuantity(String quantity)
	{
		int length = quantity.length();
		boolean numericalInput = true;
		
		for(int i = 0; i < length; i++)
		{
			char testDigit = quantity.charAt(i);
			boolean isDigit = verifyDigit(testDigit);
			if(!isDigit)
			{
				numericalInput = false;
			}
		}
		return numericalInput;
	}
	
/**
 * 	Checks a string to see if it can possibly be a price.
 * 	It returns a false if any inappropriate characters exist.
 * 	Returns a false if more than one decimal found in string.
 * 	Returns a false if digits in more than two decimal places.
 */
	private boolean verifyPrice(String price)
	{		
		int length = price.length();
		int numPeriods = 0;
		boolean numericalInput = true;
		
		for(int i = 0; i < length; i++)
		{
			char testDigit = price.charAt(i);
			boolean isDigit = verifyDigit(testDigit);
			if(!isDigit)
			{
				if(testDigit == '.') {
					numPeriods = numPeriods + 1;
				} else {
					numericalInput = false;
				}
			} 
		}

		int decimalPlace = price.indexOf('.');
		if(decimalPlace != -1)
		{
			length = length - decimalPlace;
			// 3 is the decimal plus two decimal places
			if(length > 3)
			{
				numericalInput = false;
			}
		}
		
		return numericalInput;
	}
	
/**
 * 	Similar to verifyPrice, but the weight isn't restricted to
 * 	two decimal places.  This only returns a false if inappropriate
 * 	characters exist or multiple decimals exist.
 */
	private boolean verifyWeight(String weight)
	{
		int numPeriods = 0;
		int length = weight.length();
		boolean numericalInput = true;
		
		for(int i = 0; i < length; i++)
		{
			char testDigit = weight.charAt(i);
			boolean isDigit = verifyDigit(testDigit);
			if(!isDigit)
			{
				if(testDigit == '.') {
					numPeriods = numPeriods + 1;
				} else {
					numericalInput = false;
				}
			} 
		}
		
		if(numPeriods > 1)
		{
			numericalInput = false;
		}
		return numericalInput;
	}
	
/**
 * 	A quick method to check if a character is a number.
 * 	This is used in other verification methods.
 */
	private boolean verifyDigit(char number)
	{
		if(number == '0' || number == '1' || number == '2' || number == '3' ||
				number == '4' || number == '5' || number == '6' ||
				number == '7' || number == '8' || number == '9')
		{
			return true;
		} else {
			return false;
		}
	}
	
/**
 * 	Accepts a String and determines if it matches one of our
 * 	available categories.
 */
	private boolean verifyCategory(String category)
	{
		if(category.contentEquals("grocery"))
		{
			return true;
		} else if(category.contentEquals("electronics")) {
			return true;
		} else if(category.contentEquals("clothing")) {
			return true;
		} else {
			System.out.println("Invalid category.  Please try again");
			return false;
		}
	}
	
/**
 * 	This method takes a word and a string.  The word is found
 * 	by using the getWord method and is the start of input.
 * 	trimInput removes word and any spaces to prepare input for
 * 	the next getWord call.  After trimming, it returns the input.
 */
	private String trimInput(String word, String input)
	{
		char start = word.charAt(0);		//gets first character of word
		char inputChar = input.charAt(0);	//gets first character of input
		
		while(start != inputChar)
		{
			input = input.substring(1);		//trims off the first character
			inputChar = input.charAt(0);
		}
	
		int length = word.length();
		input = input.substring(length);
		return input;
	}
	
/**
 * 	This method is called if any invalid input fields are found.
 * 	It fills the object with garbage values and sets validInput to 
 * 	false.  This makes it easy for the client to check for a valid
 * 	input prior to attempting to process anything. 
 */
	private void emptyInput()
	{
		operation = "bad input";
		category = "bad input";
		name = "bad input";
		price = 0;
		quantity = 0;
		weight = 0;
		isFragile = false;
		isPerishable = false;
		validInput = false;
		deliveryState = "bad input";
	}
}
