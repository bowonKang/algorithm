/**
 * SDS SW 자격검정 기출문제
 * 격자배열에서 특정 단어를 찾는 문제
 */

package problem.fullSearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class WordScan {
	
	static String[] line; 
	static int wordCount; // 단어갯수
	static int wordSize;
	static char[][] board = new char[15][15];
	static boolean[][] isVisited = new boolean[15][15];
	static char[] word = new char[21];
	static int[] answer = new int[4];
	
	static final int[] dy = {1,1,1,0,0,-1,-1,-1};
	static final int[] dx = {-1,0,1,-1,1,-1,0,1};
	
	public static void main(String[] args) throws Exception {
	//	System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		wordCount = Integer.parseInt(br.readLine().trim());

		for(int i=0; i<board.length; i++){
			board[i] = br.readLine().trim().toCharArray();
		}
		
		for(int wordcount=1; wordcount<=wordCount; wordcount++){
			line = br.readLine().trim().split(" ");
			wordSize = Integer.parseInt(line[0]);
			for(int i=0; i<4; i++){
				answer[i] = -1;
			}
			
			for(int i=0; i<wordSize; i++){
				word = line[1].toCharArray();
			}
			
			loop : for(int i=0; i<15; i++){
				for(int j=0; j<15; j++){
					if(word[0] == board[i][j]){
						isVisited[i][j] = true;
						if(findWord(0, i, j)){
							answer[0] = i;
							answer[1] = j;
							break loop;
						}
						isVisited[i][j] = false;
					}
				}
			}	
			
			System.out.println("#" + wordcount + " " + answer[0] + " " + answer[1] + " " + answer[2] + " " + answer[3]);
		}
		
		
		
		
	}

	private static boolean findWord(int index, int row, int col) {
		if(index == wordSize-1){
			answer[2] = row;
			answer[3] = col;
			
			return true;
		}		
		
		for(int direction = 0 ; direction < 8 ; direction++){			
			int movingRow = row + dy[direction];
			int movingCol = col + dx[direction];
			
			if(isValid(index, movingRow, movingCol, direction)){
				isVisited[movingRow][movingCol] = true;
				index++;
				if(findWord(index, movingRow, movingCol)){
					return true;
				}			
				index--;
				isVisited[movingRow][movingCol] = false;
			}
		}
		
		
		return false;
	}

	private static boolean isValid(int index, int movingRow, int movingCol, int direction) {
		if(movingRow < 0 || movingCol < 0 || movingRow >= 15 || movingCol >= 15){
			return false;
		}		
		
		if(isVisited[movingRow][movingCol] == true){
			return false;
		}
		
		if(board[movingRow][movingCol] == word[index+1]){
			return true;
		}

		return false;
	}

}