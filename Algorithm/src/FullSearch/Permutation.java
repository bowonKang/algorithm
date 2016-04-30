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
         
         //���� �迭�� Ž���ϸ� ���� ���������� �ְ� ���ڸ� �������� ���ȣ��        
         //���ȣ���� �Ϸ�Ǹ� �ٽ� ���� �����ְ� �������ڷ� �Ѿ��
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

