class Sixteen{
	// Trapping Rain Water
	public static void main(String[] args){
		int[] heights = {4,2,0,3,2,5};
		System.out.println("Total Rain Water: " + trapWater(heights));
	}
	public static int trapWater(int[] heights){
		int left = 0;
		int right = heights.length - 1;

		int leftMax = 0;
		int rightMax = 0;
		int water = 0;

		while(left < right){
			if(heights[left] < heights[right]){
				if(heights[left] >= leftMax){
					leftMax = heights[left];
				}else{
					water += leftMax - heights[left];
				}
				left++;
			}else{
				if(heights[right] >= rightMax){
					rightMax = heights[right];
				}else{
					water += rightMax - heights[right];
				}
				right--;
			}
		}		
		
		return water;
	}
}