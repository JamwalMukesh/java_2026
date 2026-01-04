import java.util.Map;
import java.util.HashMap;

class Fourteen{
	// Longest Subarray with Equal 0s and 1s
	public static void main(String[] args){
		int[] arr = {0,1,0,1,1,0,0};
		int k = 3;
		System.out.println("Longest Subarray with Equal 0s and 1s: " + findMaxLength(arr));
	}
	public static int findMaxLength(int[] arr){
		Map<Integer,Integer> map = new HashMap<>();
		map.put(0,-1);

		int prefixSum = 0;
		int maxLen = 0;

		for(int i = 0; i < arr.length; i++){
			prefixSum += arr[i] == 0 ? -1 : 1;

			if(map.containsKey(prefixSum)){
				maxLen = Math.max(maxLen, i - map.get(prefixSum));
			}else{
				map.put(prefixSum,i);
			}
			
		}

		return maxLen;
	}
}