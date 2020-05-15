
import java.util.Vector;

public class Customers implements Comparable<Customers>, Valueable {
	private int id;
	private String name;
	private int age;
	private char gender;
	private int registeredByEmpId;
	private int totalTickets;
	private double totalOrdersPrice;
	private static final char MALE = 'm';
	private static final char FEMALE = 'f';

	public Customers(int id, String name, int age, char gender, int empId, Vector<Orders> orders, Vector<Events> events)
			throws WrongGenderInputException {

		this.id = id;
		this.name = name;
		this.age = age;
		if (gender != FEMALE && gender != MALE) {
			throw new WrongGenderInputException();
		}
		this.gender = gender;
		registeredByEmpId = empId;
		totalOrdersPrice = calculatePriceForOrders(orders, events);
		totalTickets = countTickets(orders);
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

	public char getGender() {
		return gender;
	}

	public int getRegisteredEmpId() {
		return registeredByEmpId;
	}

	public double getValue() {
		return totalOrdersPrice;
	}

	public int getTickets() {
		return totalTickets;
	}

	public double calculatePriceForOrders(Vector<Orders> orders, Vector<Events> events) {
		double totalPrice = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (this.getId() == orders.elementAt(i).getCustomerId()) {
				totalPrice += orders.elementAt(i).getOrderPrice(events);
			}
		}
		return totalPrice;
	}

	public int countTickets(Vector<Orders> orders) {
		int counter = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (this.getId() == (orders.elementAt(i).getCustomerId())) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public int compareTo(Customers other) {
		return this.totalTickets - other.totalTickets;
	}

}