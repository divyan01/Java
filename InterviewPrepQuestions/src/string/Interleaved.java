package string;

public class Interleaved {
	public static void main(String[] args) {
		String a = "XY";
		String b = "X";
		String c = "XXY";
		Boolean[][] bl = new Boolean[a.length()+1][b.length()+1];
		System.out.println(interleave(a, b, c, 0, 0, 0, bl));
	}

	public static boolean interleave(String a, String b, String c, int one, int two, int three, Boolean[][] dp) {
		if (a.length() + b.length() != c.length()) {
			return false;
		}

		if (one == a.length() && two == b.length() && three == c.length()) {
			return true;
		}

		if (dp[one][two] != null) {
			return dp[one][two];
		}

		boolean bl = false;
		if (one < a.length()) {
			if (a.charAt(one) == c.charAt(three)) {
				bl = interleave(a, b, c, one + 1, two, three + 1, dp);
			}
		}

		if (bl == true) {
			return dp[one][two] = true;
		}
		if (two < b.length()) {
			if (b.charAt(two) == c.charAt(three)) {
				bl = interleave(a, b, c, one, two + 1, three + 1, dp);
			}
		}

		return dp[one][two] = bl;
	}
}
