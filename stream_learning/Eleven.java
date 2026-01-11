import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
/*
Find Duplicate Elements in a List
*/
class Eleven{
	public static void main(String[] args){
		List<Integer> list = Arrays.asList(10, 20, 20, 30, 40, 40);	
		
		Set<Integer> seen = new HashSet<>();

		Set<Integer> duplicates =
			list.stream()
				.filter( i -> !seen.add(i))
				.collect(Collectors.toSet());

		System.out.println(duplicates);
	}
}