
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
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	abstract int calculateSalary(Custumers custumer);

	abstract int calculatebonus(Custumers custumer);

//abstract long calculateSales();
}
