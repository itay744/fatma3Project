
public class OnlineOrder extends Order {
	private String url;

	public OnlineOrder(int eventId, int soldToId, int numberOfTickets, String url) {// constructor
		super(eventId, soldToId, numberOfTickets);// using father's constructor
		this.url = url;
	}
}
