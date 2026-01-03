import java.util.Map;
import java.util.HashMap;

class Eleven{
	//Count Subarray with Sum equal to K
	public static void main(String[] args){
		int[] arr = {1,1,1};
		int k = 2;
		System.out.println("Longest Subarray Length with sum K: " + countSubarray(arr,k));
	}
	public static int countSubarray(int[] arr,int k){
		Map<Integer,Integer> map = new HashMap<>();
		map.put(0,1);

		int sum = 0;
		int count = 0;

		for(int i = 0; i < arr.length; i++){
			sum += arr[i];

			if(map.containsKey(sum-k)){
				count += map.get(sum-k);
			}

			map.put(sum,map.getOrDefault(sum,0)+1);
		}

		return count;
	}
}