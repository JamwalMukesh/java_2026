class First{
	public static Integer findSecondLargest(int[] arr){
		if( arr == null || arr.length < 2){
			return null;
		}
		int largest = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;

		for(int num : arr){
			if(num > largest){
				secondLargest = largest;
				largest = num;
			}else if(num > secondLargest && num != largest){
				secondLargest = num;
			}
		}
		if(secondLargest == Integer.MIN_VALUE){
			return null;
		}
		return secondLargest;
	}
	public static void main(String[] args){
		int[] arr = {10, 20, 20, 5, 8};
		Integer second = findSecondLargest(arr);
		System.out.println("Second Largest: " + second);
	}
}