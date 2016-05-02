package problem.greedy;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 문제 : https://algospot.com/judge/problem/read/MATCHORDER
  
입력 예
 3
 6
 3000 2700 2800 2200 2500 1900
 2800 2750 2995 1800 2600 2000
 3
 1 2 3
 3 2 1
 4
 2 3 4 5
 1 2 3 4

출력 예
 5
 3
 3
  */

public class MatchOrder {

     static int T;
     static int N;
     static int winCount;
     
     static String[] inputString;
     static Integer[] korea;
     static Integer[] russia;
     
     private static class IntegerDesc implements Comparator<Integer>{

         @Override
         public int compare(Integer o1, Integer o2) {
             return o2 - o1;
         }

     
         
     }
     
     public static void main(String[] args) throws Exception{
         System.setIn(new FileInputStream("sample_input.txt"));
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
         T = Integer.parseInt(br.readLine().trim());
         
         for(int test_case = 1 ; test_case <= T; test_case++){
             N = Integer.parseInt(br.readLine().trim());
                         
             init(br);
                     
             Arrays.sort(russia, new IntegerDesc());
             Arrays.sort(korea, new IntegerDesc());
             
             int koreaIndex = 0;
             
             for(int russiaIndex=0; russiaIndex<N; russiaIndex++){
                 if(russia[russiaIndex] <= korea[koreaIndex]){
                     winCount++;
                     koreaIndex++;
                 }
             }
             
             
             System.out.println(winCount);
         }
     }
     

     private static void init(BufferedReader br) throws IOException {
         winCount = 0;
         russia = new Integer[N];
         korea = new Integer[N];
         
         inputString = br.readLine().trim().split(" ");
         
         for(int i=0; i<N; i++){
             russia[i] = Integer.parseInt(inputString[i]);
         }
         
         inputString = br.readLine().trim().split(" ");
         
         for(int i=0; i<N; i++){
             korea[i] = Integer.parseInt(inputString[i]);
         }
     }
}
