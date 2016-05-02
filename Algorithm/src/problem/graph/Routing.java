package problem.graph;
/**
 * 문제 : https://algospot.com/judge/problem/read/ROUTING
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Routing {

	static String[] strInput;

	static int C; // 테스트 케이스
	static int N; // 컴퓨터의 수
	static int M; // 회선의 수
	
	static int count; // 회선의 수

	static ArrayList<Node>[] alArr;
	static PriorityQueue<Node> pq;

	static double[] totalNoise;
	static boolean[] isVisited; // 회선의 수
	static final double infinite = Double.MAX_VALUE;

	static class Node implements Comparable<Node> {
		Node(int dest, double noise) {
			this.destNum = dest;
			this.noise = noise;
		}

		int destNum;
		double noise;

		@Override
		public int compareTo(Node node) {
			return (int) (this.noise - node.noise);
			//return 0; 일반 Queue에 넣을경우 시간초과
		}

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		C = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= C; test_case++) {
			strInput = br.readLine().trim().split(" ");
			N = Integer.parseInt(strInput[0]);
			M = Integer.parseInt(strInput[1]);

			alArr = new ArrayList[N];
			pq = new PriorityQueue<Node>();
			isVisited = new boolean[N];
			totalNoise = new double[N];

			for (int i = 0; i < N; i++) {
				alArr[i] = new ArrayList<Node>();
				isVisited[i] = false;
				totalNoise[i] = infinite;
			}

			for (int i = 0; i < M; i++) {
				strInput = br.readLine().trim().split(" ");
				int loc1 = Integer.parseInt(strInput[0]);
				int loc2 = Integer.parseInt(strInput[1]);
				double distance = Double.parseDouble(strInput[2]);

				alArr[loc1].add(new Node(loc2, distance));
				alArr[loc2].add(new Node(loc1, distance));
			}
			
			double answer = dijkstraUsingPQ(0, N - 1);

			System.out.printf("%.10f\n", answer);
			
		}

	}

	private static double dijkstraUsingPQ(int start, int end) {
		totalNoise[start] = 1;
		pq.offer(new Node(0, totalNoise[start]));

		while (!pq.isEmpty()) {
			int indexNum = pq.peek().destNum;
			double indexNoise = pq.peek().noise;
			pq.poll();
			
			if (indexNoise > totalNoise[indexNum]) {
				continue;
			}
			
			if(!isVisited[indexNum]){
				for (int i = 0; i < alArr[indexNum].size(); i++) {
					int adjNum = alArr[indexNum].get(i).destNum;
					
					if (totalNoise[adjNum] > totalNoise[indexNum] * alArr[indexNum].get(i).noise) {
						totalNoise[adjNum] = totalNoise[indexNum] * alArr[indexNum].get(i).noise;
						pq.offer(new Node(adjNum, totalNoise[adjNum]));
					}
				}
			}
			
			isVisited[indexNum] = true;
			
		}

		return totalNoise[N - 1];
	}

}