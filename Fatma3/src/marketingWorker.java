public class marketingWorker extends Employee {
	private String phone;

	public marketingWorker(int id, String name, int age, String phone) {// constructor
		super(id, name, age);
		this.phone = phone;
		this.bonusRate = 0.01;

	}

	public void addCustomerRateToSalary() {// adding 2 shekels to salary for every customer that the employee brought
		this.salary += 2;
	}
}
