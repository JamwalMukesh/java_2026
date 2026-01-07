import java.util.Stack;
class Twenty{
	// Next Greater Element
	public static void main(String[] args){
		int[] arr = {4,5,2,25};

		display(arr);
		int[] result = nextGreater(arr);
		display(result);
	}
	public static void display(int[] arr){
		System.out.print("Array Elements: ");
		for(int num:arr){
			System.out.print(" " + num);
		}
		System.out.println();
	}
	public static int[] nextGreater(int[] arr){
		int n = arr.length;
		int result[] = new int[n];
		Stack<Integer> stack = new Stack<>();

		for(int i = n - 1; i >= 0; i--){
			while(!stack.isEmpty() && stack.peek() <= arr[i]){
				stack.pop();
			}

			result[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(arr[i]);
		}

		return result;
	}
}