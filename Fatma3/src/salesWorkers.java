import java.util.Vector;

public class salesWorkers extends Employees {
	private long saleRate;
	private long salary;

	public salesWorkers(int id, String Name, int Age, int bonusRate) {
		super(id, Name, Age);
		this.salary = 0;
		this.saleRate = bonusRate;

	}

	public long getSaleRate() {
		return saleRate;
	}

	public long calculateSalary(Vector<OfflineOrders> orders,Vector <Custumers> custumers) {
		salary = this.countSales(orders) * saleRate;
		return salary;
	}

	public long getSalary() {
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

}
