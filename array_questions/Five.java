class Five{
	public static void main(String[] args){
		int[] arr = {3, 0, 1};
		System.out.println("Missing number: " + missingNumber(arr));
	}
	public static int missingNumber(int[] arr){
		int n = arr.length;
		int xor = 0;

		for(int i = 0; i <= n; i++){
			xor ^= i;
		}

		for(int num:arr){
			xor ^= num;
		}

		return xor;
	}
	
}