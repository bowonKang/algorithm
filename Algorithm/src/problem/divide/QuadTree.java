package problem.divide;
/**
 * 문제 : https://algospot.com/judge/problem/read/QUADTREE
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class QuadTree {

	static String quadStr;
	static String reverseStr;
	static char[] originStr = new char[1000];
	static int index = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		// 테스트 케이스 각각을 처리합니다.

		for (int test_case = 1; test_case <= T; test_case++) {
			/******************************************************/
			// 이 곳에 알고리즘을 구현합니다.
			/******************************************************/
			quadStr = sc.next();

			System.out.println(getReverse(0));
		}

	}

	private static String getReverse(int num) {
           String[] changeStr = new String[4];
           
           for(int i=0; i < changeStr.length;i++){
               changeStr[i] = "-";
           }
           
           index = num;
           
           originStr = quadStr.toCharArray();
                   
           if(originStr.length == 1){
               return originStr[0]+ "";
           }
           
           for(int i=0;i<changeStr.length;i++){
               if(originStr[index] == 'w' || originStr[index] == 'b'){
                   changeStr[i] = originStr[index] + "";
                   index++;
               }else if(originStr[index] == 'x'){
                   changeStr[i] = getReverse(index+1);
                   
                   if(changeStr[i].length() == originStr.length){
                       return changeStr[i];
                   }            
               }
           }     
           
           return "x" + changeStr[2] + changeStr[3] + changeStr[0] + changeStr[1];
       }

}
