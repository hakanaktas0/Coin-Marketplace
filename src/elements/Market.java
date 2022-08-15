package elements;
import java.util.*;
/**
 * Class to initialize the Market
 * @author Hakan Emre Aktas
 *
 */
public class Market {
	private PriorityQueue<SellingOrder> sellingOrders;
	private PriorityQueue<BuyingOrder> buyingOrders;
	private ArrayList<Transaction> transactions;
	private int fee;
	/**
	 * Constructor
	 * @param fee
	 */
	public Market(int fee) {
		this.fee = fee;
		this.buyingOrders = new PriorityQueue<BuyingOrder>();
		this.sellingOrders = new PriorityQueue<SellingOrder>();
		this.transactions = new ArrayList<Transaction>();
	}
	/**
	 * Adds the order to the Queue
	 * @param order
	 */
	public void giveSellOrder(SellingOrder order) {
			this.sellingOrders.add(order);
	}
	/**
	 * Adds the order to the Queue
	 * @param order
	 */
	public void giveBuyOrder(BuyingOrder order) {
			this.buyingOrders.add(order);
	}
	/**
	 * Changes the Market price with intervention.
	 * Gives counter orders while the price is above minimum selling price or below maximum buying price.
	 * @param price
	 * @param traders
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		if(buyingOrders.isEmpty() && sellingOrders.isEmpty()) {
			return;
		}
		if(!buyingOrders.isEmpty()) {
			if(price <=  buyingOrders.peek().getPrice()) {
				while(price <=  buyingOrders.peek().getPrice()) {
					SellingOrder tempO = new SellingOrder(0,buyingOrders.peek().getAmount(),buyingOrders.peek().getPrice());
					giveSellOrder(tempO);
					checkTransactions(traders);
					if(buyingOrders.isEmpty()) {
						break;
					}
				}
			}
		}
		if(!sellingOrders.isEmpty()) {
			if(price >=  sellingOrders.peek().getPrice()) {
				while(price >=  sellingOrders.peek().getPrice()) {
					BuyingOrder tempO = new BuyingOrder(0,sellingOrders.peek().getAmount(),sellingOrders.peek().getPrice());
					giveBuyOrder(tempO);
					checkTransactions(traders);
					if(sellingOrders.isEmpty()) {
						break;
					}
				}
			}
		}
	}
	/**
	 * Matches the buying orders with selling orders.
	 * It matches until the overlap disappears.
	 * Divides orders if amounts are not equal.
	 * @param traders
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		if(!buyingOrders.isEmpty() && !sellingOrders.isEmpty()) {
			if(buyingOrders.peek().getPrice() >= sellingOrders.peek().getPrice()) {
				while(buyingOrders.peek().getPrice() >= sellingOrders.peek().getPrice()) {
					if(buyingOrders.peek().getAmount() > sellingOrders.peek().getAmount()) {
						traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins() - sellingOrders.peek().getAmount());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins() + sellingOrders.peek().getAmount());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars() - sellingOrders.peek().getAmount() * buyingOrders.peek().getPrice());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getDollars() + 	(buyingOrders.peek().getPrice() - sellingOrders.peek().getPrice()) * sellingOrders.peek().getAmount());;
						traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars() + sellingOrders.peek().getAmount() * sellingOrders.peek().getPrice() - sellingOrders.peek().getAmount() * sellingOrders.peek().getPrice() * ((float)fee/1000));
						BuyingOrder tempO = new BuyingOrder(buyingOrders.peek().getTraderID() ,buyingOrders.peek().getAmount() - sellingOrders.peek().getAmount() , buyingOrders.peek().getPrice());
						BuyingOrder tempTR = new BuyingOrder(buyingOrders.peek().getTraderID(), sellingOrders.peek().getAmount(), buyingOrders.peek().getPrice());
						Transaction temp = new Transaction(tempTR,sellingOrders.peek());
						transactions.add(temp);
						sellingOrders.poll();
						buyingOrders.poll();
						buyingOrders.add(tempO);
					}
					else if(buyingOrders.peek().getAmount() < sellingOrders.peek().getAmount()) {
						traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins() - buyingOrders.peek().getAmount());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins() + buyingOrders.peek().getAmount());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars() - buyingOrders.peek().getAmount() * buyingOrders.peek().getPrice());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getDollars() + (buyingOrders.peek().getPrice() - sellingOrders.peek().getPrice()) * buyingOrders.peek().getAmount() );
						traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars() + buyingOrders.peek().getAmount() * sellingOrders.peek().getPrice() - buyingOrders.peek().getAmount() * sellingOrders.peek().getPrice() * ((float)fee/1000));
						SellingOrder tempO = new SellingOrder(sellingOrders.peek().getTraderID() ,sellingOrders.peek().getAmount() - buyingOrders.peek().getAmount() , sellingOrders.peek().getPrice());
						SellingOrder tempTR = new SellingOrder(sellingOrders.peek().getTraderID(), buyingOrders.peek().getAmount(), sellingOrders.peek().getPrice());
						Transaction temp = new Transaction(buyingOrders.peek(),tempTR);
						transactions.add(temp);
						sellingOrders.poll();
						buyingOrders.poll();
						sellingOrders.add(tempO);
					}
					else {
						traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins() - buyingOrders.peek().getAmount());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins() + buyingOrders.peek().getAmount());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars() - buyingOrders.peek().getAmount() * buyingOrders.peek().getPrice());
						traders.get(buyingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getDollars() + (buyingOrders.peek().getPrice() - sellingOrders.peek().getPrice()) * buyingOrders.peek().getAmount() );
						traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars() + buyingOrders.peek().getAmount() * sellingOrders.peek().getPrice()*(1 - ((float)fee/1000)));
						Transaction temp = new Transaction(buyingOrders.peek(),sellingOrders.peek());
						transactions.add(temp);
						sellingOrders.poll();
						buyingOrders.poll();
					}
					if(buyingOrders.isEmpty() || sellingOrders.isEmpty()) {
						break;
					}
					
				}
				
			}
		}
	}
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}
	public void setSellingOrders(PriorityQueue<SellingOrder> sellingOrders) {
		this.sellingOrders = sellingOrders;
	}
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}
	public void setBuyingOrders(PriorityQueue<BuyingOrder> buyingOrders) {
		this.buyingOrders = buyingOrders;
	}
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	
}
