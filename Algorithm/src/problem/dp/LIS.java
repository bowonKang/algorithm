package problem.dp;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LIS {

	static String[] line;
	static int C;
	static int N;
	static int[] num;
	static int[] lisCount;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		C = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= C; test_case++){
			N = Integer.parseInt(br.readLine().trim());
			
			num = new int[N];
			lisCount = new int[N];
			
			line = br.readLine().trim().split(" ");
			for(int i=0; i<N; i++){
				num[i] = Integer.parseInt(line[i]);
				lisCount[i] = 1;
			}
			
			for(int i=1; i<N; i++){
				for(int j=0; j<i; j++){
					if(num[i] > num[j]){
						lisCount[i] = Math.max(lisCount[i], lisCount[j]+1);
					}
				}
			}
			
			int answer = 0;
			for(int i=0; i<N; i++){
				if(answer < lisCount[i]){
					answer = lisCount[i];
				}					
			}
			
			System.out.println(answer);
		}
		
	}

}