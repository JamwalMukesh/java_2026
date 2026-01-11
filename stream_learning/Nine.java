import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;
/*
How to find second largest number of given list of integer using stream API
- Handles Duplicates
- No Sorting
- Case when Second largest does not exist
*/
class Nine{
	public static void main(String[] args){
		List<Integer> list1 = Arrays.asList(10, 20, 20, 30, 40, 40);	
		List<Integer> list2 = Arrays.asList(50);	
		List<Integer> list3 = Arrays.asList(7, 7, 7);	

		System.out.println(findSecondLargest(list1));
		System.out.println(findSecondLargest(list2));
		System.out.println(findSecondLargest(list3));
	}
	public static Optional<Integer> findSecondLargest(List<Integer> list){
		if(list == null && list.size() < 2){
			return Optional.empty();
		}

		int max = 
			list.stream()
				.distinct()
				.max(Integer::compareTo)
				.get();

		return list.stream()
				   .distinct()
				   .filter(n -> n < max)
				   .max(Integer::compareTo);			

	}
}