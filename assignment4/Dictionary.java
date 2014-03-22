package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary{
	ArrayList<String> words = new ArrayList<String>();
	String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String numbers = "0123456789";
	boolean fin = false;
	
	//Class constructor for dictionary, reads input words from a file and stores them in an arraylist
	public Dictionary(java.io.File f){  //
	//public Dictionary(String args[]){
		try 
		{
			FileReader freader = new FileReader(f);
			BufferedReader reader = new BufferedReader(freader);
			for (String s = reader.readLine(); s != null; s = reader.readLine())
			{
				if(isLetter(s.charAt(0)))
				{
					words.add(s.substring(0,5));
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
	
	//checks if a character is a letter of the alphabet
	public boolean isLetter(char c)
		{
			if(letters.lastIndexOf(c) == -1){
				return false;
			}
		    return true;
		}
	
	//calculates list of words for temporary array
	public ArrayList<String> wordList(String inWord, String finalWord, int pos, ArrayList<String> sol, ArrayList<String> exp){
		int value;
		ArrayList<String> newList = new ArrayList<String>();
		Iterator<String> i = words.iterator();
		while (i.hasNext()) 
		{
			String temp = i.next();
			int count = 0;
			Iterator<String> j = sol.iterator(); 
			while(j.hasNext()){      //check if any words are the solution words, ignore if they are
				String temp2 = j.next();
				if(temp.equals(temp2)){
					count++;
				}
			}
			Iterator<String> k = exp.iterator(); 
			while(k.hasNext()){          //check if any words are already explored, ignore if they are
				String temp3 = k.next();   
				if(temp.equals(temp3)){   
					count++;   
				}
			}
			if(count != 0){
				continue;
			}
			if(difference(temp,inWord) == 4){
				if(position(temp,inWord) != pos){
				    value = 5 - difference(temp,finalWord); //distance from final word
				    temp = Integer.toString(value) + temp; //add distance
				    newList.add(temp);
			}
		}
			
	}
		Collections.sort(newList); //sorts words according to how close they are to target word
		ArrayList<String> newList2 = new ArrayList<String>();
		Iterator<String> it = newList.iterator();
		while(it.hasNext()){   //remove distance from each word
			String temp = it.next();
			temp = temp.substring(1,6);
			newList2.add(temp);
			
		}
		//System.out.println(newList);
		return newList2;
	}
	
	//calculates and outputs the number of positions in two words which have the same character
	public int difference(String word1, String word2){
		int count = 0;
		if(word1.length() != word2.length()){
			return -1;
		}
		else{
			int i;
			for(i = 0;i<word1.length();i++){
				if(word1.charAt(i) == word2.charAt(i)){
					count++;
				}
			}
		return count;
		}
	}
	
	//calculates the position of the first character where word 1 and word 2 match
	public int position(String word1, String word2){
		int i;
		for(i = 0; i<word1.length(); i++){
			if(word1.charAt(i) == word2.charAt(i)){
				return i;
			}
		}
		return -1;
	}
	
	public boolean wordExists(String word)
	{
		return words.contains(word);
	}
	
	
}

