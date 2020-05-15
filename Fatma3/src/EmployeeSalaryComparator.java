import java.util.Comparator;

public class EmployeeSalaryComparator <T extends Employees> implements Comparator <T> {
	public int compare(T o1, T o2){
	    return (int)(o1.getSalary() - o2.getSalary());
	}

}
