
abstract class Employee implements Comparable<Employee>, Valueable {
	protected int id;
	protected String name;
	protected int age;
	protected double salary;
	protected double bonusRate;

	public Employee(int id, String name, int age) {// constructor
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = 0;
		this.bonusRate = 0;
	}

	public int getId() {// return employee id
		return this.id;
	}

	public String getName() {// return name
		return this.name;
	}

	public int getAge() {// return age
		return this.age;
	}

	public double getValue() {// return employee's salary
		return salary;
	}

	public double getSalary() {
		return salary;
	}

	public int compareTo(Employee other) {// comparable implement, compare by salary

		return (int) (this.salary - other.getSalary());
	}

	public void addOrderToSalary(double orderPrice) {// adding bonus rate for each order made
		this.salary += (orderPrice * this.bonusRate);
	}

}
