package example.graph;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DijkstraArray {

    static int[][] adjacent;    //각 엣지별 weight 저장
    static int[] distance;  //노드별 최단거리 저장
    static boolean[] visit; //노드별 방문여부
    static int V, E;    //V:정점, E:간선
    static final int inf = Integer.MAX_VALUE;
     
     public static void main(String[] args) throws Exception {
         System.setIn(new FileInputStream("sample_input.txt"));
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
         String[] str = br.readLine().trim().split(" ");
         V = Integer.parseInt(str[0]);
         E = Integer.parseInt(str[1]);
         
         adjacent = new int[V+1][V+1];
         distance = new int[V+1];
         visit = new boolean[V+1];
         
         for(int i=1; i<=V; i++){
             distance[i] = inf;
         }
         
         for(int i=0; i<E; i++){
             int t1,t2,t3;
             
             String[] str2 = br.readLine().trim().split(" ");
             
             t1 = Integer.parseInt(str2[0]);
             t2 = Integer.parseInt(str2[1]);
             t3 = Integer.parseInt(str2[2]);   
             
             adjacent[t1][t2] = t3;
         }
         
         dijkstra(1,5);
     }

     private static void dijkstra(int start, int end) {
         distance[start] = 0;    //최초 시작 값 distance 초기화
        String s = "";  //경로 체크용
        
         
         for(int i=0; i<V; i++){
             int min = inf;
             int index = -1;
             
             //가장 거리가 적은 정점을 선택한다.
             for(int j=1; j<=V; j++){
                 if(visit[j] == false && min > distance[j]){
                     min = distance[j];
                     index = j;
                 }
             }
             visit[index] = true;
             s += index + " ";
             
             //distnace 값을 갱신한다
            for(int j=1; j<=V; j++){
                 if(adjacent[index][j] != 0 && distance[j] > distance[index] + adjacent[index][j]){  
                     distance[j] = distance[index] + adjacent[index][j];
                 }
             }
         }
         
         for(int i=1; i<=V; i++){
             System.out.print(distance[i] + " ");
         }
         
         System.out.println();
         System.out.println(s);
         
     }

}