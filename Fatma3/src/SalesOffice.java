import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// הוספתי את הבדיקת שגיאה בבנאי 
//סידרתי את השגיאות וקיצרתי קצת את הבנאים של הוקטורים
//  עשיתי את השיטה של - בעזרת שתי פונקציות עזר (פרייבט) צריך לבדוק את הארוכה מבינהם אני לא סגורה עליה 100 printAgeReport  
// סידרתי את הפונקציית קלס, תסתכל עליה.
//הוספתי קלאס מיין כדי שנוכל לבדוק את עצמנו 
public class SalesOffice {
	public Vector<Customers> custumers;
	public Vector<Events> events;
	public Vector<Orders> orders;
	public Vector<Employees> employees;

	public SalesOffice(String fileEvents, String fileEmployees, String fileCustumers, String fileTicketsSales) {
		custumers = new Vector<Customers>();
		events = new Vector<Events>();
		orders = new Vector<Orders>();
		employees = new Vector <Employees>();
		try {
			readEventsFromFile(fileEvents);
		} catch (NegativePriceException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
		readEmployeesFromFile(fileEmployees);
		try {
			readCustumersFromFile(fileCustumers);
		} catch (WrongGenderInputException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		readOrdersFromFile(fileTicketsSales);
		

	}

	private void readCustumersFromFile(String file) throws WrongGenderInputException{ // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				this.custumers.add(new Customers(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),temp[3].charAt(0),Integer.parseInt(temp[4]),this.orders,this.events));
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

	private void readEventsFromFile(String file) throws NegativePriceException{ // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file + ".txt"));
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				this.events.add(new Events(temp[0],Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),this.orders));
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
			line = br.readLine();
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
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String temp[] = line.split("\t");
				;
				if (temp[3] == null) {
					this.employees.add(new MarketingWorkers(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]), Integer.parseInt(temp[4])));
				}
				if (temp[4] == null) {
					this.employees.add(new SalesWorkers(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),Integer.parseInt(temp[3])));
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
	    int [] custumerPerAge = custumersPerAgeToEvent(eventID);
	    int custumerToEvent = sumOfCustumerToEvent(eventID);
		System.out.println("Event name:");
		System.out.println("0-18: " + custumerPerAge[0]/custumerToEvent*100 );
		System.out.println("18-24: " + custumerPerAge[1]/custumerToEvent*100);
		System.out.println("25-35: " + custumerPerAge[2]/custumerToEvent*100);
		System.out.println("36-50: " + custumerPerAge[3]/custumerToEvent*100);
		System.out.println("51-70: " + custumerPerAge[4]/custumerToEvent*100);
		System.out.println("71+: "+ custumerPerAge[5]/custumerToEvent*100);

	}
	
	private int[] custumersPerAgeToEvent(int eventID) {
		int firstAge = 0, secondAge = 0, thirdAge = 0, fourthAge = 0, fifthAge = 0, sixAge = 0;
		int totalCustumers = 0;
		for (int i = 0; i< this.orders.size(); i++) {
			if(this.orders.elementAt(i).getEventId()== eventID) {
				for(int j=0; j< this.custumers.size(); i++) {
				    if(this.orders.elementAt(i).getSoldToId()== custumers.elementAt(j).getId()) {
					
					    if(custumers.elementAt(j).getAge()>= 0 && custumers.elementAt(j).getAge()<19) {
						   firstAge++;
					    }
					    if(custumers.elementAt(j).getAge()>= 19 && custumers.elementAt(j).getAge()<25) {
						   firstAge++;
					    }
					    if(custumers.elementAt(j).getAge()>= 25 && custumers.elementAt(j).getAge()<36) {
						   firstAge++;
					    }
					    if(custumers.elementAt(j).getAge()>= 36 && custumers.elementAt(j).getAge()<51) {
						   firstAge++;
					    }
					    if(custumers.elementAt(j).getAge()>= 51 && custumers.elementAt(j).getAge()<71) {
						   firstAge++;
					    }
					    if(custumers.elementAt(j).getAge()>= 71) {
						   firstAge++;
					    }
				    }
				}
			}
			
		}
		int [] ageCustumers = {firstAge, secondAge, thirdAge, fourthAge, fifthAge , sixAge};
		return ageCustumers;		
		
	}
	
	private int sumOfCustumerToEvent(int eventID) {
		int sumOfCustumers = 0;
		for (int i = 0; i< this.events.size(); i++) {
			if(this.orders.elementAt(i).getEventId()== eventID) {
				sumOfCustumers++;
			}
		}
		return sumOfCustumers;
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
	
	public static Comparable getMax(Vector<Comparable> comparable) {
		Comparable max = comparable.elementAt(0);
		for (int i = 1; i< comparable.size(); i++) {
			if(comparable.elementAt(i).compareTo(max)>0){
				max = comparable.elementAt(i);
			}
		}
		return max;
		
		
	}
	

	

}
