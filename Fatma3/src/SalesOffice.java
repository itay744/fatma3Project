import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesOffice {
	private Vector<Customer> customers;
	private Vector<Event> events;
	private Vector<Order> orders;
	private Vector<Employee> employees;

	public SalesOffice(String fileEvents, String fileEmployees, String fileCustomers, String fileTicketsSales) {// constructor
		customers = new Vector<Customer>();// creating vectors for each class
		events = new Vector<Event>();
		orders = new Vector<Order>();
		employees = new Vector<Employee>();
		readFiles(fileEvents, fileEmployees, fileCustomers, fileTicketsSales);

	}

	private void readFiles(String fileEvents, String fileEmployees, String fileCustomers, String fileTicketsSales) {// reads all files
		readEventsFile(fileEvents);
		readEmployeesFile(fileEmployees);
		readCustomersFile(fileCustomers);
		readOrdersFile(fileTicketsSales);

	}

	private void readCustomersFile(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));// create new buffer reader and file reader
			String line;
			line = br.readLine();// reading lines from file
			while ((line = br.readLine()) != null) {// runs until line is null
				String temp[] = line.split("\\t");// splitting string by tab
				int id = Integer.parseInt(temp[0]);
				String name = temp[1];
				int age = Integer.parseInt(temp[2]);
				char gender = temp[3].charAt(0);
				int empId = Integer.parseInt(temp[4]);
				try {
					Customer c = new Customer(id, name, age, gender, empId);// creating new customer
					customers.add(c);// adding him to vector
					marketingWorker mw = (marketingWorker) findEmployeeByID(c.getRegisteredEmpId());// finding the emplyee who wrote the customer
					mw.addCustomerRateToSalary();// adding signing rate to employee
				} catch (WrongGenderInputException e) {// if input gender is illegal catch exception
					System.err.println("Wrong gender input, can be only 'm' or 'f' ");

				}
			}

		} catch (IOException e) {// catching io exception
			e.printStackTrace();
		} finally {
			try {
				br.close();// closing buffer reader
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void readEventsFile(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\\t");
				String name = temp[0];
				int eventId = Integer.parseInt(temp[1]);
				double pricePerTicket = Double.parseDouble(temp[2]);
				try {
					events.add(new Event(name, eventId, pricePerTicket));
				} catch (NegativePriceException e) {//  catching negative price input
					System.err.println("Price cannot be nagative");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void readOrdersFile(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\\t");
				int eventId = Integer.parseInt(temp[0]);
				int customerId = Integer.parseInt(temp[1]);
				int numberOfTickets = Integer.parseInt(temp[3]);
				boolean byPhone = false;

				if (temp.length == 5) {
					String url = temp[4];
					orders.add(new OnlineOrder(eventId, customerId, numberOfTickets, url));
				}
				if (temp.length == 4) {
					int soldByEmpId = Integer.parseInt(temp[2]);
					orders.add(new OfflineOrder(eventId, customerId, numberOfTickets, soldByEmpId));
					byPhone = true;

				}
				sendOrderInfoToRelevantPlaces(byPhone);// sending order info to the relevant objects

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void sendOrderInfoToRelevantPlaces(boolean byPhone) {// sending order info to relevant objects
		Order o = this.orders.elementAt(this.orders.size() - 1); // saving current Order
		Customer c = findCustomerByID(o.getCustomerId());// saving current Customer
		Event e = findEventByID(o.getEventId());// saving current event
		double fullOrderPrice = getOrderPrice(o.getNumberOfTickets(), e); // get order price
		e.addToTotalTickets(o.numberOfTickets);
		o.setOrderPrice(fullOrderPrice); // saving order price to current order object
		if (c != null) {// if customer found
			c.addToTotalTickets(o.getNumberOfTickets()); // add tickets to customer
			c.addToTotalOrdersPrice(fullOrderPrice);// add total price to customer
			if (byPhone) {// if employee is a marketing worker
				Employee emp = findEmployeeByID(((OfflineOrder) o).getSellerId());// finding orders by emp clients
				emp.addOrderToSalary(fullOrderPrice);// adding bonus
			}
				Employee emp = findEmployeeByID(c.getRegisteredEmpId());// find employ that sign the customer
				emp.addOrderToSalary(fullOrderPrice);// adding order to salary
			
		}

	}

	private double getOrderPrice(int numberOfTickets, Event e) { // return order price
		// TODO Auto-generated method stub
		if (e != null) {// if event were found
			return numberOfTickets * e.getPricePerTicket();
		}
		return 0;
	}

	private Customer findCustomerByID(int id) {// find specific customer by id
		for (Customer c : customers) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	private Event findEventByID(int id) {// find specific event by id
		for (Event e : events) {
			if (e.getEventId() == id) {
				return e;
			}
		}
		return null;
	}

	private Employee findEmployeeByID(int id) {// find specific employee by id
		for (Employee e : employees) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}

	private int findCustomerAge(Order o) {// find customer age from specific order
		for (Customer c : customers) {
			if (o.getCustomerId() == c.getId()) {
				return c.getAge();
			}
		}
		return 0;

	}

	private void readEmployeesFile(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\\t");
				int id = Integer.parseInt(temp[0]);
				String name = temp[1];
				int age = Integer.parseInt(temp[2]);
				if (temp.length == 5) {
					String phone = temp[4];
					employees.add(new marketingWorker(id, name, age, phone));
				}
				if (temp.length == 4) {
					double saleRate = Double.parseDouble((temp[3]));
					employees.add(new salesWorker(id, name, age, saleRate));
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void printAgeReport(int eventID) {// calculating and printing age report
		int[] customersAges = customersAgesInEvent(eventID);// count customer ages in event
		int totalCustomers = totalCustomersInEvent(eventID);// count total customer in events
		double[] ages = new double[6];
		for (int i = 0; i < ages.length; i++) {
			ages[i] = ((double) customersAges[i] / totalCustomers) * 100;
		}
		printReport(ages, eventID);
	}

	private void printReport(double[] ages, int event) {// printing customer ages report
		System.out.println("Event name: " + events.elementAt(event-1).getName());
		System.out.println("0-18: " + (int) (ages[0]) + "%");
		System.out.println("18-24: " + (int) (ages[1]) + "%");
		System.out.println("25-35: " + (int) (ages[2]) + "%");
		System.out.println("36-50: " + (int) (ages[3]) + "%");
		System.out.println("51-70: " + (int) (ages[4]) + "%");
		System.out.println("71+: " + (int) (ages[5]) + "%");
	}

	private int[] customersAgesInEvent(int eventId) { // Calculating customer ages in event
		int firstAge = 0, secondAge = 0, thirdAge = 0, fourthAge = 0, fifthAge = 0, sixAge = 0;// age categories
		for (Order o : orders) {
			int customerAge = findCustomerAge(o);// gets the age of the customer who made the order
			if (o.getEventId() == eventId) {

				if (customerAge >= 0 && customerAge <= 17) {// inserting to one if the categories
					firstAge++;
				}
				if (customerAge >= 18 && customerAge <= 24) {
					secondAge++;
				}
				if (customerAge >= 25 && customerAge <= 35) {
					thirdAge++;
				}
				if (customerAge >= 36 && customerAge <= 50) {
					fourthAge++;
				}
				if (customerAge >= 51 && customerAge <= 70) {
					fifthAge++;
				}
				if (customerAge >= 71) {
					sixAge++;
				}

			}

		}
		int[] customersAge = { firstAge, secondAge, thirdAge, fourthAge, fifthAge, sixAge };// all categories
		return customersAge;

	}

	private int totalCustomersInEvent(int eventID) {// count total customer in event
		int sumOfCustumers = 0;
		for (Order o : orders) {
			if (o.getEventId() == eventID) {
				sumOfCustumers++;
			}
		}
		return sumOfCustumers;
	}

	public double getBalance() {// return the balance of the office
		double revenue = 0;
		double expenses = 0;
		double profit = 0;
		for (Order order : orders) {
			revenue += order.getValue();// count all orders value
		}
		for (Employee emp : employees) {
			expenses += emp.getValue();// count all salary expenses
		}
		System.out.println("revenue: " + revenue);
		System.out.println("expenses: " + expenses);// לזכור למחוק את החלק הזה
		profit = revenue - expenses;
		System.out.print("profit: " + profit);

		return profit;
	}

	public void firmReport() {// printing firm report

		System.out.println("SalesOffice report:");
		System.out.println("Employees list:");
		Collections.sort(employees);// sorting with comparable 
		Collections.reverse(employees);// reverse the order
		for (Employee emp : employees) {
			System.out.println("Name: " + emp.getName() + " ; age: " + emp.getAge());
		}
		System.out.println("---------------");
		Collections.sort(events);
		Collections.reverse(events);
		System.out.println("Event list:");
		for (Event event : events) {
			System.out.println("Name: " + event.getName());
		}
		System.out.println("---------------");
		Collections.sort(customers);
		Collections.reverse(customers);
		System.out.println("Customer list:");
		for (Customer customer : customers) {
			System.out.println("Name: " + customer.getName() + " ; age: " + customer.getAge() + " ; Gender: "
					+ customer.getGender());
		}
	}

	public static Comparable<?> getMax(Vector<? extends Comparable> comparable) {// return the maximum value by comparable 
		Comparable<?> max = comparable.elementAt(0);// creating comparable pointer
		for (int i = 1; i < comparable.size(); i++) {// checking for the maximum value
			if (comparable.elementAt(i).compareTo(max) > 0) {
				max = comparable.elementAt(i);
			}
		}
		return max;// return maximum value

	}

	private double countOnlineOrders() {// counting online orders from orders vector
		double counter = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i) instanceof OnlineOrder) {
				counter++;
			}
		}
		return counter;
	}

	private double countOrders() {// return orders size
		return orders.size();

	}

	public double getOnlineProportion() {// return online proportion
		double proportion = countOnlineOrders() / countOrders();// online orders divide by total orders size
		return proportion;
	}

	public static double getAvgValue(Vector<? extends Valueable> value) {// return the average value
		double countElements = 0;
		double totalValue = 0;
		for (int i = 0; i < value.size(); i++) {
			totalValue += value.elementAt(i).getValue();// using valuable interface
			countElements++;// count total elements
		}
		return totalValue / countElements;// total value divide by total elements

	}

	public static void main(String[] args) {
		String fEvents = new String("Events.txt");
		String fEmployees = new String("Employees.txt");
		String fCustomers = new String("Customers.txt");
		String fOrders = new String("Orders.txt");
		SalesOffice s = new SalesOffice(fEvents, fEmployees, fCustomers, fOrders);
	// s.printAgeReport(14);
//System.out.println(s.getBalance());
	s.getOnlineProportion();
//		s.getBalance();
//	 s.firmReport();
//		Comparable a = getMax(s.employees);
//		System.out.println(((Employee)a).getAge());
//		s.firmReport();
//		// System.out.println(s.events.elementAt(0).getName());
//		 Comparable c = getMax(s.orders);
//		 System.out.println(((Orders)c).getEventId());
		System.out.println(s.getOnlineProportion());

//		System.out.println(((Events)c).getTotalTickets());
//		 s.getBalance();
		System.out.println(getAvgValue(s.orders));
//	s.firmReport();

	}

}
