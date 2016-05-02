package problem.divide;
/**
 문제 : https://algospot.com/judge/problem/read/FENCE
 */

import java.io.FileReader;
import java.util.Scanner;

public class Fence {

	static int C; //테스트 케이스 수
	static int N; //판자 수
	static int[] height; 
	static int index; //기준점
	static int answer;
	
			
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(new FileReader("sample_input.txt"));
		
		C = sc.nextInt();
		
		for (int test_case=1; test_case <= C; test_case++){
			N = sc.nextInt();
			
			height = new int[N];
			
			for(int i=0; i<N; i++){
				height[i] = sc.nextInt();
			}
			
			answer = getSize(0, N-1);
			
			System.out.println(answer);
		}		
	}


	private static int getSize(int start, int end) {
		
		
		if(start == end){
			return height[start];
		}
		
		if(start > end){
			return -1;
		}
		
		index = start;
		for(int i=start; i<end; i++){		
			if(height[index] > height[i+1]){
				index = i+1;
			}
		}
		
		int returnValue = (end-start+1) * height[index];
			
		//System.out.println("step 1 -> start : " + start + ", end : " + end + ", index : " +index);
		
		return Math.max(Math.max(returnValue, getSize(start, index-1)), getSize(index+1, end));
	}
}