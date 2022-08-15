package executable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import elements.BuyingOrder;
import elements.Market;
import elements.SellingOrder;
import elements.Trader;
import elements.Transaction;
/**
 * Where "Mecik" happens.
 * @author Hakan Emre Aktas
 *
 */

public class Main {
	public static Random Generator;
	public static void main(String[] args) throws FileNotFoundException  {
	File inFile = new File(args[0]); 
	File outFile = new File(args[1]);
	PrintStream outstream = new PrintStream(outFile);
	Scanner reader = new Scanner(inFile);
	int A = reader.nextInt();
	int B = reader.nextInt();
	int C = reader.nextInt();
	int D = reader.nextInt();
	Generator = new Random(A);
	Market market = new Market(B);
	
	ArrayList<Trader> alltraderlist = new ArrayList<Trader>();
	String[][] splitUserLines;
	splitUserLines = new String[C][2];
	String[] userLines;
	userLines = new String[C];
	for(int i = 0; i < C + 1 ;i++) {
		if (i == 0){
			userLines[i] = reader.nextLine();
			continue;
		}
		userLines[i-1] = reader.nextLine();
	}
	for(int i = 0; i < userLines.length; i++) {
		for(int a = 0; a < 2 ; a++) {
			splitUserLines[i][a] = userLines[i].split(" ",35)[a];
		}
		
	}
	for(int i = 0; i < userLines.length; i++) {
		Trader temp = new Trader(Double.parseDouble(splitUserLines[i][0]),Double.parseDouble(splitUserLines[i][1]));
		alltraderlist.add(temp);
	}
	String[] lines;
	lines = new String[D];
	for(int i = 0; i < D ;i++) {
		if (i == 0){
			lines[i] = reader.nextLine();
			continue;
		}
		lines[i] = reader.nextLine();
	}
	String[][] splitlines;
	splitlines = new String[D][4];
	for (int i = 0; i < D  ; i++) {
		if (lines[i].split(" ",35)[0].equals("10")) {
			for(int a = 0; a < 4 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("11")) {
			for(int a = 0; a < 3 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("20")) {
			for(int a = 0; a < 4 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}					
		}
		if (lines[i].split(" ",35)[0].equals("21")) {
			for(int a = 0; a < 3 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("3")) {
			for(int a = 0; a < 3 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("4")) {
			for(int a = 0; a < 3 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("5")) {
			for(int a = 0; a < 2 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("777")) {
			for(int a = 0; a < 1 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("666")) {
			for(int a = 0; a < 2 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("500")) {
			for(int a = 0; a < 1 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("501")) {
			for(int a = 0; a < 1 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("502")) {
			for(int a = 0; a < 1 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("505")) {
			for(int a = 0; a < 1 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
		if (lines[i].split(" ",35)[0].equals("555")) {
			for(int a = 0; a < 1 ; a++) {
				splitlines[i][a] = lines[i].split(" ",35)[a];
			}
		}
	}
	int number_of_invalid_queries = 0;
	for (int i = 0; i < D  ; i++) {
		if (splitlines[i][0].equals("10")){
			if(alltraderlist.get(Integer.parseInt(splitlines[i][1])).buy(Double.parseDouble(splitlines[i][3]), Double.parseDouble(splitlines[i][2]), market) > 0) {
				market.checkTransactions(alltraderlist);
				continue;
			}
			else {
				number_of_invalid_queries = number_of_invalid_queries + 1;
				continue;
			}
		}
		if (splitlines[i][0].equals("11")){
			if(market.getSellingOrders().isEmpty()) {
				number_of_invalid_queries = number_of_invalid_queries + 1;
				continue;
			}
			else if(alltraderlist.get(Integer.parseInt(splitlines[i][1])).buy(Double.parseDouble(splitlines[i][2]), market.getSellingOrders().peek().getPrice(), market) > 0) {
				market.checkTransactions(alltraderlist);
				continue;
			}
			else {
				number_of_invalid_queries = number_of_invalid_queries + 1;
				continue;
			}
		}
		if (splitlines[i][0].equals("20")){
			if(alltraderlist.get(Integer.parseInt(splitlines[i][1])).sell(Double.parseDouble(splitlines[i][3]), Double.parseDouble(splitlines[i][2]), market) > 0) {
				market.checkTransactions(alltraderlist);
				continue;
			}
			else {
				number_of_invalid_queries = number_of_invalid_queries + 1;
				continue;
			}
		}
		if (splitlines[i][0].equals("21")){
			if(market.getBuyingOrders().isEmpty()) {
				number_of_invalid_queries = number_of_invalid_queries + 1;
				continue;
			}
			else if(alltraderlist.get(Integer.parseInt(splitlines[i][1])).sell(Double.parseDouble(splitlines[i][2]), market.getBuyingOrders().peek().getPrice(), market) > 0) {
				market.checkTransactions(alltraderlist);
				continue;
			}
			else {
				number_of_invalid_queries = number_of_invalid_queries + 1;
				continue;
			}
		}
		if (splitlines[i][0].equals("3")){
			alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().setDollars(alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().getDollars() + Double.parseDouble(splitlines[i][2]));
			continue;
		}
		if (splitlines[i][0].equals("4")){
			if(alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().getDollars() >= Double.parseDouble(splitlines[i][2])) {
				alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().setDollars(alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().getDollars() - Double.parseDouble(splitlines[i][2]));
				continue;
			}
			else {
				number_of_invalid_queries = number_of_invalid_queries + 1;
				continue;
			}
		}
		if (splitlines[i][0].equals("5")){	
			outstream.println("Trader "	+ Integer.parseInt(splitlines[i][1]) + ": " +String.format("%.5f", alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().getDollars() + alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().getBlockedDollars())  + "$ " + String.format("%.5f", alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().getCoins() + alltraderlist.get(Integer.parseInt(splitlines[i][1])).getWallet().getBlockedCoins())  + "PQ");
			continue;
		}
		if (splitlines[i][0].equals("777")){	
			for(Trader tr : alltraderlist) {
				tr.getWallet().setCoins(tr.getWallet().getCoins() + Generator.nextDouble() * 10);
			}
			continue;
		}
		if (splitlines[i][0].equals("666")){
			market.makeOpenMarketOperation(Double.parseDouble(splitlines[i][1]), alltraderlist);
			continue;
		}
		if (splitlines[i][0].equals("500")){
			Stack<BuyingOrder> tempbuySt = new Stack<BuyingOrder>();
			Stack<SellingOrder> tempsellSt = new Stack<SellingOrder>();
			double totaldollars = 0;
			double totalCoins = 0;
			while(!market.getBuyingOrders().isEmpty()) {
				totaldollars = totaldollars + market.getBuyingOrders().peek().getPrice()*market.getBuyingOrders().peek().getAmount();
				tempbuySt.push(market.getBuyingOrders().poll());
			}
			while(!tempbuySt.isEmpty()) {
				market.getBuyingOrders().add(tempbuySt.pop());
			}
			while(!market.getSellingOrders().isEmpty()) {
				totalCoins = totalCoins + market.getSellingOrders().peek().getAmount();
				tempsellSt.push(market.getSellingOrders().poll());
			}
			while(!tempsellSt.isEmpty()) {
				market.getSellingOrders().add(tempsellSt.pop());
			}
			outstream.println("Current market size: " + String.format("%.5f", totaldollars)  + " " + String.format("%.5f", totalCoins));
			continue;
		}
		if (splitlines[i][0].equals("501")){
			outstream.println("Number of successful transactions: " + market.getTransactions().size());
			continue;
		}
		if (splitlines[i][0].equals("502")){
			outstream.println("Number of invalid queries: " + number_of_invalid_queries);
			continue;
		}
		if (splitlines[i][0].equals("505")){
			if(market.getBuyingOrders().isEmpty()) {
				if(market.getSellingOrders().isEmpty()) {
					outstream.println("Current prices: 0.00000 0.00000 0.00000");
				}
				else {
					outstream.println("Current prices: 0.00000 " + String.format("%.5f", market.getSellingOrders().peek().getPrice())  + " " + String.format("%.5f", market.getSellingOrders().peek().getPrice()));
				}
			}
			else {
				if(market.getSellingOrders().isEmpty()) {
					outstream.println("Current prices: " +String.format("%.5f",market.getBuyingOrders().peek().getPrice())  + " 0.00000 " +String.format("%.5f",market.getBuyingOrders().peek().getPrice()));
				}
				else {
					double avg = (market.getBuyingOrders().peek().getPrice()+market.getSellingOrders().peek().getPrice())/2;
					outstream.println("Current prices: " + String.format("%.5f",market.getBuyingOrders().peek().getPrice())  + " " + String.format("%.5f",market.getSellingOrders().peek().getPrice()) + " " + String.format("%.5f",avg));
				}
			}
			continue;
		}
		if (splitlines[i][0].equals("555")){
			for(Trader tr : alltraderlist) {
				outstream.println("Trader " +  tr.getId() + ": " +String.format("%.5f",tr.getWallet().getDollars() + tr.getWallet().getBlockedDollars())  + "$ " +String.format("%.5f",tr.getWallet().getCoins() + tr.getWallet().getBlockedCoins())  + "PQ");
			}
			continue;
		}
	}
	
	//#TODO
	
	while(!market.getBuyingOrders().isEmpty()) {
		System.out.println(	market.getBuyingOrders().peek().getTraderID() + " " +	market.getBuyingOrders().peek().getPrice() + " " + market.getBuyingOrders().poll().getAmount());
	}
	System.out.println();
	while(!market.getSellingOrders().isEmpty()) {
		System.out.println(market.getSellingOrders().peek().getTraderID() + " " + 	market.getSellingOrders().peek().getPrice() + " " + market.getSellingOrders().poll().getAmount());
	}
	for(Transaction i : market.getTransactions()) {
		System.out.println(i);
	}
	
	
	
	
	
	

		reader.close();
		outstream.close();
	}
}
