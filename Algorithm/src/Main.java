import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Main {
	
	static String[] line; 
	
	static int T; // 테스트 케이스

	static char[] idols;
	static char[] fans;
	
	static int answer;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;
			
			idols = br.readLine().trim().toCharArray();
			fans = br.readLine().trim().toCharArray();	
									
			for(int i=0; i<fans.length - idols.length + 1; i++){
				for(int j=i; j<i+idols.length; j++){
					if(idols[j-i] == 'M' && fans[j] == 'M'){
						break;
					}
					
					if(j == i+idols.length-1){
						answer++;
					}
				}
			}
			
			System.out.println(answer);
			
		}
		
		
		
		
	}




}