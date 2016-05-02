package problem.math;
/**
 [입출력 예]
 (입력)
7
 1 16 13 2
 14 9 14 3
 4 3 6 7
 9 2 6 4
 8 13 4 7
 6 2 1 2
 2 10 12 9

 (출력)
3
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Point {
	int x;
	int y;
}

class Line {
	double a;
	double b;
	double c;

	int startX;
	int endX;
}

public class Intersection {

	static int lineNum;
	static String[] input;

	static Point p1 = new Point();
	static Point p2 = new Point();

	static Line l;

	static int count;

	static double multi;
	static double x;

	static ArrayList<Line> al = new ArrayList<Line>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		lineNum = Integer.parseInt(br.readLine().trim());

		for (int i = 0; i < lineNum; i++) {
			input = br.readLine().trim().split(" ");

			addLines();

		}

		for (int i = 0; i < al.size(); i++) {
			for (int j = i + 1; j < al.size(); j++) {
				if (compare(al.get(i), al.get(j))) {
					count++;
				}
			}
		}

		System.out.println(count);

	}

	private static boolean compare(Line l1, Line l2) {
		if (l1.b == 0 || l2.b == 0) {
			return false;
		} else if (l2.b != 0) {
			multi = l1.b / l2.b;
			l2.a = l2.a * multi;
			l2.c = l2.c * multi;

			x = -(l1.c - l2.c) / (l1.a - l2.a);

			l2.a = l2.a / multi;
			l2.c = l2.c / multi;
		} else if (l2.a != 0) {
			multi = l2.b / l1.b;
			l1.a = l1.a * multi;
			l1.c = l1.c * multi;

			x = -(l1.c - l2.c) / (l1.a - l2.a);

			l1.a = l1.a / multi;
			l1.c = l1.c / multi;
		}

		if (x >= l1.startX && x <= l1.endX && x >= l2.startX && x <= l2.endX) {
			count++;
		}

		return false;
	}

	private static void addLines() {
		l = new Line();

		p1.x = Integer.parseInt(input[0]);
		p1.y = Integer.parseInt(input[1]);
		p2.x = Integer.parseInt(input[2]);
		p2.y = Integer.parseInt(input[3]);

		al.add(getLine(l, p1, p2));
	}

	private static Line getLine(Line l, Point p1, Point p2) {
		l.a = p1.y - p2.y;
		l.b = p2.x - p1.x;
		l.c = p1.x * p2.y - p2.x * p1.y;

		l.startX = Math.min(p1.x, p2.x);
		l.endX = Math.max(p1.x, p2.x);

		return l;
	}

}