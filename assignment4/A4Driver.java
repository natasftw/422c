package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class A4Driver {
	static ArrayList<String> tempList = new ArrayList<String>();
	static ArrayList<String> solutionList = new ArrayList<String>();
	static ArrayList<String> explored = new ArrayList<String>();
	static boolean flag = false;
	static Dictionary dict;
	
	public static void main(String args[]){
		java.io.File wordfile = new java.io.File("src/assignment4/A4-words.txt");
		dict = new Dictionary(wordfile); //create dictionary object
		
		try 
		{
			FileReader freader = new FileReader(args[0]);
			BufferedReader reader = new BufferedReader(freader);
			for (String s = reader.readLine(); s != null; s = reader.readLine())
			{
				Input newInput = new Input(s, dict);
				if(newInput.getValidInput())
				{
					String word1 = newInput.getFirstWord();
					String word2 = newInput.getLastWord();
					tempList.clear();
					solutionList.clear();

					if(makeLadder(word1, word2, -1) == true)
					{ 
						System.out.println("Ladder for " + word1 + " and " + 
								word2 + ":");
						System.out.println(solutionList);
					}
					else{
						System.out.println("No ladder exists between " + word1 + 
								" and " + word2 + ".");
					}
				}
			}
			System.out.println(" ");	//print out a blank line for cleaner output
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
	
	//makeLadder function, called recursively to produce the word ladder
	public static boolean makeLadder(String startWord, String endWord, int pos)
	{
		solutionList.add(startWord);
		if(dict.difference(startWord,endWord) == 4) //if startWord and endWord are one letter away, we're done
		{
			solutionList.add(endWord);
			return true;
		}
		tempList.clear();
		tempList.addAll(dict.wordList(startWord,endWord,pos,solutionList, explored)); //add all applicable words within 1 letter of startWord to tempList
		if(tempList.isEmpty())
		{
			return false;
		}
		int i = 0;
		while(i < tempList.size()) //iterate through tempList
		{
			int val; 
			String temp = tempList.get(i);
			val = dict.position(temp, startWord); //position where temp and startWord share the same letter
			i++;
			
			//recursive part
			if(makeLadder(temp,endWord,val) == false)
			{ 
				solutionList.remove(temp); //remove current word from solution list if its tempList is empty
				explored.add(temp); //mark current word as explored
				tempList.clear();
			    tempList.addAll(dict.wordList(solutionList.get(solutionList.size()-1),endWord,pos,solutionList, explored)); //reload tempList for the word one step back from current word
				continue; //go to next word in tempList 
			} else {
				return true; //return true if ladder found
			}
		}
		return false; //return false if no ladder found
	}
	
	static String getWord(String line, int i)
	{
		String word = "";
		while(line.charAt(i) == ' ')
		{
			i++;
		}
		word = word + line.substring(i,i+5);
		return word;
	}
}