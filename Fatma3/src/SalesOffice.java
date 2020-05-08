
public class SalesOffice {
	Custumers custumer;
	Events event;
	Orders order;

	public SalesOffice(String fileEvents, String fileEmployees, String fileCustomers, String fileTicketsSales) {

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
	 
	 

}
