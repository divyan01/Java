package string;

public class GFG {

	public static void main(String[] args) {
		rotationBytwo("amazon", "azonam");
	}

	public static void rotationBytwo(String a, String b) {
		String c = b.substring(2, b.length()) + b.substring(0, 2);
		String d = b.substring(b.length() - 2, b.length()) + b.substring(0, b.length() - 2);
		if (c.equals(a) || d.equals(a)) {
			System.out.println("1");
		} else {
			System.out.println("0");
		}
	}

	public static void evenlengthPalindrome(String str) {
		int maxLen = 1;
		int maxLenIdx = -1;
		double lo = -1;
		double hi = -1;
		for (double i = 0.5; i < str.length(); i++) {
			double centre = i;
			lo = centre - 1;
			hi = centre + 1;
			while (lo > -1 && hi < str.length()) {
				if (str.charAt((int) Math.ceil(lo)) == str.charAt((int) Math.floor(hi))) {
					if (hi - lo > maxLen) {
						maxLen = (int) (hi - lo);
						maxLenIdx = (int) Math.ceil(lo);
					}
					lo--;
					hi++;
				} else {
					break;
				}
			}
		}
		System.out.println(maxLen > 1 ? str.substring(maxLenIdx, maxLenIdx + maxLen) : "null");
	}

	public static void longestPalindrome(String str) {
		int maxLen = 1;
		int maxLenIdx = 0;
		double lo = -1;
		double hi = -1;
		for (double i = 0.5; i < str.length(); i++) {
			double centre = i;
			lo = centre - 1;
			hi = centre + 1;
			while (lo > -1 && hi < str.length()) {
				if (str.charAt((int) Math.ceil(lo)) == str.charAt((int) Math.floor(hi))) {
					if (hi - lo + 1 > maxLen) {
						maxLen = (int) (hi - lo);
						maxLenIdx = (int) Math.ceil(lo);
					}
					lo--;
					hi++;
				} else {
					break;
				}
			}
		}

		for (int i = 0; i < str.length(); i++) {
			int centre = i;
			lo = centre - 1;
			hi = centre + 1;
			while (lo >= 0 && hi < str.length()) {
				if (str.charAt((int) lo) == str.charAt((int) hi)) {
					if (hi - lo > maxLen) {
						maxLen = (int) (hi - lo) + 1;
						maxLenIdx = (int) lo;
					}
					lo--;
					hi++;
				} else {
					break;
				}
			}
		}
		System.out.println(str.substring(maxLenIdx, maxLenIdx + maxLen));
	}

	public static void oddlengthPalindrome(String str) {
		int maxLen = 1;
		int maxLenIdx = -1;
		int lo = -1;
		int hi = -1;
		for (int i = 0; i < str.length(); i++) {
			int centre = i;
			lo = centre - 1;
			hi = centre + 1;
			while (lo >= 0 && hi < str.length()) {
				if (str.charAt(lo) == str.charAt(hi)) {
					if (hi - lo + 1 > maxLen) {
						maxLen = hi - lo + 1;
						maxLenIdx = lo;
					}
					lo--;
					hi++;
				} else {
					break;
				}
			}
		}
		System.out.println(maxLen > 1 ? str.substring(maxLenIdx, maxLenIdx + maxLen) : "null");
	}

	public static String reverse(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.') {
				return reverse(str.substring(i + 1, str.length())) + "." + str.substring(0, i);
			}
		}

		return str;
	}

}
