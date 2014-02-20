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
			double shippingPrice;
			double finalPrice;
			double weightMultiplier = 20;
			shippingPrice = weightMultiplier * weight * quantity * salesTax;
			finalPrice = price * quantity;
			finalPrice = finalPrice + shippingPrice;
			finalPrice = convertToDollars(finalPrice);
			return finalPrice;
		}
		
/**
 * This method overwrites the Item method to use Clothing's
 * calculatePrice method instead of Item's
 */
		public void printItemAttributes () 
		{
			double shippingPrice = calculatePrice();
			System.out.println("The cart contains " + quantity + 
					" " + name + "(s).  Base price per item is: $" + 
					price + ".  Total cost including tax and shipping is: $" +
					shippingPrice);
		}

/**
 * Just to be complete, there are a getter and setter for salesTax
 */
		public double getSalesTax()
		{
			return salesTax;
		}
		
		public void setSalesTax(double newTax)
		{
			salesTax = newTax;
		}
		
}
