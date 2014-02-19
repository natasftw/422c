// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 3

package Assignment3;

public class Item 
{
/**
 * 	Item attributes are protected to allow inheritance.
 */
	protected String name;
	protected int quantity;
	protected double price;
	protected double weight;
	
/**
 * 	Several constructors for an Item
 */
	public Item()
	{
		name = "Generic Item";
		quantity = 0;
		price = 0;
		weight = 0;
	}

	public Item(String newName)
	{
		name = newName;
		quantity = 0;
		price = 0;
		weight = 0;
	}
	
	public Item(String newName, int newQuantity, double newPrice, double newWeight)
	{
		name = newName;
		quantity = newQuantity;
		price = newPrice;
		weight = newWeight;
	}
	
/**
 * A method to calculate the price of the items including shipping.
 * @return the value for all items based on a price per weight shipping.
 * 		  	This will include the cost for all of the item in the cart.
 */
	private double calculatePrice () 
	{
		double shippingPrice;
		double finalPrice;
		double weightMultiplier = 20;
		shippingPrice = weightMultiplier * weight * quantity;
		finalPrice = price * quantity;
		finalPrice = finalPrice + shippingPrice;
		finalPrice = convertToDollars(finalPrice);
		return finalPrice;
	}

/**
 * A public method allowing the client to receive a brief
 * printout explaining how many of the item are in the cart,
 * what the item is, what the cost per item is, and what it 
 * will cost to ship all of this type of item.
 */
	public void printItemAttributes () 
	{
		double shippingPrice = calculatePrice();
		System.out.println("The cart contains " + quantity + 
				" " + name + "(s).  Base price per item is: $" + 
				price + ".  Total cost including shipping is: $" +
				shippingPrice);
	}
	
/**
 * This method truncates cash transactions to two decimal places.
 * The method is protected to allow sub-classes to inherit the method.
 * @param cashValue - The value to be converted to a cash value
 * @return - The return is simply cashValue truncated to two decimal
 * 				places if necessary.  
 */
	protected double convertToDollars(double cashValue)
	{
		String balanceString = Double.toString(cashValue);
		char decimal = '.';
		int decimalPlacing = balanceString.indexOf(decimal);
		if(decimalPlacing == '1')
		{
			cashValue = Double.parseDouble(balanceString);
		} else {
			int balanceLength = balanceString.length();
			balanceLength = balanceLength - decimalPlacing;
			if(balanceLength == 1)
			{
				cashValue = Double.parseDouble(balanceString);
			} else {
				//adding 3 grabs the decimal and 2 decimal places
				//and truncates the rest of the string.
				balanceString = balanceString.substring(0, (decimalPlacing+3));
				cashValue = Double.parseDouble(balanceString);
			}
		}
		return cashValue;
	}
	
/**
 * 	Getters for each attribute
 */
	public String getName()
	{
		return name;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public double getPrice()
	{
		return price;
	}
	
/**
 * 	Setters for each of the attributes.
 */
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setQuantity(int newQuantity)
	{
		quantity = newQuantity;
	}
	
	public void setWeight(double newWeight)
	{
		weight = newWeight;
	}
	
	public void setPrice(double newPrice)
	{
		price = newPrice;
	}
}