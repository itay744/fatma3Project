
abstract class Order implements Comparable<Order>, Valueable {
	protected int eventId;
	protected int customerId;
	protected int numberOfTickets;
	protected double orderPrice;

	public Order(int eventId, int customerId, int numberOfTickets) {// constructor
		this.eventId = eventId;
		this.customerId = customerId;
		this.numberOfTickets = numberOfTickets;
		this.orderPrice = 0;

	}

	public void setOrderPrice(double price) {// setting order price
		this.orderPrice = price;
	}

	public int getEventId() {// return event id
		return eventId;
	}

	public double getValue() {// valuable implement , return order price
		return orderPrice;
	}

	public int getNumberOfTickets() {// return number of tickets
		return numberOfTickets;
	}

	public int getCustomerId() {// return customer id
		return customerId;
	}

	public int compareTo(Order other) {// comparable implement , compare by number of tickets
		return this.numberOfTickets - other.numberOfTickets;
	}

}