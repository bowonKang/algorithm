/**
문제 : https://algospot.com/judge/problem/read/TRIANGLEPATH

교과서 문제풀이 2 -> 입력 걸러내어 효율적으로 적용 (최적부분구조 문제)

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

public class TrianglePath03 {
	
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
				}
			}	
			
			
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
						cache[i][j] = -1;
				}
			}
			
			System.out.println(path(0, 0));					
		}
	}

	private static int path(int y, int x) {
		System.out.println(y+ " / " + x+ " / " + cache[y][x]);
		if(y == N-1){
			return triArr[y][x];
		}
		
		if (cache[y][x] != -1){
			return cache[y][x];
		}
		
		return cache[y][x] = Math.max(path(y+1,x), path(y+1,x+1)) + triArr[y][x];		
	}
}