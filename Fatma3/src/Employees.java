import java.util.Vector;

abstract class Employees {
	private int id;
	private String name;
	private int age;

	public Employees(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;

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
	
	abstract public long getSalary();

	abstract long calculateSalary(Vector <OfflineOrders> orders,Vector <Custumers> custumers);

//	abstract int calculatebonus(Custumers custumer);

//abstract long calculateSales();
}
