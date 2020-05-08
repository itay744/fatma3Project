
public class marketingWorkers extends Employees {
	private int Phone;  
	private int Salary;
	
    protected marketingWorkers(int ID, String Name, int Age, int Phone) {
		super(ID, Name, Age);
		this.Phone = Phone;
		this.Salary = 0;
	}



public int getPhone() {
	return Phone;
}

public int calculateSalary(Customers customer) {
	return this.Salary = this.Salary + calculatebonus(customer); 
}

public int calculatebonus(Customers customer) {
	final int clientBonus = 2;
	final double bonus = 0.01;
	return 0;
}
	

}
