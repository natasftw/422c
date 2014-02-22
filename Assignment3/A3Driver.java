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
		  boolean foundItem = false;
		  
		  if(maxIndex == 0)
		  {
			  needIndex = false; //Ensures we won't references out of bounds.
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
				  foundItem = true;
				  currentIndex = currentIndex + 1;
				  if(currentIndex == maxIndex)
				  {
					  needIndex = false;
				  }
			  } else if(alphaTest < 0) {
				  //if this case occurs, a match cannot exist.  There's no
				  //reason to continue checking.  Output failure message.
				  needIndex = false;
				  if(!foundItem) 
				  {
					  System.out.println(name + " not found in the cart.");
				  }
			  } else {
				  //argument is lexigraphically less.  This means we should
				  //check the next value.  If this was the last value, we need
				  //to stop checking.
				  currentIndex = currentIndex + 1;
				  if(currentIndex == maxIndex)
				  {
					  needIndex = false;
					  if(!foundItem)
					  {
						  System.out.println(name + " not found in the cart.");
					  }
				  }
			  }	  
		  }
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