class First{
	public static void main(String[] args){
		int[] arr = {10, 11, 16, 5};
		for(int num:arr){
			System.out.println(num + " is " + (isOdd(num) ? "Odd" : "Even"));
			System.out.println(num + " is power of two: " + isPowerOfTwo(num));
			System.out.println(num + " Set Bits are " + countSetBits(num));
		}
	}
	static boolean isOdd(int n){
		return (n & 1) == 1;
	}
	static boolean isPowerOfTwo(int n){
		return n > 0 && (n & (n - 1)) == 0;
	}
	static int countSetBits(int n){
		int count = 0;
		while(n > 0){
			n = n & (n - 1);
			count++;
		}
		return count;
	}
}