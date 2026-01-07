class Eighteen{
	// Rotate the arrays in right
	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,6,7};

		display(arr);
		rotate(arr,3);
		display(arr);
	}
	public static void display(int[] arr){
		System.out.print("Array Elements: ");
		for(int num:arr){
			System.out.print(" " + num);
		}
		System.out.println();
	}
	public static void rotate(int[] arr,int k){
		int n = arr.length;
		k = k % n;

		reverse(arr, 0, n - 1);
		reverse(arr, 0, k - 1);
		reverse(arr, k, n - 1);
	}
	public static void reverse(int[] arr, int left, int right){
		while(left < right){
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}
}