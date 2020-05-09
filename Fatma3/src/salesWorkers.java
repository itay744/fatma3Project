
public class salesWorkers extends Employees {
	private long bonusRate;
	private int salary;

	public salesWorkers(int id, String Name, int Age, int bonusRate) {
		super(id, Name, Age);
		this.salary = 0;
		this.bonusRate = bonusRate;
		
	}

	public long getBonusRateForSale() {
		return bonusRate;
	}

	public int calculateSalary(Custumers custumer) {

		return salary += calculatebonus(custumer);
	}

	public int calculatebonus(Custumers custumer) {

		return 0;
	}

	public int getSalary() {

		return this.salary;
	}

	public int countSales() {
		int counter = 0;
		return counter;
	}

}
