import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesOffice {
	public Vector<Customers> customers;
	public Vector<Events> events;
	public Vector<Orders> orders;
	public Vector<Employees> employees;

	public SalesOffice(String fileEvents, String fileEmployees, String fileCustomers, String fileTicketsSales) {
		customers = new Vector<Customers>();
		events = new Vector<Events>();
		orders = new Vector<Orders>();
		employees = new Vector<Employees>();
		readOrdersFile(fileTicketsSales);
		readEventsFile(fileEvents);
		readCustomersFile(fileCustomers);
		readEmployeesFile(fileEmployees);

	}

	private void readCustomersFile(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\\t");
				try {
					this.customers.add(new Customers(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
							temp[3].charAt(0), Integer.parseInt(temp[4]), orders, events));
				} catch (WrongGenderInputException e) {
					System.err.println("Wrong gender input, can be only m or f");
					customers.remove(customers.lastElement());
				}
			}
			customers.remove(customers.firstElement());// removing the titles string from the vector

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
				try {
					this.events.add(new Events(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), orders));
				} catch (NegativePriceException e) {
					System.err.println("Price cannot be nagative");
					events.remove(events.lastElement());
				}
			}
			events.remove(events.firstElement());// removing the titles string from the vector

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
				if (temp.length == 5) {

					this.orders.add(new OnlineOrders(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
							Integer.parseInt(temp[3]), temp[4]));
				} else {
					this.orders.add(new OfflineOrders(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
							Integer.parseInt(temp[3]), Integer.parseInt(temp[2])));

				}

			}
			orders.remove(orders.firstElement());// remove titles from vector

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

	private void readEmployeesFile(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\\t");
				;
				if (temp.length == 5) {
					this.employees.add(new marketingWorkers(Integer.parseInt(temp[0]), temp[1],
							Integer.parseInt(temp[2]), temp[4], orders, customers, events));
				} else {
					this.employees.add(new salesWorkers(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
							Double.parseDouble((temp[3])), orders, customers, events));
				}

			}
			employees.remove(employees.firstElement());// remove titles from vector

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
		int[] customerPerAge = customersAgesInEvent(eventID);
		int customerInEvent = totalCustomersInEvent(eventID);
		double[] ages = new double[6];
		for (int i = 0; i < ages.length; i++) {
			ages[i] = ((double) customerPerAge[i] / customerInEvent) * 100;
		}
		printReport(ages);
	}

	private void printReport(double[] ages) {
		System.out.println("Event name:");
		System.out.println("0-18: " + (int) (ages[0]) + "%");
		System.out.println("18-24: " + (int) (ages[1]) + "%");
		System.out.println("25-35: " + (int) (ages[2]) + "%");
		System.out.println("36-50: " + (int) (ages[3]) + "%");
		System.out.println("51-70: " + (int) (ages[4]) + "%");
		System.out.println("71+: " + (int) (ages[5]) + "%");
	}

	private int[] customersAgesInEvent(int eventID) {
		int firstAge = 0, secondAge = 0, thirdAge = 0, fourthAge = 0, fifthAge = 0, sixAge = 0;
		for (int i = 0; i < this.orders.size(); i++) {
			if (this.orders.elementAt(i).getEventId() == eventID) {

				if (orders.elementAt(i).getCustomerAge(customers) > 0
						&& orders.elementAt(i).getCustomerAge(customers) < 19) {
					firstAge++;
				}
				if (orders.elementAt(i).getCustomerAge(customers) > 18
						&& orders.elementAt(i).getCustomerAge(customers) < 25) {
					secondAge++;
				}
				if (orders.elementAt(i).getCustomerAge(customers) > 23
						&& orders.elementAt(i).getCustomerAge(customers) < 36) {
					thirdAge++;
				}
				if (orders.elementAt(i).getCustomerAge(customers) > 34
						&& orders.elementAt(i).getCustomerAge(customers) < 51) {
					fourthAge++;
				}
				if (orders.elementAt(i).getCustomerAge(customers) > 50
						&& orders.elementAt(i).getCustomerAge(customers) < 71) {
					fifthAge++;
				}
				if (orders.elementAt(i).getCustomerAge(customers) > 70) {
					sixAge++;
				}

			}

		}
		int[] customersAge = { firstAge, secondAge, thirdAge, fourthAge, fifthAge, sixAge };
		return customersAge;

	}

	private int totalCustomersInEvent(int eventID) {
		int sumOfCustumers = 0;
		for (int i = 0; i < this.orders.size(); i++) {
			if (this.orders.elementAt(i).getEventId() == eventID) {
				sumOfCustumers++;
			}
		}
		return sumOfCustumers;
	}

	public double getOnlineProportion2() {
		int onlineOrders = 0;
		for (int i = 0; i < this.orders.size(); i++) {
			if (this.orders.elementAt(i) instanceof OnlineOrders) {
				onlineOrders++;
			}
		}
		return (double) onlineOrders / this.orders.size();
	}

	public double getBalance() {
		double revenue = 0;
		int expenses = 0;
		for (int i = 0; i < orders.size(); i++) {
			revenue += orders.elementAt(i).getOrderPrice(events);
		}
		for (int i = 0; i < employees.size(); i++) {
			expenses += employees.elementAt(i).getSalary();
		}

		return revenue - expenses;
	}

	public void firmReport() {

		System.out.println("SalesOffice report:");
		System.out.println("Employees list:");
		Collections.sort(employees);
		Collections.reverse(employees);
		for (int i = 0; i < employees.size(); i++) {
			System.out.println("Name: " + employees.elementAt(i).getName() + " ; age: " + employees.elementAt(i).getAge());
		}
		System.out.println("---------------");
		Collections.sort(events);
		Collections.reverse(events);
		System.out.println("Event list:");
		for (int i = 0; i < events.size(); i++) {
			System.out.println("Name: " + events.elementAt(i).getName());
		}
		System.out.println("---------------");
		Collections.sort(customers);
		Collections.reverse(customers);
		System.out.println("Customer list:");
		for (int i = 0; i < customers.size(); i++) {
			System.out.println("Name: " + customers.elementAt(i).getName() + " ; age: " + customers.elementAt(i).getAge()
					+ " ; Gender: " + customers.elementAt(i).getGender());
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
		double counter = 0;
		for (int i = 0; i < orders.size(); i++) {
			counter++;

		}
		return counter;
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
//
//		Comparable a = getMax(s.employees);
//		System.out.println(((Employees) a).getSalary());
		// double salary = s.employees.elementAt(3).calculateSalary(s.orders,
		// s.customers, s.events);
		s.firmReport();

	}

}
