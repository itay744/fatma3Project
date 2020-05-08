
abstract class Employees {
protected int ID; 
protected String Name;
protected int	Age;


protected Employees(int ID, String Name, int Age) {
	this.ID = ID; 
	this.Name = Name;
	this.Age = Age;

}

public int getID() {
	return ID;
}

public String getName() {
	return Name;
}

public String getAge() {
	return Name;
}

abstract int calculateSalary(Customers customer);

abstract int calculatebonus(Customers customer);

//abstract long calculateSales();
}


