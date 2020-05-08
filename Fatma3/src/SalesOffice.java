
//package read_file;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesOffice {
  public Vector <Customers> custumers;
  public Vector <Events> events;
  public Vector <Orders> orders;

	public SalesOffice(String fileEvents, String fileEmployees, String fileCustomers, String fileTicketsSales) {
		custumers= new Vector<Customers>();
		events= new Vector<Events>();
		orders= new Vector<Orders>();
		readEventsFromFile(fileEvents);
		readCustomersFromFile(fileCustomers);
		readOrdersFromFile(fileTicketsSales);
		
		
	}
	
	private void readCustomersFromFile(String file){ // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			int id;
			String name;
			int age;
			char gender; 
			int registeredByEmpId;
			
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				id = Integer.parseInt(temp[0]);
				name = temp[1];
				age = Integer.parseInt(temp[2]);
				gender = (char)temp[3]; // צריך לשנות שיהיה char
				registeredByEmpId = Integer.parseInt(temp[4]);
				this.custumers.add(new Customers(id, name, age, gender, registeredByEmpId));				
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
	
	
	private void readEventsFromFile(String file){ // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			String name;
			int id;		
			int pricePerTicket;
			
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				name = temp[0];
				id = Integer.parseInt(temp[1]);
				pricePerTicket = Integer.parseInt(temp[2]);
				this.events.add(new Events(name, id, pricePerTicket));				
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
	
	
	private void readOrdersFromFile(String file){ // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			int eventId;
			int soldToId;
			int numberOfTickets;
			int soldBy;
			String URL;
			
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				eventId = Integer.parseInt(temp[0]);
				soldToId = Integer.parseInt(temp[1]);
				numberOfTickets = Integer.parseInt(temp[2]);
				soldBy = Integer.parseInt(temp[3]);
				URL = temp[4];
				this.orders.add(new Orders(eventId,soldToId ,numberOfTickets,soldBy,URL));				
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

}
