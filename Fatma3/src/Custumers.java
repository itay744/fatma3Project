
public class Custumers {
	private int id;
	private String name;
	private int age;
	private char gender;
	private int registeredByEmpId;
	private static final char MALE = 'm';
	private static final char FEMALE = 'f';

	public Custumers(int id, String name, int age, char gender, int empId) {

		this.id = id;
		this.name = name;
		this.age = age;
		if (gender != FEMALE && gender != MALE) {
			throw new WrongGenderInputException();
		}
		this.gender = gender;
		registeredByEmpId = empId;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return new String(name);
	}

	public int getAge() {
		return this.age;
	}

	public int getGender() {
		return this.gender;
	}

	public int getRegisteredEmpId() {
		return this.registeredByEmpId;
	}
}
