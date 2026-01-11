import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Comparator;
/*
How to find second largest number of given list of integer using stream API
*/
class Six{
	public static void main(String[] args){
		List<Integer> list = Arrays.asList(10, 20, 20, 30, 40, 40);	
		// list = Arrays.asList(40, 40);	

		Optional<Integer> secondLargest = 
			list.stream()
				.distinct()
				.sorted(Comparator.reverseOrder())
				.skip(1)
				.findFirst();

		secondLargest.ifPresent(System.out::println);

		list = Arrays.asList(10, 20, 20, 30, 40, 40);	

		secondLargest = 
			list.stream()
				.distinct()
				.sorted(Comparator.reverseOrder())
				.limit(2)
				.min(Integer::compareTo);

		secondLargest.ifPresent(System.out::println);

	}
}