package elements;
/**
 * Class to create SellingOrder Objects
 * @author Hakan Emre Aktas
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {
	/**
	 * Constructor
	 * @param traderID
	 * @param amount
	 * @param price
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID,amount,price);
	}
	/**
	 * CompareTo method to keep PriorityQueue sorted
	 */
	public int compareTo(SellingOrder other) {
		if(this.getPrice() > other.getPrice()) {
			return 1;
		}
		else if(this.getPrice() < other.getPrice()) {
			return -1;
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
				else if(this.getTraderID() < other.getTraderID()){
					return -1;
				}
				else {
					return 0;
				}
					
			}
		}
	}
}
