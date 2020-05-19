import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesOffice {
	private Vector<Customers> customers;
	private Vector<Events> events;
	private Vector<Orders> orders;
	private Vector<Employees> employees;

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
				try {
					this.events.add(new Events(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), orders));
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
				if (temp.length == 5) {

					this.orders.add(new OnlineOrders(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
							Integer.parseInt(temp[3]), temp[4]));
				} else {
					this.orders.add(new OfflineOrders(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
							Integer.parseInt(temp[3]), Integer.parseInt(temp[2])));

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

	private void readEmployeesFile(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\\t");
				
				if (temp.length == 5) {
					this.employees.add(new marketingWorkers(Integer.parseInt(temp[0]), temp[1],
							Integer.parseInt(temp[2]), temp[4], orders, customers, events));
				} else {
					this.employees.add(new salesWorkers(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
							Double.parseDouble((temp[3])), orders, customers, events));
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
		int customersInEvent = totalCustomersInEvent(eventID);
		double[] ages = new double[6];
		for (int i = 0; i < ages.length; i++) {
			ages[i] = ((double) customersAges[i] / customersInEvent) * 100;
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
				if (orders.elementAt(i).getCustomerAge(customers) > 24
						&& orders.elementAt(i).getCustomerAge(customers) < 36) {
					thirdAge++;
				}
				if (orders.elementAt(i).getCustomerAge(customers) > 35
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
		double expenses = 0;
		for (int i = 0; i < orders.size(); i++) {
			revenue += orders.elementAt(i).getOrderPrice(events);
		}
		for (int i = 0; i < employees.size(); i++) {
			expenses += employees.elementAt(i).getSalary();
		}
		System.out.println("revenue: " + revenue);
		System.out.println("expenses: " + expenses);
		System.out.print("profit: ");

		return revenue - expenses;
	}

	public void firmReport() {

		System.out.println("SalesOffice report:");
		System.out.println("Employees list:");
		Collections.sort(employees);
		Collections.reverse(employees);
		for (int i = 0; i < employees.size(); i++) {
			System.out.println(
					"Name: " + employees.elementAt(i).getName() + " ; age: " + employees.elementAt(i).getAge());
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
			System.out.println("Name: " + customers.elementAt(i).getName() + " ; age: "
					+ customers.elementAt(i).getAge() + " ; Gender: " + customers.elementAt(i).getGender());
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
		return orders.size()+1;
		
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
		s.printAgeReport(1);
		
		
//		Comparable a = getMax(s.employees);
//		s.firmReport();
		//System.out.println(s.events.elementAt(0).getName());
		//Comparable c = getMax(s.events);
     	//System.out.println(((Events)c).getTotalTickets());
//		System.out.println(s.orders.elementAt(144).getEventId());
     	
//		System.out.println(((Events)c).getTotalTickets());
		System.out.println(s.getBalance());
//		System.out.println(getAvgValue(s.orders));
//		s.firmReport();

	}

}
