import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesOffice {
	private Vector<Customer> customers;
	private Vector<Events> events;
	private Vector<Orders> orders;
	private Vector<Employees> employees;

	public SalesOffice(String fileEvents, String fileEmployees, String fileCustomers, String fileTicketsSales) {
		customers = new Vector<Customer>();
		events = new Vector<Events>();
		orders = new Vector<Orders>();
		employees = new Vector<Employees>();
		readFiles(fileEvents, fileEmployees, fileCustomers, fileTicketsSales);

	}

	private void readFiles(String fileEvents, String fileEmployees, String fileCustomers, String fileTicketsSales) {
		readEventsFile(fileEvents);
		readEmployeesFile(fileEmployees);
		readCustomersFile(fileCustomers);
		readOrdersFile(fileTicketsSales);

	}

	private void readCustomersFile(String file) { // reads from the Customers file.
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
				char gender = temp[3].charAt(0);
				int empId = Integer.parseInt(temp[4]);
				try {
					Customer c = new Customer(id, name, age, gender, empId);
					customers.add(c);
					marketingWorkers mw = (marketingWorkers) findEmployeesByID(c.getRegisteredEmpId());
					mw.addCustomerRateToSalary();
				} catch (WrongGenderInputException e) {
					System.err.println("Wrong gender input, can be only 'm' or 'f' ");

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
					events.add(new Events(name, eventId, pricePerTicket));
				} catch (NegativePriceException e) {
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
					orders.add(new OnlineOrders(eventId, customerId, numberOfTickets, url, events, employees,customers));
				}
				if (temp.length == 4) {
					int soldByEmpId = Integer.parseInt(temp[2]);
					orders.add(new OfflineOrders(eventId, customerId, numberOfTickets, soldByEmpId,events, employees,customers));
					byPhone =true;

				}
				sendOrderInfoToRelevantPlaces(byPhone);

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

	private void sendOrderInfoToRelevantPlaces(boolean byPhone) {
		Orders o = this.orders.elementAt(this.orders.size()-1);
		Customer c = findCustomerByID(o.getCustomerId());
		Events e = findEventByID(o.getEventId());
		double fullOrderPrice = getOrderPrice(o.getNumberOfTickets(), e);
		o.setOrderPrice(fullOrderPrice);
		if (c!=null) {
			c.addToTotalTickets(o.getNumberOfTickets());
			c.addToTotalOrdersPrice(fullOrderPrice);
			Employees emp = findEmployeesByID(c.getRegisteredEmpId());
			emp.addOrderToSalary(fullOrderPrice);
		}
		if (byPhone) {
			Employees emp = findEmployeesByID(((OfflineOrders) o).getSellerId());
			emp.addOrderToSalary(fullOrderPrice);
		}
			
	}
	
	private double getOrderPrice(int numberOfTickets, Events e) {
		// TODO Auto-generated method stub
		if (e!=null) {
			return numberOfTickets*e.getPricePerTicket();
		}
		return 0;
	}

	private Customer findCustomerByID(int id) {
		for(Customer c: customers) {
			if (c.getId()==id) {
				return c;
			}
		}
		return null;
	}
	
	private Events findEventByID(int id) {
		for(Events e: events) {
			if (e.getEventId()==id) {
				return e;
			}
		}
		return null;
	}
	
	private Employees findEmployeesByID(int id) {
		for(Employees e: employees) {
			if (e.getId()==id) {
				return e;
			}
		}
		return null;
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
					employees.add(new marketingWorkers(id, name, age, phone,  events));
				}
				if (temp.length == 4) {
					double saleRate = Double.parseDouble((temp[3]));
					employees.add(new salesWorkers(id, name, age, saleRate, events));
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

	public void printAgeReport(int eventID) {
		int[] customersAges = customersAgesInEvent(eventID);
		int totalCustomers = totalCustomersInEvent(eventID);
		double[] ages = new double[6];
		for (int i = 0; i < ages.length; i++) {
			ages[i] = ((double) customersAges[i] / totalCustomers) * 100;
		}
		printReport(ages, eventID);
	}

	private void printReport(double[] ages, int event) {
		System.out.println("Event name:" + events.elementAt(event).getName());
		System.out.println("0-18: " + (int) (ages[0]) + "%");
		System.out.println("18-24: " + (int) (ages[1]) + "%");
		System.out.println("25-35: " + (int) (ages[2]) + "%");
		System.out.println("36-50: " + (int) (ages[3]) + "%");
		System.out.println("51-70: " + (int) (ages[4]) + "%");
		System.out.println("71+: " + (int) (ages[5]) + "%");
	}

	private int[] customersAgesInEvent(int eventId) {
		int firstAge = 0, secondAge = 0, thirdAge = 0, fourthAge = 0, fifthAge = 0, sixAge = 0;
		for (Orders order : orders) {
			int customerAge = order.getCustomerAge(customers);
			if (order.getEventId() == eventId) {

				if (customerAge > 0 && customerAge < 19) {
					firstAge++;
				}
				if (customerAge > 18 && customerAge < 25) {
					secondAge++;
				}
				if (customerAge > 24 && customerAge < 36) {
					thirdAge++;
				}
				if (customerAge > 35 && customerAge < 51) {
					fourthAge++;
				}
				if (customerAge > 50 && customerAge < 71) {
					fifthAge++;
				}
				if (customerAge > 70) {
					sixAge++;
				}

			}

		}
		int[] customersAge = { firstAge, secondAge, thirdAge, fourthAge, fifthAge, sixAge };
		return customersAge;

	}

	private int totalCustomersInEvent(int eventID) {
		int sumOfCustumers = 0;
		for (Orders order : orders) {
			if (order.getEventId() == eventID) {
				sumOfCustumers++;
			}
		}
		return sumOfCustumers;
	}

	public double getOnlineProportion2() {
		int onlineOrders = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (orders.elementAt(i) instanceof OnlineOrders) {
				onlineOrders++;
			}
		}
		return (double) onlineOrders / this.orders.size();
	}

	public double getBalance() {
		double revenue = 0;
		double expenses = 0;
		double profit = 0;
		for (Orders order : orders) {
			revenue += order.getOrderPrice(events);
		}
		for (Employees emp : employees) {
			expenses += emp.getValue();
		}
		System.out.println("revenue: " + revenue);
		System.out.println("expenses: " + expenses);
		profit = revenue - expenses;
		System.out.print("profit: " + profit);

		return revenue - expenses;
	}

	public void firmReport() {

		System.out.println("SalesOffice report:");
		System.out.println("Employees list:");
		Collections.sort(employees);
		Collections.reverse(employees);
		for (Employees emp : employees) {
			System.out.println("Name: " + emp.getName() + " ; age: " + emp.getAge());
		}
		System.out.println("---------------");
		Collections.sort(events);
		Collections.reverse(events);
		System.out.println("Event list:");
		for (Events event : events) {
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

	public static Comparable<?> getMax(Vector<? extends Comparable> comparable) {
		Comparable<?> max = comparable.elementAt(0);
		for (int i = 1; i < comparable.size(); i++) {
			if (comparable.elementAt(i).compareTo(max) > 0) {
				max = comparable.elementAt(i);
			}
		}
		return max;

	}

	private double countOnlineOrders() {
		double counter = 0;
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i) instanceof OnlineOrders) {
				counter++;
			}
		}
		return counter;
	}

	private double countOrders() {
		return orders.size();

	}

	public double getOnlineProportion() {
		double proportion = countOnlineOrders() / countOrders();
		return proportion;
	}

	public static double getAvgValue(Vector<? extends Valueable> value) {
		double countElements = 0;
		double totalValue = 0;
		for (int i = 0; i < value.size(); i++) {
			totalValue += value.elementAt(i).getValue();
			countElements++;
		}
		return totalValue / countElements;

	}

	public static void main(String[] args) {
		String fEvents = new String("Events.txt");
		String fEmployees = new String("Employees.txt");
		String fCustomers = new String("Customers.txt");
		String fOrders = new String("Orders.txt");
		SalesOffice s = new SalesOffice(fEvents, fEmployees, fCustomers, fOrders);
		// s.printAgeReport(1);
//		//System.out.println(s.getBalance());
//		s.getOnlineProportion();
//		s.getBalance();
		// s.firmReport();
//		Comparable a = getMax(s.employees);
//		s.firmReport();
//		// System.out.println(s.events.elementAt(0).getName());
//		 Comparable c = getMax(s.orders);
//		 System.out.println(((Orders)c).getEventId());
//		System.out.println(s.orders.elementAt(144).getEventId());

//		System.out.println(((Events)c).getTotalTickets());
		// s.getBalance();
		System.out.println(getAvgValue(s.employees));
//	s.firmReport();

	}

}
