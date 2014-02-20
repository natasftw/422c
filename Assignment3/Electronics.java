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
	private final double premiumMultiplier = 1.2;
	
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
			double shippingPrice;
			double finalPrice;
			double weightMultiplier = 20;
			shippingPrice = weightMultiplier * weight * quantity;
			if(isFragile)
			{
				shippingPrice = shippingPrice * premiumMultiplier;
			}
			finalPrice = price * quantity;
			finalPrice = finalPrice + shippingPrice;
			finalPrice = convertToDollars(finalPrice);
			return finalPrice;
		}
		
/**
 * This method overwrites the Item method to alert the user if
 * the electronics are fragile and where they'll ship.
 */
		public void printItemAttributes () 
		{
			double shippingPrice = calculatePrice();
			System.out.println("The cart contains " + quantity + 
					" " + name + "(s).  Base price per item is: $" + 
					price + ".  Total cost including applicable tax and shipping is: $" +
					shippingPrice);
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
		
/**
 * A series of setters and getters for Electronics attributes.
 */
		public double getSalesTax()
		{
			return salesTax;
		}
		
		public String getDeliveryState()
		{
			return deliveryState;
		}
		
		public boolean getIsFragile()
		{
			return isFragile;
		}
		
		public void setSalesTax(double newTax)
		{
			salesTax = newTax;
		}
		
		public void setDeliveryState(String newState)
		{
			deliveryState = newState;
		}
		
		public void setIsFragile(boolean fragileStatus)
		{
			isFragile = fragileStatus;
		}
}
