class Third{
	public static int findSingle(int[] arr){
		int xor = 0;

		for(int num : arr){
			xor ^= num;
		}

		return xor;
	}
	public static void main(String[] args){
		int[] arr = {4, 1, 2, 1, 2};
		System.out.println("Single element is: " + findSingle(arr));
	}
}