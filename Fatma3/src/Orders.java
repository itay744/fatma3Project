
import java.util.Vector;

public class Orders implements Comparable<Orders>, Valueable {
	protected int eventId;
	protected int customerId;
	protected int numberOfTickets;
	protected double orderPrice;

	public Orders(int eventId, int customerId, int numberOfTickets,Vector<Events> events) {
		this.eventId = eventId;
		this.customerId = customerId;
		this.numberOfTickets = numberOfTickets;	
		orderPrice = getOrderPrice(events);
		
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

	public int getEventId() {
		return eventId;
	}

	public int getCustomerAge(Vector<Customers> customers) {
		int age = 0;
		for (Customers customer: customers) {
			if (customerId == customer.getId()) {
				age = customer.getAge();
			}
		}
		return age;
	}

	public double getValue() {
		return orderPrice;
	}

	public int getCustomerId() {
		return customerId;
	}

	public double getOrderPrice(Vector<Events> events) {
		double price = 0;
		price = getEventPrice(events) * numberOfTickets;
		return price;
	}

	public int compareTo(Orders other) {
		return this.numberOfTickets - other.numberOfTickets;
	}

}