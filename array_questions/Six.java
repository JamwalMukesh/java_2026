class Six{
	public static void main(String[] args){
		int[] arr = {2,2, 3, 2};
		System.out.println("Single number: " + singleNumber(arr));
		System.out.println("Single number: " + singleNumberOther(arr));
	}
	public static int singleNumber(int[] arr){
		int result = 0;

		for(int bit = 0; bit < 32; bit++){
			int count = 0;

			for(int num:arr){
				if((num >> bit & 1) == 1){
					count++;
				}
			}

			if(count % 3 != 0){
				result |= (1 << bit);
			}
		}
		return result;
	}
	public static int singleNumberOther(int[] arr){
		int ones = 0, twos = 0;

		for(int num:arr){
			// Update ones and twos using bit manipulation
			ones = (ones ^ num) & ~twos;
			twos = (twos ^ num) & ~ones;
		}

		// ones contains the unique number
		return ones;
	}
	
}