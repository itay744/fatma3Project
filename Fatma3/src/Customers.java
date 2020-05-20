
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
		totalOrdersPrice = calculateOrdersPrice(orders, events);
		totalTickets = totalTickets(orders);

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
	
	public int totalTickets() {
		return totalTickets;
	}
    
	private int totalTickets(Vector<Orders> orders) {
		int numOfTickets = 0;
		for (Orders order : orders) {
			if (getId() == order.getCustomerId()) {
				numOfTickets+= order.getValue();// gets the number of tickets from order
			}
		}
		return numOfTickets;
	}

	private double calculateOrdersPrice(Vector<Orders> orders, Vector<Events> events) {
		double totalPrice = 0;
		for (Orders order : orders) {
			if (getId() == order.getCustomerId()) {
				totalPrice += order.getOrderPrice(events);
			}
		}
		return totalPrice;
	}

	public int compareTo(Customers other) {
		return this.totalTickets - other.totalTickets;
	}

}