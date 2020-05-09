import java.util.Comparator;

public class EmployeeSalaryComparator implements Comparator {
	public int compare(Object o1, Object o2){
	    return (int)(((Employees)o1).getSalary() - ((Employees)o2).getSalary());
	}

}
