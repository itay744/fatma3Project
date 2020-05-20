import java.util.Vector;

public class salesWorkers extends Employees {
	private double saleRate;

	public salesWorkers(int id, String name, int age, double saleRate, Vector<Orders> o, Vector<Customers> c,
			Vector<Events> e) {
		super(id, name, age);
		this.saleRate = saleRate;
		salary = calculateSalary(o, c, e);

	}

	public double getSaleRate() {
		return saleRate;
	}

	protected double calculateSalary(Vector<Orders> orders, Vector<Customers> custumers, Vector<Events> events) {
		double Bonus = 0;
		for (Orders order: orders) {
			if (order instanceof OfflineOrders && getId() == ((OfflineOrders) order).getSellerId()) {
				Bonus += order.getOrderPrice(events);
			}
		}
		return Bonus * saleRate;
	}

}
