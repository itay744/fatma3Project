import java.util.Vector;


abstract class Employees implements Comparable<Employees>, Valueable {
	protected int id;
	protected String name;
	protected int age;
	protected double salary;

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

	
	public double getValue() {
		return salary;
	}


	public int compareTo(Employees other) {

		return (int) (this.salary - other.salary);
	}

	abstract double calculateSalary(Vector<Orders> orders, Vector<Customers> customers, Vector<Events> events);

}
