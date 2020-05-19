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
		for (int i = 0; i < orders.size(); i++) {
			if (orders.elementAt(i) instanceof OfflineOrders
					&& getId() == ((OfflineOrders) orders.elementAt(i)).getSellerId()) {
				Bonus += orders.elementAt(i).getOrderPrice(events);
			}
		}
		return Bonus * saleRate;
	}

}
