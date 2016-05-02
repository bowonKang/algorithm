/**
 * 문제 : https://algospot.com/judge/problem/read/CLOCKSYNC
 */

package problem.fullSearch;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class ClockSync {
	
	static String[] line; 
	
	static int C; // 테스트 케이스
	
	final static int MAXVALUE = 9999;
	final static int CLOCKSNUM = 16;
	final static int SWITCHESNUM = 10;

	static int[] clocks;
	
	final static char[][] link ={
	   //0123456789012345	
		"###.............".toCharArray(),
		"...#...#.#.#....".toCharArray(),
		"....#.....#...##".toCharArray(),
		"#...####........".toCharArray(),
		"......###.#.#...".toCharArray(),
		"#.#...........##".toCharArray(),
		"...#..........##".toCharArray(),
		"....##.#......##".toCharArray(),
		".#####..........".toCharArray(),
		"...###...#...#..".toCharArray(),
	};
	
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		C = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= C; test_case++) {
			clocks = new int[CLOCKSNUM];
			
			line = br.readLine().trim().split(" ");
						
			for(int i=0; i<CLOCKSNUM; i++){
				clocks[i] = Integer.parseInt(line[i]);
			}
			
			int answer = solve(0);
			
			if(answer == 9999){
				answer = -1;
			}
			
			System.out.println(answer);
		}
	}



	private static int solve(int switchNum) {
		if(switchNum == SWITCHESNUM){
			return areAligned() ? 0 : MAXVALUE;
		}
		
		int ret = MAXVALUE;
		for(int i=0; i<4; i++){
			ret = Math.min(ret, i + solve(switchNum + 1));
			//System.out.println(switchNum + " " + i + " " + ret);
			push(switchNum);
		}
		return ret;		
	}



	private static void push(int switchNum) {
		for(int clock = 0; clock < CLOCKSNUM; clock++){
			if(link[switchNum][clock] == '#'){
				clocks[clock] = clocks[clock] + 3;
				if(clocks[clock] == 15){
					clocks[clock] = 3;
				}
			}
		}
		
	}



	private static boolean areAligned() {
		for(int clock = 0; clock < CLOCKSNUM; clock++){
			if(clocks[clock] != 12){
				return false;
			}
		}
		return true;
	}
}