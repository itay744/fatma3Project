
public class OnlineOrders extends Orders {
	private String url;
	public OnlineOrders(int eventId, int soldToId, int numberOfTickets,String url) {
		super(eventId, soldToId, numberOfTickets);
		this.url = url;
	}

}
