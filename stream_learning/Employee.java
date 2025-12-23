class Employee
{
	private String name;
	private String department;
	private double salary;

	Employee()
	{

	}
	Employee(String name,String department,double salary)
	{
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public double getSalary()
	{
		return this.salary;
	}

	public String getName()
	{
		return this.name;
	}

	public String getDepartment()
	{
		return this.department;
	}
}