import java.util.Vector;

public class salesWorkers extends Employees {
	private double saleRate;

	public salesWorkers(int id, String Name, int Age, double saleRate, Vector<Orders> o, Vector<Customers> c,
			Vector<Events> e) {
		super(id, Name, Age);
		this.saleRate = saleRate;
		this.salary = calculateSalary(o, c, e);

	}

	public double getSaleRate() {
		return saleRate;
	}

	public double calculateSalary(Vector<Orders> orders, Vector<Customers> custumers, Vector<Events> events) {
		return (calculateFutureBonus(orders, events));

	}

	private double calculateFutureBonus(Vector<Orders> orders, Vector<Events> events) {
		double Bonus = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (orders.elementAt(i) instanceof OfflineOrders
					&& this.getId() == ((OfflineOrders) orders.elementAt(i)).getSellerId()) {
				Bonus += orders.elementAt(i).getOrderPrice(events)*saleRate ;
			}
		}
		return Bonus;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}


}
