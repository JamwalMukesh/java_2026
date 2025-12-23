import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
class Second
{
	public static void main(String[] args)
	{
		List<Employee> employees = Arrays.asList(
			new Employee("Alice","Engineering",100000),
			new Employee("Bob","HR",60000),
			new Employee("Charlie","Engineering",120000),
			new Employee("David","HR",65000)
		);

		Map<String,Double> avgSalaryByDept = employees.stream()
												.collect(Collectors.groupingBy(
													Employee::getDepartment,
													Collectors.averagingDouble(Employee::getSalary)
												));
		System.out.println(avgSalaryByDept);
	}
}