package problem.graph;
/**
문제 : https://algospot.com/judge/problem/read/ARCTIC

 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Road{
	public int[] node = new int[2];
	public double distance;	
}

class Comp implements Comparator<Road>{	
	@Override
	public int compare(Road r1, Road r2) {		
		
		int result = 0;
		
		if(r1.distance < r2.distance){
			result = -1;
		}else if(r1.distance > r2.distance){
			result = 1;
		}

		return result;
	}
}


public class Arctic {
	static int T = 0;
	static int N = 0;
	static double[][] loc;
	static double[][] adj;
	
	static double locX;
	static double locY;
	
	static double answer;
	
	static String[] str;
	static int[] set;
	
	public static void main (String args[]) throws Exception{
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		
		for (int i = 1; i <= T; i++){
			N = Integer.parseInt(br.readLine().trim());
			
			loc = new double[N][2];
			set = new int[N];
			
			for(int j=0; j<N; j++){
				str = br.readLine().trim().split(" ");
				loc[j][0] = Double.parseDouble(str[0]); 
				loc[j][1] = Double.parseDouble(str[1]);
			}
			
			answer = KruskalForMax(loc);
			
			System.out.printf("%.2f\n", answer);
		}
	}

	//좌표 값을 받으면 해당 좌표들로 최소비용 신장트리를 구성할경우 가장 긴 간선을 구한다.
	private static double KruskalForMax(double[][] loc) {
		Road[] roads = new Road[N*N];
		
		Road[] mst = new Road[N-1];
		
		for (int i=0; i<roads.length; i++){
			roads[i] = new Road();
		}

		answer = 0;
		
		int index=0;
		
		for(int i=0; i<loc.length; i++){
			for(int j=i+1; j<loc.length; j++){
				roads[index].node[0] = i;
				roads[index].node[1] = j;
				roads[index].distance = (Math.pow((loc[j][0]) - (loc[i][0]),2) + Math.pow((loc[j][1]) - (loc[i][1]),2));
				index++;
			}
		}
		
		Comp c = new Comp();
		Arrays.sort(roads, 0, index, c);		
		
		int countMst = 0;
		int countRoads = 0;
		
		for(int i=0; i<set.length; i++){
			set[i] = i;
		}		
		
		while(countMst < N-1){
			mst[countMst] = roads[countRoads];
			
			if(!isCycled(mst, countMst)){
				answer = Math.sqrt(mst[countMst].distance);
				countMst++;						
			}
			
			countRoads++;
		}
		
		return answer;
	}

	//Union - find 함수를 이용하여 구현
	private static boolean isCycled(Road[] mst, int countMst) {
			int num1 = mst[countMst].node[0];
			int num2 = mst[countMst].node[1];
						
			if(find(set, num1, num2)){
				return true;
			}
			union(set, num1, num2);
		
		return false;
	}

	private static boolean find(int[] set, int num1, int num2) {
		int a = set[num1];
		int b = set[num2];
		
		if(a == b){
			return true;
		}else{
			return false;
		}		
	}
	
	private static void union(int[] set, int num1, int num2) {
		if(set[num1] < set[num2]){
			int setNum = set[num2];
			for(int i=0; i<set.length; i++){
				if(set[i] == setNum){
					set[i] = set[num1];
				}
			}
		}else{
			int setNum = set[num1];
			for(int i=0; i<set.length; i++){
				if(set[i] == setNum){
					set[i] = set[num2];
				}
			}
		}
	}

}