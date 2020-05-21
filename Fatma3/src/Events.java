import java.util.Vector;

public class Events implements Comparable<Events>, Valueable {
	private String name;
	private int eventId;
	private double pricePerTicket;
	private int totalTickets;

	public Events(String name, int id, double pricePerTicket) throws NegativePriceException {

		this.name = name;
		eventId = id;
		if (pricePerTicket < 0) {
			throw new NegativePriceException();
		}
		this.pricePerTicket = pricePerTicket;
		//id.this.totalTickets = countTotalTickets(orders);

	}

	public int getEventId() {
		return eventId;
	}

	public String getName() {
		return new String(name);
	}


//	public double getTotalTickets() {
//		return totalTickets;
//	}

	public double getPricePerTicket() {
		return pricePerTicket;
	}

	private int countTotalTickets(Vector<Orders> orders) {
		int count = 0;
		for (Orders order : orders) {
			if (order.getEventId() == eventId) {
				count += order.getNumberOfTickets();
			}
		}
		return count;

	}

	
	public int compareTo(Events other) {
		return  this.totalTickets - other.totalTickets;
		
	}


	public double getValue() {
		
		return pricePerTicket;
	}

}
