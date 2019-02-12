package backTracking;

import java.util.*;
import java.lang.*;
import java.io.*;

class RottenOranges {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- > 0) {
			int n = scn.nextInt();
			int m = scn.nextInt();
			int[][] arr = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					arr[i][j] = scn.nextInt();
				}
			}
			rottenOranges(arr, 0);
		}
	}

	private static class pair {
		int i;
		int j;

		public pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void rottenOranges(int[][] arr, int t) {
		if (allRotten(arr)) {

			System.out.println("All rotten in " + t);
			return;
		}

		int intiCount = countRotten(arr);

		Stack<pair> stack = new Stack<>();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 2) {
					stack.push(new pair(i, j));
				}
			}
		}

		while (stack.size() > 0) {
			pair rp = stack.pop();
			rotOrange(rp.i + 1, rp.j, arr);
			rotOrange(rp.i - 1, rp.j, arr);
			rotOrange(rp.i, rp.j + 1, arr);
			rotOrange(rp.i, rp.j - 1, arr);
		}
		int currCount = countRotten(arr);
		if (intiCount == currCount) {
			System.out.println("-1");
			return;
		}
		rottenOranges(arr, t + 1);
	}


	public static int countRotten(int[][] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 2) {
					count++;
				}
			}
		}
		return count;
	}

	public static boolean allRotten(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static void rotOrange(int i, int j, int[][] arr) {
		if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
			return;
		}
		if (arr[i][j] == 1) {
			arr[i][j] = 2;
		}
	}
}