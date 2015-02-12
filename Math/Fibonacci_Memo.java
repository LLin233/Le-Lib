import java.math.BigInteger;
import java.util.HashMap;

public class Fibonacci_Memo {

	
	// static int[] memo;

	public static void main(String[] args) {
		int n = 1070;
		long startTime = System.currentTimeMillis();
		HashMap<Integer, BigInteger> memoized = new HashMap<Integer, BigInteger>();
		BigInteger result = fibonacci(n, memoized);
		System.out.println(" Result of " + n + ": " + result + "    \n"
				+ " Total Time " + (System.currentTimeMillis() - startTime));

		// // ---------------------------------------------- 
		
		// memo = new int[n + 1];
		// memo[0] = 0;
		// memo[1] = 1;
		// int result2 = fibonacci2(n);
		// System.out.println(" Result of " + n + ": " + result2 + "    \n"
		// 		+ " Total Time " + (System.currentTimeMillis() - startTime));

	}

	private static BigInteger fibonacci(Integer n,
			HashMap<Integer, BigInteger> memoized) {
		if (n < 0) {
			throw new IllegalArgumentException(
					"We assume the positive Fibonacci sequence only");
		}
		if (memoized.containsKey(n)) {
			return memoized.get(n);
		}
		if (n == 0) {
			memoized.put(n, BigInteger.ZERO);
			return memoized.get(n);
		}
		if (n == 1) {
			memoized.put(n, BigInteger.ONE);
			return memoized.get(n);
		}
		BigInteger fibonacci = fibonacci(n - 1, memoized).add(
				fibonacci(n - 2, memoized));
		memoized.put(n, fibonacci);
		return fibonacci;
	}

	// private static int fibonacci2(int n) {
	// 	if (n == 0) {
	// 		return memo[0];
	// 	} else if (n == 1) {
	// 		return memo[1];
	// 	} else {
	// 		memo[n] = fibonacci2(n - 2) + fibonacci2(n - 1);
	// 		return memo[n];
	// 	}
	// }
}
