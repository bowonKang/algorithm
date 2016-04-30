package FullSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Permutation {

     static final int numLength = 4;
     
     static int[] numArr = new int[numLength];
     static boolean[] isUsedNum = new boolean[numLength];    
     
     public static void main(String[] args) throws Exception {
         getPermutation(0);
     }

     private static void getPermutation(int num) {
          if(num == numLength){
             for(int i=0; i<numArr.length; i++){
                 System.out.print(numArr[i] + " ");
             }
             System.out.println();
             return;
         }
         
         //숫자 배열을 탐색하며 값이 비어있을경우 넣고 숫자를 증가시켜 재귀호출        
         //재귀호출이 완료되면 다시 값을 지워주고 다음숫자로 넘어간다
        for(int i=0; i<numLength; i++){
             if(isUsedNum[i] == false){
                 numArr[num] = i+1;
                 isUsedNum[i] = true;
                 getPermutation(num+1);
                 
                 isUsedNum[i] = false;            
             }         
         }            
     }      
}

