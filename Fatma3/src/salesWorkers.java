
public class salesWorkers extends Employees{
    private long BonusRateForSale;	
    private int Salary;
	
	
	public salesWorkers(int ID, String Name, int Age) {
		super(ID, Name, Age);
		this.Salary = 0;
		// BonusRateForSale // לקבל מהקובץ נתונים
	}
	
	public long getBonusRateForSale() {
		return BonusRateForSale;
	}

	
	public int calculateSalary(Customers customer) {
		
		return Salary = Salary + calculatebonus(customer);
	}
    
	
	public int calculatebonus(Customers customer) {
			
		return 0;
	}
	
	public int getSalary() {
		return Salary;
	}
	
	
	
	

	

	
	

}
