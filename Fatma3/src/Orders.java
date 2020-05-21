
import java.util.Vector;

public class Orders implements Comparable<Orders>, Valueable {
	protected int eventId;
	protected int customerId;
	protected int numberOfTickets;
	protected double orderPrice;

	public Orders(int eventId, int customerId, int numberOfTickets) {
		this.eventId = eventId;
		this.customerId = customerId;
		this.numberOfTickets = numberOfTickets;	
		this.orderPrice = 0;
		
	}

	private double getEventPrice(Vector<Events> events) {
		double price = 0;
		for (Events event : events) {
			if (eventId == event.getEventId()) {
				price = event.getPricePerTicket();

			}
		}
		return price;
	}
	
	public void setOrderPrice(double price) {
		this.orderPrice = price;
	}

	public int getEventId() {
		return eventId;
	}


	public double getValue() {
		return orderPrice;
	}
	
	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public int getCustomerId() {
		return customerId;
	}


	public int compareTo(Orders other) {
		return this.numberOfTickets - other.numberOfTickets;
	}

}