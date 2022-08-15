package elements;
/**
 * Class to create Wallet objects for traders
 * @author Hakan Emre Aktas
 *
 */
public class Wallet {
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	/**
	 * Constructor
	 * @param dollars
	 * @param coins
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		this.blockedCoins = 0;
		this.blockedDollars = 0;
	}
	public double getDollars() {
		return dollars;
	}
	public void setDollars(double dollars) {
		this.dollars = dollars;
	}
	public double getCoins() {
		return coins;
	}
	public void setCoins(double coins) {
		this.coins = coins;
	}
	public double getBlockedDollars() {
		return blockedDollars;
	}
	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}
	public double getBlockedCoins() {
		return blockedCoins;
	}
	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
	
}
