import java.util.Vector;

public class marketingWorkers extends Employees {
	private String phone;
	private final int signClientBonus = 2;
	private final double bonusRate = 0.01;

	public marketingWorkers(int id, String name, int Age, String phone, Vector<Orders> o, Vector<Customers> c,
			Vector<Events> e) {
		super(id, name, Age);
		this.phone = phone;
		this.salary = calculateSalary(o, c, e);

	}

	public double calculateSalary(Vector<Orders> orders, Vector<Customers> customers, Vector<Events> events) {
		return (countEmpClients(customers) * signClientBonus + calculateFutureBonus(customers));

	}

	private double calculateFutureBonus(Vector<Customers> customers) {
		int bonus = 0;
		for (int i = 0; i < customers.size(); i++) {
			if (id == customers.elementAt(i).getRegisteredEmpId()) {
				bonus += customers.elementAt(i).getValue();
			}
		}
		return bonus * bonusRate;
	}

	private int countEmpClients(Vector<Customers> customers) {
		int counter = 0;
		for (int i = 0; i < customers.size(); i++) {
			if (id == customers.elementAt(i).getRegisteredEmpId()) {
				counter++;
			}
		}
		return counter;
	}

}
