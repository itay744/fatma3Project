import java.util.Vector;

public class salesWorkers extends Employees {
	private double saleRate;

	public salesWorkers(int id, String Name, int Age, double saleRate, Vector <Orders> o,Vector <Customers> c,Vector <Events> e) {
		super(id, Name, Age);
		this.saleRate = saleRate;
		calculateSalary(o, c, e);

	}

	public double getSaleRate() {
		return saleRate;
	}

	public void calculateSalary(Vector<Orders> orders, Vector<Customers> custumers, Vector<Events> events) {
		setSalary(calculateFutureBonus(orders, events));

	}

	public double calculateFutureBonus(Vector<? extends Orders> orders, Vector<Events> events) {
		double salary = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (orders.elementAt(i) instanceof OfflineOrders  &&
					this.getId() == ((OfflineOrders) orders.elementAt(i)).getSellerId()) {
				salary += orders.elementAt(i).getOrderPrice(events) * saleRate;
			}
		}
		return salary;
	}

}
