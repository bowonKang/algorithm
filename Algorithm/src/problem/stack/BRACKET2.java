package problem.stack;
/**
 ¹®Á¦ : https://algospot.com/judge/problem/read/BRACKETS2 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class BRACKET2 {

	static int T;
	static StringBuilder sb = new StringBuilder();
	
	static String answer;
	
	static Stack<Character> stack = new Stack<Character>(); 

	public static void main(String[] args) throws Exception {
	//	System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1 ; testCase <= T; testCase++){
			sb.delete(0, sb.length());
			sb.append(br.readLine().trim());
			
			answer = "";
						
			stack.clear();
			
			for(int i=0; i<sb.length(); i++){
				if(sb.charAt(i) == '(' || sb.charAt(i) == '{' || sb.charAt(i) == '['){
					stack.push(sb.charAt(i));
				}else if((sb.charAt(i) == ')' || sb.charAt(i) == '}' || sb.charAt(i) == ']') && stack.isEmpty()){
					answer = "NO";
					break;
				}else if(sb.charAt(i) == ')'){
					if(stack.pop() != '('){
						answer = "NO";
						break;
					}
				}else if(sb.charAt(i) == '}'){
					if(stack.pop() != '{'){
						answer = "NO";
						break;
					}
				}else if(sb.charAt(i) == ']'){
					if(stack.pop() != '['){
						answer = "NO";
						break;
					}
				}
			}
			
			if(stack.isEmpty() && answer == ""){
				answer = "YES";
			}else{
				answer = "NO";
			}			
			
			System.out.println(answer);
		}
	}
}