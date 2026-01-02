class Nine{
	public static void main(String[] args){
		int[] arr = {2,3,-2,4};
		System.out.println("Maximum Product of Subarray: " + maxProduct(arr));
		arr = new int[]{-2,-3,-4};
		System.out.println("Maximum Product of Subarray: " + maxProduct(arr));
	}
	static int maxProduct(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int maxSoFar = arr[0];
		int minSoFar = arr[0];
		int result = arr[0];

		for(int i = 1; i < arr.length; i++){
			int current = arr[i];

			if(current < 0){
				int temp = maxSoFar;
				maxSoFar = minSoFar;
				minSoFar = temp;
			}

			maxSoFar = Math.max(current, maxSoFar * current);
			minSoFar = Math.min(current, minSoFar * current);
			result = Math.max(result, maxSoFar);
		}

		return result;
	}
}