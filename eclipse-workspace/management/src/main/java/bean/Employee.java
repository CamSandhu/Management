package bean;

public class Employee {
	private int id;
	private String name;
	private String email;
	private long salary;

	public int getId() {
		return id;
	}

	public Employee(int id, String name, String email, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.salary = salary;
	}

	public Employee(String name, String email, long salary) {
		super();
		this.name = name;
		this.email = email;
		this.salary = salary;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", salary=" + salary + "]";
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

}
