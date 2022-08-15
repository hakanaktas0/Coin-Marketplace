package elements;
/**
 * Class to create Trader objects
 * @author Hakan Emre Aktas
 *
 */
public class Trader {
	private int id;
	private Wallet wallet;
	public static int numberOfUsers = 0;
	/**
	 * Constructor
	 * @param dollars
	 * @param coins
	 */
	public Trader(double dollars, double coins) {
		Wallet temp = new Wallet(dollars,coins);
		this.wallet = temp;
		this.id = numberOfUsers;
		numberOfUsers = numberOfUsers + 1;
	}
	/**
	 * Method to check whether sell order is valid, it also creates the order if valid
	 * @param amount
	 * @param price
	 * @param market
	 * @return 1 if valid, 0 if not
	 */
	public int sell(double amount, double price,Market market) {
		if(this.wallet.getCoins() >= amount) {
			SellingOrder tempO = new SellingOrder(this.id, amount, price);
			market.giveSellOrder(tempO);
			this.wallet.setCoins(this.wallet.getCoins() - amount);
			this.wallet.setBlockedCoins(this.wallet.getBlockedCoins() + amount);
			return 1;
		}
		else {
			return 0;
		}
	}
	/**
	 * Method to check whether buy order is valid, it also creates the order if valid
	 * @param amount
	 * @param price
	 * @param market
	 * @return	1 if valid, 0 if not
	 */
	public int buy(double amount, double price, Market market) {
		if(this.wallet.getDollars() >= amount*price) {
			BuyingOrder tempO = new BuyingOrder(this.id, amount, price);
			market.giveBuyOrder(tempO);
			this.wallet.setDollars(this.wallet.getDollars() - amount*price);
			this.wallet.setBlockedDollars(this.wallet.getBlockedDollars() + amount*price);
			return 1;
		}
		else {
			return 0;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public static int getNumberOfUsers() {
		return numberOfUsers;
	}
	public static void setNumberOfUsers(int numberOfUsers) {
		Trader.numberOfUsers = numberOfUsers;
	}
	
}
