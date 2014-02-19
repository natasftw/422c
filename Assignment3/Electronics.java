// Garcia, Jose
// jag7235
// Boellaard, Jeffrey
// jrb4229
// EE422C-Assignment 3

package Assignment3;

public class Electronics extends Item {

/**
 * A list of attributes necessary for electronics.
 * Included is a constant for the default sales tax.
 */
	private double salesTax;
	private String deliveryState;
	private boolean isFragile;
	private final double defaultTax = 1.1;
	
/**
 * Constructors are inherited with an additional constructor
 * added to allow for electronics specific attributes.
 */
	public Electronics() {
		super();
		salesTax = defaultTax;
		deliveryState = "unknown";
		isFragile = false;
	}

	public Electronics(String newName) {
		super(newName);
		salesTax = defaultTax;
		deliveryState = "unknown";
		isFragile = false;
	}

	public Electronics(String newName, int newQuantity, double newPrice,
			double newWeight) {
		super(newName, newQuantity, newPrice, newWeight);
		salesTax = defaultTax;
		deliveryState = "unknown";
		isFragile = false;
	}
	
	public Electronics(String newName, int newQuantity, double newPrice,
			double newWeight, String newState, boolean fragileStatus) {
		super(newName, newQuantity, newPrice, newWeight);
		deliveryState = newState;
		isFragile = fragileStatus;
		salesTax = calculateSalesTax(deliveryState);
	}
	
/**
 * This method calculates the price for this collection of electronics.
 * It accounts for premium shipping and appropriate sales tax.
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
 * the electronics are fragile and where they'll ship.
 */
		public void printItemAttributes () 
		{
			System.out.println(name + " is a Grocery.");
		}

/**
 * This method calculates the sales tax charged for this item.
 * @param state - some states are tax-free so the state needs to be checked
 * @return - the sales tax multiplier
 */
		private double calculateSalesTax(String state)
		{
			if(state.contentEquals("TX") || state.contentEquals("NM") || 
					state.contentEquals("VA") || state.contentEquals("AZ") ||
					state.contentEquals("AK"))
			{
				return 1;	//No sales tax means we want a multiplier of 1.
			}
			return defaultTax;	//Otherwise, return the default sales tax.
		}
}
