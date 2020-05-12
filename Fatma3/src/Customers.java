import java.util.Vector;

public class Customers implements Comparable<Customers>{
	private int id;
	private String name;
	private int age;
	private char gender;
	private int registeredByEmpId;
	private double totalAmountOrders;
	private static final char MALE = 'm';
	private static final char FEMALE = 'f';

	public Customers(int id, String name, int age, char gender, int empId,Vector <Orders> orders,Vector <Events> events) throws WrongGenderInputException  {

		this.id = id;
		this.name = name;
		this.age = age;
		if (gender != FEMALE && gender != MALE) {
			throw new WrongGenderInputException();
		}
		this.gender = gender;
		registeredByEmpId = empId;
		totalAmountOrders = getAmountForAllOrders(orders,events);
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

	public int getGender() {
		return this.gender;
	}

	public int getRegisteredEmpId() {
		return this.registeredByEmpId;
	}
	
	public double getAmountForAllOrders(Vector <Orders> orders,Vector <Events> events) {
		double totalPrice = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (this.getId() == orders.elementAt(i).getSoldToId()) {
				for (int j = 0; j < events.size(); j++) {
					if(orders.elementAt(i).getEventId() ==  events.elementAt(j).getId()) {
						totalPrice = totalPrice + orders.elementAt(i).getOrderPrice(events)* orders.elementAt(i).getNumberOfTickets();
					}
				}
				
			}
		}
		return totalPrice;
	}
	
	public int getTickets(Vector <Orders> orders) {
		int counter=0;
		 for (int i = 0 ; i < orders.size() ; i++) {
	            if (this.getId() == (orders.elementAt(i).getSoldToId()) ) {
	             counter++;
	            }
	          }
		return counter;
	}


	@Override
	public int compareTo(Customers o) {
	
		return (int) (this.totalAmountOrders - o.getTotalAmountOrders());
	}

	public double getTotalAmountOrders() {
		return totalAmountOrders;
	}


}
