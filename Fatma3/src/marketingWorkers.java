import java.util.Vector;

public class marketingWorkers extends Employees {
	private String phone;
//	private final int signClientBonus = 2;

	public marketingWorkers(int id, String name, int age, String phone) {
		super(id, name, age);
		this.phone = phone;
		this.bonusRate = 0.01;

		// salary = calculateSalary(o, c, e);

	}

	public void addCustomerRateToSalary() {
		this.salary += 2;
	}
}
