package arrays;

import java.util.*;
import java.lang.*;
import java.io.*;

class FirstNonRepeating {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			Character[] arr = new Character[n];
			String[] temp = br.readLine().split(" ");
			for (int i = 0; i < arr.length; i++) {
				arr[i] = temp[i].charAt(0);
			}
			solve(arr);
			System.out.println();
		}
	}

	public static void solve(Character[] buff) {
		int[] arr = new int[26];
		ArrayList<Character> list = new ArrayList<>();

		for (int i = 0; i < buff.length; i++) {
			int loc = buff[i] - 97;
			arr[loc]++;
			if (list.contains(buff[i])) {
				list.remove(buff[i]);
			} else {
				if (arr[loc] == 1) {
					list.add(buff[i]);
				}
			}

			if (list.size() > 0) {
				System.out.print(list.get(0) + " ");
			} else {
				System.out.print("-1 ");
			}
		}
	}
}