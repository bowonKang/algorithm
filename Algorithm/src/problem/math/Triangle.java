package problem.math;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Triangle {
	
	static class Point{
	     int x;
	     int y;
	}

	static class Line{
	     int a;
	     int b;
	     int c;
	     
	     int startX;
	     int endX;
	}
     
     static int T;
     static String[] input;
     
     static long count;
     
     static Point p1 = new Point();
     static Point p2 = new Point();
     static Point p3 = new Point();
     
     static Line l1 = new Line();
     static Line l2 = new Line();
     static Line l3 = new Line();
     
     static int maxX;
     static int minX;
     static int maxY;
     static int minY;
     
     static int minYtemp;
     static int maxYtemp;
         
     public static void main(String[] args) throws Exception {
         System.setIn(new FileInputStream("sample\\triangle_input.txt"));
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         T = Integer.parseInt(br.readLine().trim());
         
         for(int test=1; test<=T; test++){
             input = br.readLine().trim().split(" ");
             
             count = 0;
             
             settingPointsAndLines();
             
             getRange();
             
             for(int i=minX+1; i<maxX; i++){ //X를 탐색하며 최소 Y와 최대 Y값을 구한다.
                 minYtemp = Math.min(getMinY(i, l1), Math.min(getMinY(i, l2), getMinY(i, l3)));
                 maxYtemp = Math.max(getMaxY(i, l1), Math.max(getMaxY(i, l2), getMaxY(i, l3)));        
                 
                 if(maxYtemp > minYtemp){
                     count = count + (maxYtemp - minYtemp + 1);
                 }else if(maxYtemp == minYtemp){
                     count++;
                 }
             }
             
             System.out.println(count);
             
         }
     }

     private static int getMaxY(int x, Line l) {
         if(x < l.startX || x > l.endX || l.b == 0){
             return Integer.MIN_VALUE;
         }
         
         double value = - (double)(l.a * x + l.c) / (double)l.b;
         
         if(value < 0 || value % 1 == 0 && value >= 0){  //음수이거나 양수인데 정수일경우 -1
             return  (int)value - 1;
         }else{
             return  (int)value;
         }      
         
     
     }

     private static int getMinY(int x, Line l) {
         if(x < l.startX || x > l.endX || l.b == 0){
             return Integer.MAX_VALUE;
         }
         
         double value = - (double)(l.a * x + l.c) / (double)l.b;
         
         if(value >= 0 || value % 1 == 0 && value < 0){  //양수이거나 음수인데 정수일경우 +1
             return  (int)value + 1;
         }else{
             return  (int)value;
         }
     }

     private static void getRange() {
         maxX = Math.max(p1.x, Math.max(p2.x, p3.x));
         minX = Math.min(p1.x, Math.min(p2.x, p3.x));
         maxY = Math.max(p1.y, Math.max(p2.y, p3.y));
         minY = Math.min(p1.y, Math.min(p2.y, p3.y));
     }

     private static void settingPointsAndLines() {
         p1.x = Integer.parseInt(input[0]);
         p1.y = Integer.parseInt(input[1]);
         p2.x = Integer.parseInt(input[2]);
         p2.y = Integer.parseInt(input[3]);
         p3.x = Integer.parseInt(input[4]);
         p3.y = Integer.parseInt(input[5]);
         
         setLine(l1, p2, p3);
         setLine(l2, p1, p3);
         setLine(l3, p1, p2);
     }

     private static void setLine(Line l, Point p1, Point p2) {
         l.a = p1.y - p2.y;
         l.b = p2.x - p1.x;
         l.c = p1.x * p2.y - p2.x * p1.y;
         
         l.startX = Math.min(p1.x, p2.x);
         l.endX = Math.max(p1.x, p2.x);
     }   
     
}