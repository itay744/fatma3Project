import java.util.Vector;

abstract class Employees implements Comparable<Employees>, Valueable {
	private int id;
	private String name;
	private int age;
	private double salary;

	public Employees(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = 0;

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return new String(name);
	}

	public int getAge() {
		return age;
	}

//	public double getSalary() {
//		return salary;
//	}

	public double setSalary(double amount) {
		salary += amount;
		return salary;
	}

	public double getValue() {
		return salary;
	}

	public int compareTo(Employees other) {

		return (int) (this.salary - other.salary);
	}

	abstract public void calculateSalary(Vector<Orders> orders, Vector<Customers> customers, Vector<Events> events);

}
