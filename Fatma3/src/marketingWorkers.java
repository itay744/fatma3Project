import java.util.Vector;

public class marketingWorkers extends Employees {
	private String phone;
	final int signClientBonus = 2;
	final double bonusRate = 0.01;

	public marketingWorkers(int id, String name, int Age, String phone, Vector<Orders> o, Vector<Customers> c,
			Vector<Events> e) {
		super(id, name, Age);
		this.phone = phone;
		calculateSalary(o, c, e);

	}

	public String getPhone() {
		return this.phone;
	}

	public void calculateSalary(Vector<Orders> orders, Vector<Customers> customers, Vector<Events> events) {
		this.setSalary(countEmpClients(customers) * 2 + calculateFutureBonus(customers));

	}

	public double calculateFutureBonus(Vector<Customers> customers) {
		int bonus = 0;
		for (int i = 0; i < customers.size(); i++) {
			if (this.getId() == customers.elementAt(i).getRegisteredEmpId()) {
				bonus += customers.elementAt(i).getValue();
			}
		}
		return bonus * 0.1;
	}

	public int countEmpClients(Vector<Customers> customers) {
		int counter = 0;
		for (int i = 0; i < customers.size(); i++) {
			if (this.getId() == customers.elementAt(i).getRegisteredEmpId()) {
				counter++;
			}
		}
		return counter;
	}

}
