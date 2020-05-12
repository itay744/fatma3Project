import java.util.Vector;

public class SalesWorkers extends Employees {
	private long saleRate;
	private long salary;

	public SalesWorkers(int id, String Name, int Age, int bonusRate) {
		super(id, Name, Age);
		this.salary = 0;
		this.saleRate = bonusRate;

	}

	public long getSaleRate() {
		return saleRate;
	}

	public long calculateSalary(Vector<OfflineOrders> orders,Vector <Customers> custumers) {
		salary = this.countSales(orders) * saleRate;
		return salary;
	}

	public double getSalary() {
		return this.salary;
	}

	public int countSales(Vector<OfflineOrders> orders) {
		int counter = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (this.getId() == orders.elementAt(i).getSoldBy()) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public int compareTo(Employees o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void calculateSalary(Vector<OfflineOrders> orders, Vector<Customers> custumers, Vector<Events> events) {
		// TODO Auto-generated method stub
		
	}

}
