// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 3

package Assignment3;

public class Clothing extends Item {

/**
 * The only attribute required for clothing items
 * is sales tax.  A constant exists to quickly change
 * the sales tax in the implementation if the tax
 * ever changes.  Currently it is 10%.
 */
	private double salesTax;
	private final double defaultTax = 1.1;
	
	public Clothing() {
		super();
		salesTax = defaultTax;
	}

	public Clothing(String newName) {
		super(newName);
		salesTax = defaultTax;
	}

	public Clothing(String newName, int newQuantity, double newPrice,
			double newWeight) {
		super(newName, newQuantity, newPrice, newWeight);
		salesTax = defaultTax;
	}

/**
 * This method calculates the price for this collection of clothing.
 * It accounts for sales tax.
 * @return the price to ship all of this type of item
 */
		private double calculatePrice () 
		{
			double finalPrice = 0;
			finalPrice = convertToDollars(finalPrice);
			return finalPrice;
		}
		
/**
 * This method overwrites the Item method to alert the user if
 * the
 
		public void printItemAttributes () 
		{
			System.out.println(name + " is a Grocery.");
		}
 */
}
