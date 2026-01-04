import java.util.Map;
import java.util.HashMap;

class Fifteen{
	// find the total number of continuous subarrays whose sum equals K.
	public static void main(String[] args){
		int[] arr = {1,2,1,3};
		int k = 3;
		System.out.println("Total number of continuous Subarray whose sum equals K: " + subarraySum(arr,k));
	}
	public static int subarraySum(int[] arr,int k){
		Map<Integer,Integer> map = new HashMap<>();
		map.put(0,1);

		int prefixSum = 0;
		int count = 0;

		for(int i = 0; i < arr.length; i++){
			prefixSum += arr[i];

			if(map.containsKey(prefixSum - k)){
				count += map.get(prefixSum - k);
			}

			map.put(prefixSum,map.getOrDefault(prefixSum,0) + 1);
			
		}

		return count;
	}
}