import java.util.Vector;

public class OnlineOrders extends Orders {
	private String url;

	public OnlineOrders(int eventId, int soldToId, int numberOfTickets, String url,Vector<Events> events,Vector<Employees> employees, Vector<Customer> customers) {
		super(eventId, soldToId, numberOfTickets);
		this.url = url;
	}
}
