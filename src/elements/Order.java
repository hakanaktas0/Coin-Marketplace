package elements;
/**
 * Parent Class of Orders
 * @author Hakan Emre Aktas
 *
 */
public class Order {
	private double amount;
	private double price;
	private int traderID;
	/**
	 * Constructor
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTraderID() {
		return traderID;
	}
	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}
	
}


