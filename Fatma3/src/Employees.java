import java.util.Vector;

abstract class Employees implements Comparable <Employees> {
	private int id;
	private String name;
	private int age;
	private double salary;

	public Employees(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary=0;

	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return new String(name);
	}

	public int getAge() {
		return this.age;
	}

	public double getSalary() {
		return salary;
	}
	
	public double setSalary(double num) {
		return salary +num ;
	}
	
	abstract public void calculateSalary(Vector<OfflineOrders> orders,Vector <Custumers> custumers,Vector<Events> events);


}
