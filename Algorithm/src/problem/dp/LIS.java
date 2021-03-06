/*

문제 : https://algospot.com/judge/problem/read/LIS
기존 동영상 풀이법으로 푼 문제

/INPUT/
3
4
1 2 3 4
8
5 4 3 2 1 6 7 8 
8
5 6 7 8 1 2 3 4

/OUTPUT/
4
4
4

*/

package problem.dp;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LIS {

	static String[] line;
	static int C;
	static int N;
	static int[] num;
	static int[] cache;
		
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		C = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= C; test_case++){
			N = Integer.parseInt(br.readLine().trim());
			
			num = new int[N];
			cache = new int[N];
			
			line = br.readLine().trim().split(" ");
			for(int i=0; i<N; i++){
				num[i] = Integer.parseInt(line[i]);
				cache[i] = -1;
			}
			
			System.out.println(lis01(0));
		}
	}

	private static int lis01(int start) {
		if(cache[start] != -1){
			return cache[start];
		}
		
		cache[start] = 1;
		
		for(int i=start+1; i < N; i++){
			if(num[start] < num[i]){
				cache[start] = Math.max(cache[start], lis01(i)+1);
			}
		}
		
		return cache[start];
		
	}

}