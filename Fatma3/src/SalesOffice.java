import java.util.Collections;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesOffice {
	public Vector<Custumers> custumers;
	public Vector<Events> events;
	public Vector<Orders> orders;
	public Vector<Employees> employees;

	public SalesOffice(String fileEvents, String fileEmployees, String fileCustumers, String fileTicketsSales) {
		custumers = new Vector<Custumers>();
		events = new Vector<Events>();
		orders = new Vector<Orders>();
		employees = new Vector <Employees>();
		readEventsFromFile(fileEvents);
		readCustumersFromFile(fileCustumers);
		readOrdersFromFile(fileTicketsSales);
		readEmployeesFromFile(fileEmployees);

	}

	private void readCustumersFromFile(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				this.custumers.add(new Custumers(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),temp[3].charAt(0),Integer.parseInt(temp[4])));
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

	private void readEventsFromFile(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				this.events.add(new Events(temp[0],Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
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

	private void readOrdersFromFile(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				String URL = temp[4];
				if (URL == null) {
					this.orders.add(new OfflineOrders(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),Integer.parseInt(temp[3])));
				}
				if (temp[3] == null) {
					this.orders.add(new OnlineOrders(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),temp[4]));
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

	private void readEmployeesFromFile(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				;
				if (temp[3] == null) {
					this.employees.add(new marketingWorkers(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]), Integer.parseInt(temp[4])));
				}
				if (temp[4] == null) {
					this.employees.add(new salesWorkers(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),Integer.parseInt(temp[3])));
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
		System.out.println("Event name:");
		System.out.println("0-18: ");
		System.out.println("18-24: ");
		System.out.println("25-35: ");
		System.out.println("36-50: ");
		System.out.println("51-70: ");
		System.out.println("71+: ");

	}

	public double getOnlineProportion() {
		return 0;
	}

	public double getBalance() {
		return 0;
	}

	public void firmReport() {

		System.out.println("SalesOffice report:");
		System.out.println("Employees list:");
		System.out.println("Event list:");
		System.out.println("Customer list:");
	}

	public static double getAvgValue() {
		return 0;
	}
	
	public int countEmpSales (int SellerId, Vector offlineOrders ) {
		
		return 0;
	}
	
	public static Comparable getMax(Vector v) {
		Collections.sort(v, new EmployeeSalaryComparator());
		return 0;
		
	}
	
	


}
