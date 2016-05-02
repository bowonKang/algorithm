package problem.fullSearch;

/**
 * 문제 : https://algospot.com/judge/problem/read/NQUEEN
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class NQueen {
	static int T;

	static int length;
	static int nQueenCount; // N-Queen 정답 갯수

	static int[] queenLoc;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			nQueenCount = 0;

			length = Integer.parseInt(br.readLine().trim());

			queenLoc = new int[length];

			nQueenSovle(0);
			System.out.println(nQueenCount);
		}
	}

	private static void nQueenSovle(int row) {
		if (row == length) {
			nQueenCount++;
			return;
		}

		for (int col = 0; col < length; col++) {
			if (row == 0) {
				queenLoc[row] = col + 1;

				nQueenSovle(row + 1);

			} else if (isPromising(queenLoc, row, col + 1)) {
				queenLoc[row] = col + 1;

				nQueenSovle(row + 1);
			}
		}
	}

	private static boolean isPromising(int[] queenLoc, int row, int col) {
		for (int i = 0; i < row; i++) {
			int beforeX = queenLoc[i];
			int beforeY = i + 1;
			int newX = col;
			int newY = row + 1;

			if (beforeX == newX || beforeX + beforeY == newX + newY || beforeX - beforeY == newX - newY) {
				return false;
			}
		}
		return true;
	}

}
