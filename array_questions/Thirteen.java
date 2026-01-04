import java.util.Map;
import java.util.HashMap;

class Thirteen{
	//Subarray with Sum divisible by K
	public static void main(String[] args){
		int[] arr = {2,7,6,1,4,5}; // 2 + 7, 2 + 7 + 6, 6, 2 + 7 + 6 + 1 + 4, 4 + 5
		int k = 3;
		System.out.println("Count Subarray Length with sum divisible by K: " + countSubarrayDivByK(arr,k));
	}
	public static int countSubarrayDivByK(int[] arr,int k){
		Map<Integer,Integer> map = new HashMap<>();
		map.put(0,1);

		int prefixSum = 0;
		int count = 0;

		for(int i = 0; i < arr.length; i++){
			prefixSum += arr[i];

			int remainder = ((prefixSum % k) + k) % k;

			if(map.containsKey(remainder)){
				count += map.get(remainder);
			}
			map.put(remainder,map.getOrDefault(remainder,0)+1);
		}

		return count;
	}
}