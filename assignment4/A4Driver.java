package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class A4Driver {

	public static void main(String[] args) {
		//FileReader freader = new FileReader("/Assignment4/src/assignment4/A4words.txt");
		//FileReader freader = new FileReader("/Assignment4/src/assignment4/A4words.txt");
		//BufferedReader wordList = new BufferedReader(freader);

		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		
		try 
		{
			FileReader freader = new FileReader(args[0]);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				//Main program logic goes here.  Read each line and act.
				System.out.println(s);
				Input newInput = new Input(s);

				String firstWord = newInput.getFirstWord();
				String lastWord = newInput.getLastWord();
				
				if(!newInput.getValidInput())
				{
					System.out.println("Input did not contain two words.");
				} else {
					System.out.println("First Word: " + firstWord);
					System.out.println("Last Word: " + lastWord);
				}
				
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}	
		
		
	}
}
