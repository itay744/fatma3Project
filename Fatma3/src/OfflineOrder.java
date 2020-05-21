
public class OfflineOrder extends Order {
	private int soldByEmpId;

	public OfflineOrder(int eventId, int soldToId, int numberOfTickets, int soldByEmpId) {// constructor
		super(eventId, soldToId, numberOfTickets);// using father's constructor
		this.soldByEmpId = soldByEmpId;
	}

	public int getSellerId() {// return seller id
		return soldByEmpId;
	}

}
