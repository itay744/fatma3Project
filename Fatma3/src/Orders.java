import java.util.Vector;

public class Orders implements Comparable<Orders> {
	private int eventId;
	private int soldToId;
	private int numberOfTickets;
	private int soldBy;
	private String URL;

	public Orders(int eventId, int soldToId, int numberOfTickets) {
		this.eventId = eventId;
		this.soldToId = soldToId;
		this.numberOfTickets = numberOfTickets;

	}

	public int getEventId() {
		return eventId;
	}

	public int getSoldToId() {
		return soldToId;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public int getSoldBy() {
		return soldBy;
	}

	public String getURL() {
		return URL;
	}
    
	public int numOfTicketPerCustumer(Customers custumer) {
		if(this.getSoldToId() == custumer.getId()) {
			return this.numberOfTickets;
		}
		else
			return 0;
	}
	
	public int getPricePerOrder(Vector<Events> events) {
		int price = 0;
		for (int i = 0; i < events.size(); i++) {
			if (this.getEventId() == events.elementAt(i).getId()) {
				price += events.elementAt(i).getPricePerTicket(); 
			}
		}
		return price;
	}

	public int getOrderPrice(Vector<Events> events) {
		int price = 0;
		for (int i = 0; i < events.size(); i++) {
			if (this.getEventId() == events.elementAt(i).getId()) { // checking all the events to get event id price
				price += events.elementAt(i).getPricePerTicket()* numberOfTickets; // calculate the price 
			}
		}
		return price;
	}


	@Override
	public int compareTo(Orders o) {
		return this.getNumberOfTickets() - o.getNumberOfTickets();
	}

}
