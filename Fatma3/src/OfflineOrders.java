import java.util.Vector;

public class OfflineOrders extends Orders {
	private int soldByEmpId;

	public OfflineOrders(int eventId, int soldToId, int numberOfTickets, int soldByEmpId,Vector<Events> events,Vector<Employees> employees, Vector<Customer> customers) {
		super(eventId, soldToId, numberOfTickets, events, employees,customers);
		this.soldByEmpId = soldByEmpId;
	}

	public int getSellerId() {
		return soldByEmpId;
	}

}
