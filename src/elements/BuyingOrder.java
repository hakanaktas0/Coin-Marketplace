package elements;
/**
 * Class to generate BuyingOrder Objectts.
 * @author Hakan Emre Aktas
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	/**
	 * Constructor
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID,amount,price);
	}
	/**
	 * CompareTo method to keep PriorityQueue sorted the way intended
	 */
	public int compareTo(BuyingOrder other) {
		if(this.getPrice() > other.getPrice()) {
			return -1;
		}
		else if(this.getPrice() < other.getPrice()) {
			return 1;
		}
		else {
			if(this.getAmount() > other.getAmount()) {
				return -1;
			}
			else if(this.getAmount() < other.getAmount()) {
				return 1;
			}
			else {
				if(this.getTraderID() > other.getTraderID()) {
					return 1;
				}
				else if(this.getTraderID() < other.getTraderID()) {
					return -1;
				}
				else {
					return 0;
				}
			}
		}
	}
}
