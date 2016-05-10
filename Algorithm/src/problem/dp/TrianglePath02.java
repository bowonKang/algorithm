/**
문제 : https://algospot.com/judge/problem/read/TRIANGLEPATH

교과서 문제풀이 1 -> 무식하게 DP 로 적용하기

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

public class TrianglePath02 {
	
	static String[] line;	
	static int T;
	static int N;
	
	static int maxValue;

	static int[][] triArr;
	static int[][][] cache;
		
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++){
			N = Integer.parseInt(br.readLine().trim());
			triArr = new int[N][N];
			cache = new int[N][N][100000];
			maxValue = 0;
			
			for(int i=0; i<N; i++){
				line = br.readLine().trim().split(" ");
				for(int j=0; j<line.length; j++){
					triArr[i][j] = Integer.parseInt(line[j]);
				}
			}	
			
			
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					for(int k=0; k<100000; k++){
						cache[i][j][k] = -1;
					}
				}
			}
			
			System.out.println(path(0, 0, 0));					
		}
	}

	private static int path(int y, int x, int sum) {
		//System.out.println(y + " / " + x + " / " + sum);
		if(y == N-1){
			return sum + triArr[y][x];
		}
		
		if (cache[y][x][sum] != -1){
			return cache[y][x][sum];
		}
		
		sum += triArr[y][x];
		return cache[y][x][sum] = Math.max(path(y+1,x+1,sum), path(y+1,x,sum));		
	}
}