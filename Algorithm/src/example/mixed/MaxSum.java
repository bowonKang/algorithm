package example.mixed;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MaxSum {

	static String[] line;
	static int[] arr;
	static int arrSize;
	static int maxValue = Integer.MIN_VALUE;

	
	//최대합을 가지는 부분구간 구하기
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		line = br.readLine().trim().split(" ");
		arr = new int[line.length];
		
		arrSize = arr.length;
		
		for(int i=0; i<arrSize; i++){
			arr[i] = Integer.parseInt(line[i]);
		}
		
		//System.out.println(getMaxBruteForce());
		//System.out.println(getMaxBruteForceBetter());
		//System.out.println(getMaxDivide(0, arrSize-1));
		System.out.println(getMaxDp());
	}

	//전체 탐색 O(N^3)	
	private static int getMaxBruteForce() {				
		for(int i=0; i<arrSize; i++){
			for(int j=i; j<arrSize; j++){
				int areaSum=0;
				for(int k=i; k<=j; k++){
					areaSum = areaSum + arr[k];
				}
				maxValue = Math.max(maxValue, areaSum);
			}
		}			
		return maxValue;	
	}
	
	//전체 탐색2 O(N^2)
	private static int getMaxBruteForceBetter() {
		for(int i=0; i<arrSize; i++){
			int areaSum=0;
			for(int j=i; j<arrSize; j++){
				areaSum = areaSum + arr[j];
				maxValue = Math.max(maxValue, areaSum);
			}
		}			
		return maxValue;	
		
	}
	
	//분할정복 O(NLogN)
	private static int getMaxDivide(int low, int high) {
		if(low == high){
			return arr[low];
		}
		
		int mid = (low + high) / 2;
		int sum = 0;		
		int leftMaxValue = Integer.MIN_VALUE;
		int rightMaxValue = Integer.MIN_VALUE;
		
		for(int i=mid; i>=low; i--){
			sum = sum + arr[i];
			leftMaxValue = Math.max(leftMaxValue, sum);
		}
		
		for(int i=mid+1; i<=high; i++){
			sum = sum + arr[i];
			rightMaxValue = Math.max(rightMaxValue, sum);
		}
		

		int single = Math.max(getMaxDivide(low,mid), getMaxDivide(mid+1,high));
		return Math.max(leftMaxValue+rightMaxValue, single);
	}
	
	//동적 계획법 O(N)
	private static int getMaxDp() {
		int psum = 0;
		for(int i=0; i<arrSize; i++){
			psum = Math.max(psum, 0) + arr[i];
			maxValue = Math.max(psum, maxValue);
		}
		return maxValue;
	}
}