class Seventeen{
	// Container with More Water
	public static void main(String[] args){
		int[] heights = {1,8,6,2,5,4,8,3,7};
		System.out.println("Container with More Water: " + maxArea(heights));
	}
	public static int maxArea(int[] heights){
		int left = 0;
		int right = heights.length - 1;
		int maxArea = 0;

		while(left < right){
			int width = right - left;
			int minHeight = Math.min(heights[left],heights[right]);
			int area = width * minHeight;

			maxArea = Math.max(maxArea,area);

			if(heights[left] < heights[right]){
				left++;
			}else{
				right--;
			}
		}

		return maxArea;
	}
}