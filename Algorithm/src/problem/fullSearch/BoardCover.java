/**
 * 문제 : https://algospot.com/judge/problem/read/BOARDCOVER
 */


package problem.fullSearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BoardCover {

	static String[] line;

	static int C; // 테스트 케이스
	static int H;
	static int W;
	static char[][] board;

	static int whiteCount;
	static int answer;

	final static int[][][] coverType = {
		{{0,0},{1,0},{0,1}},
		{{0,0},{0,1},{1,1}},
		{{0,0},{1,0},{1,1}},
		{{0,0},{1,0},{1,-1}},
		}; 
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		C = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= C; test_case++) {
			line = br.readLine().trim().split(" ");
			H = Integer.parseInt(line[0]);
			W = Integer.parseInt(line[1]);
			whiteCount = 0;
			answer = 0;

			board = new char[H][W];

			for (int i = 0; i < H; i++) {
				board[i] = br.readLine().trim().toCharArray();
				for(int j=0; j<W; j++){
					if(board[i][j] == '.'){
						whiteCount++;
					}
				}
			}

			cover(0, 0);

			System.out.println(answer);
		}
	}

	private static boolean cover(int y, int x) {
		if (whiteCount == 0) {
			answer++;
			return true;
		}

		if (whiteCount == 1 || whiteCount == 2) {
			return true;
		}

		int startY = 0;
		int startX = 0;		
		
		
		
		loop : for (int i=y; i<H; i++) {
			for(int j=x; j<W; j++){
				if(board[i][j] == '.'){
					startY = i;
					startX = j;
					break loop;
				}
			}
		}
		
		
		
		for(int direction=0; direction<coverType.length; direction++){
			int paint0Y = startY + coverType[direction][0][0];
			int paint1Y = startY + coverType[direction][1][0];
			int paint2Y = startY + coverType[direction][2][0];
			int paint0X = startX + coverType[direction][0][1];
			int paint1X = startX + coverType[direction][1][1];
			int paint2X = startX + coverType[direction][2][1];
			
			if(paint1Y < 0 || paint2Y < 0 || paint1X < 0 || paint2X < 0 ||
					paint1Y >= H || paint2Y >= H || paint1X >= W || paint2X >= W){
				continue;
			}
			
			if(board[paint1Y][paint1X] == '#' || board[paint2Y][paint2X] == '#'){
				continue;
			}
			
			board[paint0Y][paint0X] = board[paint1Y][paint1X] = board[paint2Y][paint2X] = '#';
			whiteCount = whiteCount - 3;
			
			/*System.out.println(startY + " " + startX);
			for(int i=0; i<H; i++){
	            for(int j=0; j<W; j++){
	                System.out.print(board[i][j]);
	            }
	            System.out.println();
	        }
			System.out.println();*/
				
			cover(0, 0);			
			
			board[paint0Y][paint0X] = board[paint1Y][paint1X] = board[paint2Y][paint2X] = '.';
			whiteCount = whiteCount + 3;
		}
		return false;
	}

}