import java.util.Vector;

public class Events implements Comparable <Events>, Valueable {
	private String name;
	private int id;
	private double pricePerTicket;
	private int totalTickets;

	public Events(String name, int id, double pricePerTicket,Vector<Orders> orders) throws NegativePriceException {

		this.name = name;
		this.id = id;
		if (pricePerTicket<0) {
			throw new NegativePriceException();
		}
		this.pricePerTicket = pricePerTicket;
		this.totalTickets = countTotalTickets(orders);
		
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return new String(name);
	}

	public double getPricePerTicket() {
		return this.pricePerTicket;
	}
	
	public double getTotalTickets() {
		return this.totalTickets;
	}
	
	public double getValue() {
		return pricePerTicket;
	}
	
	public int countTotalTickets(Vector<Orders> orders) {
		int count = 0;
		for(int i = 0; i< orders.size(); i++) {
			if(orders.elementAt(i).getEventId() == this.id) {
				count = count + orders.elementAt(i).getNumOfTickets();
			}
		}
		return count;
		
	}

	@Override
	public int compareTo(Events other) {
		double answer = this.totalTickets - other.totalTickets;
		return (int)answer ;
	}



}
