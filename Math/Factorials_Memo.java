import java.math.BigInteger;
import java.util.HashMap;


public class Factorials_Memo {
	public static void main(String[] args) {
		int n = 10;
		long startTime = System.currentTimeMillis();
		HashMap<Integer, BigInteger> memoized = new HashMap<Integer, BigInteger>();
		BigInteger result = Factorial(n, memoized);
		System.out.println(" Result of " + n + ": " + result + "    \n"
				+ " Total Time " + (System.currentTimeMillis() - startTime));


	}
	
	private static BigInteger Factorial(Integer n, HashMap<Integer, BigInteger> memoized) {
		
		if (n < 0) {
			throw new IllegalArgumentException(
					"We assume the positive number only");
		}
		if (memoized.containsKey(n)) {
			return memoized.get(n);
		}
		
		if (n == 0) {
			memoized.put(n, BigInteger.ONE);
			return memoized.get(n);
		}
		BigInteger factorial = Factorial(n - 1, memoized).multiply(new BigInteger(n.toString()));
		memoized.put(n, factorial);
		return factorial;
		
	}
}
