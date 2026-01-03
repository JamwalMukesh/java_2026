import java.util.Map;
import java.util.HashMap;

class Ten{
	//Longest Subarray Sum K
	public static void main(String[] args){
		int[] arr = {1,-1,5,-2,3};
		int k = 3;
		System.out.println("Longest Subarray Length with sum K: " + longestSubarray(arr,k));
	}
	public static int longestSubarray(int[] arr,int k){
		Map<Integer,Integer> map = new HashMap<>();
		map.put(0,-1);

		int sum = 0;
		int maxLength = 0;

		for(int i = 0; i < arr.length; i++){
			sum += arr[i];

			if(map.containsKey(sum-k)){
				maxLength = Math.max(maxLength, i - map.get(sum-k));
			}

			map.putIfAbsent(sum,i);
		}

		return maxLength;
	}
}