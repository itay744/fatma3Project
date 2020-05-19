
import java.util.Vector;

public class Orders implements Comparable<Orders>, Valueable {
	protected int eventId;
	protected int customerId;
	protected int numberOfTickets;

	public Orders(int eventId, int customerId, int numberOfTickets) {
		this.eventId = eventId;
		this.customerId = customerId;
		this.numberOfTickets = numberOfTickets;
	}

	private double getEventPrice(Vector<Events> events) {
		double price = 0;
		for (int i = 0; i < events.size(); i++) {
			if (this.eventId == events.elementAt(i).getEventId()) {
				price = events.elementAt(i).getPricePerTicket();
				
			}
		}
		return price;
	}

	public int getEventId() {
		return eventId;
	}

	public int getCustomerAge(Vector<Customers> customers) {
		int age = 0;
		for (int i = 0; i < customers.size(); i++) {
			if (this.customerId == customers.elementAt(i).getId()) {
				age = customers.elementAt(i).getAge();
			}
		}
		return age;
	}

	public double getValue() {
		return numberOfTickets;
	}

	public int getCustomerId() {
		return customerId;
	}

	public double getOrderPrice(Vector<Events> events) {
		double price = 0;
		price = getEventPrice(events)*numberOfTickets;
		return price;
	}

	public int compareTo(Orders other) {
		return this.numberOfTickets - other.numberOfTickets;
	}
	
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
}