
public class Customer implements Comparable<Customer>, Valueable {
	private int id;
	private String name;
	private int age;
	private char gender;
	private int registeredByEmpId;
	private int totalTickets;
	private double totalOrdersPrice;
	private static final char MALE = 'm';
	private static final char FEMALE = 'f';

	public Customer(int id, String name, int age, char gender, int empId) throws WrongGenderInputException {// constructor

		this.id = id;
		this.name = name;
		this.age = age;
		if (gender != FEMALE && gender != MALE) {
			throw new WrongGenderInputException();
		}
		this.gender = gender;
		registeredByEmpId = empId;
		totalTickets = 0;
		totalOrdersPrice = 0;
	}

	public int getId() {// return id
		return this.id;
	}

	public String getName() {// return name
		return this.name;
	}

	public int getAge() {// return age
		return age;
	}

	public char getGender() {// return gender
		return gender;
	}

	public int getRegisteredEmpId() {// return the employ that register customer
		return registeredByEmpId;
	}

	public double getValue() {// valuable implement , return total order price
		return totalOrdersPrice;
	}

	public int getTotalTickets() {// return total tickets bought
		return totalTickets;
	}

	public void addToTotalTickets(int amount) {// adding tickets to total tickets
		this.totalTickets += amount;
	}

	public void addToTotalOrdersPrice(double orderPrice) {// adding order price to total amount
		this.totalOrdersPrice += orderPrice;
	}

	public int compareTo(Customer other) {// comparable implement compare by total tickets 
		return this.totalTickets - other.totalTickets;
	}

}