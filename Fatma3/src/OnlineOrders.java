import java.util.Vector;

public class OnlineOrders extends Orders {
	private String url;

	public OnlineOrders(int eventId, int soldToId, int numberOfTickets, String url,Vector<Events> events) {
		super(eventId, soldToId, numberOfTickets, events);
		this.url = url;
	}
}
