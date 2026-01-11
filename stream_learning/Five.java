import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;
/*
How to convert a list of integer into map<integer,integer> so key is the integer value and 
value is the frequency of the occurrence present in the list.
Write this code into java stream api.
*/
class Five{
	public static void main(String[] args){
		List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 3, 4);	

		Map<Integer,Integer> frequencyMap =
			list.stream()
				.collect(Collectors.groupingBy(
					i -> i,
					Collectors.collectingAndThen(
						Collectors.counting(),
						Long::intValue
					)
				));

		System.out.println(frequencyMap);
	}
}