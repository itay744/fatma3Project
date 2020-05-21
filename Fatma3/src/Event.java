
public class Event implements Comparable<Event>, Valueable {
	private String name;
	private int eventId;
	private double pricePerTicket;
	private int totalTickets;

	public Event(String name, int id, double pricePerTicket) throws NegativePriceException {// Constructor

		this.name = name;
		eventId = id;
		if (pricePerTicket < 0) {// if price is negative throw negative price exception
			throw new NegativePriceException();
		}
		this.pricePerTicket = pricePerTicket;
		this.totalTickets = 0;

	}

	public int getEventId() {// return event id
		return eventId;
	}

	public String getName() {// Return name
		return this.name;
	}


	public double getPricePerTicket() {// return price per ticket
		return pricePerTicket;
	}
	
	public void addToTotalTickets(int amount) {// add amount to total tickets
		this.totalTickets += amount;
	}
	
	public double getValue() {	// implement valuable , return price per ticket
		return pricePerTicket;
	}

	
	public int compareTo(Event other) {// implement comparable, compare by total tickets
		return  this.totalTickets - other.totalTickets;
		
	}




}
