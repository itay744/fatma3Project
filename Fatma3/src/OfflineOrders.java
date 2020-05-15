
public class OfflineOrders extends Orders {
	private int soldByEmpId;

	public OfflineOrders(int eventId, int soldToId, int numberOfTickets, int soldByEmpId) {
		super(eventId, soldToId, numberOfTickets);
		this.soldByEmpId = soldByEmpId;
	}

	public int getSellerId() {
		return soldByEmpId;
	}

}
