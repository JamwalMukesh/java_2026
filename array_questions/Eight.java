import java.util.List;
import java.util.ArrayList;

class Eight{
	public static void main(String[] args){
		int[] arr = {1,1,2,2,3,3,1,2};
		System.out.println("Majority Elements(n/3): " + majorityElement(arr));
	}
	static List<Integer> majorityElement(int[] arr){
		int candidate1 = 0, candidate2 = 0;
		int count1 = 0, count2 = 0;

		for(int num:arr){
			if(num == candidate1){
				count1++;
			}else if(num == candidate2){
				count2++;
			}else if(count1 == 0){
				candidate1 = num;
				count1 = 1;
			}else if(count2 == 0){
				candidate2 = num;
				count2 = 1;
			}else{
				count1--;
				count2--;
			}
		}

		count1 = count2 = 0;
		for(int num:arr){
			if(num == candidate1){
				count1++;
			}else if(num == candidate2){
				count2++;
			}
		}

		

		List<Integer> result = new ArrayList<>();
		int n = arr.length;

		if(count1 > n / 3){
			result.add(candidate1);
		}
		if(count2 > n / 3){
			result.add(candidate2);
		}
		return result;
	}
}