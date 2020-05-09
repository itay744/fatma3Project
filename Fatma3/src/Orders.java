public class Orders {
	private int eventId;
	private int soldToId;
	private int numberOfTickets;
	private int soldBy;
	private String URL;
	
	public Orders (int eventId,int soldToId,int numberOfTickets, int soldBy, String url) {
		this.eventId = eventId;
		this.soldToId = soldToId;
		this.numberOfTickets = numberOfTickets;
		this.soldBy = soldBy;
		this.URL = url;
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


	
	
	
}
