package elements;
/**
 * Class to create Transaction Objects
 * @author Hakan Emre Aktas
 *
 */
public class Transaction {
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	/**
	 * Constructor
	 * @param buyingOrder
	 * @param sellingOrder
	 */
	public Transaction(BuyingOrder buyingOrder , SellingOrder sellingOrder) {
		this.buyingOrder = buyingOrder;
		this.sellingOrder = sellingOrder;
	}
}
