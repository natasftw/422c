// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 3

package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class A3Driver 
{
	  	  	  
	  public static void main(String[] args) 
	  {
		ArrayList<Item> shoppingCart = new ArrayList<Item>();
		  
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
				Input currentInput = new Input(s); 
				boolean test = currentInput.getValidInput();
				if(test)
				{
					String operation = currentInput.getOperation();
					if(operation.contentEquals("insert"))
					{
						processInsert(shoppingCart, currentInput);
						int length = shoppingCart.size();
						System.out.println("The cart has " + length + " items in it.");
					} else if(operation.contentEquals("search")) {
						boolean printResults = true;
						processSearch(shoppingCart, currentInput, printResults);
					} else if(operation.contentEquals("update")) {
						processUpdate(shoppingCart, currentInput);
					} else if(operation.contentEquals("delete")) {
						processDelete(shoppingCart, currentInput);
					} else {
						processPrint(shoppingCart);
					}
				}
			}
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

/**
 * A method to handle insertion of new items into cart
 * @param cart - the cart's ArrayList
 * @param inputData - the Input object containing the new item's details
 */
	  private static void processInsert(ArrayList<Item> cart, Input inputData)
	  {
		  //search cart for item
		  //if item exists, add quantity to current item
		  //otherwise, create an object and add it to cart
		  System.out.println("Insert method called.");
		  Clothing shirt = new Clothing("shirt", 1, 1, 1);
		  cart.add(shirt);
	  }
	 
/**
 * A method to search through the ArrayList to find the desired item.
 * @param cart - the cart's ArrayList
 * @param inputData - the Input object with the item to search for
 * @param showResults - surpresses results for use within other methods
 */
	  private static void processSearch(ArrayList<Item> cart, Input inputData, 
			  boolean showResults)
	  {
		  //search the cart for the item
		  //currently have a boolean, maybe replace with an int return
		  //return lets other methods use this to check.  
		  //show results lets this method print results to screen if needed
		  System.out.println("Search method called.");
	  }
	  
/**
 * A method to update cart items to the new value.
 * @param cart - the cart's ArrayList
 * @param inputData - the Input object with the update information
 */
	  private static void processUpdate(ArrayList<Item> cart, Input inputData)
	  {
		  //search for item
		  //if item exists, update it
		  //else, send error message
		  System.out.println("Update method called.");
	  }
	  
/**
 * A method to remove objects from the cart
 * @param cart - the cart's ArrayList
 * @param inputData - details about the object to remove
 */
	  private static void processDelete(ArrayList<Item> cart, Input inputData)
	  {
		  //look for all items with that name
		  //delete all of them
		  //update console with each deletion
		  //list error message if none deleted
		  System.out.println("Delete method called.");
	  }
	  
/**
 * A method to print all of the items in the cart
 * @param cart - the cart itself
 */
	  private static void processPrint(ArrayList<Item> cart)
	  {
		  //check if cart is empty
		  //if empty, say so
		  //else, iterate through the cart calling showItemAttributes
		  System.out.println("Print method called.");
	  }
}


/**
 *    Everything below this is starter code.		  
 */
		  
		//Stub for arraylist.
//		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
/*		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice(); 
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
			 * 

		}
*/	