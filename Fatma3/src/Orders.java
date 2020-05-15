
import java.util.Vector;

public class Orders implements Comparable<Orders> {
	private int eventId;
	private int customerId;
	private int numberOfTickets;

	public Orders(int eventId, int customerId, int numberOfTickets) {
		this.eventId = eventId;
		this.customerId = customerId;
		this.numberOfTickets = numberOfTickets;
	}

	public int getEventId() {
		return eventId;
	}

	public int getCustomerAge(Vector<Customers> customers) {
		for (int i = 0; i < customers.size(); i++) {
			if (this.customerId == customers.elementAt(i).getId()) {
				return customers.elementAt(i).getAge();
			}
		}
		return 0;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getNumOfTickets() {
		return numberOfTickets;
	}

	public int getOrderPrice(Vector<Events> events) {
		int price = 0;
		for (int i = 0; i < events.size(); i++) {
			if (this.getEventId() == events.elementAt(i).getId()) {
				price += events.elementAt(i).getPricePerTicket() * numberOfTickets;
			}
		}
		return price;
	}

	@Override
	public int compareTo(Orders other) {
		return this.getNumOfTickets() - other.getNumOfTickets();
	}

}