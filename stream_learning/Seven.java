import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Comparator;
/*
How to find second largest number of given list of integer using stream API
Without using Sorted method
*/
class Seven{
	public static void main(String[] args){
		List<Integer> list = Arrays.asList(10, 20, 20, 30, 40, 40);	
		// list = Arrays.asList(40, 40);	

		Optional<Integer> secondLargest = 
			list.stream()
				.distinct()
				.reduce((a,b) -> a > b ? a : b)
				.flatMap(max -> 
					list.stream()	
						.filter(n -> n < max)
						.max(Integer::compareTo)
				);

		secondLargest.ifPresent(System.out::println);

	}
}