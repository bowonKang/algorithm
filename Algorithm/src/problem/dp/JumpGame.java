/**
문제 : https://algospot.com/judge/problem/read/JUMPGAME
Input
2
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 2
3 3 1 2 3 4 1
1 5 2 9 4 7 0
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 3
3 3 1 2 3 4 1
1 5 2 9 4 7 0 

Output
YES
NO
 */

package problem.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class JumpGame {
	
	static String[] line;	
	static int T;
	static int n;
	static int[][] board;
	static int[][] cache;
	static String answer;
	
		
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++){
			n = Integer.parseInt(br.readLine().trim());
			board = new int[n][n];
			cache = new int[n][n];	// -1 : 사용안함, 0 : false, 1 : true
			
			for(int i=0; i<n ; i++){
				line = br.readLine().trim().split(" ");
				for(int j=0; j<n; j++){
					board[i][j] = Integer.parseInt(line[j]);
					cache[i][j] = -1;
				}
			}
						
			if(fullScan(0, 0) == 0){
				answer = "NO";
			}else{
				answer = "YES";
			}
						
			System.out.println(answer);
			
		}
	}


	private static int fullScan(int row, int col) {
		
		
		if(row == n-1 && col == n-1){
			return 1;
		}
		
		if(row >= n || col >= n){
			return 0;
		}	
		
		
		int nextRow = row + board[row][col];
		int nextCol = col + board[row][col];
		
		if(cache[row][col] != -1){
			return cache[row][col];
		}else{
			return cache[row][col] = fullScan(nextRow, col) | fullScan(row, nextCol);
		}		
	}
}