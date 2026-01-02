class Seven{
	public static void main(String[] args){
		int[] arr = {2,2,1,1,1,2,2};
		System.out.println("Majority Element: " + majorityElement(arr));
	}
	static Integer majorityElement(int[] arr){
		int candidate = 0;
		int count = 0;

		for(int num:arr){
			if(count == 0){
				candidate = num;
			}
			count += ( num == candidate ) ? 1 : -1;
		}

		count = 0;
		for(int num:arr){
			if(num==candidate){
				count++;
			}
		}

		return count > arr.length / 2 ? candidate : null;
	}
}