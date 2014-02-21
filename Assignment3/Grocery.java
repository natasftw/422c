// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 3

package Assignment3;

public class Grocery extends Item {

/**
 * Groceries are items, but can be perishable.
 * Only one additional attribute required.
 * A constant is included to handle premium shipping costs.
 */
	private boolean isPerishable;
	private final double premiumShipping = 1.2;
	
/**
 * Constructors are inherited, but add on the perishable
 * attribute.
 */
	public Grocery() {
		super();
		isPerishable = false;
	}

	public Grocery(String newName) {
		super(newName);
	}

	public Grocery(String newName, int newQuantity, double newPrice,
			double newWeight) {
		super(newName, newQuantity, newPrice, newWeight);
	}
	
	public Grocery(String newName, int newQuantity, double newPrice,
			double newWeight, boolean perishableNeed) {
		super(newName, newQuantity, newPrice, newWeight);
		isPerishable = perishableNeed;
	}

/**
 * This method calculates the price for this collection of groceries.
 * It accounts for shipping needs related to perishable items.
 * @return the price to ship all of this type of item
 */
	private double calculatePrice () 
	{
		double shippingPrice;
		double finalPrice;
		double weightMultiplier = 20;
		shippingPrice = weightMultiplier * weight * quantity;
		if(isPerishable)
		{
			shippingPrice = shippingPrice * premiumShipping;
		}
		finalPrice = price * quantity;
		finalPrice = finalPrice + shippingPrice;
		finalPrice = convertToDollars(finalPrice);
		return finalPrice;
	}
	
/**
 * This method overwrites the Item method to alert the user if
 * the grocery is perishable.
 */
	public double printItemAttributes () 
	{
		double shippingPrice = calculatePrice();
		System.out.println("The cart contains " + quantity + 
				" " + name + "(s).  Base price per item is: $" + 
				price + ".  Total cost including shipping is: $" +
				shippingPrice);
		return shippingPrice;
	}
	
}
