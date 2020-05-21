import java.util.Vector;

abstract class Employees implements Comparable<Employees>, Valueable {
	protected int id;
	protected String name;
	protected int age;
	protected double salary;
	protected double bonusRate;

	public Employees(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = 0;
		this.bonusRate = 0;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public double getValue() {
		return salary;
	}
	
	public double getSalary() {
		return salary;
	}

	public int compareTo(Employees other) {

		return (int) (this.salary - other.getSalary());
	}

	public void addOrderToSalary(double orderPrice) {
		this.salary += (orderPrice * this.bonusRate);
	}


}
