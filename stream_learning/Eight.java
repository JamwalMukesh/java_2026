import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;
/*
How to find second largest number of given list of integer using stream API
*/
class Eight{
	public static void main(String[] args){
		List<Integer> list = Arrays.asList(10, 20, 20, 30, 40, 40);	

		Optional<Integer> secondLargest = 
			list.stream()
				.distinct()
				.collect(
					Collectors.collectingAndThen(
						Collectors.toList(),
						l -> l.stream()
							  .sorted(Comparator.reverseOrder())
							  .skip(1)
							  .findFirst()
					)
				);

		secondLargest.ifPresent(System.out::println);

	}
}