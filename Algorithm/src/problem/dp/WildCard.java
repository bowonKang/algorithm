/**
¹®Á¦ : https://algospot.com/judge/problem/read/JUMPGAME

/Input/
3
he?p
3
help
heap
helpp
*p*
3
help
papa
hello
*bb*
1
babbbc

/Output/
heap
help
help
papa
babbbc

 */

package problem.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class WildCard {
	
	static String[] line;	
	static int T;
	static int N;

	static StringBuffer word;
	static StringBuffer[] str;
	static int[][] cache;
		
	static class CharComp implements Comparator<StringBuffer>{

		@Override
		public int compare(StringBuffer o1, StringBuffer o2) {
			for(int i=0; i<o1.length(); i++){
				if(i == o2.length()){
					return 1;
				}
				
				if(o1.charAt(i) != o2.charAt(i)){
					return o1.charAt(i)-o2.charAt(i);
				}
			}
			return -1;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++){
			word = new StringBuffer(br.readLine().trim());
			
			N  = Integer.parseInt(br.readLine().trim());
			str = new StringBuffer[N];			
			
			for(int i=0; i<N; i++){
				str[i] = new StringBuffer(br.readLine().trim());
			}
			
			Arrays.sort(str, new CharComp());
			
			for(int i=0; i<N; i++){			
				
				cache = new int[word.length()+1][str[i].length()+1];
				for(int j=0; j<word.length()+1;j++){
					for(int k=0; k<str[i].length()+1;k++){
						cache[j][k] = -1;
					}
				}
				
				if(matchDp(0, 0, i) == 1){
					System.out.println(str[i]);
				}
			}
		}
	}

	private static int matchDp(int w, int s, int i) {
		if(cache[w][s] != -1){
			return cache[w][s];
		}
		
		if (w < word.length() && s < str[i].length() && 
				(word.charAt(w) == '?' || word.charAt(w) == str[i].charAt(s))){
			return cache[w][s] = matchDp(w+1,s+1,i);
		}
		
		if(w == word.length()){
			if(s == str[i].length()){
				return cache[w][s] = 1;
			}else{
				return cache[w][s] = -1;
			}
		}
		
		if(word.charAt(w) == '*'){
			if((matchDp(w+1, s, i) == 1) || ((s < str[i].length()) && (matchDp(w, s+1, i) == 1)))
				return cache[w][s] = 1;
		}			
		
		return -1;
	}


}