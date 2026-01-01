import java.util.Set;
import java.util.HashSet;

class Four{
	public static void main(String[] args){
		int[] arr = {100, 4, 200, 3, 2, 1};
		System.out.println("Longest Consecutive length: " + longestConsecutive(arr));
	}
	public static int longestConsecutive(int[] arr){
		if(arr == null || arr.length == 0 ){
			return 0;
		}

		Set<Integer> set = new HashSet<>();
		for(int num:arr){
			set.add(num);
		}

		int longest = 0;

		for(int num:set){
			if(!set.contains(num-1)){
				int currentNum = num;
				int currentStreak = 1;

				while(set.contains(currentNum+1)){
					currentNum++;
					currentStreak++;
				}
				longest = Math.max(longest,currentStreak);
			}
		}

		return longest;
	}
}