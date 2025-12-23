import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
class First
{
	public static void main(String[] args)
	{
		List<Integer> numbers = Arrays.asList(9,2,5,4,8,1,6);

		List<Integer> result = numbers.stream()
								.filter( n -> n % 2 == 0 )
								.map( n -> n * n)
								.sorted()
								.collect(Collectors.toList());

		System.out.println(numbers);
		System.out.println(result);
	}
}