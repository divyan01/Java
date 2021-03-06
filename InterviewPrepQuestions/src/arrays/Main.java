package arrays;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//		Scanner scn = new Scanner(System.in);
////		int t = scn.nextInt();
////		while (t-- > 0) {
//		int n = scn.nextInt();
////			int k = scn.nextInt();
//		int[] arr = new int[n];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = scn.nextInt();
//		}
//			System.out.println(kConcatenation(arr, k));
//		}
//		equilibriumIndex(new int[] { -7, 1, 5, 2, -4, 3, 0 });
//		subArraySum(new int[] { 1, 4, 0, 0, 3, 10, 5 }, 7);
//		chocolateDistribution(new int[] { 12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43, 50 }, 7);
//		trappingRainWater(new int[] { 8, 8, 2, 4, 5, 5, 1 });
//		StockSell(new int[] { 23, 13, 25, 29, 33, 19, 34, 45, 65, 67 });
//		kPairsmallestSum(new int[] { 1, 3, 11 }, new int[] { 2, 4, 8 }, 4);

//		int x = findPivot(new int[] { 30, 40, 50 });
////		int x = findPivot(new int[] { 10, 20, 30 }, 0, 2);
//		System.out.println(x);
//		sortedRotatedPair(new int[] { 11, 15, 26, 38, 9, 10 }, 45);\
//		int[] arr = { 1, 14, 5, 20, 4, 2, 54, 20, 87, 98, 3, 1, 32 };
//		threeWaySort(20, 20, arr);
//		for (int val : arr) {
//			System.out.println(val);
//		}
//		Scanner scn = new Scanner(System.in);
//		int n = scn.nextInt();
//		int diff = scn.nextInt();
//
//		int[] arr = new int[n];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = scn.nextInt();
//		}

//		solve(diff, arr);
		int x=maxProfit(new int[] {2, 30, 15, 10, 8, 25, 80},7);
		System.out.println(x);
	}
	
	
	
	
	
	static int maxProfit(int price[], int n) 
    { 
        // Create profit array and initialize it as 0 
        int profit[] = new int[n]; 
        for (int i=0; i<n; i++) 
            profit[i] = 0; 
       
        /* Get the maximum profit with only one transaction 
           allowed. After this loop, profit[i] contains maximum 
           profit from price[i..n-1] using at most one trans. */
        int max_price = price[n-1]; 
        for (int i=n-2;i>=0;i--) 
        { 
            // max_price has maximum of price[i..n-1] 
            if (price[i] > max_price) 
                max_price = price[i]; 
       
            // we can get profit[i] by taking maximum of: 
            // a) previous maximum, i.e., profit[i+1] 
            // b) profit by buying at price[i] and selling at 
            //    max_price 
            profit[i] = Math.max(profit[i+1], max_price-price[i]); 
        } 
       
        /* Get the maximum profit with two transactions allowed 
           After this loop, profit[n-1] contains the result */
        int min_price = price[0]; 
        for (int i=1; i<n; i++) 
        { 
            // min_price is minimum price in price[0..i] 
            if (price[i] < min_price) 
                min_price = price[i]; 
       
            // Maximum profit is maximum of: 
            // a) previous maximum, i.e., profit[i-1] 
            // b) (Buy, Sell) at (min_price, price[i]) and add 
            //    profit of other trans. stored in profit[i] 
            profit[i] = Math.max(profit[i-1], profit[i] + 
                                        (price[i]-min_price) ); 
        } 
        int result = profit[n-1]; 
        return result; 
    } 

	public static void countBst(int n) {
		int[] strg = new int[n + 1];
		strg[0] = strg[1] = 1;

		for (int i = 2; i <=n; i++) {
			for (int j = 1; j <= i; j++) {
				strg[i] += strg[j - 1] * strg[i - j];
			}
		}

		System.out.println(strg[strg.length - 1]);
	}

	private static void solve(int diff, int[] arr) {
		Arrays.sort(arr);

		int start = 0;
		int end = 1;
		boolean found = false;

		while (start < arr.length && end < arr.length) {
			// calculate current diff here
			int cdiff = arr[end] - arr[start];
			if (cdiff > diff) {
				// update start here
				start++;
			} else if (cdiff < diff) {
				// update end here
				end++;
			} else {

				// print the pair
				// if(start!=end)
				System.out.println(arr[start] + " " + arr[end]);
				// handle the test case that start != end
				// only increase end, maybe next number is also same
				if (start < arr.length - 1 && arr[start] == arr[start + 1]) {
					start++;
				} else {
					start++;
					end++;
				}
				found = true;
			}
		}

		if (!found) {
			System.out.println(-1);
		}
	}

	public static void abc(int[] arr, int sum) {
		int curSum = arr[0];
		int start = 0;
		for (int j = 1; j <= arr.length; j++) {

			while (curSum > sum && start < j) {
				curSum -= arr[start];
				start++;
			}

			if (curSum == sum) {
				System.out.println(start + " " + (j - 1));
			}

			if (j < arr.length) {
				curSum += arr[j];
			}
		}
	}

	private static void threeWaySort(int lowVal, int highVal, int[] arr) {
		int i = -1;
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] < lowVal) {
				swap(i + 1, j, arr);
				i++;
			}
		}

		i = arr.length;
		for (int j = arr.length - 1; j >= 0; j--) {
			if (arr[j] > highVal) {
				swap(i - 1, j, arr);
				i--;
			}
		}
	}

	private static void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static void sortedRotatedPair(int[] arr, int sum) {
		int pivot = findPivot(arr);
		int left = (0 + pivot) % arr.length;
		int right = (arr.length - 1 + pivot) % arr.length;
		while (left != right) {
			int currSum = arr[left] + arr[right];
			if (currSum == sum) {
				System.out.println(arr[left] + " " + arr[right]);
				break;
			} else if (currSum < sum) {
				left++;
				left = left % arr.length;
			} else {
				right--;
				if (right < 0) {
					right += arr.length;
				}
			}
		}
	}

	static int findPivot(int arr[], int low, int high) {
		// base cases
		if (high < low)
			return -1;
		if (high == low)
			return low;

		/* low + (high - low)/2; */
		int mid = (low + high) / 2;
		if (mid < high && arr[mid] > arr[mid + 1])
			return mid;
		if (mid > low && arr[mid] < arr[mid - 1])
			return (mid - 1);
		if (arr[low] >= arr[mid])
			return findPivot(arr, low, mid - 1);
		return findPivot(arr, mid + 1, high);
	}

	public static int findPivot(int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;
		int mid = -1;
		while (lo <= hi) {
			if (lo == hi) {
				return lo;
			}
			mid = (lo + hi) / 2;
			if (arr[mid] > arr[mid + 1]) {
				return mid + 1;
			} else if (arr[mid] < arr[mid - 1]) {
				return mid;
			} else if (arr[mid] <= arr[hi]) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return mid;
	}

	private static class pair implements Comparable<pair> {
		int i;
		int j;
		int data;

		public pair(int i, int j, int data) {
			this.i = i;
			this.j = j;
			this.data = data;
		}

		@Override
		public int compareTo(pair o) {
			return this.data - o.data;
		}
	}

	public static void kPairsmallestSum(int[] arr1, int[] arr2, int k) {
		PriorityQueue<pair> pq = new PriorityQueue<>();
		pq.add(new pair(0, 0, arr1[0] + arr2[0]));
		while (k-- > 0) {
			pair rem = pq.remove();
			System.out.println("[" + arr1[rem.i] + " " + arr2[rem.j] + "]");
			if (rem.i < arr1.length - 1) {
				pq.add(new pair(rem.i + 1, rem.j, arr1[rem.i + 1] + arr2[rem.j]));
			}
			if (rem.j < arr2.length - 1) {
				pq.add(new pair(rem.i, rem.j + 1, arr1[rem.i] + arr2[rem.j + 1]));
			}
		}
	}

	public static void trappingRainWater(int[] arr) {
		int left = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				left = i;
				break;
			}
		}
		int cap = 0;
		for (int i = left + 1; i < arr.length; i++) {
			if (arr[i] != 0) {
				int count = i;
				int bw = 0;
				while (count < arr.length - 1) {
					count++;
					if (arr[count] != 0) {
						if (arr[count] > arr[i] && arr[count] > arr[left]) {
							bw += arr[i];
							i = count;
						} else if (arr[count] == arr[i]) {
							continue;
						} else {
							break;
						}
					}

				}
				int oldcap = cap;
				cap += (i - left - 1) * Math.min(arr[i], arr[left]);
				cap -= bw;
				if (cap != oldcap || i == left + 1) {
					left = i;
				}
			}
		}
		System.out.println(cap);
	}

	public static void chocolateDistribution(int[] arr, int m) {
		Arrays.sort(arr);
		int i = 0;
		int j = m - 1;
		int minDiff = Integer.MAX_VALUE;
		while (j < arr.length) {
			minDiff = Math.min(minDiff, arr[j] - arr[i]);

			i++;
			j++;
		}
		System.out.println(minDiff);
	}

	public static void zigzag(int[] arr) {
		boolean flag = false;
		for (int i = 0; i < arr.length - 1; i++) {
			if (flag == false) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			} else {
				if (arr[i] < arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
			flag = !flag;
		}
		for (int val : arr) {
			System.out.println(val);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void subArraySum(int[] arr, int k) {
		int lIdx = 0;
		int lIdxSum = 0;
		int rIdxSum = 0;
		for (int i = 0; i < arr.length; i++) {
			rIdxSum += arr[i];
			if (rIdxSum - lIdxSum == k) {
				System.out.println(lIdx + "bazinga" + i);
			} else {
				while (lIdx < i && (rIdxSum - lIdxSum > k)) {

					lIdxSum += arr[lIdx];
					lIdx++;
					if (rIdxSum - lIdxSum == k) {
						System.out.println(lIdx + "bazinga" + i);
					}
				}

			}
		}

	}

	public static void equilibriumIndex(int[] arr) {
		int arrSum = 0;
		for (int val : arr) {
			arrSum += val;
		}

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arrSum - sum - arr[i] == sum) {
				System.out.println(i);
			}
			sum += arr[i];
		}
	}

	public static long kConcatenation(long[] arr, int k) {
		if (k == 1) {
			return maxSub(arr);
		}
		long sum = 0;
		long[] arr2 = new long[2 * arr.length];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = arr[i % arr.length];
			if (i < arr.length) {
				sum += arr[i];
			}
		}

		if (sum >= 0) {
			return (long) maxSub(arr2) + (k - 2) * sum;
		} else {
			return (long) maxSub(arr2);
		}
	}

	private static long maxSub(long[] arr) {
		long max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] + arr[i] > arr[i]) {
				arr[i] += arr[i - 1];
			}
			max = Math.max(max, arr[i]);
		}
		return max;
	}

	private static void StockSell(int[] arr) {
		int left = 0;
		int right = 0;
		while (right < arr.length) {
			if (right != arr.length - 1 && arr[right] < arr[right + 1]) {
				right++;
			} else {
				if (right != left) {
					System.out.print("(" + left + " " + right + ") ");
				}
				left = right + 1;
				right = right + 1;
			}
		}
	}

	private static void kPairSmallest(int[] arr1, int[] arr2, int k) {
		int i = 0;
		int j = 0;
		while (k-- > 0) {
			System.out.println("(" + arr1[i] + " " + arr2[j] + ")");
			if (i + 1 == arr1.length || j + 1 == arr2.length) {
				break;
			}
			if (arr1[i + 1] + arr2[j] < arr1[i] + arr2[j + 1]) {
				i = i + 1;
			} else if (arr1[i + 1] + arr2[j] == arr1[i] + arr2[j + 1]) {
				System.out.println("(" + arr1[i] + " " + arr2[j + 1] + ")");
				i += 1;
				k--;
			} else {
				j = j + 1;
			}
		}
	}

}
