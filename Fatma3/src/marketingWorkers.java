
public class marketingWorkers extends Employees {
	private int phone;
	private int salary;
	final int signClientBonus = 2;
	final double bonusRate = 0.01;

	public marketingWorkers(int id, String name, int Age, int phone) {
		super(id, name, Age);
		this.phone = phone;
		this.salary = 0;
	}

	public int getPhone() {
		return this.phone;
	}

	public int calculateSalary(Custumers custumer) {
		return this.salary += calculatebonus(custumer);
	}

	public int calculatebonus(Custumers custumer) {
		return 0;
	}

}
