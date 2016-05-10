/**
문제 : https://algospot.com/judge/problem/read/TRIANGLEPATH

개별적인 풀이임 -> 점진적으로 더하며 Cache에 값 더함

/input/
2
5
6
1 2
3 7 4
9 4 1 7
2 7 5 9 4
5
1 
2 4
8 16 8
32 64 32 64
128 256 128 256 128

/output/
28
341
 */

package problem.dp;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class TrianglePath01 {
	
	static String[] line;	
	static int T;
	static int N;
	
	static int maxValue;

	static int[][] triArr;
	static int[][] cache;
		
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++){
			N = Integer.parseInt(br.readLine().trim());
			triArr = new int[N][N];
			cache = new int[N][N];
			maxValue = 0;
			
			for(int i=0; i<N; i++){
				line = br.readLine().trim().split(" ");
				for(int j=0; j<line.length; j++){
					triArr[i][j] = Integer.parseInt(line[j]);
					if(i == 0){
						cache[i][j] = triArr[i][j];
					}else if(j == 0){
						cache[i][j] = triArr[i][j] + cache[i-1][j];
					}else{
						cache[i][j] = Math.max(triArr[i][j] + cache[i-1][j], triArr[i][j] + cache[i-1][j-1]);
					}					
				}
			}		
			
			for(int i=0; i<N; i++){
				if(cache[N-1][i] > maxValue){
					maxValue = cache[N-1][i];
				}
			}
			
			System.out.println(maxValue);					
		}
	}
}