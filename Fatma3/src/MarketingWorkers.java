import java.util.Vector;

public class MarketingWorkers extends Employees {
	private int phone;
	private int salary;
	final int signClientBonus = 2;
	final double bonusRate = 0.01;

	public MarketingWorkers(int id, String name, int Age, int phone) {
		super(id, name, Age);
		this.phone = phone;
		this.salary = 0;
	}

	public int getPhone() {
		return this.phone;
	}

	public long calculateSalary(Vector<OfflineOrders> orders, Vector<Customers> custumers) {
		salary = countEmpClients(custumers) * signClientBonus;

		return this.salary;
	}

	public double getSalary() {
		return this.salary;
	}

	public double calculateFutureBonus(Vector<Customers> custumers, Vector<Orders> order, Vector<Events> events) {// לא בטוח שנכון הייתי כבר לא מרוכז
		int bonus = 0;
		for (int i = 0; i < custumers.size(); i++) {
			if (this.getId() == custumers.elementAt(i).getRegisteredEmpId()) {
				for (int j = 0; j < order.size(); j++) {
					if (this.getId() == order.elementAt(j).getEventId()) {
						bonus += bonusRate * order.elementAt(j).getOrderPrice(events);
					}
				}

			}
		}
		return bonus;
	}

	public int countEmpClients(Vector<Customers> custumers) {
		int counter = 0;
		for (int i = 0; i < custumers.size(); i++) {
			if (this.getId() == custumers.elementAt(i).getRegisteredEmpId()) {
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
