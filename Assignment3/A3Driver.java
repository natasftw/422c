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
					} else if(operation.contentEquals("search")) {
						processSearch(shoppingCart, currentInput);
					} else if(operation.contentEquals("update")) {
						processUpdate(shoppingCart, currentInput);
					} else if(operation.contentEquals("delete")) {
						processDelete(shoppingCart, currentInput);
					} else {
						processPrint(shoppingCart);
					}
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

/**
 * A method to handle insertion of new items into cart
 * @param cart - the cart's ArrayList
 * @param inputData - the Input object containing the new item's details
 */
	  private static void processInsert(ArrayList<Item> cart, Input inputData)
	  {
		  boolean alreadyExists = false;
		  String category = inputData.getCategory();
		  String name = inputData.getName();
		  double price = inputData.getPrice();
		  double weight = inputData.getWeight();
		  int quantity = inputData.getQuantity();
		  
		  //We need to keep the list ordered alphabetically by item
		  //name.  insertIndex will track where to place the new item.
		  //If it isn't set, we want to insert at the end.  This line
		  //ensures the end of the ArrayList is the default location.
		  int insertIndex = cart.size();
		  int currentIndex = 0;
		  
		  boolean needIndex = true;
		  
		  if(insertIndex == 0)
		  {
			  needIndex = false; //Ensures we won't references out of bounds.
		  }
		  
		  while(needIndex)
		  {
			  Item currentItem = cart.get(currentIndex);
			  int alphaTest = name.compareTo(currentItem.getName());
			  if(alphaTest == 0)
			  {
				  alreadyExists = true;
				  needIndex = false;
				  System.out.println(name + " already exists in the cart. Please use update to change quantity.");
			  } else if(alphaTest < 0) {
				  //argument is lexigraphically greater than
				  needIndex = false;
				  insertIndex = currentIndex;
			  } else {
				  //argument is lexigraphically less
				  currentIndex = currentIndex + 1;
			  }	  
		  }
		  
		  //search cart for item
		  if(alreadyExists)
		  {
			  //if item exists, add quantity to current item
		  } else {
			  if(category.contentEquals("grocery"))
			  {
				  boolean isPerishable = inputData.getIsPerishable();
				  Grocery newGrocery = new Grocery(name, quantity, price, weight,
						  isPerishable);
				  cart.add(insertIndex, newGrocery);
			  } else if(category.contentEquals("electronics")) {
				  boolean isFragile = inputData.getIsFragile();
				  String deliveryState = inputData.getDeliveryState();
				  Electronics newElec = new Electronics(name, quantity, price,
						  weight, deliveryState, isFragile);
				  cart.add(insertIndex, newElec);
			  } else {
				  Clothing newClothing = new Clothing(name, quantity, price, weight);
				  cart.add(insertIndex, newClothing);
			  }
			  System.out.println(name + " added to cart with " + quantity + 
					  " item(s) priced at $" + price + " per unit.  Each item weighs: "
					  + weight + ".");
		  }
	  }
	 
/**
 * A method to search through the ArrayList to find the desired item.
 * @param cart - the cart's ArrayList
 * @param inputData - the Input object with the item to search for
 */
	  private static void processSearch(ArrayList<Item> cart, Input inputData)
	  {
		  String name = inputData.getName();
		  
		  int maxIndex = cart.size();
		  int currentIndex = 0;
		  
		  boolean needIndex = true;
		  
		  if(maxIndex == 0)
		  {
			  needIndex = false; //Ensures we won't references out of bounds.
			  System.out.println("Do you really want me to search through an empty cart?");
		  }
		  
		  while(needIndex)
		  {
			  Item currentItem = cart.get(currentIndex);
			  int alphaTest = name.compareTo(currentItem.getName());
			  if(alphaTest == 0)
			  {
				  //match found.  send output
				  int quantity = currentItem.getQuantity();
				  double price = currentItem.getPrice();

				  System.out.println("There are currently " + quantity +
						  " " + name + "(s) in the cart priced at $" +
						  price + " each.");
				  needIndex = false;
			  } else if(alphaTest < 0) {
				  //if this case occurs, a match cannot exist.  There's no
				  //reason to continue checking.  Output failure message.
				  needIndex = false;
				  System.out.println(name + " not found in the cart.");
			  } else {
				  //argument is lexigraphically less.  This means we should
				  //check the next value.  If this was the last value, we need
				  //to stop checking.
				  currentIndex = currentIndex + 1;
				  if(currentIndex == maxIndex)
				  {
					  needIndex = false;
					  System.out.println(name + " not found in the cart.");
				  }
			  }	  
		  }
	  }
	  
/**
 * A method to update cart items to the new value.  It searches for the item
 * and updates the quantity if it exists.  After it updates the item or 
 * determines the item doesn't exist, it updates the console with the 
 * transaction's details.
 * @param cart - the cart's ArrayList
 * @param inputData - the Input object with the update information
 */
	  private static void processUpdate(ArrayList<Item> cart, Input inputData)
	  { 
		  String name = inputData.getName();
		  int quantity = inputData.getQuantity();
		  
		  int maxIndex = cart.size();
		  int currentIndex = 0;
		  
		  boolean needIndex = true;
		  
		  if(maxIndex == 0)
		  {
			  needIndex = false; //Ensures we won't references out of bounds.
			  System.out.println("The cart is empty.  I can't update your item.");
		  }
		  
		  while(needIndex)
		  {
			  Item currentItem = cart.get(currentIndex);
			  int alphaTest = name.compareTo(currentItem.getName());
			  if(alphaTest == 0)
			  {
				  //if item matches, update quantity for current item
				  cart.get(currentIndex).setQuantity(quantity);
				  needIndex = false;
				  System.out.println(name + " updated to a quantity of: " + quantity);
			  } else if(alphaTest < 0) {
				  //We cannot find a possible match as all remaining items
				  //in the cart are less than the item we want to update.
				  needIndex = false;
				  System.out.println(name + " was not found in our cart.");
			  } else {
				  //Check the next item.
				  currentIndex = currentIndex + 1;
				  if(currentIndex == maxIndex)
				  {
					  needIndex = false;
					  System.out.println(name + " was not found in our cart.");
				  }
			  }	  
		  }
	  }
	  
/**
 * A method to remove objects from the cart
 * Our insert method limits the cart to having a single object of any name.
 * As a result, we only need to delete one item and we know all have been deleted.
 * @param cart - the cart's ArrayList
 * @param inputData - details about the object to remove
 */
	  private static void processDelete(ArrayList<Item> cart, Input inputData)
	  {		  
		  String name = inputData.getName();
		  
		  int maxIndex = cart.size();
		  int currentIndex = 0;
		  
		  boolean needIndex = true;
		  
		  if(maxIndex == 0)
		  {
			  needIndex = false; //Ensures we won't references out of bounds.
			  System.out.println("The cart is empty.  There's nothing to delete.");
		  }
		  
		  while(needIndex)
		  {
			  Item currentItem = cart.get(currentIndex);
			  int alphaTest = name.compareTo(currentItem.getName());
			  if(alphaTest == 0)
			  {
				  //Item matches desired deletion.  It is deleted
				  //and boolean is set to end while loop
				  cart.remove(currentIndex);				 
				  needIndex = false;
				  System.out.println(name + " was deleted from the cart.");
			  } else if(alphaTest < 0) {
				  //The item being checked comes after the desired
				  //deletion item.  It cannot exist in our cart so
				  //the loop is exited and console alerted.
				  needIndex = false;
				  System.out.println(name + " not found in cart.  As such, it wasn't deleted.");
			  } else {
				  //We need to check the next item in the cart.
				  currentIndex = currentIndex + 1;
				  //If we reached the end of the cart, we need to exit
				  //the while loop.
				  if(currentIndex == maxIndex)
				  {
					  needIndex = false;
					  System.out.println(name + " not found in cart.  As such, it wasn't deleted.");
				  }
			  }	  
		  }
	  }
	  
/**
 * A method to print all of the items in the cart
 * @param cart - the cart itself
 */
	  private static void processPrint(ArrayList<Item> cart)
	  {
		  double totalCartCost = 0;
		  
		  int length = cart.size();
		  if(length == 0)
		  {
			  System.out.println("Isn't it strange to display an empty cart?");
		  } else {
			  Iterator<Item> i = cart.iterator();
			  while (i.hasNext()) 
			  {
				  Item temp = i.next();
				  double itemCost = temp.printItemAttributes();
				  totalCartCost = totalCartCost + itemCost;
			  }
			  System.out.println("The total price for your cart is: $" + totalCartCost);
		  }
	  }
}
