import java.util.Map;
import java.util.HashMap;

class Twelve{
	//Subarray with Sum divisible by K
	public static void main(String[] args){
		int[] arr = {2,7,6,1,4,5};
		int k = 3;
		System.out.println("Longest Subarray Length with sum divisible by K: " + longestSubarrayDivByK(arr,k));
	}
	public static int longestSubarrayDivByK(int[] arr,int k){
		Map<Integer,Integer> map = new HashMap<>();
		map.put(0,-1);

		int prefixSum = 0;
		int maxLen = 0;

		for(int i = 0; i < arr.length; i++){
			prefixSum += arr[i];

			int remainder = ((prefixSum % k) + k) % k;

			if(map.containsKey(remainder)){
				maxLen = Math.max(maxLen,i - map.get(remainder));
			}else{
				map.put(remainder,i);
			}
		}

		return maxLen;
	}
}